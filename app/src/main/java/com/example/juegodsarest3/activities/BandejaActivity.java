package com.example.juegodsarest3.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.juegodsarest3.models.Mensaje;
import com.example.juegodsarest3.models.Swagger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.juegodsarest3.R;

public class BandejaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdaptadorBandeja adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ProgressBar theProgressBar;
    private final String TAG = BandejaActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandeja);

        recyclerView = (RecyclerView) findViewById(R.id.bandeja_mensajes);
        theProgressBar = (ProgressBar) findViewById(R.id.progressBarfaq);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdaptadorBandeja();
        recyclerView.setAdapter(adapter);
        doApiCall();


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        //adapter.remove(viewHolder.getAdapterPosition());
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    private void doApiCall() {
        Swagger swagger = Swagger.retrofit.create(Swagger.class);
        Call<String> call = swagger.getlistaBandeja();
        theProgressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                theProgressBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {

                    if (response.body() != null) {
                        adapter.setData(response.body());
                    }
                } else {
                    Toast.makeText(BandejaActivity.this, "Failed to fetch messages", Toast.LENGTH_SHORT).show();
                }


            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                theProgressBar.setVisibility(View.INVISIBLE);
                String msg = "Error with Retrofit: " + t.toString();
                Log.d(TAG, msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}