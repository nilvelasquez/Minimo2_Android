package com.example.juegodsarest3.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juegodsarest3.RetrofitClient;
import com.example.juegodsarest3.models.Objeto;
import com.example.juegodsarest3.models.Swagger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.juegodsarest3.R;
import com.example.juegodsarest3.models.TablaCompra;
import com.example.juegodsarest3.models.Usuario;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class UsuarioActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdaptadorObjetosComprados adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ProgressBar theProgressBar;
    private final String TAG = InicialActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);


        recyclerView = (RecyclerView) findViewById(R.id.my_new_recycler_view_usuario);
        theProgressBar = (ProgressBar) findViewById(R.id.progressBarUsuario);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdaptadorObjetosComprados();
        recyclerView.setAdapter(adapter);
        doApiCall();
        doApiCall2();


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        adapter.remove(viewHolder.getAdapterPosition());
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    private void doApiCall() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String correo = sharedPreferences.getString("correo", "");
        Swagger swagger = Swagger.retrofit.create(Swagger.class);
        Call<List<TablaCompra>> call = swagger.getlistaObjetosUsuario(correo);

        theProgressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<List<TablaCompra>>() {
            @Override
            public void onResponse(Call<List<TablaCompra>> call, Response<List<TablaCompra>> response) {
                theProgressBar.setVisibility(View.INVISIBLE);

                adapter.setData(response.body());


            }


            @Override
            public void onFailure(Call<List<TablaCompra>> call, Throwable t) {


                String msg = "Error con el retrofit: "+t.toString();
                Log.d(TAG,msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
            }
        });
    }


    private void doApiCall2() {
    Swagger swaggerApi = RetrofitClient.getInstance().getMyApi();
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String correo = sharedPreferences.getString("correo", "");
        //String correo="c";
    Call<Usuario> call = swaggerApi.getUsuario(correo);
    call.enqueue(new Callback<Usuario>() {
        @Override
        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
            if (response.isSuccessful()) {
                Usuario usuario = response.body();
                if (usuario != null) {

                    String nombre = usuario.getNombre();
                    String correo = usuario.getCorreo();
                    double dsaCoins = usuario.getDsacoins();
                    String texto = String.valueOf(dsaCoins);

                    TextView txtNombreUsuario = findViewById(R.id.txtNombreUsuario);
                    TextView txtCorreo = findViewById(R.id.txtCorreo);
                    TextView txtDinero = findViewById(R.id.txtDinero);

                    String nombreLabel = "Nombre: " + nombre;
                    String correoLabel = "Correo: " + correo;
                    String dineroLabel = "Dinero: " + texto +" €";

                    txtNombreUsuario.setText(nombreLabel);
                    txtCorreo.setText(correoLabel);
                    txtDinero.setText(dineroLabel);

                } else {
                    // La respuesta no contiene un objeto Usuario válido
                    // Maneja el caso en consecuencia
                }
            } else {
                // La solicitud no fue exitosa (código de respuesta diferente de 2xx)
                // Maneja el caso en consecuencia
            }
        }

        @Override
        public void onFailure(Call<Usuario> call, Throwable t) {
            // Error en la comunicación con el servidor
            // Maneja el caso en consecuencia
        }
    });

    }
}