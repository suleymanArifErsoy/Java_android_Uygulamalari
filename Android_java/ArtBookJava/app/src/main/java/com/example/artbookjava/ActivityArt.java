package com.example.artbookjava;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.artbookjava.databinding.ActivityArtBinding;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ActivityArt extends AppCompatActivity {

    private ActivityArtBinding binding;

    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<String> permissionsLauncher;

    Bitmap selectedImage;
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityArtBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        database=this.openOrCreateDatabase("Sanat",MODE_PRIVATE,null);
        registerLauncher();


        Intent intent =getIntent();
        String info=intent.getStringExtra("info");
        if(info.equals("new"))
        {
            binding.tabloAdText.setText("");
            binding.sanatciText.setText("");
            binding.yapildigiYilText.setText("");
            binding.kaydetButton.setVisibility(View.VISIBLE);
            binding.imageView2.setImageResource(R.drawable.ic_launcher_background);

        }else{
            int id =intent.getIntExtra("id",0);
            binding.kaydetButton.setVisibility(View.INVISIBLE);

            try {
                Cursor cursor= database.rawQuery("SELECT * FROM arts WHERE id=?",new String[] {String.valueOf(id)});
                int nameIx=cursor.getColumnIndex("artname");
                int painterIx=cursor.getColumnIndex("painter");
                int yearIx=cursor.getColumnIndex("year");
                int imageIx=cursor.getColumnIndex("image");

                while(cursor.moveToNext()){
                    binding.tabloAdText.setText(cursor.getString(nameIx));
                    binding.sanatciText.setText(cursor.getString(painterIx));
                    binding.yapildigiYilText.setText(cursor.getString(yearIx));

                    byte[] bytes=cursor.getBlob(imageIx);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    binding.imageView2.setImageBitmap(bitmap);
                }


            }catch (Exception e){
                e.getStackTrace();
            }

        }
    }

    public void save(View view)
    {

        String tablo_ismi=binding.tabloAdText.getText().toString();
        String sanatci=binding.sanatciText.getText().toString();
        String yil=binding.yapildigiYilText.getText().toString();

        Bitmap kucukimage=resimKucultucu(selectedImage,300);

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        kucukimage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
        byte[] byteArray=outputStream.toByteArray();


        try {

            database.execSQL("CREATE TABLE IF NOT EXISTS sanat(id INTEGER PRIMARY KEY, artname VARCHAR,painter VARCHAR,year INT,image BLOB)");

            String sqlString="INSERT INTO arts(artname , painter, year , image) VALUES(?,?,?,?)";
            SQLiteStatement sqLiteStatement=database.compileStatement(sqlString);
            sqLiteStatement.bindString(1,tablo_ismi);
            sqLiteStatement.bindString(2,sanatci);
            sqLiteStatement.bindString(3,yil);
            sqLiteStatement.bindBlob(4,byteArray);
            sqLiteStatement.execute();


        }catch (Exception e){
            e.getStackTrace();
        }

        Intent geriDon=new Intent(ActivityArt.this,MainActivity.class);
        geriDon.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// tüm activiteleri kapat ve alttaki komut ile sadece main activite acık kalmis olacak
        startActivity(geriDon);

    }
    public Bitmap resimKucultucu(@NonNull Bitmap image, int maximumSize)
    {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;

        if (bitmapRatio > 1) {
            width = maximumSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maximumSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image,width,height,true);
    }
    public void selectImage(View view)
    {

        // Kullanıcıdan önceden izin istenilmediyse if içinde izin istenilecek
        // Kullanıcının sadece bir defa izin vermesi yeterlidir . izin verdiyse else içinde fotografa ulasılacak


        if(Build.VERSION.SDK_INT==Build.VERSION_CODES.TIRAMISU)// Android API 33+
        {

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)!= PackageManager.PERMISSION_GRANTED)
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_MEDIA_IMAGES))
                {
                    //Kullanıcı bizim izinimizi reddetiyse bu izin olmadan fotografları gostermemizin mumkun olmadıgını gosteren bir uyarı yapmamız gerekiyor

                    Snackbar.make(view,"galerye gitmek için izin lazım",Snackbar.LENGTH_INDEFINITE).setAction("izin ver", new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            // izin isteyecez
                            permissionsLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES);
                        }
                    }).show();
                }else {
                    // izin isteyecez
                    permissionsLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES);
                }

            }
            else {
                //galeri
                Intent intentGallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intentGallery);

            }
        }else
        { // Android API 32-
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE))
                {
                    //Kullanıcı bizim izinimizi reddetiyse bu izin olmadan fotografları gostermemizin mumkun olmadıgını gosteren bir uyarı yapmamız gerekiyor

                    Snackbar.make(view,"galerye gitmek için izin lazım",Snackbar.LENGTH_INDEFINITE).setAction("izin ver", new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            // izin isteyecez
                            permissionsLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                        }
                    }).show();
                }else {
                    // izin isteyecez
                    permissionsLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                }

            }
            else {
                //galeri
                Intent intentGallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intentGallery);

            }
        }
    }
    private void registerLauncher()
    {

        activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()== RESULT_OK){

                    Intent intentFromResult=result.getData();

                  if(intentFromResult!=null){
                      Uri imageData=intentFromResult.getData();
                      //binding.imageView2.setImageURI(imageData);

                      if(Build.VERSION.SDK_INT >= 28){

                          try {
                              ImageDecoder.Source source =ImageDecoder.createSource(ActivityArt.this.getContentResolver(),imageData);
                              selectedImage=ImageDecoder.decodeBitmap(source);
                              binding.imageView2.setImageBitmap(selectedImage);
                          }catch(Exception e ){
                              e.getStackTrace();
                          }
                      }
                      else{
                          try {
                              selectedImage= MediaStore.Images.Media.getBitmap(ActivityArt.this.getContentResolver(),imageData);
                              binding.imageView2.setImageBitmap(selectedImage);
                          } catch (IOException e) {
                              throw new RuntimeException(e);
                          }

                      }
                  }
                }
            }
        });

        permissionsLauncher=registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if(result)
                {
                    Intent intentGalllery=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncher.launch(intentGalllery);
                }
                else{
                    Toast.makeText(ActivityArt.this,"izin gerekli !!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}