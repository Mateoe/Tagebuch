package com.example.tagebuch.view.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tagebuch.R;
import com.example.tagebuch.controller.ControladorInterfazPrincipal;
import com.example.tagebuch.view.Actividad_interfaz_principal;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;


public class Reportar_pensamiento extends Fragment {

    private TextInputLayout tituloPensamiento;
    private TextInputLayout descripcionPensamiento;
    private Button reportar, cancelar;
    private View rootView;
    private Spinner categoriaPensamiento;
    private ControladorInterfazPrincipal controladorInterfazPrincipal;

    public Reportar_pensamiento() {
        // Required empty public constructor
    }

    public static Reportar_pensamiento newInstance() {
        Reportar_pensamiento fragment = new Reportar_pensamiento();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Se crean los apuntadores a los elementos de la vista
        rootView = inflater.inflate(R.layout.fragment_reportar_pensamiento, container, false);
        categoriaPensamiento = rootView.findViewById(R.id.selector_categoria_reportar_pensamiento);
        tituloPensamiento = rootView.findViewById(R.id.reportar_titulo_pensamiento);
        descripcionPensamiento = rootView.findViewById(R.id.text_input_layout_reportar_pensamieto);
        cancelar = rootView.findViewById(R.id.boton_cancelar_reporte_pensamiento_fragmento);
        reportar = rootView.findViewById(R.id.boton_reportar_pensamiento_fragmento);

        controladorInterfazPrincipal = new ControladorInterfazPrincipal();

        //Se obtienen los nombres de las categorias
        List<String> categorias = controladorInterfazPrincipal.obtenerCategorias(this);

        //Se asignan los nombres de las categorias a la lista desplegable
        ArrayAdapter<String> elementosSpinner = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, categorias);
        elementosSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriaPensamiento.setAdapter(elementosSpinner);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarReportePensamiento();
            }
        });

        reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean pensamientoVacio = TextUtils.isEmpty(tituloPensamiento.getEditText().getText());
                Boolean descripcionVacia = TextUtils.isEmpty(descripcionPensamiento.getEditText().getText());
                Boolean tituloMuyLargo = tituloPensamiento.getEditText().getText().length()>100;

                if(pensamientoVacio || descripcionVacia || tituloMuyLargo){
                    if (pensamientoVacio){
                        tituloPensamiento.getEditText().setError("Campo requerido");
                    }
                    if (descripcionVacia){
                        descripcionPensamiento.getEditText().setError("Campo requerido");
                    }
                    if (tituloMuyLargo){
                        tituloPensamiento.getEditText().setError("La longitud máxima del titulo es de 100 caracteres");
                    }
                }else {
                    reportarPensamiento();
                }
            }
        });


        return rootView;
    }

    public void cerrarReportePensamiento(){
        requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        requireActivity().getSupportFragmentManager().popBackStack();
        controladorInterfazPrincipal.activarBotonReporte((Actividad_interfaz_principal) this.getActivity());
    }

    public void reportarPensamiento(){
        String titulo = tituloPensamiento.getEditText().getText().toString();
        String descripcion = descripcionPensamiento.getEditText().getText().toString();
        String categoria = categoriaPensamiento.getSelectedItem().toString();
        controladorInterfazPrincipal.reportarPensamientoControlador(this, titulo, descripcion, categoria);
    }
}