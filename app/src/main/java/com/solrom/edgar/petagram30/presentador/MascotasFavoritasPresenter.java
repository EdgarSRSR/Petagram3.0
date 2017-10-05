package com.solrom.edgar.petagram30.presentador;

import android.content.Context;

import com.solrom.edgar.petagram30.Activity.IMascotasFavoritasView;
import com.solrom.edgar.petagram30.bd.ConstructorMascotas;
import com.solrom.edgar.petagram30.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by edgarsr on 02/10/17.
 */

public class MascotasFavoritasPresenter implements IMascotasFavoritasPresenter {

    private IMascotasFavoritasView iMascotasFavoritasView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public MascotasFavoritasPresenter(IMascotasFavoritasView iMascotasFavoritasView, Context context){
        this.iMascotasFavoritasView = iMascotasFavoritasView;
        this.context = context;
        obtenerMascotasBaseDatos();
    }


    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();

    }

    @Override
    public void mostrarMascotasRV() {
        iMascotasFavoritasView.inicializarAdaptadorRV(iMascotasFavoritasView.crearAdaptador(mascotas));
        iMascotasFavoritasView.generarLinearLayoutVertical();
    }
}
