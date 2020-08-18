package com.example.covidavoider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }
    public void startHist(View v){
        Intent activity2Intent = new Intent(getApplicationContext(), HistoryActivity.class);
        startActivity(activity2Intent);
    }
    public void startMain(View v){
        Intent activity2Intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(activity2Intent);
    }
    public void startSett(View v){
        Intent activity2Intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(activity2Intent);
    }
}