package com.example.tagebuch.controller;


import android.content.Context;

import com.example.tagebuch.model.LocalStorage;
import com.example.tagebuch.model.dao.PensamientoRoomDAO;
import com.example.tagebuch.model.pojo.Pensamiento;
import com.example.tagebuch.view.Actividad_interfaz_principal;
import com.example.tagebuch.view.fragmentos.Reportar_pensamiento;

public class ControladorInterfazPrincipal {

    private PensamientoRoomDAO pensamientoRoomDAO;

    public void reportar(Actividad_interfaz_principal actividad_interfaz_principal, String titulo, String descripcion) {
        this.pensamientoRoomDAO = LocalStorage
                .getLocalStorage(actividad_interfaz_principal.getApplicationContext())
                .pensamientoRoomDAO();

        Pensamiento pensamiento = new Pensamiento();
        pensamiento.setTitulo(titulo);
        pensamiento.setDescripcion(descripcion);
        this.pensamientoRoomDAO.insertar(pensamiento);
    }

}
