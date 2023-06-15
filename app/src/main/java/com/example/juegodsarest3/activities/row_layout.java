package com.example.juegodsarest3.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.juegodsarest3.R;
import com.example.juegodsarest3.models.Swagger;
import com.example.juegodsarest3.models.TablaCompra;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import okhttp3.Callback;
import okhttp3.Response;
import retrofit2.Call;

public class row_layout extends AppCompatActivity {
    private TextView objetoComprado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_row_layout);
        objetoComprado = findViewById(R.id.objetoComprado);

        Button compraButton = findViewById(R.id.compraORM);
        compraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí se ejecutará el código cuando se presione el botón
                Log.d("row_layout", "Botón de compra presionado");
            }
        });

    }
}
