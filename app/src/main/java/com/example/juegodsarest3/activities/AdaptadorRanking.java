package com.example.juegodsarest3.activities;

import java.util.ArrayList;
import java.util.List;

//import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.juegodsarest3.R;
import com.example.juegodsarest3.models.Objeto;
import com.example.juegodsarest3.models.Ranking;
import com.squareup.picasso.Picasso;

public class AdaptadorRanking extends RecyclerView.Adapter<AdaptadorRanking.ViewHolder> {
    private List<Ranking> values;


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public TextView txtFooter2;

        public ImageView icon;
        public View layout;


        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.nickname);
            txtFooter = (TextView) v.findViewById(R.id.fecha);
            txtFooter2 = (TextView) v.findViewById(R.id.puntos);
            icon = (ImageView) v.findViewById(R.id.icon2);

        }
    }

    public void setData(List<Ranking> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
    }

    public void add(int position, Ranking item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public AdaptadorRanking(){values = new ArrayList<>();}

    public AdaptadorRanking(List<Ranking> myDataset) {
        values = myDataset;
    }


    @Override
    public AdaptadorRanking.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.activity_row_layout_ranking, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Ranking ra = values.get(position);
        final String name = ra.getNickname();
        holder.txtHeader.setText(name);

        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove (holder.getAdapterPosition());
            }
        });

        holder.txtFooter.setText(ra.getFecha());
        holder.txtFooter2.setText(String.valueOf(ra.getPuntos()));

        Picasso.get().load(ra.getAvatar()).into(holder.icon);

    }


    @Override
    public int getItemCount() {
        return values.size();
    }


}