package com.example.sayacexcel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonArttir;
    private Button buttonYenile;
    private Button buttonAzalt;

    private TextView sayacDegeri;
    private int kontrolDeger=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonYenile= (Button) findViewById(R.id.buttonYenile);
        buttonArttir = (Button) findViewById(R.id.buttonArttir);
        sayacDegeri= (TextView) findViewById(R.id.textView);
        buttonAzalt= (Button) findViewById(R.id.buttonAzalt);

        buttonArttir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text= (String) sayacDegeri.getText();
                int sayac = Integer.parseInt(text) + 1;
                sayacDegeri.setText(String.valueOf(sayac));
                kontrolDeger=0;
            }
        });

        buttonAzalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = (String) sayacDegeri.getText();
                int deger=Integer.parseInt(text);
                if(deger>0) {
                    int yeniDeger = deger -1;
                    sayacDegeri.setText(String.valueOf(yeniDeger));
                }
                else{
                    if(kontrolDeger>=1){
                        Toast.makeText(MainActivity.this, "Salakmısın cemile", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Sayi sıfırlandı Azaltılamaz daha",Toast.LENGTH_SHORT).show();
                    }
                    kontrolDeger++;
                }
                }
            }
        );

        buttonYenile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayacDegeri.setText("0");
            }
        });
    }
}