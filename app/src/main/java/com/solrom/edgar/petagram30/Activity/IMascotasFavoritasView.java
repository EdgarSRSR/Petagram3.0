package com.solrom.edgar.petagram30.Activity;

import com.solrom.edgar.petagram30.adapter.MascotaAdaptador;
import com.solrom.edgar.petagram30.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by edgarsr on 02/10/17.
 */

public interface IMascotasFavoritasView {

public void generarLinearLayoutVertical();

public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

public void inicializarAdaptadorRV(MascotaAdaptador adaptador);

}
