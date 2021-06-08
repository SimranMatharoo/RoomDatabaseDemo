package com.example.roomdemoapp;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseClient {

    Context mCtx;
    static DatabaseClient mInstance;


    //AppDatabase Object
    AppDatabase appDatabase;

    DatabaseClient(Context mCtx)
    {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyDatabase is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "MyDatabase").build();
    }



}
