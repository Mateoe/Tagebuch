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

public class item_pensamiento extends Fragment {
    private TextView contenedorTituloPemsamiento;
    private TextView contenedorFechaPensamiento;
    private TextView contenedorDescripcion;
    private TextView contenedorCategoria;
    private String tituloPensamiento;
    private String fechaPensamiento;
    private String descripicionPensamiento;
    private String categoriaPensamiento;
    private String colorPensamiento;
    private Button marcoPensamiento;
    private View rootView;
    private ControladorInterfazPrincipal controladorInterfazPrincipal;

    public item_pensamiento() {
        // Required empty public constructor
    }

    public static item_pensamiento newInstance(String titulo, String fecha, String descripcion, String categoria, String color) {
        item_pensamiento fragment = new item_pensamiento();

        fragment.setTituloPensamiento(titulo);
        fragment.setFechaPensamiento(fecha);
        fragment.setDescripicionPensamiento(descripcion);
        fragment.setCategoriaPensamiento(categoria);
        fragment.setColorPensamiento(color);

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
        rootView = inflater.inflate(R.layout.fragment_item_pensamiento, container, false);
        contenedorTituloPemsamiento = rootView.findViewById(R.id.titulo_item_pensamiento);
        contenedorFechaPensamiento = rootView.findViewById(R.id.fecha_item_pensamiento);
        contenedorDescripcion = rootView.findViewById(R.id.descripcion_item_pensamiento);
        contenedorCategoria = rootView.findViewById(R.id.categoria_item_pensamiento);
        marcoPensamiento = rootView.findViewById(R.id.boton_marco_item_pensamiento);

        contenedorTituloPemsamiento.setText(tituloPensamiento);
        contenedorFechaPensamiento.setText(fechaPensamiento);
        contenedorDescripcion.setText(descripicionPensamiento);
        contenedorCategoria.setText(categoriaPensamiento);

        marcoPensamiento.setBackgroundColor(Color.parseColor(colorPensamiento));


        controladorInterfazPrincipal = new ControladorInterfazPrincipal();
        marcoPensamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDetalleControlador();
            }
        });

        return rootView;
    }

    public void mostrarDetalleControlador(){
        controladorInterfazPrincipal.mostrarDetallePensamiento((Actividad_interfaz_principal) this.getActivity(), categoriaPensamiento,
                fechaPensamiento,tituloPensamiento,descripicionPensamiento,colorPensamiento);
    }

    public String getTituloPensamiento() {
        return tituloPensamiento;
    }

    public void setTituloPensamiento(String tituloPensamiento) {
        this.tituloPensamiento = tituloPensamiento;
    }

    public String getFechaPensamiento() {
        return fechaPensamiento;
    }

    public void setFechaPensamiento(String fechaPensamiento) {
        this.fechaPensamiento = fechaPensamiento;
    }

    public String getDescripicionPensamiento() {
        return descripicionPensamiento;
    }

    public void setDescripicionPensamiento(String descripicionPensamiento) {
        this.descripicionPensamiento = descripicionPensamiento;
    }

    public String getCategoriaPensamiento() {
        return categoriaPensamiento;
    }

    public void setCategoriaPensamiento(String categoriaPensamiento) {
        this.categoriaPensamiento = categoriaPensamiento;
    }

    public String getColorPensamiento() {
        return colorPensamiento;
    }

    public void setColorPensamiento(String colorPensamiento) {
        this.colorPensamiento = colorPensamiento;
    }
}