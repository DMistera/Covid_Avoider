package com.example.covidavoider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.tomer.fadingtextview.FadingTextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private FadingTextView fadingTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fadingTextView = findViewById(R.id.fading_text_view);
    }
    public void startExample2(View v){
        String[] example2 = {"F","dla","Dominika","FFFFFFFFFFFFFFFFFF"};
        fadingTextView.setTexts(example2);
        fadingTextView.setTimeout(1000, TimeUnit.MILLISECONDS);
    }
}