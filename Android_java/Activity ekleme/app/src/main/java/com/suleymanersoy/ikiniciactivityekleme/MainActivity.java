package com.suleymanersoy.ikiniciactivityekleme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.textView2);

    }
    public void changeActivity(View view)
    {
        Intent intent =new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);

    }
}