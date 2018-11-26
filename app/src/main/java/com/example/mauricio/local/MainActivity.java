package com.example.mauricio.local;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    EditText Nombre;
    EditText Cancion;
    EditText Edad;
    EditText Letra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Nombre = (EditText) findViewById(R.id.Cantante);
        Cancion = (EditText) findViewById(R.id.Cancion);
        Edad = (EditText) findViewById(R.id.Anio);
        Letra = (EditText) findViewById(R.id.Letra);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                Class fragmentClass = InicioFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = null;
        Class fragmentClass = InicioFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        setTitle("Local Music");
    }

        @Override
        public void onBackPressed ()
        {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START))
            {
                drawer.closeDrawer(GravityCompat.START);
            } else
                {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected (MenuItem item){
            // Handle navigation view item clicks here.
            Fragment fragment = null;
            Class fragmentClass= InicioFragment.class;
            int id = item.getItemId();

            if (id == R.id.action_listar) {
                fragmentClass = InicioFragment.class;
            } else if (id == R.id.nav_gallery) {
                fragmentClass = GuardarFragment.class;
            } else if (id == R.id.action_listar) {
                fragmentClass = CargarFragment.class;
            } else if (id == R.id.nav_camera) {

            }else if (id == R.id.nav_share) {
                AlertDialog.Builder uBuilder2 = new AlertDialog.Builder(MainActivity.this);
                View aView2 = getLayoutInflater().inflate(R.layout.acercade, null);
                uBuilder2.setView(aView2);
                final AlertDialog dialog2 = uBuilder2.create();
                dialog2.show();
                Button close = (Button) aView2.findViewById(R.id.close);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog2.cancel();
                    }
                });

            } else if (id == R.id.nav_send) {
                finish();
            }

            try{
                fragment = (Fragment) fragmentClass.newInstance();
            }catch (Exception e){
                e.printStackTrace();
            }


            FragmentManager fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

            item.setChecked(true);
            setTitle(item.getTitle());

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }


    public void guardarDatos(View view)
    {
        String can = "Se Te Olvido";
        String nom = "Kalimba";
        int ed = 2009;
        String letra = "se te olvido que yo sin ti No puedo respirar";

        try {
            Guardar save = new Guardar(this, "DEMODBB", null, 1);
            SQLiteDatabase db = save.getWritableDatabase();
            if (db != null) {
                ContentValues reValues = new ContentValues();
                reValues.put("Nombre", can);
                reValues.put("Cantante", nom);
                reValues.put("Anio", ed);
                reValues.put("Descripcion", letra);
                long i = db.insert("Cancion", null, reValues);
                if (i > 0) {
                    Toast.makeText(this, "Registro insertado", Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }

    }

    public void cargarDatos(View view){
        Guardar save = new Guardar(this, "DEMODBB", null, 1);
        SQLiteDatabase db = save.getReadableDatabase();
        if (db != null) {
            Cursor c = db.rawQuery("select * from Cancion", null);
            int cantidad = c.getCount();
            int  i = 0;
            String[] arreglo = new String[cantidad];
            if(c.moveToFirst())
            {
                do {
                    String linea = c.getInt(0)+" "+c.getString(1)+" "+c.getString(2)+" "+c.getInt(3)+" "+c.getString(4);
                    arreglo[i] = linea;
                    i++;
                }
                while (c.moveToNext());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arreglo);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);
        }
    }
}









