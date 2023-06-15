package com.example.juegodsarest3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.juegodsarest3.R;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask tarea= new TimerTask() {
            @Override
            public void run() {
                Class dest=LoginActivity.class;

                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);


                if (isLoggedIn) {
                    Log.i("ERRORCRISTIAN", "login OK");
                    dest = MenuPrincipal.class;
                }
                    //if ()
                Intent intent=new Intent(Splash.this, dest);
                startActivity(intent);
                finish();
            }
        };
        Timer tiempo =new Timer();
        tiempo.schedule(tarea,3500);

        }

}
