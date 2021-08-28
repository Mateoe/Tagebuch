package com.example.tagebuch.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.tagebuch.R;
import com.example.tagebuch.controller.ControladorInterfazPrincipal;
import com.example.tagebuch.model.pojo.Pensamiento;
import com.example.tagebuch.view.fragmentos.reportar_pensamiento;
import com.example.tagebuch.view.fragmentos.detalle_pensamiento;
import com.example.tagebuch.view.fragmentos.editar_pensamiento;
import com.example.tagebuch.view.fragmentos.item_pensamiento;

import java.util.List;

public class Actividad_interfaz_principal extends AppCompatActivity {

    private Button botonReportarPensamiento;
    private ControladorInterfazPrincipal controladorInterfazPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfaz_principal);

        //Se crea el apuntador a los elementos de la interfaz
        botonReportarPensamiento = findViewById(R.id.boton_reportar_pensamiento_interfaz_principal);

        //Se crea una instancia del controlador
        controladorInterfazPrincipal = new ControladorInterfazPrincipal();
        crearCategorias();
        listarPensamiento();
        //Se establece el estado del bonton como visible
        botonReportarPensamiento.setVisibility(View.VISIBLE);

        //Se crea la accion del boton reportar pensamiento
        botonReportarPensamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Al hacer click se establece el estado del botón como invisibe y se ejecuta el
                // metodo para reportar pensamiento
                botonReportarPensamiento.setVisibility(View.GONE);
                reportarPensamiento();
            }
        });
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.black)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_principal, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.boton_deshacer:
                mensaje("Deshacer","Deshacer");
                return true;
            case R.id.boton_rehacer:
                mensaje("Rehacer","Rehacer");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void crearCategorias(){
        controladorInterfazPrincipal.insertarCategorias(this);
    }

    //Se crea un metodo para establecer el boton "reportar pensamiento" como visible
    public void activarBoton(){
        botonReportarPensamiento.setVisibility(View.VISIBLE);
    }

    //Se crea el metodo para activar la interfaz de reporte de pensamiento
    public void reportarPensamiento(){
        //Se invoca el fragmento encargado de crear el pensamiento
        getSupportFragmentManager().beginTransaction().replace(R.id.linear_layout_interfaz_principal,
                reportar_pensamiento.newInstance()).commit();
    }

    public void listarPensamiento(){
        List<Pensamiento> pensamientos = controladorInterfazPrincipal.obtenerPensamientos(this);

        for (Pensamiento pensamiento: pensamientos){
            getSupportFragmentManager().beginTransaction().add(R.id.linear_layout_interfaz_principal,
                    item_pensamiento.newInstance(
                            pensamiento.getTitulo(),
                            pensamiento.getFecha(),
                            pensamiento.getDescripcion(),
                            pensamiento.getCategoria(),
                            controladorInterfazPrincipal.obtenerColor(
                                    this,
                                    pensamiento.getCategoria()
                            )
                    )
            ).commit();
        }
    }

    public void visualizarDetallePensamiento(String categoria, String fecha, String titulo, String descripcion,
                                             String color){
        botonReportarPensamiento.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.linear_layout_interfaz_principal,
                detalle_pensamiento.newInstance(categoria,fecha,titulo,descripcion,color)).commit();
    }

    public void visualizarEditarPensamiento(String titulo, String descripcion, String categoria, String fecha, String color){
        botonReportarPensamiento.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.linear_layout_interfaz_principal,
                editar_pensamiento.newInstance(titulo,descripcion,categoria,fecha,color)).commit();
    }

    //Se crea el metodo encargado de mostrar los mensajes al usuario
    public void mensaje(String titulo, String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensaje)
                .setTitle(titulo)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }



}