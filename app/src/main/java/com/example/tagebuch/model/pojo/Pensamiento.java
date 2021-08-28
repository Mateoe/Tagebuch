package com.example.tagebuch.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

@Entity(tableName = "pensamientos",
        foreignKeys =
                {@ForeignKey(entity = Categoria.class,
                parentColumns = "nombre",
                childColumns = "categoria",
                onDelete = ForeignKey.CASCADE)})
public class Pensamiento {

    private String titulo;
    private String descripcion;
    @PrimaryKey
    @NonNull
    private String fecha;
    private String categoria;

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @NonNull
    public String getFecha() {
        return fecha;
    }

    public void setFecha(@NonNull String fecha) {
        this.fecha = fecha;
    }
}
