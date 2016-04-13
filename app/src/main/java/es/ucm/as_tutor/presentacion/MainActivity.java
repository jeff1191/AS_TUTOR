package es.ucm.as_tutor.presentacion;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.integracion.DBHelper;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE =3;
    private static final int SELECCIONAR_GALERIA = 2;
    private static final int CAMARA = 1;

    private String[] titulos;
    private DrawerLayout NavDrawerLayout;
    private ListView NavList;
    private ArrayList<Item_Navegacion> NavItms;
    private TypedArray NavIcons;
    private ActionBarDrawerToggle mDrawerToggle;
    private AdaptadorNavegacion NavAdapter;
    private ImageButton tareas;
    private ImageButton usuarios;
    private String panelActivo = "usuarios";
    private Menu menuActionBar;
    private DBHelper mDBHelper = null;

    private DBHelper getHelper() {
        if (mDBHelper == null) {
            mDBHelper = OpenHelperManager.getHelper(this, DBHelper.class);
        }
        return mDBHelper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDBHelper != null) {
            OpenHelperManager.releaseHelper();
            mDBHelper = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //mDBHelper= getHelper();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.logo);
        try {
            Dao<es.ucm.as_tutor.negocio.suceso.Tarea, Integer> a = getHelper().getTareaDao();
            a.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        NavDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavList = (ListView) findViewById(R.id.lista);
        View header = getLayoutInflater().inflate(R.layout.header, null);
        NavList.addHeaderView(header);
        NavIcons = getResources().obtainTypedArray(R.array.navigation_iconos);
        titulos = getResources().getStringArray(R.array.nav_options);
        NavItms = new ArrayList<Item_Navegacion>();

        //Usuarios
        NavItms.add(new Item_Navegacion(titulos[0], NavIcons.getResourceId(0, -1)));
        //Eventos
        NavItms.add(new Item_Navegacion(titulos[1], NavIcons.getResourceId(1, -1)));
        //Ayuda
        NavItms.add(new Item_Navegacion(titulos[2], NavIcons.getResourceId(2, -1)));
        NavAdapter = new AdaptadorNavegacion(this, NavItms);
        NavList.setAdapter(NavAdapter);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                NavDrawerLayout,
                R.string.app_name,
                R.string.hello_world
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                Log.e("Cerrando drawer", "!!");
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                Log.e("Abriendo drawe", "!!");
            }
        };

        // Establecemos que mDrawerToggle declarado anteriormente sea el DrawerListener
        NavDrawerLayout.setDrawerListener(mDrawerToggle);
        //Establecemos que el ActionBar muestre el Boton Home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);*/

        //Establecemos la accion al clickear sobre cualquier item del menu.
        //De la misma forma que hariamos en una app comun con un listview.
        NavList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                MostrarFragment(position);
            }
        });

        //Cuando la aplicacion cargue por defecto mostrar la opcion Home
        //MostrarFragment(1);
        /* cuando inicia empieza con el listado del usuario */

        ArrayList<String> nombres = new ArrayList<String>();
        ArrayList<Integer> imagenes = new ArrayList<Integer>();
        ArrayList<String> dnis = new ArrayList<String>();
        ArrayList<String> direcciones = new ArrayList<String>();
        ArrayList<String> telefonos = new ArrayList<String>();
        ArrayList<String> correos = new ArrayList<String>();
        ArrayList<String> colegios = new ArrayList<String>();
        ArrayList<String> estudios = new ArrayList<String>();
        ArrayList<String> cursos = new ArrayList<String>();
        ArrayList<String> notas = new ArrayList<String>();
        ArrayList<String> nombrePadres = new ArrayList<String>();
        ArrayList<String> nombreMadres = new ArrayList<String>();
        ArrayList<String> telfPadres = new ArrayList<String>();
        ArrayList<String> telfMadres = new ArrayList<String>();
        ArrayList<String> correoPadres = new ArrayList<String>();
        ArrayList<String> correoMadres = new ArrayList<String>();
        ArrayList<String> perfiles = new ArrayList<String>();
        ArrayList<String> sincronizaciones = new ArrayList<String>();

        nombres.add("María Salgado");
        imagenes.add(R.drawable.avatar);
        dnis.add("12345678Q");
        direcciones.add("C/ Alacala 46, 6ºA");
        telefonos.add("678 678 678");
        correos.add("correo@gmail.com");
        colegios.add("Pilar");
        estudios.add("ESO");
        cursos.add("4");
        notas.add("Alergia al huevo");
        nombrePadres.add("Manuel");
        nombreMadres.add("Carmen");
        telfPadres.add("666 666 666");
        telfMadres.add("666 666 666");
        correoPadres.add("correo@gmail.com");
        correoMadres.add("correo@gmail.com");
        perfiles.add("Perfil A");
        sincronizaciones.add("VIC001");

        nombres.add("Juanlu Armas");
        imagenes.add(R.drawable.avatar);
        dnis.add("12345678Q");
        direcciones.add("C/ Alacala 46, 6ºA");
        telefonos.add("678 678 678");
        correos.add("correo@gmail.com");
        colegios.add("Pilar");
        estudios.add("ESO");
        cursos.add("4");
        notas.add("Alergia al huevo");
        nombrePadres.add("Manuel");
        nombreMadres.add("Carmen");
        telfPadres.add("666 666 666");
        telfMadres.add("666 666 666");
        correoPadres.add("correo@gmail.com");
        correoMadres.add("correo@gmail.com");
        perfiles.add("Perfil A");
        sincronizaciones.add("VIC001");

        nombres.add("Jefferson Almache");
        imagenes.add(R.drawable.avatar);
        dnis.add("12345678Q");
        direcciones.add("C/ Alacala 46, 6ºA");
        telefonos.add("678 678 678");
        correos.add("correo@gmail.com");
        colegios.add("Pilar");
        estudios.add("ESO");
        cursos.add("4");
        notas.add("Alergia al huevo");
        nombrePadres.add("Manuel");
        nombreMadres.add("Carmen");
        telfPadres.add("666 666 666");
        telfMadres.add("666 666 666");
        correoPadres.add("correo@gmail.com");
        correoMadres.add("correo@gmail.com");
        perfiles.add("Perfil A");
        sincronizaciones.add("VIC001");

        nombres.add("Marta García");
        imagenes.add(R.drawable.avatar);
        dnis.add("12345678Q");
        direcciones.add("C/ Alacala 46, 6ºA");
        telefonos.add("678 678 678");
        correos.add("correo@gmail.com");
        colegios.add("Pilar");
        estudios.add("ESO");
        cursos.add("4");
        notas.add("Alergia al huevo");
        nombrePadres.add("Manuel");
        nombreMadres.add("Carmen");
        telfPadres.add("666 666 666");
        telfMadres.add("666 666 666");
        correoPadres.add("correo@gmail.com");
        correoMadres.add("correo@gmail.com");
        perfiles.add("Perfil A");
        sincronizaciones.add("VIC001");

        Bundle arguments = new Bundle();
        arguments.putStringArrayList("nombres", nombres);
        arguments.putIntegerArrayList("imagenes", imagenes);
        arguments.putStringArrayList("dnis", dnis);
        arguments.putStringArrayList("direcciones", direcciones);
        arguments.putStringArrayList("telefonos", telefonos);
        arguments.putStringArrayList("correos", correos);
        arguments.putStringArrayList("colegios", colegios);
        arguments.putStringArrayList("estudios", estudios);
        arguments.putStringArrayList("cursos", cursos);
        arguments.putStringArrayList("notas", notas);
        arguments.putStringArrayList("nombrePadres", nombrePadres);
        arguments.putStringArrayList("nombreMadres", nombreMadres);
        arguments.putStringArrayList("telfPadres", telfPadres);
        arguments.putStringArrayList("telfMadres", telfMadres);
        arguments.putStringArrayList("correoPadres", correoPadres);
        arguments.putStringArrayList("correoMadres", correoMadres);
        arguments.putStringArrayList("perfiles", perfiles);
        arguments.putStringArrayList("sincronizaciones", sincronizaciones);

        FragmentListadoUsuario frgListado = new FragmentListadoUsuario();
        frgListado.setArguments(arguments);
        getSupportFragmentManager().beginTransaction().replace(R.id.FrgListado, frgListado).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuActionBar = menu;
        getMenuInflater().inflate(R.menu.menu_main_usuarios, menu);
        return true;
    }


    private void MostrarFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 1: // Usuarios
                menuActionBar.clear();
                getMenuInflater().inflate(R.menu.menu_main_usuarios, menuActionBar);
                //Fragmento en blanco
                BlankFragment fragmentBlank = new BlankFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentBlank).commit();

                ArrayList<String> nombres = new ArrayList<String>();
                ArrayList<Integer> imagenes = new ArrayList<Integer>();
                ArrayList<String> dnis = new ArrayList<String>();
                ArrayList<String> direcciones = new ArrayList<String>();
                ArrayList<String> telefonos = new ArrayList<String>();
                ArrayList<String> correos = new ArrayList<String>();
                ArrayList<String> colegios = new ArrayList<String>();
                ArrayList<String> estudios = new ArrayList<String>();
                ArrayList<String> cursos = new ArrayList<String>();
                ArrayList<String> notas = new ArrayList<String>();
                ArrayList<String> nombrePadres = new ArrayList<String>();
                ArrayList<String> nombreMadres = new ArrayList<String>();
                ArrayList<String> telfPadres = new ArrayList<String>();
                ArrayList<String> telfMadres = new ArrayList<String>();
                ArrayList<String> correoPadres = new ArrayList<String>();
                ArrayList<String> correoMadres = new ArrayList<String>();
                ArrayList<String> perfiles = new ArrayList<String>();
                ArrayList<String> sincronizaciones = new ArrayList<String>();

                nombres.add("María Salgado");
                imagenes.add(R.drawable.avatar);
                dnis.add("12345678Q");
                direcciones.add("C/ Alacala 46, 6ºA");
                telefonos.add("678 678 678");
                correos.add("correo@gmail.com");
                colegios.add("Pilar");
                estudios.add("ESO");
                cursos.add("4");
                notas.add("Alergia al huevo");
                nombrePadres.add("Manuel");
                nombreMadres.add("Carmen");
                telfPadres.add("666 666 666");
                telfMadres.add("666 666 666");
                correoPadres.add("correo@gmail.com");
                correoMadres.add("correo@gmail.com");
                perfiles.add("Perfil A");
                sincronizaciones.add("VIC001");

                nombres.add("Juanlu Armas");
                imagenes.add(R.drawable.avatar);
                dnis.add("12345678Q");
                direcciones.add("C/ Alacala 46, 6ºA");
                telefonos.add("678 678 678");
                correos.add("correo@gmail.com");
                colegios.add("Pilar");
                estudios.add("ESO");
                cursos.add("4");
                notas.add("Alergia al huevo");
                nombrePadres.add("Manuel");
                nombreMadres.add("Carmen");
                telfPadres.add("666 666 666");
                telfMadres.add("666 666 666");
                correoPadres.add("correo@gmail.com");
                correoMadres.add("correo@gmail.com");
                perfiles.add("Perfil A");
                sincronizaciones.add("VIC001");

                nombres.add("Jefferson Almache");
                imagenes.add(R.drawable.avatar);
                dnis.add("12345678Q");
                direcciones.add("C/ Alacala 46, 6ºA");
                telefonos.add("678 678 678");
                correos.add("correo@gmail.com");
                colegios.add("Pilar");
                estudios.add("ESO");
                cursos.add("4");
                notas.add("Alergia al huevo");
                nombrePadres.add("Manuel");
                nombreMadres.add("Carmen");
                telfPadres.add("666 666 666");
                telfMadres.add("666 666 666");
                correoPadres.add("correo@gmail.com");
                correoMadres.add("correo@gmail.com");
                perfiles.add("Perfil A");
                sincronizaciones.add("VIC001");

                nombres.add("Marta García");
                imagenes.add(R.drawable.avatar);
                dnis.add("12345678Q");
                direcciones.add("C/ Alacala 46, 6ºA");
                telefonos.add("678 678 678");
                correos.add("correo@gmail.com");
                colegios.add("Pilar");
                estudios.add("ESO");
                cursos.add("4");
                notas.add("Alergia al huevo");
                nombrePadres.add("Manuel");
                nombreMadres.add("Carmen");
                telfPadres.add("666 666 666");
                telfMadres.add("666 666 666");
                correoPadres.add("correo@gmail.com");
                correoMadres.add("correo@gmail.com");
                perfiles.add("Perfil A");
                sincronizaciones.add("VIC001");

                Bundle arguments = new Bundle();
                arguments.putStringArrayList("nombres", nombres);
                arguments.putIntegerArrayList("imagenes", imagenes);
                arguments.putStringArrayList("dnis", dnis);
                arguments.putStringArrayList("direcciones", direcciones);
                arguments.putStringArrayList("telefonos", telefonos);
                arguments.putStringArrayList("correos", correos);
                arguments.putStringArrayList("colegios", colegios);
                arguments.putStringArrayList("estudios", estudios);
                arguments.putStringArrayList("cursos", cursos);
                arguments.putStringArrayList("notas", notas);
                arguments.putStringArrayList("nombrePadres", nombrePadres);
                arguments.putStringArrayList("nombreMadres", nombreMadres);
                arguments.putStringArrayList("telfPadres", telfPadres);
                arguments.putStringArrayList("telfMadres", telfMadres);
                arguments.putStringArrayList("correoPadres", correoPadres);
                arguments.putStringArrayList("correoMadres", correoMadres);
                arguments.putStringArrayList("perfiles", perfiles);
                arguments.putStringArrayList("sincronizaciones", sincronizaciones);

                FragmentListadoUsuario fragmentListaUsuario = new FragmentListadoUsuario();
                fragmentListaUsuario.setArguments(arguments);
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgListado, fragmentListaUsuario).commit();

                NavList.setItemChecked(position, true);
                NavList.setSelection(position);
                //Cambiamos el titulo en donde decia "
                setTitle(titulos[position - 1]);
                //Cerramos el menu deslizable
                NavDrawerLayout.closeDrawer(NavList);
                break;
            case 2: // Eventos
                menuActionBar.clear(); //poner otro menu
                getMenuInflater().inflate(R.menu.menu_main_eventos, menuActionBar);

                //Fragmento en blanco
                BlankFragment fragmentDetalleTarea = new BlankFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentDetalleTarea).commit();

                FragmentListadoEvento fragmentListadoEvento = new FragmentListadoEvento();

                ArrayList<String> nombresEventos = new ArrayList<String>();
                ArrayList<String> fechaEventos = new ArrayList<String>();
                ArrayList<String> horaEventos = new ArrayList<String>();
                ArrayList<String> horaAlarma= new ArrayList<String>();
                //ArrayList<ArrayList<String>> listaUsuarios = new ArrayList<>();


                nombresEventos.add("Ir a las barcas");
                fechaEventos.add("12 Febrero 2016");
                horaAlarma.add("12:00");
                horaEventos.add("12:30");

                nombresEventos.add("Ir al retiro");
                fechaEventos.add("21 Mayo 2016");
                horaAlarma.add("2:00");
                horaEventos.add("2:10");

                nombresEventos.add("Esquiar ");
                fechaEventos.add("04 Abril 2016");
                horaAlarma.add("11:00");
                horaEventos.add("12:30");

                nombresEventos.add("Jugar al rugby");
                fechaEventos.add("15 Noviembre 2016");
                horaAlarma.add("17:00");
                horaEventos.add("18:30");

                nombresEventos.add("Jugar al fútbol");
                fechaEventos.add("07 Marzo 2016");
                horaAlarma.add("21:00");
                horaEventos.add("21:30");


                Bundle bundle = new Bundle();
                bundle.putStringArrayList("listaEventos", nombresEventos);
                bundle.putStringArrayList("fechasEventos", fechaEventos);
                bundle.putStringArrayList("horaEventos", horaEventos);
                bundle.putStringArrayList("horaAlarmas", horaAlarma);


                fragmentListadoEvento.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgListado, fragmentListadoEvento).commit();

                NavList.setItemChecked(position, true);
                NavList.setSelection(position);
                //Cambiamos el titulo en donde decia "
                setTitle(titulos[position - 1]);
                //Cerramos el menu deslizable
                NavDrawerLayout.closeDrawer(NavList);
                break;

            case 3: // Ayuda
                menuActionBar.clear();

                FragmentDetalleAyuda fragmentDetalleAyuda = new FragmentDetalleAyuda();
                //fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentDetalleAyuda).commit();

                FragmentListadoAyuda fragmentListadoAyuda = new FragmentListadoAyuda();
                //fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgListado, fragmentListadoAyuda).commit();

                NavList.setItemChecked(position, true);
                NavList.setSelection(position);
                setTitle(titulos[position - 1]);
                NavDrawerLayout.closeDrawer(NavList);
                break;

            default:
                //si no esta la opcion mostrara un toast y nos mandara a Home
                Toast.makeText(getApplicationContext(), "Opcion " + titulos[position - 1] + "no disponible!", Toast.LENGTH_SHORT).show();

                position = 1;
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            //fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            // Actualizamos el contenido segun la opcion elegida
            NavList.setItemChecked(position, true);
            NavList.setSelection(position);
            //Cambiamos el titulo en donde decia "
            setTitle(titulos[position - 1]);
            //Cerramos el menu deslizable
            NavDrawerLayout.closeDrawer(NavList);
        } else {
            //Si el fragment es nulo mostramos un mensaje de error.
            Log.e("Error  ", "MostrarFragment" + position);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
         }else {
            switch (item.getItemId()) {
                case R.id.tareasUsuario:
                    Intent intent = new Intent(this, UsuarioTareasActivity.class);
                    startActivity(intent);
                    break;
                case R.id.retoUsuario:
                    //Aqui iria un if/else
                   FragmentDetalleReto fragmentReto = new FragmentDetalleReto();
                    getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentReto).commit();
                    /* FragmentDetalleNuevoReto fragmentNueoReto = new FragmentDetalleNuevoReto();
                    getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentNuevoReto).commit();*/
                    break;
                case R.id.eventosUsuario:
                    FragmentDetalleUsuarioEvento fragmentEventoUsuario = new FragmentDetalleUsuarioEvento();
                    getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentEventoUsuario).commit();
                    break;
                case R.id.enviarCorreo:
                    // aquí habrá que ejecutar el comando de enviar correo
                    break;
                case R.id.eliminarUsuario:
                    //aqui habrá que ejecutar el comando de eliminar usuario

               /* case R.id.editarListaUsuariosEventos:

                    AdaptadorEventoUsuarios adapter = new AdaptadorEventoUsuarios(5,this);
                    adapter.addItem(new ItemUsuarioEvento(1,"Pepe", "Tipo A" ));
                    adapter.addItem(new ItemUsuarioEvento(2,"Juan", "Tipo A" ));
                    adapter.addItem(new ItemUsuarioEvento(3,"Pedro", "Tipo B" ));
                    adapter.addItem(new ItemUsuarioEvento(4,"Pepa", "Tipo C" ));
                    adapter.addItem(new ItemUsuarioEvento(5,"Alfredo", "Tipo D" ));
                    //String names[] ={"Alberto","Berta","Carlos","Daniel","Julian","Alfredo"};
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    LayoutInflater inflater = getLayoutInflater();
                    View convertView = (View) inflater.inflate(R.layout.evento_listado_usuarios, null);
                    alertDialog.setView(convertView);
                    alertDialog.setTitle("Usuarios");
                    ListView lv = (ListView) convertView.findViewById(R.id.listView1);
                    //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
                    lv.setAdapter(adapter);
                    alertDialog.show();

                break;*/

                default:
                    return super.onOptionsItemSelected(item);
            }
        }
        return true;
    }
    public void nuevoEvento(View view){
        TextView nuevoEv = (TextView) findViewById(R.id.DetalleNombreEvento);
        nuevoEv.setText("Nuevo evento");

        EditText nuevoNombre = (EditText) findViewById(R.id.editTextNombreEvento);
        EditText nuevaHoraEvento = (EditText) findViewById(R.id.editTextHoraEvento);
        EditText nuevaHoraAlarma = (EditText) findViewById(R.id.editTextHoraAlarma);
        ListView listaEventoUsuarios = (ListView) findViewById(R.id.listViewUsuariosEvento);
        Button boton_nuevo= (Button) findViewById(R.id.botonEvento);

        nuevoNombre.setText("");
        nuevaHoraEvento.setText("");
        nuevaHoraAlarma.setText("");

        AdaptadorEventoUsuarios adapterListadoUsuarios = new AdaptadorEventoUsuarios(7,this);
        ArrayList<String> nombresUsuarios = new ArrayList<String>();
        ArrayList<Boolean> usuariosActivos  = new ArrayList<Boolean>();


        nombresUsuarios.add("Juan Perez");
        usuariosActivos.add(false);
        nombresUsuarios.add("Maria Salgado");
        usuariosActivos.add(false);
        nombresUsuarios.add("Julian Iturrino");
        usuariosActivos.add(false);
        nombresUsuarios.add("Juan Luis Armas");
        usuariosActivos.add(false);
        nombresUsuarios.add("David Guess");
        usuariosActivos.add(false);
        nombresUsuarios.add("Alfredo Almache");
        usuariosActivos.add(false);
        nombresUsuarios.add("Jeff Gordon");
        usuariosActivos.add(false);
        nombresUsuarios.add("Andres Hamilton");
        usuariosActivos.add(false);



        adapterListadoUsuarios.setDatos(nombresUsuarios);
        adapterListadoUsuarios.setDatosCheck(usuariosActivos);

        listaEventoUsuarios.setAdapter(adapterListadoUsuarios);
        boton_nuevo.setText("Crear");
   //Calendar
        //Initialize CustomCalendarView from layout
        final CustomCalendarView calendarView = (CustomCalendarView) findViewById(R.id.calendar_view);
        final Calendar calendarioVista =calendarView.getCurrentCalendar();
        calendarioVista.set(Calendar.DAY_OF_MONTH, 15);
        calendarioVista.set(Calendar.MONTH, 2 - 1);
        calendarioVista.set(Calendar.YEAR, 2016);

//Initialize calendar with date
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());


