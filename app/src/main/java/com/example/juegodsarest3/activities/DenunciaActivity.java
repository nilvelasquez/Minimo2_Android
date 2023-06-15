package com.example.juegodsarest3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.juegodsarest3.R;
import com.example.juegodsarest3.models.Denuncia;
import com.example.juegodsarest3.models.Swagger;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DenunciaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);
        configureDenunciaButton();
    }

    private void configureDenunciaButton() {
        Button BotonDenuncia = (Button) findViewById(R.id.EnviarAbuso_button);
        EditText fecha = (EditText) findViewById(R.id.fecha);
        EditText nombre = (EditText) findViewById(R.id.nombre);
        EditText comentario = (EditText) findViewById(R.id.comentario);

        BotonDenuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Swagger swagger = Swagger.retrofit.create(Swagger.class);

                Denuncia d = new Denuncia(fecha.getText().toString(), nombre.getText().toString(), comentario.getText().toString());
                Call<Denuncia> call = swagger.añadirDenuncia(d);
                 call.enqueue(new Callback<Denuncia>() {
                @Override
                   public void onResponse(Call<Denuncia> call, Response<Denuncia> response) {
                        Log.d("ERRORCRISTIAN",call.toString());
                        Log.d("ERRORCRISTIAN",response.toString());
                        if (response.isSuccessful()){
                            Snackbar mySnackbar = Snackbar.make(view, "Se ha registrado la denuncia", BaseTransientBottomBar.LENGTH_SHORT);
                            mySnackbar.show();
                        }
                        else {
                            Snackbar mySnackbar = Snackbar.make(view, "Esta denuncia ya ha sido registrada", BaseTransientBottomBar.LENGTH_SHORT);
                            mySnackbar.show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Denuncia> call, Throwable t) {
                        Snackbar mySnackbar = Snackbar.make(view, "No se ha podido establecer la conexión para enviar la denuncia", BaseTransientBottomBar.LENGTH_SHORT);
                        Log.d("ERRORCRISTIAN",t.toString());
                        mySnackbar.show();
                    }
                });
            }
        });
            }
        }
