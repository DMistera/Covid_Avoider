package com.example.covidavoider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.tomer.fadingtextview.FadingTextView;

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
    }
}