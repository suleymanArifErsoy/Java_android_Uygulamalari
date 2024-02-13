package com.example.sayac;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.sayac.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {



    int sayac=0;
    private TextView sayiGoster;
    private Button sayacButtonArttir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sayiGoster = (TextView) findViewById(R.id.TextId);
        sayacButtonArttir=(Button) findViewById(R.id.sayacId);
        sayacButtonArttir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String text = (String) sayiGoster.getText();
              int sayac = Integer.parseInt(text)+1;
              sayiGoster.setText("Sayac = "+ sayac);
            }
        });

    }
    public void arttir(View view)
    {
        String text = (String) sayiGoster.getText();
        int sayac = Integer.parseInt(text) + 1 ;

        sayiGoster.setText("sayac == "+ sayac);
    }

}