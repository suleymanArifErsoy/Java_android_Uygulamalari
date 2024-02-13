package com.suleymanersoy.layoutlearing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences=this.getSharedPreferences("com.suleymanersoy.layoutlearing", Context.MODE_PRIVATE);

    }
}