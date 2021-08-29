package com.example.tagebuch.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tagebuch.model.pojo.Pensamiento;

import java.util.List;

@Dao
public interface PensamientoRoomDAO {
    @Query("SELECT * FROM pensamientos")
    List<Pensamiento> getAll();

    @Query("SELECT * FROM pensamientos WHERE fecha = :fecha LIMIT 1")
    Pensamiento obtenerPorFecha(String fecha);

    @Insert
    void insertar(Pensamiento pensamiento);
    @Delete
    void eliminar(Pensamiento pensamiento);
    @Update
    void actualizar(Pensamiento pensamiento);
}
