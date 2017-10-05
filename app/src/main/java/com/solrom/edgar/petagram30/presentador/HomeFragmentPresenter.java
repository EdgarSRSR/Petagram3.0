package com.solrom.edgar.petagram30.presentador;

import android.content.Context;

import com.solrom.edgar.petagram30.bd.ConstructorMascotas;
import com.solrom.edgar.petagram30.pojo.Mascota;
import com.solrom.edgar.petagram30.vista_fragment.IHomeFragmentView;

import java.util.ArrayList;

/**
 * Created by edgarsr on 02/10/17.
 */

public class HomeFragmentPresenter implements IHomeFragmentPresenter{

    private IHomeFragmentView iHomeFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public HomeFragmentPresenter(IHomeFragmentView iHomeFragmentView, Context context){
        this.iHomeFragmentView = iHomeFragmentView;
        this.context = context;
        obtenerTopCinco();
    }


    @Override
    public void mostrarMascotasRV() {
        //primero se crea el adaptador y pasarle el arraylist datos, así se inicializa el adaptador
        iHomeFragmentView.inicializarAdaptadorRV(iHomeFragmentView.crearAdaptador(mascotas));
        // luego ordena que se genere el linearLayoutVertical
        iHomeFragmentView.generarLinearLayoutVertical();
    }

    @Override
    public void obtenerTopCinco() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtener5datos();
        mostrarMascotasRV();
    }

}
