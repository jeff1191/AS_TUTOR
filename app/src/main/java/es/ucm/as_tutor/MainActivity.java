package es.ucm.as_tutor;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements FragmentListado.ListadoListener,FragmentSeleccion.SeleccionListener {
    private String[] titulos;
    private DrawerLayout NavDrawerLayout;
    private ListView NavList;
    private ArrayList<Item_Navegacion> NavItms;
    private TypedArray NavIcons;
    private ActionBarDrawerToggle mDrawerToggle;
    AdaptadorNavegacion NavAdapter;
    private ImageButton tareas;
    private ImageButton usuarios;
    private String panelActivo="usuarios";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.logo);

        NavDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavList = (ListView) findViewById(R.id.lista);
        View header = getLayoutInflater().inflate(R.layout.header, null);
        NavList.addHeaderView(header);
        NavIcons = getResources().obtainTypedArray(R.array.navigation_iconos);
        titulos = getResources().getStringArray(R.array.nav_options);
        NavItms = new ArrayList<Item_Navegacion>();

        //Home
        NavItms.add(new Item_Navegacion(titulos[0], NavIcons.getResourceId(0, -1)));
        //Perfil
        NavItms.add(new Item_Navegacion(titulos[1], NavIcons.getResourceId(1, -1)));
        //Configuracion
        NavItms.add(new Item_Navegacion(titulos[2], NavIcons.getResourceId(2, -1)));
        //Acerca de
        NavItms.add(new Item_Navegacion(titulos[3], NavIcons.getResourceId(3, -1)));


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
        MostrarFragment(1);


        final FragmentListado frgListado =
                (FragmentListado)getSupportFragmentManager()
                        .findFragmentById(R.id.FrgListado);

        usuarios =(ImageButton)findViewById(R.id.imageButtonUsuarios);
        tareas =(ImageButton)findViewById(R.id.imageButtonTareas);

        usuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* FragmentListado frgListado =
                        (FragmentListado)getSupportFragmentManager()
                                .findFragmentById(R.id.FrgListado);*/

                /*if(panelActivo.equals("usuarios")){
                    usuarios.setImageResource(R.drawable.ic_action_group);
                    usuarios.setBackgroundColor(Color.parseColor("#eae8e8"));

                    tareas.setImageResource(R.drawable.ic_action_go_to_today);
                    tareas.setBackgroundColor(Color.parseColor("#dad6d6"));

                }else{
                    usuarios.setImageResource(R.drawable.ic_action_go_to_today);
                    usuarios.setBackgroundColor(Color.parseColor("#eae8e8"));

                    tareas.setImageResource(R.drawable.ic_action_group);
                    tareas.setBackgroundColor(Color.parseColor("#dad6d6"));
                }
*/
                if(panelActivo.equals("tareas")) { // si el panel activo es tareas, hacemos el swap
                    usuarios.setBackgroundColor(Color.parseColor("#eae8e8")); //boton Activo
                    tareas.setBackgroundColor(Color.parseColor("#dad6d6")); //boton Inactivo*/
                    float posXusuarios = usuarios.getX();
                    float posYusuarios = usuarios.getY();
                    float posXtareas = tareas.getX();
                    float posYtareas = tareas.getY();

                    tareas.setX(posXusuarios);
                    tareas.setY(posYusuarios);

                    usuarios.setX(posXtareas);
                    usuarios.setY(posYtareas);
                }


                panelActivo="usuarios";

            }
        });

        tareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*FragmentListado frgListado =
                        (FragmentListado) getSupportFragmentManager()
                                .findFragmentById(R.id.FrgListado);*/


               // Activar Fragments o rellenar fragments con la nueva info*/
            if(panelActivo.equals("usuarios")) {
                tareas.setBackgroundColor(Color.parseColor("#eae8e8")); //boton Activo
                usuarios.setBackgroundColor(Color.parseColor("#dad6d6")); //boton Inactivo
                float posXusuarios = usuarios.getX();
                float posYusuarios = usuarios.getY();
                float posXtareas = tareas.getX();
                float posYtareas = tareas.getY();

                tareas.setX(posXusuarios);
                tareas.setY(posYusuarios);

                usuarios.setX(posXtareas);
                usuarios.setY(posYtareas);
            }
            ///////////////////cambio de fragmento de tareas ///////////////////////////////////


               // FragmentSeleccion fragment = new FragmentSeleccion();
                //fragment.setArguments(arguments);
                //getSupportFragmentManager().beginTransaction().replace(R.id.LstListado, fragment).commit();
                /*
                ((FragmentSeleccion)getSupportFragmentManager()
                        .findFragmentById(R.id.FrgSeleccion)).pruebaFragment("HHH");*/

               /* FragmentSeleccion fragment2 = (FragmentSeleccion) getSupportFragmentManager().findFragmentById(R.id.FrgSeleccion);
                if(fragment != null)
                    getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
                */
                /////////////////////////////////////////////////////////////////////////////////

                panelActivo="tareas";
            }
        });

        frgListado.setListadoListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
            ((FragmentDetalle)getSupportFragmentManager()
                    .findFragmentById(R.id.FrgDetalle)).mostrarDetalle(c);
        }
        else {
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra("Detalle Texto", c.getTexto());
            startActivity(i);
        }
    }


    @Override
    public void onSeleccionListener(String opt) {

    }


    /*Pasando la posicion de la opcion en el menu nos mostrara el Fragment correspondiente*/
    private void MostrarFragment(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 1:
             //   fragment = new HomeFragment();
                break;
            case 2:
               // fragment = new ProfileFragment();
                break;


            default:
                //si no esta la opcion mostrara un toast y nos mandara a Home
                Toast.makeText(getApplicationContext(), "Opcion " + titulos[position - 1] + "no disponible!", Toast.LENGTH_SHORT).show();
          //      fragment = new HomeFragment();
                position=1;
                break;
        }
        //Validamos si el fragment no es nulo
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