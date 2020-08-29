package com.example.covidavoider.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.covidavoider.models.UserHistoryEntry;

import java.util.List;

@Dao
public interface UserHistoryDao {

    @Query("SELECT * FROM userhistoryentry ORDER BY date")
    List<UserHistoryEntry> getAll();

    @Insert
    void insertAll(UserHistoryEntry... entries);
}
