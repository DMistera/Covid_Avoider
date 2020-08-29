package com.example.covidavoider.singletons;

import android.content.Context;

import androidx.room.Room;
import com.example.covidavoider.database.AppDatabase;

public class DatabaseService {
    private static DatabaseService instance;
    public static void create(Context context) {
        instance = new DatabaseService(context);
    }
    public static DatabaseService getInstance() {
        return instance;
    }

    private AppDatabase database;
    public DatabaseService(Context context) {
        database = Room.databaseBuilder(context, AppDatabase.class, "database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
