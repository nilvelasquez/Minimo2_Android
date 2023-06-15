package com.example.juegodsarest3.activities;
import java.util.ArrayList;
import java.util.List;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.juegodsarest3.R;
import com.example.juegodsarest3.models.Mensaje;

public class AdaptadorBandeja extends RecyclerView.Adapter<AdaptadorBandeja.ViewHolder> {
    private String values;


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtPregunta;
        public TextView txtRespuesta;

        public View layout;


        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtPregunta = (TextView) v.findViewById(R.id.pregunta);
            txtRespuesta = (TextView) v.findViewById(R.id.respuesta);


        }
    }

    public void setData(String myDataset) {
        values = myDataset;
        notifyDataSetChanged();
    }

    public AdaptadorBandeja(){
        String mensaje = null;
        mensaje = values;}

    public AdaptadorBandeja(String myDataset) {
        values = myDataset;
    }


    @Override
    public AdaptadorBandeja.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {

        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.activity_row_layout_faq, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String f = values;
        holder.txtPregunta.setText(f);

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}