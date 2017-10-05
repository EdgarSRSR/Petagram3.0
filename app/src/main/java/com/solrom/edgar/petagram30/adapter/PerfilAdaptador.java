package com.solrom.edgar.petagram30.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.solrom.edgar.petagram30.pojo.Mascota;
import com.solrom.edgar.petagram30.R;

import java.util.ArrayList;

/**
 * Created by edgarsr on 01/10/17.
 */

public class PerfilAdaptador extends RecyclerView.Adapter<PerfilAdaptador.PerfilViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;


    public PerfilAdaptador (ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    // infla el layout y lo pasara al ViewHolder para que obtenga los views
    @Override
    public PerfilAdaptador.PerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil, parent, false);
        return new PerfilAdaptador.PerfilViewHolder(v);
    }


    // obtiene datos de la clase MascotaViewHolder con los datos de la lista recibida
    @Override
    public void onBindViewHolder(final PerfilAdaptador.PerfilViewHolder mascotaViewHolder, int position){
        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNumLikes.setText(Integer.toString(mascota.getNumLikes()));
        //mascotaViewHolder.llCardViewPerfil.setBackgroundResource(mascota.getColorFondo());
    }

    @Override
    public int getItemCount(){
        return mascotas.size();
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView tvNumLikes;
        private LinearLayout llCardViewPerfil;

        public PerfilViewHolder(View itemView){
            super(itemView);
            // Carga las vistas del cardview
            this.imgFoto    = (ImageView) itemView.findViewById(R.id.imgFoto);;
            this.tvNumLikes = (TextView) itemView.findViewById(R.id.tvNumLikes);
            this.llCardViewPerfil = (LinearLayout) itemView.findViewById(R.id.llCardViewPerfil);
        }

    }
}