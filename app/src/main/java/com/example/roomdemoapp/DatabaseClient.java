package com.example.roomdemoapp;


import android.content.Context;

import androidx.room.Room;

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


    //A synchronized block in Java is synchronized on some object.
    // All synchronized blocks synchronized on the same object can
    // only have one thread executing inside them at a time.
    //Thus here DatabaseClient is synchronized to object mInstance.

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    AppDatabase getAppDatabase()
    {
        return appDatabase;
    }



}
