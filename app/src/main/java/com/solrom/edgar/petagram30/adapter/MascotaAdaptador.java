package com.solrom.edgar.petagram30.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.solrom.edgar.petagram30.bd.ConstructorMascotas;
import com.solrom.edgar.petagram30.pojo.Mascota;
import com.solrom.edgar.petagram30.R;

import java.util.ArrayList;

/**
 * Created by edgarsr on 01/10/17.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    private static final  String TAG = "depurador";
    ArrayList<Mascota> mascotas;
    Activity activity;
    int likes;


    // Constructor
    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    // Método que infla el layout y lo pasa al ViewHolder para que obtenga los views
    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }


    // Se cargan los datos de la clase MascotaViewHolder con los datos de la lista
    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position){
        final Mascota mascota = mascotas.get(position); //Obtiene los datos de la mascota en la posición position
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());// carga el cardView con la foto del ArrayList
        mascotaViewHolder.tvNumLikes.setText(Integer.toString(mascota.getNumLikes()));// carga el Número de likes del cardView
        mascotaViewHolder.tvNombre.setText(mascota.getNombre()); // carga el nombre de la mascota sacado del Arraylist

        mascotaViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.darLikeMascota(mascota);
                mascotaViewHolder.tvNumLikes.setText(Integer.toString(constructorMascotas.obtenerLikesMascota(mascota)));
                Toast.makeText(activity, "Diste like a " + mascota.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount(){
        if(mascotas == null){
            return 0;
        } else {
            return mascotas.size();
        }
    }


    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private ImageButton btnLike;
        private TextView tvNombre;
        private TextView tvNumLikes;

        private LinearLayout llCardView;

        // Constructor
        public MascotaViewHolder(View itemView){
            super(itemView);
            // Cargo las vistas del cardview
            this.imgFoto    = (ImageView) itemView.findViewById(R.id.imgFoto);
            this.btnLike    = (ImageButton) itemView.findViewById(R.id.btnLike);
            this.tvNombre   = (TextView) itemView.findViewById(R.id.tvNombre);
            this.tvNumLikes = (TextView) itemView.findViewById(R.id.tvNumLikes);

        }

    }
}