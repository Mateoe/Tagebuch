package com.example.tagebuch.view.fragmentos;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tagebuch.R;
import com.example.tagebuch.controller.ControladorInterfazPrincipal;
import com.example.tagebuch.view.Actividad_interfaz_principal;

public class detalle_pensamiento extends Fragment {

    private TextView categoriaDetalle, fechaDetalle, tituloDetalle, descripcionDetalle, colorDetalle;
    private View rootView;
    private Button atras, eliminar;
    private ControladorInterfazPrincipal controladorInterfazPrincipal;

    private String categoriaDetallePensamiento;
    private String fechaDetallePensamiento;
    private String tituloDetallePensamiento;
    private String descripcionDetallePensamiento;
    private String colorDetallePensamiento;

    public detalle_pensamiento() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static detalle_pensamiento newInstance(String categoria, String fecha, String titulo, String descripcion, String color) {
        detalle_pensamiento fragment = new detalle_pensamiento();

        fragment.setCategoriaDetallePensamiento(categoria);
        fragment.setFechaDetallePensamiento(fecha);
        fragment.setTituloDetallePensamiento(titulo);
        fragment.setDescripcionDetallePensamiento(descripcion);
        fragment.setColorDetallePensamiento(color);

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
        rootView = inflater.inflate(R.layout.fragment_detalle_pensamiento, container, false);

        categoriaDetalle = rootView.findViewById(R.id.categoria_detalle_pensamiento);
        fechaDetalle = rootView.findViewById(R.id.fecha_detalle_pensamiento);
        tituloDetalle = rootView.findViewById(R.id.titulo_detalle_pensamiento);
        descripcionDetalle = rootView.findViewById(R.id.descripcion_detalle_pensamiento);
        atras = rootView.findViewById(R.id.boton_atras_detalle_pensamiento);
        eliminar = rootView.findViewById(R.id.boton_eliminar_pensamiento);

        categoriaDetalle.setText(categoriaDetallePensamiento);
        fechaDetalle.setText(fechaDetallePensamiento);
        tituloDetalle.setText(tituloDetallePensamiento);
        descripcionDetalle.setText(descripcionDetallePensamiento);
        rootView.getRootView().setBackgroundColor(Color.parseColor(colorDetallePensamiento));
        atras.setBackgroundColor(Color.parseColor(colorDetallePensamiento));
        controladorInterfazPrincipal = new ControladorInterfazPrincipal();

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarDetalle();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarPensamiento();
            }
        });

        return rootView;
    }

    public void eliminarPensamiento(){
        controladorInterfazPrincipal.eliminarPensamiento((Actividad_interfaz_principal) this.getActivity(),
                tituloDetallePensamiento,descripcionDetallePensamiento,categoriaDetallePensamiento,fechaDetallePensamiento);
        cerrarDetalle();
        controladorInterfazPrincipal.mensajeEliminarPensamiento((Actividad_interfaz_principal) this.getActivity());
    }

    public void cerrarDetalle(){
        requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        requireActivity().getSupportFragmentManager().popBackStack();
        controladorInterfazPrincipal.activarBotonReporte((Actividad_interfaz_principal) this.getActivity());
    }


    public String getCategoriaDetallePensamiento() {
        return categoriaDetallePensamiento;
    }

    public void setCategoriaDetallePensamiento(String categoriaDetallePensamiento) {
        this.categoriaDetallePensamiento = categoriaDetallePensamiento;
    }

    public String getFechaDetallePensamiento() {
        return fechaDetallePensamiento;
    }

    public void setFechaDetallePensamiento(String fechaDetallePensamiento) {
        this.fechaDetallePensamiento = fechaDetallePensamiento;
    }

    public String getTituloDetallePensamiento() {
        return tituloDetallePensamiento;
    }

    public void setTituloDetallePensamiento(String tituloDetallePensamiento) {
        this.tituloDetallePensamiento = tituloDetallePensamiento;
    }

    public String getDescripcionDetallePensamiento() {
        return descripcionDetallePensamiento;
    }

    public void setDescripcionDetallePensamiento(String descripcionDetallePensamiento) {
        this.descripcionDetallePensamiento = descripcionDetallePensamiento;
    }

    public String getColorDetallePensamiento() {
        return colorDetallePensamiento;
    }

    public void setColorDetallePensamiento(String colorDetallePensamiento) {
        this.colorDetallePensamiento = colorDetallePensamiento;
    }
}