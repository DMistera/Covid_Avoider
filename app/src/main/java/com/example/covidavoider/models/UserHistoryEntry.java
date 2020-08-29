package com.example.covidavoider.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class UserHistoryEntry {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public Date date;
    public String country;

    public String toString() {
        return "In " + country + " on " + date.toString();
    }
}
