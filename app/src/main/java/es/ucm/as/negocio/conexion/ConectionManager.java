package es.ucm.as.negocio.conexion;

import android.app.ProgressDialog;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import es.ucm.as.negocio.conexion.msg.Mensaje;
import es.ucm.as.negocio.factoria.FactoriaSA;
import es.ucm.as.negocio.suceso.SASuceso;
import es.ucm.as.negocio.suceso.TransferEvento;
import es.ucm.as.negocio.suceso.TransferReto;
import es.ucm.as.negocio.suceso.TransferTarea;
import es.ucm.as.negocio.suceso.TransferUsuarioEvento;
import es.ucm.as.negocio.usuario.SAUsuario;
import es.ucm.as.negocio.usuario.TransferUsuario;
import es.ucm.as.presentacion.controlador.Controlador;
import es.ucm.as.presentacion.controlador.ListaComandos;
import es.ucm.as.presentacion.vista.main.Manager;

/**
 * Created by msalitu on 05/05/2016.
 */
public class ConectionManager {

    private ServerSocket serverSocket;
    private Mensaje message;
    private String codigo;
    private Thread socketServerThread;
    private Mensaje messageFromClient;
    private ProgressDialog progress;

    public ConectionManager(Mensaje message){
        this.message = message;
        this.codigo = message.getUsuario().getCodigoSincronizacion();
        Log.e("CODIGO_CONECTION_M", codigo);
    }

    public Mensaje getMensajeUsuario(){
        return messageFromClient;
    }

    public void lanzarHebra(){
        if(socketServerThread != null && socketServerThread.isAlive())
            socketServerThread.interrupt();

        progress = new ProgressDialog(Manager.getInstance().getActivity());
        progress.setTitle("Sincronizando...");
        progress.setMessage("Cargando base de datos de " + message.getUsuario().getNombre());
        progress.setCancelable(true);
        progress.show();

        socketServerThread= new Thread(new SocketServerThread());
        socketServerThread.start();
       /* while(socketServerThread.isAlive()){
            // Log.e("Comprueba", "Esperando");
        }*/


//       socketServerThread.run();
    }

    public boolean sigueActivo(){
        return socketServerThread.isAlive();
    }


    private class SocketServerThread extends Thread {

        static final int SocketServerPORT = 8080;
        int count = 0;

