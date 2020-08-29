package com.example.covidavoider.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.covidavoider.daos.UserDao;
import com.example.covidavoider.daos.UserHistoryDao;
import com.example.covidavoider.models.User;
import com.example.covidavoider.models.UserHistoryEntry;

@Database(entities = {User.class, UserHistoryEntry.class}, version = 4, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract UserHistoryDao userHistoryDao();
}