package com.solrom.edgar.petagram30.vista_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solrom.edgar.petagram30.R;
import com.solrom.edgar.petagram30.adapter.MascotaAdaptador;
import com.solrom.edgar.petagram30.pojo.Mascota;
import com.solrom.edgar.petagram30.presentador.HomeFragmentPresenter;
import com.solrom.edgar.petagram30.presentador.IHomeFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by edgarsr on 01/10/17.
 */

public class HomeFragment extends Fragment implements IHomeFragmentView{

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    public MascotaAdaptador adaptador;
    private IHomeFragmentPresenter presentador;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //    asigna clase java al layout
        View v = inflater.inflate(R.layout.fragment_home , container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);
        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        presentador = new HomeFragmentPresenter(this, getContext());

        //inicializarListaMascotas(); // Inicializa la lista de mascotas
        //inicializaAdaptador();    // Inicializa el adaptador
        return v;
    }

    @Override
    public  void generarLinearLayoutVertical() {
        // Instacia el linearLayoutManager que sirve para manejar la forma en que se ve la lista
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //el RecyclerView se comportará como un LinearLayoutManager y adquirirá todas sus propiedades
        listaMascotas.setLayoutManager(llm);

    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }


    // Inicializa el adaptador
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador){
        listaMascotas.setAdapter(adaptador);
    }


    // Carga las mascotas para mostrarlas
    /*
    public void inicializarListaMascotas(){
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota("Hachiko", 0, R.drawable.shibainu));
        mascotas.add(new Mascota("Pilón", 0, R.drawable.schnauzer_black));
        mascotas.add(new Mascota("Toby", 0, R.drawable.schnauzer_blackandpepper));
        mascotas.add(new Mascota("Kiby", 0, R.drawable.hamster));
        mascotas.add(new Mascota("Mu", 0, R.drawable.mouse));
        mascotas.add(new Mascota("Cochon", 0, R.drawable.french_bulldog));
    }*/

}
