package com.example.tagebuch.view.fragmentos;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tagebuch.R;
import com.example.tagebuch.controller.ControladorInterfazPrincipal;
import com.example.tagebuch.view.Actividad_interfaz_principal;
import com.google.android.material.textfield.TextInputLayout;


public class editar_pensamiento extends Fragment {

    private String tituloEditarPensamiento;
    private String descripcionEditarPensamiento;
    private String categoriaEditarPensamiento;
    private String fechaEditarPensamiento;
    private String colorEditarPensamiento;

    private TextInputLayout tituloPensamiento, descripcionPensamiento;
    private TextView categoriaPensamiento, fechaPensamiento;
    private View rootView;
    private Button cancelar, editar;
    private ControladorInterfazPrincipal controladorInterfazPrincipal;

    public editar_pensamiento() {
    }

    public static editar_pensamiento newInstance(String titulo, String descripcion, String categoria, String fecha, String color) {
        editar_pensamiento fragment = new editar_pensamiento();
        fragment.setTituloEditarPensamiento(titulo);
        fragment.setDescripcionEditarPensamiento(descripcion);
        fragment.setCategoriaEditarPensamiento(categoria);
        fragment.setFechaEditarPensamiento(fecha);
        fragment.setColorEditarPensamiento(color);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_editar_pensamiento, container, false);
        tituloPensamiento = rootView.findViewById(R.id.editar_titulo_pensamiento);
        descripcionPensamiento = rootView.findViewById(R.id.editar_descripcion_pensamiento);
        categoriaPensamiento = rootView.findViewById(R.id.categoria_editar_pensamiento);
        fechaPensamiento = rootView.findViewById(R.id.fecha_editar_pensamiento);
        cancelar = rootView.findViewById(R.id.boton_cancelar_editar_pensamiento);
        editar = rootView.findViewById(R.id.boton_editar_editar_pensamiento);

        tituloPensamiento.getEditText().setText(tituloEditarPensamiento);
        descripcionPensamiento.getEditText().setText(descripcionEditarPensamiento);
        categoriaPensamiento.setText(categoriaEditarPensamiento);
        fechaPensamiento.setText(fechaEditarPensamiento);
        rootView.getRootView().setBackgroundColor(Color.parseColor(colorEditarPensamiento));

        controladorInterfazPrincipal = new ControladorInterfazPrincipal();

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarEditar();
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
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
                    editarPensamiento();
                }
            }
        });

        return rootView;
    }

    public void cerrarEditar(){
        requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        requireActivity().getSupportFragmentManager().popBackStack();
        controladorInterfazPrincipal.activarBotonReporte((Actividad_interfaz_principal) this.getActivity());
    }

    public void editarPensamiento(){
        String titulo = tituloPensamiento.getEditText().getText().toString();
        String descripcion = descripcionPensamiento.getEditText().getText().toString();
        String categoria = categoriaPensamiento.getText().toString();
        String fecha = fechaPensamiento.getText().toString();
        controladorInterfazPrincipal.editarPensamientoControlador((Actividad_interfaz_principal) this.getActivity(),
                titulo,descripcion,categoria,fecha);
        cerrarEditar();
        controladorInterfazPrincipal.mensajeEditarPensamiento((Actividad_interfaz_principal) this.getActivity());
    }


    public String getTituloEditarPensamiento() {
        return tituloEditarPensamiento;
    }

    public void setTituloEditarPensamiento(String tituloEditarPensamiento) {
        this.tituloEditarPensamiento = tituloEditarPensamiento;
    }

    public String getDescripcionEditarPensamiento() {
        return descripcionEditarPensamiento;
    }

    public void setDescripcionEditarPensamiento(String descripcionEditarPensamiento) {
        this.descripcionEditarPensamiento = descripcionEditarPensamiento;
    }

    public String getCategoriaEditarPensamiento() {
        return categoriaEditarPensamiento;
    }

    public void setCategoriaEditarPensamiento(String categoriaEditarPensamiento) {
        this.categoriaEditarPensamiento = categoriaEditarPensamiento;
    }

    public String getFechaEditarPensamiento() {
        return fechaEditarPensamiento;
    }

    public void setFechaEditarPensamiento(String fechaEditarPensamiento) {
        this.fechaEditarPensamiento = fechaEditarPensamiento;
    }

    public String getColorEditarPensamiento() {
        return colorEditarPensamiento;
    }

    public void setColorEditarPensamiento(String colorEditarPensamiento) {
        this.colorEditarPensamiento = colorEditarPensamiento;
    }
}