package com.suleymanersoy.ikiniciactivityekleme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void changeActivity(View view)
    {
        Intent intent =new Intent(MainActivity2.this,MainActivity.class);
        startActivity(intent);
    }
}