package com.example.tagebuch.controller.memento;

import java.util.ArrayList;

public class Caretaker {

    private ArrayList<Memento> deshacer = new ArrayList<>();

    private ArrayList<Memento> rehacer = new ArrayList<>();

    public void agregarMementoDeshacer(Memento memento){
        deshacer.add(memento);
    }

    public void agregarMementoRehacer(Memento memento){
        rehacer.add(memento);
    }

    public Memento obtenerMementoDeshacer(){
        Memento ultimoCambio;
        if (deshacer.size()!=0) {
            ultimoCambio = deshacer.get(deshacer.size() - 1);
            deshacer.remove(deshacer.size() - 1);
        }else{
            ultimoCambio = null;
        }
        return  ultimoCambio;
    }

    public Memento obtenerMementoRehacer(){
        Memento ultimoCambioDeshecho;
        if (rehacer.size()!=0){
            ultimoCambioDeshecho = rehacer.get(rehacer.size() - 1);
            rehacer.remove(rehacer.size() - 1);
        }else{
            ultimoCambioDeshecho=null;
        }
        return ultimoCambioDeshecho;
    }

    public void limpiarRehacer(){
        rehacer.clear();
    }

}
