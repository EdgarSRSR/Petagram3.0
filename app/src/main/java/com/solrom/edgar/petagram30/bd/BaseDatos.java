package com.solrom.edgar.petagram30.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.solrom.edgar.petagram30.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by edgarsr on 01/10/17.
 */

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;
    private static final String TAG = "Depurador";

    public BaseDatos(Context context){
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" +
                ConstantesBaseDatos.TABLE_MASCOTAS_ID       +   " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE   + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_FOTO     + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_LIKES    + " INTEGER"+
                ")";
        /*String queryCrearTablaLikesMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA + "(" +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID              + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA      + " INTEGER, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES    + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + ")" +
                "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" + ConstantesBaseDatos.TABLE_MASCOTAS_ID + ")" +
                ")";*/
        db.execSQL(queryCrearTablaMascota);
        //db.execSQL(queryCrearTablaLikesMascota);
    }

    @Override //Si la tabla mascota cambia de versión la borra y crea una nueva
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_MASCOTAS);
        //db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerTodasLasMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));
            mascotaActual.setNumLikes(registros.getInt(3));

            /*String queryLikes = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES + ") as likes " +
                    " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                    " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + "=" +mascotaActual.getId();
            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()) {
                mascotaActual.setNumLikes(registrosLikes.getInt(1));
            }else {
                mascotaActual.setNumLikes(1);
            }*/
            mascotas.add(mascotaActual);
        }

        db.close();
        return mascotas;
    }


    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTAS, null, contentValues);
        db.close();
    }

    //este método inserta un like a una mascota
    public void insertarLikeMascota(ContentValues contentValues, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(ConstantesBaseDatos.TABLE_MASCOTAS, contentValues, "id=" +id, null);
        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota){
        int likes = 0;

        String query = "SELECT " + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES +
                " FROM " + ConstantesBaseDatos.TABLE_MASCOTAS +
                " WHERE " + ConstantesBaseDatos.TABLE_MASCOTAS_ID + "=" + mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
            Log.d(TAG, "El valor de likes en 'ObtenerLikesMascota' es : " + likes);
        }

        db.close();
        return likes;
    }

    public ArrayList<Mascota> obtenerTopCinco(){

        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();


        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS +
                " ORDER BY " + ConstantesBaseDatos.TABLE_MASCOTAS_LIKES +
                " DESC LIMIT 5";

        SQLiteDatabase db = this.getWritableDatabase();//abre cla base de datos para que se puesa escribir SQL
        Cursor registros = db.rawQuery(query, null);//obtiene el codigo del query


        while(registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));
            mascotaActual.setNumLikes(registros.getInt(3));

            mascotas.add(mascotaActual);
        }

        db.close();//cierra la base de datos una vez terminado de realizar los queries
        return mascotas;

    }


}
