package com.example.covidavoider.singletons;

import com.example.covidavoider.models.UserHistoryEntry;

import java.util.Calendar;
import java.util.List;

public class HistoryService {

    private static HistoryService instance;

    public static void create() {
        instance = new HistoryService();
    }

    private OnLocationChangeCallback callback;

    public static HistoryService getInstance() {
        return instance;
    }

    public List<UserHistoryEntry> getHistory() {
        return DatabaseService.getInstance().getDatabase().userHistoryDao().getAll();
    }

    public void onLocationChange(OnLocationChangeCallback callback) {
        this.callback = callback;
    }

    public String getLastSavedLocation() {
        List<UserHistoryEntry> history = getHistory();
        if(history.size() > 0) {
            return history.get(history.size() - 1).country;
        }
        return null;
    }

    public void locationChange(String location) {
        if(!location.equals(getLastSavedLocation())) {
            UserHistoryEntry entry = new UserHistoryEntry();
            entry.country = location;
            Calendar today = Calendar.getInstance();
            entry.date = new java.sql.Date(today.getTime().getTime());
            DatabaseService.getInstance().getDatabase().userHistoryDao().insertAll(entry);

            if(callback != null) {
                callback.onLocationChange();
            }
        }
    }
}

