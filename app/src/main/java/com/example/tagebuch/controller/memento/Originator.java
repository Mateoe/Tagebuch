package com.example.tagebuch.controller.memento;

import com.example.tagebuch.model.pojo.Pensamiento;

public class Originator {
    private Pensamiento pensamiento;
    private String operacion;


    public Pensamiento getPensamiento() {
        return pensamiento;
    }

    public void setPensamiento(Pensamiento pensamiento) {
        this.pensamiento = pensamiento;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public Memento guardar(){
        return new Memento(pensamiento, operacion);
    }

    public void restaurar(Memento memento){
        this.pensamiento = memento.getPensamiento();
        this.operacion = memento.getOperacion();
    }

}
