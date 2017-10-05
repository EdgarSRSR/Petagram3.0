package com.solrom.edgar.petagram30.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.solrom.edgar.petagram30.pojo.Mascota;
import com.solrom.edgar.petagram30.adapter.MascotaAdaptador;
import com.solrom.edgar.petagram30.R;
import com.solrom.edgar.petagram30.presentador.IMascotasFavoritasPresenter;
import com.solrom.edgar.petagram30.presentador.MascotasFavoritasPresenter;

import java.util.ArrayList;

import static com.solrom.edgar.petagram30.R.id.rvMascotas;

/**
 * Created by edgarsr on 01/10/17.
 */

public class MascotasFavoritas extends AppCompatActivity implements IMascotasFavoritasView{

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    public MascotaAdaptador adaptador;
    private IMascotasFavoritasPresenter presentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mascotas_favoritas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.miToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaMascotas = (RecyclerView) findViewById(rvMascotas);
        presentador = new MascotasFavoritasPresenter(this, getApplicationContext());

        //ajusta la forma en que se ve la lista
        //LinearLayoutManager llm = new LinearLayoutManager(this);
        //llm.setOrientation(LinearLayoutManager.VERTICAL);

        //Lhace que el RecyclerView se comporte como un LinearLayoutManager con todo y propiedades
        //listaMascotas.setLayoutManager(llm);
        //inicializarListaMascotas();
        //inicializaAdaptador();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //infla el menu
        getMenuInflater().inflate(R.menu.menu_mascotasfavoritas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item_menu) {
        int id = item_menu.getItemId(); // obtiene el item de cada item del menu
        if (id == R.id.salir) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item_menu);
    }

    @Override
    public void generarLinearLayoutVertical(){
        //ajusta la forma en que se ve la lista
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //Lhace que el RecyclerView se comporte como un LinearLayoutManager con todo y propiedades
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, this);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador){
        listaMascotas.setAdapter(adaptador);
    }

    /*
    public void inicializaAdaptador(){
        adaptador = new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(adaptador);
    }

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
