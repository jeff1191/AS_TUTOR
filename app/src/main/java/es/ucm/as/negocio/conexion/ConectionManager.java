package es.ucm.as.negocio.conexion;

import android.app.ProgressDialog;
import android.os.Handler;
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
import es.ucm.as.negocio.suceso.TransferUsuarioEvento;
import es.ucm.as.negocio.usuario.SAUsuario;
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

                     // Log.e("ENVIANDO RETO********", message.getReto().getTexto());
                        Log.e("RESPUESTA A USUARIO"," ENVIANDO BDD DE MENSAJE "+ message.getUsuario().getNombre());
                        dataOutputStream.writeObject(message);
                        socket.close();
                        serverSocket.close();

                        if(!messageFromClient.getVerificar().equals("registro")) {
                            SAUsuario saUsuario = FactoriaSA.getInstancia().nuevoSAUsuario();
                            SASuceso saSuceso = FactoriaSA.getInstancia().nuevoSASuceso();

                            saUsuario.actualizarPuntuacion(messageFromClient.getUsuario());

                            if (messageFromClient.getReto() == null) {
                                //falta comando eliminar reto
                            } else {
                                //Reto
                                TransferReto actualizar = saSuceso.consultarReto(message.getUsuario().getId());
                                actualizar.setContador(messageFromClient.getReto().getContador());

                                actualizar.setIdUsuario(message.getUsuario().getId());
                                saSuceso.crearReto(actualizar);

                                //Eventos
                                ArrayList<TransferUsuarioEvento> eventosUsuarioBDD = saUsuario.consultarEventosUsuario(message.getUsuario().getId());

                                List<TransferEvento> eventosSincro = messageFromClient.getEventos();
                                ArrayList<TransferUsuarioEvento> eventosUsuarioFinal = new ArrayList<>();
                                String nombreEventoBDD;
                                String nombreEventoSincro;
                                for(int i=0; i < eventosUsuarioBDD.size(); i++){

                                    nombreEventoBDD= eventosUsuarioBDD.get(i).getEvento().getNombre();
                                    for(int j=0; j < eventosSincro.size(); j++){
                                        nombreEventoSincro= eventosUsuarioBDD.get(j).getEvento().getNombre();
                                        if(nombreEventoBDD.equals(nombreEventoSincro)) {
                                            TransferUsuarioEvento evento_f;

                                        }
                                    }
                                }






                            }
                            Controlador.getInstancia().ejecutaComando(ListaComandos.CONSULTAR_USUARIO, messageFromClient.getUsuario().getId());
                        }
                        progress.dismiss();
//                        Toast.makeText(Manager.getInstance().getActivity(), "Datos actualizados", Toast.LENGTH_SHORT).show();
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
