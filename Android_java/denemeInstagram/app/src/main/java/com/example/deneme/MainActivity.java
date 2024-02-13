package com.example.deneme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.deneme.databinding.ActivityMainBinding;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();
        FirebaseUser user =auth.getCurrentUser();
        if(user!=null){
            Intent intent=new Intent(MainActivity.this,FeedActivity.class);
            startActivity(intent);
            finish();

        }
    }

    public void signUp(View view)
    {
      String email=binding.emailText.getText().toString();
      String passaword=binding.passawordText.getText().toString();

      if(email.equals("") && passaword.equals(""))
      {
          Toast.makeText(MainActivity.this,"Enter a email and passaword",Toast.LENGTH_LONG).show();
      }
      else{
          auth.createUserWithEmailAndPassword(email,passaword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
              @Override
              public void onSuccess(AuthResult authResult) {
                  Intent intent=new Intent(MainActivity.this,FeedActivity.class);
                  startActivity(intent);
                  finish();
              }
          }).addOnFailureListener(new OnFailureListener() {
              @Override
              public void onFailure(@NonNull Exception e) {
                  Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
              }
          });
      }
    }
    public void login(View view)
    {
        String email=binding.emailText.getText().toString();
        String passaword=binding.passawordText.getText().toString();

        if(email.equals("") || email.equals(passaword))
        {
            Toast.makeText(MainActivity.this,"Enter a password and email",Toast.LENGTH_LONG).show();
        }else{
            auth.signInWithEmailAndPassword(email,passaword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent =new Intent(MainActivity.this,FeedActivity.class);
                    startActivity(intent);
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}