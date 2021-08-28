package com.example.tagebuch.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tagebuch.model.pojo.Categoria;

import java.util.List;

@Dao
public interface CategoriaRoomDAO {
    @Query("SELECT * from Categoria")
    List<Categoria> getAll();

    @Query("SELECT color FROM categoria where nombre = :categoriaPensamiento")
    String obtenerColor(String categoriaPensamiento);

    @Query("SELECT nombre FROM CATEGORIA")
    List<String> obtenerNombres();

    @Insert
    void insertarMuchos(Categoria ... categorias);
    @Insert
    void insertarUno(Categoria categoria);
    @Delete
    void eliminarUno(Categoria categoria);
    @Update
    void actualizarUno(Categoria categoria);
}
