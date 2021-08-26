package com.example.tagebuch.view.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tagebuch.R;
import com.example.tagebuch.controller.ControladorInterfazPrincipal;
import com.example.tagebuch.view.Actividad_interfaz_principal;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class Reportar_pensamiento extends Fragment {

    private EditText tituloPensamiento;
    private TextInputLayout descripcionPensamiento;
    private Button reportar, cancelar;
    private View rootView;
    private ControladorInterfazPrincipal controladorInterfazPrincipal;

    public Reportar_pensamiento() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
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

        rootView = inflater.inflate(R.layout.fragment_reportar_pensamiento, container, false);
        tituloPensamiento = rootView.findViewById(R.id.reportar_titulo_pensamiento);
        descripcionPensamiento = rootView.findViewById(R.id.text_input_layout_reportar_pensamieto);
        cancelar = rootView.findViewById(R.id.boton_cancelar_reporte_pensamiento_fragmento);
        reportar = rootView.findViewById(R.id.boton_reportar_pensamiento_fragmento);

        controladorInterfazPrincipal = new ControladorInterfazPrincipal();

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelarReportePensamiento();
            }
        });

        reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportarPensamiento();
            }
        });


        return rootView;
    }

    private void cancelarReportePensamiento(){
        requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        requireActivity().getSupportFragmentManager().popBackStack();
        getActivity().recreate();
    }

    private void reportarPensamiento(){
        String titulo = tituloPensamiento.getText().toString();
        String descripcion = descripcionPensamiento.getEditText().getText().toString();
        controladorInterfazPrincipal.reportar((Actividad_interfaz_principal) this.getActivity(), titulo, descripcion);
    }
}