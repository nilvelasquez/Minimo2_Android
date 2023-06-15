package com.example.juegodsarest3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.juegodsarest3.models.Swagger;
import com.example.juegodsarest3.models.Usuario;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.example.juegodsarest3.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        configureRegisterButton();
    }

    private void configureRegisterButton(){
        Button BotonLogin = (Button) findViewById(R.id.Login_button);
        EditText mail = (EditText) findViewById(R.id.mail);
        EditText password = (EditText) findViewById(R.id.password);
        EditText name = (EditText) findViewById(R.id.name);

        BotonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Swagger swagger = Swagger.retrofit.create(Swagger.class);

                Usuario ur = new Usuario(mail.getText().toString(),password.getText().toString(),name.getText().toString(),500.00);
                Call<Usuario> call = swagger.registrarUsuario2(ur);
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        Log.d("ERRORCRISTIAN",call.toString());
                        Log.d("ERRORCRISTIAN",response.toString());
                        if (response.isSuccessful()){
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                        else {
                            Snackbar mySnackbar = Snackbar.make(view, "Este usuario ya está registrado", BaseTransientBottomBar.LENGTH_SHORT);
                            mySnackbar.show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Snackbar mySnackbar = Snackbar.make(view, "No se ha podido establecer la conexión para el registro", BaseTransientBottomBar.LENGTH_SHORT);
                        Log.d("ERRORCRISTIAN",t.toString());
                        mySnackbar.show();
                    }
                });
            }
        });
    }
}