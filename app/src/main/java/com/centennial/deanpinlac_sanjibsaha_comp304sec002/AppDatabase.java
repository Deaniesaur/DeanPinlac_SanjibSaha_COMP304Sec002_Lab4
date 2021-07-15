package com.centennial.deanpinlac_sanjibsaha_comp304sec002;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "ClassroomDB";

    public static synchronized AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase.class,
                    DATABASE_NAME
            ).build();
        }

        return INSTANCE;
    }
}
