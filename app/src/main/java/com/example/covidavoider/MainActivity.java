package com.example.covidavoider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.example.covidavoider.ui.login.LoginActivity;
import com.tomer.fadingtextview.FadingTextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mysong;
    private FadingTextView fadingTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fadingTextView = findViewById(R.id.fading_text_view);
    }
    public void startExample2(View v){
        Intent activity2Intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(activity2Intent);
        //setContentView(R.layout.activity_login);
        //String[] example2 = {"F","dla","Dominika","FFFFFFFFFFFFFFFFFF"};
        //mysong = MediaPlayer.create(MainActivity.this, R.raw.rick);
        //mysong.start();
        //fadingTextView.setTexts(example2);
        //fadingTextView.setTimeout(1000, TimeUnit.MILLISECONDS);
    }
}