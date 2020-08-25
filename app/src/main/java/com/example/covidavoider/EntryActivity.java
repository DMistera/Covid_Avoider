package com.example.covidavoider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.covidavoider.singletons.UserService;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        UserService.create();

        Progress();
    }

    private void Progress() {
        startActivity(new Intent(this, MainActivity.class));
    }
}