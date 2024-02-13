package com.example.futbolcular;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.futbolcular.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    ArrayList<OyuncuOzellikleri> oyuncular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        com.example.futbolcular.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        oyuncular=new ArrayList<OyuncuOzellikleri>();

        // data
        OyuncuOzellikleri sorloth=new OyuncuOzellikleri("Sorloth","Norvec","Forvet",84,R.drawable.sorloth);
        OyuncuOzellikleri hamsik=new OyuncuOzellikleri("Hamsik","Slovakya","Deneyimli Orta Saha",81,R.drawable.hamsik);
        OyuncuOzellikleri omur=new OyuncuOzellikleri("Abdulkadir","Türk","Orta saha",85,R.drawable.omur);
        OyuncuOzellikleri pereira=new OyuncuOzellikleri("Pereira","Portekiz","Sağ bek",75,R.drawable.pereira);
        OyuncuOzellikleri nwakaeme =new OyuncuOzellikleri("Nwakaeme ","Nijerya","Sol Kanat Forvet",79,R.drawable.nwakaeme);

        oyuncular.add(sorloth);
        oyuncular.add(hamsik);
        oyuncular.add(pereira);
        oyuncular.add(omur);
        oyuncular.add(nwakaeme);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        OyuncularAdapter oyuncularAdapter=new OyuncularAdapter(oyuncular);
        binding.recyclerView.setAdapter(oyuncularAdapter);

/*  ->>   ListView kullanmak verimsizdir . çok fazla veriler ile çalışılacağı zaman recyclerView kullanmak daha performanslı olacaktır
    ->>   RecyclerView tekrar tekrar kullanılmasını sağlar

        // Adapter
        //ListView

        //mapping
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                oyuncular.stream().map(oyuncuOzellikleri -> oyuncuOzellikleri.isim).collect(Collectors.toList()));
        binding.listView.setAdapter(arrayAdapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this,oyuncular.get(position).isim,Toast.LENGTH_LONG).show();

                 Intent intent =new Intent(MainActivity.this,ActivityDetayi.class);
                 intent.putExtra("oyuncu",oyuncular.get(position));
                 startActivity(intent);
            }
        });

        */
    }
}