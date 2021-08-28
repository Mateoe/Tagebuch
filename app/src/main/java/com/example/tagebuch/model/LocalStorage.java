package com.example.tagebuch.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tagebuch.model.dao.CategoriaRoomDAO;
import com.example.tagebuch.model.dao.PensamientoRoomDAO;
import com.example.tagebuch.model.pojo.Categoria;
import com.example.tagebuch.model.pojo.Pensamiento;

@Database(entities = {Pensamiento.class, Categoria.class}, version = 1)
public abstract class LocalStorage extends RoomDatabase {

    //Definicion de los DAO
    public abstract PensamientoRoomDAO pensamientoRoomDAO();
    public abstract CategoriaRoomDAO categoriaRoomDAO();

    private static LocalStorage localStorage;

    public static LocalStorage getLocalStorage(final Context context){
        if (localStorage == null){
            localStorage = Room.databaseBuilder(context, LocalStorage.class,
                    "tegebuchDB")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return localStorage;
    }
}
