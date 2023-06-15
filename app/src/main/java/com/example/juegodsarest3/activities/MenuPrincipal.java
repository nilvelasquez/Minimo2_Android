package com.example.juegodsarest3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.juegodsarest3.R;
import com.example.juegodsarest3.RetrofitClient;
import com.example.juegodsarest3.models.Mapa;
import com.example.juegodsarest3.models.Swagger;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();

        Locale.setDefault(new Locale("es"));
        Configuration config = new Configuration();
        config.locale = Locale.getDefault();
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        configureTiendaButton();
        configureDenunciaButton();
        configureBandejaButton();
        configureRankingButton();
        configureImageButton();
        configureEmpezarButton();
        configureCerrarSesionbtn();

        TextView myTextview = findViewById(R.id.textMenuPrincipal);
        String textoMenu = getResources().getString(R.string.Main_menu);
        myTextview.setText(textoMenu);

    }

    private void configureTiendaButton() {
        Button tienda_button = (Button) findViewById(R.id.tienda);
        String textoTienda = getResources().getString(R.string.Boton_tienda);
        tienda_button.setText(textoTienda);
        tienda_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, TiendaActivity.class));
            }
        });
    }
        private void configureDenunciaButton() {
            Button denuncia_button = (Button) findViewById(R.id.denunciabtn);
            String textoDenuncia = getResources().getString(R.string.Boton_Denuncia);
            denuncia_button.setText(textoDenuncia);
            denuncia_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // startActivity(new Intent(MenuPrincipal.this, DenunciaActivity.class));
                }
            });
        }
    private void configureBandejaButton() {
        Button bandeja_button = (Button) findViewById(R.id.Bandejabtn);
        bandeja_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, BandejaActivity.class));
            }
        });
    }
    private void configureRankingButton() {
        Button ranking_button = (Button) findViewById(R.id.btnRanking);
        ranking_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  startActivity(new Intent(MenuPrincipal.this, RankingActivity.class));
            }
        });
    }
    private void configureImageButton() {
        ImageButton image_button = (ImageButton) findViewById(R.id.imageButton);
        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent siguienteIntent = new Intent(MenuPrincipal.this, UsuarioActivity.class);
                startActivity(siguienteIntent);
            }
        });
    }
    private void configureEmpezarButton() {
        Button empezarbutton = findViewById(R.id.empezarJuegobtn); // Reemplaza "button" con el ID de tu botón
        String nombremapaunity = "Nivel 1";
        AtomicReference<String> nombremapabackend = new AtomicReference<>("");
        AtomicReference<String> mapatxtbackend = new AtomicReference<>("");

        Swagger swaggerApi = RetrofitClient.getInstance().getMyApi();
        Call<Mapa> call = swaggerApi.getMapa(nombremapaunity);
        call.enqueue(new Callback<Mapa>() {
            @Override
            public void onResponse(Call<Mapa> call, Response<Mapa> response) {
                if (response.isSuccessful()) {
                    Mapa m = response.body();
                    if (m != null) {
                        nombremapabackend.set(m.getNombremapa());
                        mapatxtbackend.set(m.getMapatxt());
                        // Continúa con el resto de la lógica después de recibir la respuesta exitosa
                    } else {
                        // La respuesta no contiene un objeto Mapa válido
                        // Maneja el caso en consecuencia
                    }
                } else {
                    // La solicitud no fue exitosa (código de respuesta diferente de 2xx)
                    // Maneja el caso en consecuencia
                }
            }

            @Override
            public void onFailure(Call<Mapa> call, Throwable t) {
                // Error en la comunicación con el servidor
                // Maneja el caso en consecuencia
            }
        });

        empezarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setComponent(new ComponentName("com.DefaultCompany.MyProject", "com.unity3d.player.UnityPlayerActivity"));
                String data = nombremapabackend.get(); // Aquí obtienes el valor de la AtomicReference
                String data2 = mapatxtbackend.get(); // Aquí obtienes el valor de la AtomicReference
                Log.d("MiApp", "" + nombremapabackend);
                Log.d("MiApp", "" + mapatxtbackend);
                i.putExtra("input", data);
                i.putExtra("input2", data2);
                startActivity(i);
            }
        });
    }
    private void configureCerrarSesionbtn() {

        ImageButton cerrarbutton = (ImageButton) findViewById(R.id.cerrarSesionBtn);
        cerrarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Borrar los datos guardados en SharedPreferences
                SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                // Cerrar la aplicación

                finish();
                Intent intent = new Intent(MenuPrincipal.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                }
            });

    }
    }