//Show Monday as first date of week
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

//Show/hide overflow days of a month
        calendarView.setShowOverflowDate(false);

//call refreshCalendar to update calendar the view
        calendarView.refreshCalendar(calendarioVista);

//Handling custom calendar events
        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Toast.makeText(MainActivity.this, "NUEVO: " + df.format(date), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMonthChanged(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
                Toast.makeText(MainActivity.this,"NUEVO: " + df.format(date), Toast.LENGTH_SHORT).show();
            }
        });

        boton_nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.mostrarProgreso(MainActivity.this, "Cargando", "Creando el evento...", 2);
            }
        });

    }

    public void enviarUsuarios(View view){
        Log.e("USR", "asda");
    }

    public void nuevoUsuario(View view){
        FragmentDetalleNuevoUsuario fragmentNuevoUsuario = new FragmentDetalleNuevoUsuario();
        getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentNuevoUsuario).commit();
    }

    public void infoPadre(View view){
        AlertDialog a = createInfoProgenitoresDialogo("padre");
        a.show();
    }

    public void infoMadre(View view){
        AlertDialog a = createInfoProgenitoresDialogo("madre");
        a.show();
    }

    public AlertDialog createInfoProgenitoresDialogo(String who) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_info_padres, null);

        EditText name = (EditText) v.findViewById(R.id.name);
        EditText phone = (EditText) v.findViewById(R.id.phone);
        EditText mail = (EditText) v.findViewById(R.id.mail);
        builder.setView(v);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acciones
            }
        });
        builder.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        if (who.equals("padre"))
            builder.setTitle("Información del padre");
        else
            builder.setTitle("Información de la madre");
        return builder.create();
    }

    public void cambiarImagenPerfil(View v) {
        final CharSequence[] items = { "Hacer foto", "Elegir de la galeria", "Imagen por defecto" };
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Hacer foto")) {
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMARA);
                } else if (items[item].equals("Elegir de la galeria")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECCIONAR_GALERIA);
                } else if (items[item].equals("Imagen por defecto")) {
                    ImageView iv = (ImageView) findViewById(R.id.avatar);
                    iv.setImageResource(R.drawable.avatar);
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
}