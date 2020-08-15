package com.example.covidavoider;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.tomer.fadingtextview.FadingTextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mysong;
    private FadingTextView fadingTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mysong = MediaPlayer.create(MainActivity.this, R.raw.titanic_bad_recorder);
        mysong.start();
        fadingTextView = findViewById(R.id.fading_text_view);
    }
    public void startExample2(View v){
        String[] example2 = {"F","dla","Dominika","FFFFFFFFFFFFFFFFFF"};
        mysong = MediaPlayer.create(MainActivity.this, R.raw.rick);
        mysong.start();
        fadingTextView.setTexts(example2);
        fadingTextView.setTimeout(1000, TimeUnit.MILLISECONDS);
    }
}