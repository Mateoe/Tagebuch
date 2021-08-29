package com.example.tagebuch.controller;


import android.text.TextUtils;

import com.example.tagebuch.controller.memento.Memento;
import com.example.tagebuch.controller.memento.Originator;
import com.example.tagebuch.model.LocalStorage;
import com.example.tagebuch.model.dao.CategoriaRoomDAO;
import com.example.tagebuch.model.dao.PensamientoRoomDAO;
import com.example.tagebuch.model.pojo.Categoria;
import com.example.tagebuch.model.pojo.Pensamiento;
import com.example.tagebuch.view.Actividad_interfaz_principal;
import com.example.tagebuch.view.fragmentos.reportar_pensamiento;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ControladorInterfazPrincipal {

    private PensamientoRoomDAO pensamientoRoomDAO;
    private CategoriaRoomDAO categoriaRoomDAO;
    private Originator originator = new Originator();

    public void insertarCategorias (Actividad_interfaz_principal actividad_interfaz_principal){

        this.categoriaRoomDAO = LocalStorage
                .getLocalStorage(actividad_interfaz_principal.getApplicationContext())
                .categoriaRoomDAO();

        List<Categoria> categoriaBd = categoriaRoomDAO.getAll();

        if(categoriaBd.size()==0){
            Categoria[] categoria = new Categoria[5];

            categoria[0] = new Categoria("Deportes","Deportes","Rojo");
            categoria[1] = new Categoria("Entretenimiento","Entretenimiento","Amarillo");
            categoria[2] = new Categoria("Educación","Educación","Verde");
            categoria[3] = new Categoria("Ocio","Ocio","Naranja");
            categoria[4] = new Categoria("Hogar","Hogar","Azul");

            this.categoriaRoomDAO.insertarMuchos(categoria);
        }

    }

    //Metodo del controlador encargado de reportar(insertar) el pensamiento
    public void reportarPensamientoControlador(reportar_pensamiento reportar_pensamiento, String titulo, String descripcion, String categoria) {

        Actividad_interfaz_principal actividad_interfaz_principal = (Actividad_interfaz_principal) reportar_pensamiento.getActivity();

        this.pensamientoRoomDAO = LocalStorage
                .getLocalStorage(actividad_interfaz_principal.getApplicationContext())
                .pensamientoRoomDAO();

        Pensamiento pensamiento = new Pensamiento();
        pensamiento.setTitulo(titulo);
        pensamiento.setDescripcion(descripcion);

        pensamiento.setCategoria(categoria);
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        pensamiento.setFecha(
                fecha.format(Calendar.getInstance().getTime()));
        this.pensamientoRoomDAO.insertar(pensamiento);

        originator.setPensamiento(pensamiento);
        originator.setOperacion("reportar");
        actividad_interfaz_principal.guardarMementoDeshacer(originator.guardar(),true);
    }


    //Se crea un metodo para activar el boton de reportar pensamiento en la interfaz principal
    public void activarBotonReporte(Actividad_interfaz_principal actividad_interfaz_principal){
        actividad_interfaz_principal.listarPensamiento();
        actividad_interfaz_principal.activarBoton();
    }

    public List<String> obtenerCategorias(reportar_pensamiento reportar_pensamiento){
        this.categoriaRoomDAO = LocalStorage
                .getLocalStorage(reportar_pensamiento.getActivity().getApplicationContext())
                .categoriaRoomDAO();

        return this.categoriaRoomDAO.obtenerNombres();
    }

    public List<Pensamiento> obtenerPensamientos(Actividad_interfaz_principal actividad_interfaz_principal){
        this.pensamientoRoomDAO = LocalStorage
                .getLocalStorage(actividad_interfaz_principal.getApplicationContext())
                .pensamientoRoomDAO();

        return this.pensamientoRoomDAO.getAll();
    }

    public String obtenerColor(Actividad_interfaz_principal actividad_interfaz_principal, String nombreCategoria){
        this.categoriaRoomDAO = LocalStorage
                .getLocalStorage(actividad_interfaz_principal.getApplicationContext())
                .categoriaRoomDAO();

        String color;

        switch (this.categoriaRoomDAO.obtenerColor(nombreCategoria)){
            case "Rojo":
                color = "#F44848";
                break;
            case "Amarillo":
                color = "#EBEE85";
                break;
            case "Verde":
                color = "#85EE88";
                break;
            case "Naranja":
                color = "#FF9B4D";
                break;
            case "Azul":
                color = "#65FFDB";
                break;
            default:
                color = "#E090F0";
                break;
        }
        return color;
    }

    public void mostrarDetallePensamiento(Actividad_interfaz_principal actividad_interfaz_principal,
                                          String categoria, String fecha, String titulo, String descripcion,
                                          String color){
        actividad_interfaz_principal.visualizarDetallePensamiento(categoria,fecha,titulo,descripcion,color);

    }

    public void eliminarPensamientoControlador(Actividad_interfaz_principal actividad_interfaz_principal,
                                    String titulo, String descripcion, String categoria, String fecha){

        this.pensamientoRoomDAO = LocalStorage
                .getLocalStorage(actividad_interfaz_principal.getApplicationContext())
                .pensamientoRoomDAO();

        Pensamiento pensamiento = new Pensamiento();
        pensamiento.setTitulo(titulo);
        pensamiento.setDescripcion(descripcion);
        pensamiento.setCategoria(categoria);
        pensamiento.setFecha(fecha);

        pensamientoRoomDAO.eliminar(pensamiento);

        originator.setPensamiento(pensamiento);
        originator.setOperacion("eliminar");
        actividad_interfaz_principal.guardarMementoDeshacer(originator.guardar(),true);
    }

    public void mostrarEditarPensamiento(Actividad_interfaz_principal actividad_interfaz_principal,
                                          String categoria, String fecha, String titulo, String descripcion,
                                          String color){
        actividad_interfaz_principal.visualizarEditarPensamiento(titulo,descripcion,categoria,fecha,color);
    }

    public void editarPensamientoControlador(Actividad_interfaz_principal actividad_interfaz_principal,
                                             String titulo, String descripcion, String categoria, String fecha){
        this.pensamientoRoomDAO = LocalStorage
                .getLocalStorage(actividad_interfaz_principal.getApplicationContext())
                .pensamientoRoomDAO();

        originator.setPensamiento(pensamientoRoomDAO.obtenerPorFecha(fecha));
        originator.setOperacion("editar");
        actividad_interfaz_principal.guardarMementoDeshacer(originator.guardar(),true);

        Pensamiento pensamiento = new Pensamiento();
        pensamiento.setTitulo(titulo);
        pensamiento.setDescripcion(descripcion);
        pensamiento.setCategoria(categoria);
        pensamiento.setFecha(fecha);

        pensamientoRoomDAO.actualizar(pensamiento);
    }

    public void mensajeReportarPensamiento(Actividad_interfaz_principal actividad_interfaz_principal) {
        actividad_interfaz_principal.mensaje("Pensamiento reportado!",
                "Tu pensamiento se agregó a la lista.");
    }

    public void mensajeEditarPensamiento(Actividad_interfaz_principal actividad_interfaz_principal) {
        actividad_interfaz_principal.mensaje("Pensamiento editado!",
                "Los cambios se verán reflejados en la lista.");
    }

    public void mensajeEliminarPensamiento(Actividad_interfaz_principal actividad_interfaz_principal) {
        actividad_interfaz_principal.mensaje("Pensamiento eliminado!",
                "Tu pensamiento se eliminó de la lista.");
    }

    public boolean verificarCampoLleno(String texto){
        return  TextUtils.isEmpty(texto);
    }

    public boolean verificarLongitud(String texto){
        return  texto.length()>100;
    }

    public void ejecutarMementoDeshacer (Actividad_interfaz_principal actividad_interfaz_principal, Memento memento){
        this.pensamientoRoomDAO = LocalStorage
                .getLocalStorage(actividad_interfaz_principal.getApplicationContext())
                .pensamientoRoomDAO();

        String operacion = memento.getOperacion();
        Pensamiento pensamiento = memento.getPensamiento();

        if (operacion == "reportar") {
            pensamientoRoomDAO.eliminar(pensamiento);
            actividad_interfaz_principal.guardarMementoRehacer(memento);
        }if (operacion == "eliminar") {
            pensamientoRoomDAO.insertar(pensamiento);
            actividad_interfaz_principal.guardarMementoRehacer(memento);
        }if (operacion == "editar"){
            originator.setOperacion(operacion);
            originator.setPensamiento(pensamientoRoomDAO.obtenerPorFecha(pensamiento.getFecha()));
            actividad_interfaz_principal.guardarMementoRehacer(originator.guardar());
            pensamientoRoomDAO.actualizar(pensamiento);
        }

        actividad_interfaz_principal.actualizarLista();
        actividad_interfaz_principal.activarBoton();

    }

    public void ejecutarMementoRehacer (Actividad_interfaz_principal actividad_interfaz_principal, Memento memento){
        this.pensamientoRoomDAO = LocalStorage
                .getLocalStorage(actividad_interfaz_principal.getApplicationContext())
                .pensamientoRoomDAO();

        String operacion = memento.getOperacion();
        Pensamiento pensamiento = memento.getPensamiento();

        if (operacion == "reportar") {
            pensamientoRoomDAO.insertar(pensamiento);
            actividad_interfaz_principal.guardarMementoDeshacer(memento,false);
        }if (operacion == "eliminar") {
            pensamientoRoomDAO.eliminar(pensamiento);
            actividad_interfaz_principal.guardarMementoDeshacer(memento,false);
        }if (operacion == "editar"){
            originator.setOperacion(operacion);
            originator.setPensamiento(pensamientoRoomDAO.obtenerPorFecha(pensamiento.getFecha()));
            actividad_interfaz_principal.guardarMementoDeshacer(originator.guardar(),false);
            pensamientoRoomDAO.actualizar(pensamiento);
        }

        actividad_interfaz_principal.actualizarLista();
        actividad_interfaz_principal.activarBoton();

    }

}
