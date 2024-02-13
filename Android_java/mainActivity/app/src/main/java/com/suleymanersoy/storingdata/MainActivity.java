package com.suleymanersoy.storingdata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editTextNumber);
        textView=findViewById(R.id.textView);

        sharedPreferences = this.getSharedPreferences("com.suleymanersoy.storingdata", Context.MODE_PRIVATE);

        int storedAge=sharedPreferences.getInt("StoredAge",0);
        if(storedAge==0)
        {
            textView.setText("Your Age: ");
        }
        else
        {
            textView.setText("Your Age:"+ storedAge);
        }

    }

        public void save(View view)
        {
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
            alertDialog.setTitle("save");
            alertDialog.setMessage("Emin misin???");
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    if(!editText.getText().toString().matches(""))
                    {
                        int age =Integer.parseInt(editText.getText().toString());
                        textView.setText("Your Age: "+age);
                        sharedPreferences.edit().putInt("StoredAge",age).apply();
                    }
                    else{
                        textView.setText("Your Age: ");
                    }
                    Toast.makeText(MainActivity.this,"Saved",Toast.LENGTH_LONG).show();
                }
            });
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                 Toast.makeText(MainActivity.this,"Not Saved",Toast.LENGTH_LONG).show();

                }
            });
            alertDialog.show();



        }
        @SuppressLint({"CommitPrefEdits", "SetTextI18n"})
        public void delete(View view)
        {
            int storedData=sharedPreferences.getInt("StoredAge",0);

            if(storedData!=0)
            {
                sharedPreferences.edit().remove("StoredAge");
                textView.setText("Your Age:");
            }

        }
}