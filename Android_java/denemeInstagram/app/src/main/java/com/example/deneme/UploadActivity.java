package com.example.deneme;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.appsearch.PackageIdentifier;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.deneme.databinding.ActivityUploadBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class UploadActivity extends AppCompatActivity {

    private ActivityUploadBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<String> permissionsLauncher;
    Uri imageData;
    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;
    private FirebaseStorage firebaseStorage;
    private FirebaseAuth auth;

    Bitmap selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        registerLauncher();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

    }

    public void uploadButton(View view)
    {
        UUID uuid=UUID.randomUUID();
        String imageName="images/"+uuid+".jpg";

        if(imageData!=null){
            storageReference.child(imageName).putFile(imageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(UploadActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    public void selectImage(View view)
    {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                Snackbar.make(view,"Galeriye gitmek için izin lazım",Snackbar.LENGTH_INDEFINITE).setAction("İzin ver", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // izin al
                        permissionsLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                    }
                }).show();
            }else {
                //izin al
                permissionsLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }

        }
        else {
            //izin verildi galeriye git
            Intent intentToGallery =new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncher.launch(intentToGallery);

        }

    }

    public void registerLauncher()
    {
        activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==Activity.RESULT_OK){
                    Intent intentFromResult=result.getData();
                    if(intentFromResult!=null){
                        imageData=intentFromResult.getData();
                        try {
                            if(Build.VERSION.SDK_INT>=28){
                                ImageDecoder.Source source=ImageDecoder.createSource(UploadActivity.this.getContentResolver(),imageData);
                                selectedImage=ImageDecoder.decodeBitmap(source);
                                binding.imageView.setImageBitmap(selectedImage);
                            }else{
                                selectedImage=MediaStore.Images.Media.getBitmap(UploadActivity.this.getContentResolver(),imageData);
                                binding.imageView.setImageBitmap(selectedImage);
                            }
                        }catch(Exception e){
                            e.getStackTrace();
                        }
                    }
                }

            }
        });

        permissionsLauncher=registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {

            if(result){
                // izin verildiyse
                Intent intentGallery=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intentGallery);
            }
            else{
                //izin verilmediyse
                Toast.makeText(UploadActivity.this,"izin gerekli",Toast.LENGTH_LONG).show();
            }
            }
        });

    }

}