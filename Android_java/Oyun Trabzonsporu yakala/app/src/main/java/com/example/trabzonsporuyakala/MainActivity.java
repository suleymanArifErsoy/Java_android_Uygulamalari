package com.example.trabzonsporuyakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int sayac;
    TextView textView;
    TextView textView1;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;

    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sayac=0;
        textView=findViewById(R.id.süreGostergesiTxtview);
        textView.setText("Zaman : 0");
        textView1=findViewById(R.id.skorGostergesi);
        textView1.setText("Skor: 0");

        // Tüm grid içindeki resmileri tanimla ve dizi içerisine aktar
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageArray=new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        //Rastgele ekranda resim goster
        imageRandom();

        // süremiz 10 saniyeden 1 er 1 er azalıyor
        new CountDownTimer(10000,1000) {
            @SuppressLint("SetTextI18n")
            @Override
            //her azalmada yapılacak işlemi belirtir
            public void onTick(long millisUntilFinished) {
                textView.setText("Zaman : "+millisUntilFinished/1000);
            }

            @Override
            // Süre bittiğinde ne yapılacak
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"zaman bitti",Toast.LENGTH_LONG).show();
                textView.setText("Zaman Bitti");
                handler.removeCallbacks(runnable);// runnable'ı sonlandırır.
                imageHide();
            }
        }.start();

    }
    @SuppressLint("SetTextI18n")
    public void arttir(View view)
    {
        sayac++;
        textView1.setText("Skor : "+sayac);
    }
    public void imageHide()
    {
        // Grid üzerindeki tüm resimleri gizler
        for(ImageView image:imageArray)
        {
            image.setVisibility(View.INVISIBLE);
        }
    }
    public void imageRandom()
    {
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run()
            {
                imageHide();
                Random rnd=new Random();
                int i =rnd.nextInt(9);//
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500); // her 0,5 saniyede bir tane resim gorunur kılar .
            }
        };
        handler.post(runnable);
    }
}