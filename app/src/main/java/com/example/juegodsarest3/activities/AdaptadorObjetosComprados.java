package com.example.juegodsarest3.activities;

import java.util.ArrayList;
import java.util.List;

//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.juegodsarest3.R;
import com.example.juegodsarest3.models.Objeto;
import com.example.juegodsarest3.models.TablaCompra;
import com.squareup.picasso.Picasso;

public class AdaptadorObjetosComprados extends RecyclerView.Adapter<AdaptadorObjetosComprados.ViewHolder> {
    private List<TablaCompra> values;


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public ImageView icon;
        public View layout;


        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.objetoComprado);
            icon = (ImageView) v.findViewById(R.id.fotoObjeto);

        }
    }

    public void setData(List<TablaCompra> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
    }

    public void add(int position, TablaCompra item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public AdaptadorObjetosComprados(){values = new ArrayList<>();}

    public AdaptadorObjetosComprados(List<TablaCompra> myDataset) {
        values = myDataset;
    }


    @Override
    public AdaptadorObjetosComprados.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.activity_objetoscomprados, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        TablaCompra o = values.get(position);
        final String name = o.getNombreobjeto();
        holder.txtHeader.setText(name);

        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove (holder.getAdapterPosition());
            }
        });




    }


    @Override
    public int getItemCount() {
        return values.size();
    }


}