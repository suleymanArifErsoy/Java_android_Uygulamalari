package com.example.deneme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.deneme.databinding.ActivityFeedBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class FeedActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private ArrayList<Kullanici> kullaniciListesi;
    private ActivityFeedBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFeedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.addPostMenu){
            // menu ekle git

            Intent intent=new Intent(FeedActivity.this,UploadActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.outPutMenu)
        {
            // Çıkış yap
            auth.signOut();
            Intent intent=new Intent(FeedActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }
}