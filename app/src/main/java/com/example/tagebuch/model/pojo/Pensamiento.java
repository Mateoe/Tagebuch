package com.example.tagebuch.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pensamientos")
public class Pensamiento {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer _id;
    private String titulo;
    private String descripcion;

    @NonNull
    public Integer get_id() {
        return _id;
    }

    public void set_id(@NonNull Integer _id) {
        this._id = _id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
