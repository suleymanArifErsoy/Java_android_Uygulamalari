package com.example.futbolcular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futbolcular.databinding.ActivityDetayiBinding;

public class ActivityDetayi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.futbolcular.databinding.ActivityDetayiBinding binding = ActivityDetayiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent =getIntent();
        OyuncuOzellikleri secilenOyuncu= (OyuncuOzellikleri) intent.getSerializableExtra("oyuncu");


        binding.isimTxt.setText(secilenOyuncu.isim);
        binding.ulkeTxt.setText(secilenOyuncu.ulke);
        binding.mevkiTxt.setText(secilenOyuncu.mevki);
        binding.potansiyelTxt.setText(String.valueOf(secilenOyuncu.potansiyel));
        binding.imageView.setImageResource(secilenOyuncu.image);

    }


}