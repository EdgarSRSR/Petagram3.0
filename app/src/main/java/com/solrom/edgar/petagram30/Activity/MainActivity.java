package com.solrom.edgar.petagram30.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.solrom.edgar.petagram30.vista_fragment.HomeFragment;
import com.solrom.edgar.petagram30.adapter.PageAdapter;
import com.solrom.edgar.petagram30.vista_fragment.PerfilFragment;
import com.solrom.edgar.petagram30.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar     = (Toolbar)findViewById(R.id.toolbar);
        tabLayout   = (TabLayout)findViewById(R.id.tabLayout);
        viewPager   = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();

        if (toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Oculta el titulo del ToolBar
        }

        ImageView imgFavoritas = (ImageView)findViewById(R.id.imgFavoritas);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "¡Hola! este botón no sirve de nada ¡jajaja!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        //infla menu, agrega items a la action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.mContacto:
                Intent intent1 = new Intent(this, FormularioActivity.class);
                startActivity(intent1);
                break;

            case R.id.mAcercaDe:
                Intent intent2 = new Intent(this, AcercadeActivity.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    // muestra el activity de las mascotas favoritas
    public void irFavoritas(View view){
        Intent intent = new Intent(this, MascotasFavoritas.class);
        startActivity(intent);
    }

    // Método para cargar el ArrayList con los fragments que tenemos
    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        //carga los fragments en el orden deseado
        fragments.add(new HomeFragment());
        fragments.add(new PerfilFragment());
        return  fragments;
    }

    // activa los fragments
    private void setUpViewPager(){
        // inicializa el viewPager con instancia de la clase PageAdapter, se le pasa el manejador de fragments y
        // se llama a la funcion agregarFragments que devuelve el ArrayList con los fragments.
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.icons8_hotel_96);
        tabLayout.getTabAt(1).setIcon(R.drawable.icons8_dog_bowl_96);
    }


}
