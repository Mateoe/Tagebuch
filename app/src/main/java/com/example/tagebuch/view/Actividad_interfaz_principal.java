package com.example.tagebuch.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tagebuch.R;
import com.example.tagebuch.view.fragmentos.Reportar_pensamiento;

public class Actividad_interfaz_principal extends AppCompatActivity {

    private Button botonReportarPensamiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfaz_principal);

        botonReportarPensamiento = findViewById(R.id.boton_reportar_pensamiento_interfaz_principal);

        botonReportarPensamiento.setVisibility(View.VISIBLE);
        botonReportarPensamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonReportarPensamiento.setVisibility(View.GONE);
                reportarPensamiento();
            }
        });
    }

    public void reportarPensamiento(){
        getSupportFragmentManager().beginTransaction().replace(R.id.linear_layout_interfaz_principal,
                Reportar_pensamiento.newInstance()).commit();
    }


}