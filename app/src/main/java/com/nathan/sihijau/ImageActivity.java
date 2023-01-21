package com.nathan.sihijau;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
    }
    public void returnToKontenSampah(View v) {
        startActivity(new
                Intent(getApplicationContext(),
                KontenSampah.class));
    }
}