package es.ucm.as_tutor.presentacion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import es.ucm.as_tutor.R;

/**
 * Created by msalitu on 01/04/2016.
 */
public class UsuarioEventosActivity extends AppCompatActivity {
        private  ListView listViewEventos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_eventos);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.logo);
        listViewEventos = (ListView) findViewById(R.id.listViewEventos);

        String[] items = { "Ir al Barcas a las 12:00 - 14 feb 2016",
                "Ir al Barcas a las 12:00 - 14 febrero de 2016",
                "Ir al Sitio A a las 1:00 - 09 Marzo de 2016",
                "Ir al Museo a las 15:00 - 11 abril de 2016",
                "Ir al Sitio B a las 22:30 - 22 mayo de 2016",
                "Ir a donde Pepe a las 08:30 - 12 diciembre de 2016"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);

        listViewEventos.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
