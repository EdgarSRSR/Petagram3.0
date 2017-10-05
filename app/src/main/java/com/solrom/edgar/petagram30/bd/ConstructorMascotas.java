package com.solrom.edgar.petagram30.bd;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.solrom.edgar.petagram30.pojo.Mascota;
import com.solrom.edgar.petagram30.R;

import java.util.ArrayList;

/**
 * Created by edgarsr on 01/10/17.
 */

public class ConstructorMascotas {

    //private static final int LIKE = 1;
    private static final String TAG = "Depurador";
    Context context;

    public ConstructorMascotas(Context context){
        this.context = context;
    }


    public ArrayList<Mascota> obtenerDatos() {

        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
        BaseDatos db = new BaseDatos(context);
        mascotas = db.obtenerTodasLasMascotas();
        if(mascotas.size() == 0){
            insertarMascotas(db);//si no hay contactos cargados se agregan
            return db.obtenerTodasLasMascotas();
        }else{
            return mascotas;
        }
        //insertarCincoContactos(db);
        //return db.obtenerTodasLasMascotas();
    }


    public void insertarMascotas(BaseDatos db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Toby");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.schnauzer_blackandpepper);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES, 0);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Pilón");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.schnauzer_black);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES, 0);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Hachiko");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.shibainu);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES, 0);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Mu");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.mouse);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES, 0);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Cochon");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.french_bulldog);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES, 0);
        db.insertarMascota(contentValues);
    }

    public void darLikeMascota(Mascota mascota){

        Integer likes = obtenerLikesMascota(mascota);
        likes = likes + 1;
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        //contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES, likes);
        db.insertarLikeMascota(contentValues, mascota.getId());
        Log.d(TAG, "El valor de likes en 'darLikesMascota' es : " + likes);
    }

    public int obtenerLikesMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }

    public ArrayList<Mascota> obtener5datos(){
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
        BaseDatos db = new BaseDatos(context);
        mascotas = db.obtenerTopCinco();
        return mascotas;
    }

}


