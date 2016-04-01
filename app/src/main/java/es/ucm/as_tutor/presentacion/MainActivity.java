package es.ucm.as_tutor.presentacion;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.integracion.DBHelper;

public class MainActivity extends AppCompatActivity
        implements FragmentListadoUsuario.ListadoListener {
    private String[] titulos;
    private DrawerLayout NavDrawerLayout;
    private ListView NavList;
    private ArrayList<Item_Navegacion> NavItms;
    private TypedArray NavIcons;
    private ActionBarDrawerToggle mDrawerToggle;
    private AdaptadorNavegacion NavAdapter;
    private ImageButton tareas;
    private ImageButton usuarios;
    private String panelActivo="usuarios";
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
        //Acerca de
        NavItms.add(new Item_Navegacion(titulos[2], NavIcons.getResourceId(2, -1)));
        NavAdapter= new AdaptadorNavegacion(this,NavItms);
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
        Bundle arguments = new Bundle();
        arguments.putBoolean("activoListadoUsuario", true);
        FragmentListadoUsuario frgListado =new FragmentListadoUsuario();
        frgListado.setArguments(arguments);
        getSupportFragmentManager().beginTransaction().replace(R.id.FrgListado, frgListado).commit();

        //frgListado.setListadoListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuActionBar = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onUsuarioSeleccionado(Usuario c) {
        boolean hayDetalle =
                (getSupportFragmentManager().findFragmentById(R.id.FrgDetalle) != null);

        if(hayDetalle) {
            /*((FragmentDetalleUsuario)getSupportFragmentManager() //Primera manera de pasar datos al fragment
                    .findFragmentById(R.id.FrgDetalle)).mostrarDetalle(c);*/
            //2da manera de pasar datos al fragment
            Bundle arguments = new Bundle();
            arguments.putString("campoApasar", "123");
            FragmentDetalleUsuario detalle = new FragmentDetalleUsuario();
            detalle.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, detalle).commit();
        }
        else {
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra("Detalle Texto", c.getTexto());
            startActivity(i);
        }
    }

    private void MostrarFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 1: // Usuarios
                menuActionBar.clear();
                getMenuInflater().inflate(R.menu.menu_main, menuActionBar);
                FragmentDetalleUsuario fragmentDetalleUsuarios= new FragmentDetalleUsuario();
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentDetalleUsuarios).commit();


                Bundle arguments = new Bundle();
                arguments.putBoolean("activoListadoUsuario", true);
                FragmentListadoUsuario fragmentListaUsuario =new FragmentListadoUsuario();
                fragmentListaUsuario.setArguments(arguments);
                //fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgListado, fragmentListaUsuario).commit();
                NavList.setItemChecked(position, true);
                NavList.setSelection(position);
                //Cambiamos el titulo en donde decia "
                setTitle(titulos[position-1]);
                //Cerramos el menu deslizable
                NavDrawerLayout.closeDrawer(NavList);
                break;
            case 2: // Eventos
                menuActionBar.clear();
                getMenuInflater().inflate(R.menu.menu_main_tareas, menuActionBar);

                FragmentDetalleEvento fragmentDetalleTarea = new FragmentDetalleEvento();
                //fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentDetalleTarea).commit();


                FragmentListadoEvento fragmentListaTarea = new FragmentListadoEvento();
                //fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgListado, fragmentListaTarea).commit();
                NavList.setItemChecked(position, true);
                NavList.setSelection(position);
                //Cambiamos el titulo en donde decia "
                setTitle(titulos[position-1]);
                //Cerramos el menu deslizable
                NavDrawerLayout.closeDrawer(NavList);
                break;


            default:
                //si no esta la opcion mostrara un toast y nos mandara a Home
                Toast.makeText(getApplicationContext(), "Opcion " + titulos[position - 1] + "no disponible!", Toast.LENGTH_SHORT).show();

                position=1;
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            //fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            // Actualizamos el contenido segun la opcion elegida
            NavList.setItemChecked(position, true);
            NavList.setSelection(position);
            //Cambiamos el titulo en donde decia "
            setTitle(titulos[position-1]);
            //Cerramos el menu deslizable
            NavDrawerLayout.closeDrawer(NavList);
        } else {
            //Si el fragment es nulo mostramos un mensaje de error.
            Log.e("Error  ", "MostrarFragment"+position);
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
            Log.e("mDrawerToggle pushed", "x");
            return true;
        }
        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }


}