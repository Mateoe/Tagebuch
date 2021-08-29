package com.example.tagebuch.controller.memento;

import com.example.tagebuch.model.pojo.Pensamiento;

public class Memento {

    private Pensamiento pensamiento;
    private String operacion;

    public Memento(Pensamiento pensamiento, String operacion){
        this.pensamiento = pensamiento;
        this.operacion = operacion;
    }

    public Pensamiento getPensamiento() {
        return pensamiento;
    }

    public String getOperacion() {
        return operacion;
    }
}
