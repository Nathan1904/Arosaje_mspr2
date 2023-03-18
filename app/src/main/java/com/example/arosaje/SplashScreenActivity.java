package com.example.arosaje;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.app.ActionBar;

import android.content.Intent;

import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {
    private final int SPLASH_SCREEN_TIMEOUT= 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        },  SPLASH_SCREEN_TIMEOUT);
    }

}