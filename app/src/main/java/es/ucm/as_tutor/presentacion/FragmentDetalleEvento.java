package es.ucm.as_tutor.presentacion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import es.ucm.as_tutor.R;



public class FragmentDetalleEvento extends Fragment {

    private ListView listaEventoUsuarios;
    private CustomCalendarView calendarView;
    private AdaptadorEventoUsuarios adapterListadoUsuarios;
    private Button aceptar;
    private String nombreEvento;
    private String horaEvento;
    private String horaAlarma;
    private  View rootView;

    public FragmentDetalleEvento(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if(bundle != null) {
            ArrayList<String> listaUsuarios = bundle.getStringArrayList("listaUsuarios");
            ArrayList<String> listaUsuariosAsistencia = bundle.getStringArrayList("listaUsuariosAsistencia");
            ArrayList<Integer> listaUsuariosActivos = bundle.getIntegerArrayList("listaUsuariosActivos");
            nombreEvento = bundle.getString("nombreEvento");
            horaEvento = bundle.getString("horaEvento");
            horaAlarma = bundle.getString("horaAlarma");
            adapterListadoUsuarios = new AdaptadorEventoUsuarios(getActivity());
            adapterListadoUsuarios.setDatos(listaUsuarios);
            adapterListadoUsuarios.setDatosAsistencia(listaUsuariosAsistencia);

            ArrayList<Boolean> usuariosActivos = new ArrayList<Boolean>();
            for(int i = 0; i < listaUsuariosActivos.size();i++){
                if(listaUsuariosActivos.get(i) == 1)
                    usuariosActivos.add(true);
                else
                    usuariosActivos.add(false);
            }

            adapterListadoUsuarios.setDatosCheck(usuariosActivos);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_detalle_evento, container, false);
       listaEventoUsuarios = (ListView) rootView.findViewById(R.id.listViewUsuariosEvento);
        View header = inflater.inflate(R.layout.row_evento_listado_header, listaEventoUsuarios, false);
        aceptar= (Button) rootView.findViewById(R.id.botonEvento);
        TextView textViewNombreEvento = (TextView) rootView.findViewById(R.id.DetalleNombreEvento);
        EditText editTextNombreEvento = (EditText) rootView.findViewById(R.id.editTextNombreEvento);
        EditText editTextHoraAlarma = (EditText) rootView.findViewById(R.id.editTextHoraAlarma);
        EditText editTextHoraEvento = (EditText) rootView.findViewById(R.id.editTextHoraEvento);

        textViewNombreEvento.setText(nombreEvento);
        editTextNombreEvento.setText(nombreEvento);
        editTextHoraAlarma.setText(horaAlarma);
        editTextHoraEvento.setText(horaEvento);

        listaEventoUsuarios.setAdapter(adapterListadoUsuarios);
        listaEventoUsuarios.addHeaderView(header, null, false);

        listaEventoUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapterListadoUsuarios.cambiaCheck(position - 1); // Si tienes header sino cambiaCheck(position);
               // Toast.makeText(getActivity(), "POSITION: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        // Fin Listado de usuarios del evento*/





        //CALENDARIO
        //Initialize CustomCalendarView from layout
        calendarView = (CustomCalendarView) rootView.findViewById(R.id.calendar_view);

//Initialize calendar with date
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());
        currentCalendar.set(Calendar.DAY_OF_MONTH, 15);
        currentCalendar.set(Calendar.MONTH, 7 - 1);
        currentCalendar.set(Calendar.YEAR, 2016);
//Show Monday as first date of week
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

//Show/hide overflow days of a month
        calendarView.setShowOverflowDate(false);

//call refreshCalendar to update calendar the view
        calendarView.refreshCalendar(currentCalendar);
//Handling custom calendar events
        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
               // Toast.makeText(getActivity(), df.format(date), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMonthChanged(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
               // Toast.makeText(getActivity(), df.format(date), Toast.LENGTH_SHORT).show();
            }
        });
        ////

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // To dismiss the dialog

            }
        });


        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear(); //poner otro menu
        inflater.inflate(R.menu.menu_main_eventos, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.eliminarEvento:
                DialogEliminarEvento alertDialog = DialogEliminarEvento.newInstance(nombreEvento);
                alertDialog.show(getActivity().getFragmentManager(), "Eliminar evento");
                break;
        }
        return true;
    }

}
