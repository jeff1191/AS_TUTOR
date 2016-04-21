package es.ucm.as_tutor.presentacion.vista.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
import es.ucm.as_tutor.presentacion.controlador.Controlador;
import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.vista.ayuda.FragmentDetalleAyuda;
import es.ucm.as_tutor.presentacion.vista.ayuda.FragmentListadoAyuda;
import es.ucm.as_tutor.presentacion.vista.evento.AdaptadorEventoUsuarios;
import es.ucm.as_tutor.presentacion.vista.evento.FragmentListadoEvento;
import es.ucm.as_tutor.presentacion.vista.tutor.FragmentDetalleTutor;
import es.ucm.as_tutor.presentacion.vista.tutor.FragmentListadoTutor;


public class MainActivity extends AppCompatActivity {

    private String[] titulos;
    private DrawerLayout NavDrawerLayout;
    private ListView NavList;
    private ArrayList<Item_Navegacion> NavItms;
    private TypedArray NavIcons;
    private ActionBarDrawerToggle mDrawerToggle;
    private AdaptadorNavegacion NavAdapter;
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

        Manager.getInstance().setActivity(this);

        mDBHelper = getHelper();
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
        //Mi perfil
        NavItms.add(new Item_Navegacion(titulos[2], NavIcons.getResourceId(2, -1)));
        //Ayuda
        NavItms.add(new Item_Navegacion(titulos[3], NavIcons.getResourceId(3, -1)));
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

        //Inicialemente se carga el listado de usuarios y el fragment grande en blanco
        // Luego carga los usuarios de la base de datos
        BlankFragment fragmentBlank = new BlankFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentBlank).commit();

        Log.e("test", "Llega hasta crear usuarios");
        Controlador.getInstancia().ejecutaComando(ListaComandos.CREAR_USUARIOS, null); // Este hay q eliminarlo mas adelante
        Log.e("test", "Llega hasta crear retos");
        Controlador.getInstancia().ejecutaComando(ListaComandos.CREAR_RETOS, null); // Este hay q eliminarlo mas adelante
        Log.e("test", "Llega hasta listado usuarios");
        Controlador.getInstancia().ejecutaComando(ListaComandos.LISTADO_USUARIOS, null);
        Log.e("test", "Acaba onCreate");

        //Esto tiene que ir en el comando de arriba + dispacher ;)
       // FragmentListadoUsuario frgListado = new FragmentListadoUsuario();
       // frgListado.setArguments(arguments);
        //getSupportFragmentManager().beginTransaction().replace(R.id.FrgListado, frgListado).commit();
    }

    private void MostrarFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 1: // Usuarios
                //Fragmento en blanco
                BlankFragment fragmentBlank = new BlankFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentBlank).commit();
                //Listado
                Controlador.getInstancia().ejecutaComando(ListaComandos.LISTADO_USUARIOS, null);

                NavList.setItemChecked(position, true);
                NavList.setSelection(position);
                //Cambiamos el titulo en donde decia "
                setTitle(titulos[position - 1]);
                //Cerramos el menu deslizable
                NavDrawerLayout.closeDrawer(NavList);
                break;
            case 2: // Eventos
               /* menuActionBar.clear(); //poner otro menu
                getMenuInflater().inflate(R.menu.menu_main_eventos, menuActionBar);*/

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
            case 3: // Mi perfil
               // menuActionBar.clear();

                FragmentDetalleTutor fragmentDetalleTutor = new FragmentDetalleTutor();
                //fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentDetalleTutor).commit();

                FragmentListadoTutor fragmentListadoTutor = new FragmentListadoTutor();
                //fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgListado, fragmentListadoTutor).commit();

                NavList.setItemChecked(position, true);
                NavList.setSelection(position);
                //Cambiamos el titulo en donde decia "
                setTitle(titulos[position - 1]);
                //Cerramos el menu deslizable
                NavDrawerLayout.closeDrawer(NavList);
                break;
            case 4: // Ayuda
              //  menuActionBar.clear();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.e("testJL", "se mete aqui main");
        menuActionBar = menu;
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        /*
        * If your Activity's onOptionsItemSelected method returs true,
        * the call is consumed in activity and Fragment's onOptionsItemSelected is not called
        *
        * thank you stack overflow (L)(L)
        */
        if(mDrawerToggle.onOptionsItemSelected(item)) return true;
        else return false;

    }
    public void nuevoEvento(View view){
        menuActionBar.clear();
        getMenuInflater().inflate(R.menu.menu_usuario, menuActionBar);


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

        AdaptadorEventoUsuarios adapterListadoUsuarios = new AdaptadorEventoUsuarios(this);
        ArrayList<String> nombresUsuarios = new ArrayList<String>();
        ArrayList<String> usuariosAsistencia = new ArrayList<String>();
        ArrayList<Boolean> usuariosActivos  = new ArrayList<Boolean>();


        nombresUsuarios.add("Juan Perez");
        usuariosActivos.add(false);
        usuariosAsistencia.add("NO");

        nombresUsuarios.add("Maria Salgado");
        usuariosActivos.add(false);
        usuariosAsistencia.add("NO");

        nombresUsuarios.add("Julian Iturrino");
        usuariosActivos.add(false);
        usuariosAsistencia.add("NO");

        nombresUsuarios.add("Juan Luis Armas");
        usuariosActivos.add(false);
        usuariosAsistencia.add("NO");

        nombresUsuarios.add("David Guess");
        usuariosActivos.add(false);
        usuariosAsistencia.add("NO");

        nombresUsuarios.add("Alfredo Almache");
        usuariosActivos.add(false);
        usuariosAsistencia.add("NO");

        nombresUsuarios.add("Jeff Gordon");
        usuariosActivos.add(false);
        usuariosAsistencia.add("NO");

        nombresUsuarios.add("Andres Hamilton");
        usuariosActivos.add(false);
        usuariosAsistencia.add("NO");



        adapterListadoUsuarios.setDatos(nombresUsuarios);
        adapterListadoUsuarios.setDatosCheck(usuariosActivos);
       // adapterListadoUsuarios.setDatosAsistencia(usuariosAsistencia);

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

            }
        });

    }

}