package com.example.juegodsarest3.activities;

import java.util.ArrayList;
import java.util.List;

//import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.juegodsarest3.R;
import com.example.juegodsarest3.models.Objeto;
import com.example.juegodsarest3.models.Swagger;
import com.example.juegodsarest3.models.TablaCompra;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    private List<Objeto> values;


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public TextView txtFooter2;

        public Button buttonCompra;

        public ImageView icon;
        public View layout;


        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.objetoComprado);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            txtFooter2 = (TextView) v.findViewById(R.id.thirdLine);
            icon = (ImageView) v.findViewById(R.id.fotoObjeto);
            buttonCompra = (Button) v.findViewById(R.id.compraORM);

        }
    }

    public void setData(List<Objeto> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
    }

    public void add(int position, Objeto item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public Adaptador(){values = new ArrayList<>();}

    public Adaptador(List<Objeto> myDataset) {
        values = myDataset;
    }


    @Override
    public Adaptador.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.activity_row_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Objeto o = values.get(position);
        final String name = o.getNombre();
        holder.txtHeader.setText(name);

        holder.buttonCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    // Get the clicked item based on the adapter position
                    Objeto clickedItem = values.get(adapterPosition);

                    // Perform any actions you need with the clicked item
                    Log.d("Adaptador", "Botón de compra presionado en la posición: " + adapterPosition);
                    Log.d("Adaptador", "Nombre del objeto comprado: " + clickedItem.getNombre());

                    // Aquí puedes llamar al método hacerCompra de la interfaz Swagger

                    SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    String correo = sharedPreferences.getString("correo", "");
                    String nombreObjeto = clickedItem.getNombre();

                    Swagger swagger = Swagger.retrofit.create(Swagger.class); // Obtén la instancia de Swagger desde tu Retrofit

                    Call<TablaCompra> call = swagger.hacerCompra(correo, nombreObjeto);
                    call.enqueue(new Callback<TablaCompra>() {
                        @Override
                        public void onResponse(Call<TablaCompra> call, Response<TablaCompra> response) {
                            if (response.isSuccessful()) {
                                // La compra se realizó con éxito, puedes manejar la respuesta aquí
                                TablaCompra tablaCompra = response.body();
                                Log.d("Adaptador", "Compra realizada: " + tablaCompra.toString());
                            } else {
                                // Ocurrió un error al realizar la compra, puedes manejar el error aquí
                                Log.d("Adaptador", "Error al realizar la compra: " + response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<TablaCompra> call, Throwable t) {

                        }
                    });
                }
            }
        });

        holder.txtFooter.setText(o.getDescripcion());
        holder.txtFooter2.setText(String.valueOf(o.getPrecio()));

        Picasso.get().load(o.getFotoimagen()).into(holder.icon);

    }


    @Override
    public int getItemCount() {
        return values.size();
    }


}