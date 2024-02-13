package com.example.artbookjava;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.artbookjava.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    ArtAdapter artAdapter;
    ArrayList<Art> artlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        artlist=new ArrayList<>();



        binding.recyyclerView.setLayoutManager(new LinearLayoutManager(this));
        artAdapter= new ArtAdapter(artlist);
        binding.recyyclerView.setAdapter(artAdapter);

        getData();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void getData()
    {
        try {

            SQLiteDatabase sqLiteDatabase=this.openOrCreateDatabase("Sanat",MODE_PRIVATE,null);

            Cursor cursor =sqLiteDatabase.rawQuery("SELECT * FROM arts",null);
            int idIx=cursor.getColumnIndex("id");
            int isimIx= cursor.getColumnIndex("artname");

            while(cursor.moveToNext()){

                String name=cursor.getString(isimIx);
                int id =cursor.getInt(idIx);
                Art art =new Art(name,id);
                artlist.add(art);
            }

            artAdapter.notifyDataSetChanged();
            cursor.close();

        }catch(Exception e )
        {
            e.getStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.art_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.add_art){
            Intent intent=new Intent(this,ActivityArt.class);
            intent.putExtra("info","new");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}