        @Override
        public void run() {


            Socket socket = null;
            ObjectInputStream dataInputStream = null;
            ObjectOutputStream dataOutputStream = null;

            try {
                serverSocket = new ServerSocket(SocketServerPORT);
                while (true) {
                    socket = serverSocket.accept();
                    dataInputStream = new ObjectInputStream(
                            socket.getInputStream());
                    dataOutputStream = new ObjectOutputStream(
                            socket.getOutputStream());


                    //If no message sent from client, this code will block the program
                    messageFromClient = (Mensaje) dataInputStream.readObject();

                    String[] s = messageFromClient.getVerificar().split(":");
                    boolean primerMensaje = s[0].equals("Cod");


                    if (primerMensaje && s[1].equals(codigo)) {
                        Log.e("PERMITIDO","ENVIADO PERMITIDO");
                        Mensaje msgReply2 = new Mensaje("Permitido");
                        dataOutputStream.writeObject(msgReply2);
                        socket.close();
                        //Se rechaza la conexion
                    }else if(primerMensaje && !s[1].equals(codigo)) {
                        Log.e("RECHAZANDO ","USUARIO: " + messageFromClient.getUsuario().getCodigoSincronizacion());
                        socket.close();
                        // Se procesa el mensaje
                    }else if(!primerMensaje){
                        Log.e("SEGUNDO_MSG"," RECIBIENDO MENSAJE");

                        ArrayList<TransferUsuarioEvento> eventosUsuarioFinal = new ArrayList<>();
                        List<TransferEvento> actualizar = new ArrayList<>();
                     // Log.e("ENVIANDO RETO********", message.getReto().getTexto());
                     //   Log.e("ANTES DEL WRITE OBJECT", " ENVIANDO BDD DE MENSAJE " + message.getTareas().get(0).getTextoPregunta());


                        if(!messageFromClient.getVerificar().equals("registro")) {
                            SAUsuario saUsuario = FactoriaSA.getInstancia().nuevoSAUsuario();
                            SASuceso saSuceso = FactoriaSA.getInstancia().nuevoSASuceso();

                            TransferUsuario transferUsuario = messageFromClient.getUsuario();
                            transferUsuario.setId(message.getUsuario().getId());
                            saUsuario.actualizarPuntuacion(transferUsuario);

                            //Reto
                            TransferReto actualizarReto = saSuceso.consultarReto(message.getUsuario().getId());
                            if (messageFromClient.getReto() != null) {
                                actualizarReto.setContador(messageFromClient.getReto().getContador());
                                actualizarReto.setUsuario(message.getUsuario());
                            }
                            saSuceso.crearReto(actualizarReto);
                            message.setReto(actualizarReto);


                            //Eventos
                            ArrayList<TransferUsuarioEvento> eventosUsuarioBDD = saUsuario.consultarEventosUsuario(message.getUsuario().getId());
                            List<TransferEvento> eventosSincro = messageFromClient.getEventos();

                           if(eventosSincro.size() > 0) {

                               String nombreEventoBDD;
                               String nombreEventoSincro;
                               TransferUsuarioEvento usuario_e = new TransferUsuarioEvento(null, message.getUsuario());
                               eventosUsuarioFinal.add(usuario_e);
                               for (int i = 1; i < eventosUsuarioBDD.size(); i++) {

                                   nombreEventoBDD = eventosUsuarioBDD.get(i).getEvento().getNombre();
                                   for (int j = 0; j < eventosSincro.size(); j++) {
                                       nombreEventoSincro = eventosSincro.get(j).getNombre();
                                       if (nombreEventoBDD.equals(nombreEventoSincro)/* && eventosSincro.get(j).getAsistencia().equalsIgnoreCase("SI")*/) {
                                           //Log.e("EVENTOS: ", "sincro: "+ eventosSincro.get(j).getNombre()+ " asiste: "+ eventosSincro.get(j).getAsistencia());
                                           TransferUsuarioEvento evento_usuario = new TransferUsuarioEvento(eventosUsuarioBDD.get(i).getEvento(), message.getUsuario());
                                           evento_usuario.setAsistencia(eventosSincro.get(j).getAsistencia());
                                           eventosUsuarioFinal.add(evento_usuario);
                                       }
                                   }
                               }

                               for(int i = 1; i < eventosUsuarioBDD.size(); i++){
                                   boolean existeEvento=false;
                                   TransferUsuarioEvento evento_usuario;
                                        for(int j=1; j < eventosUsuarioFinal.size();j++ ){
                                            if(eventosUsuarioBDD.get(i).getEvento().getNombre().equals(eventosUsuarioFinal.get(j).getEvento().getNombre())) {
                                                existeEvento = true;
                                            }
                                        }
                                   if(existeEvento == false){
                                       //Aqui mete los nuevos
                                       evento_usuario = new TransferUsuarioEvento(eventosUsuarioBDD.get(i).getEvento(), message.getUsuario());
                                       evento_usuario.setAsistencia("NO");
                                       eventosUsuarioFinal.add(evento_usuario);
                                   }
                               }

                               for(int i =1; i < eventosUsuarioFinal.size(); i++){
                                   eventosUsuarioFinal.get(i).getEvento().setAsistencia(eventosUsuarioFinal.get(i).getAsistencia());
                                   //Log.e("EVENTO2ALUSER", eventosUsuarioFinal.get(i).getEvento().getNombre()+" "+eventosUsuarioFinal.get(i).getEvento().getAsistencia());
                                   actualizar.add(eventosUsuarioFinal.get(i).getEvento());
                               }


                               saUsuario.guardarEventosUsuario(eventosUsuarioFinal);

                               message.setEventos(actualizar);

                           }

                            // Tareas
                            List<TransferTarea> tareasUsuario = messageFromClient.getTareas();
                            for(int i= 0; i < tareasUsuario.size(); i++)
                                tareasUsuario.get(i).setUsuario(message.getUsuario());
                            saSuceso.guardarTareas(tareasUsuario);
                            ArrayList<TransferTarea> tareasSincro = saSuceso.consultarTareasHabilitadas(message.getUsuario().getId());
                            message.setTareas(tareasSincro);

                            Controlador.getInstancia().ejecutaComando(ListaComandos.CONSULTAR_USUARIO, messageFromClient.getUsuario().getId());
                        }

                        dataOutputStream.writeObject(message);

                        socket.close();
                        serverSocket.close();
                        progress.dismiss();
                    //  Toast.makeText(Manager.getInstance().getActivity(), "Datos actualizados", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }catch (StreamCorruptedException e){
                Log.e("StreamCorruptedExceptio", "mensaje nulo");

            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
//            handler.sendEmptyMessage(0);


        }
    }

    public void desconectar(){
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getIpAddress() {
        String ip = "";
        try {
            Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (enumNetworkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = enumNetworkInterfaces
                        .nextElement();
                Enumeration<InetAddress> enumInetAddress = networkInterface
                        .getInetAddresses();
                while (enumInetAddress.hasMoreElements()) {
                    InetAddress inetAddress = enumInetAddress.nextElement();

                    if (inetAddress.isSiteLocalAddress()) {
                        ip += "SiteLocalAddress: "
                                + inetAddress.getHostAddress() + "\n";
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            ip += "Something Wrong! " + e.toString() + "\n";
        }
        return ip;
    }


}
