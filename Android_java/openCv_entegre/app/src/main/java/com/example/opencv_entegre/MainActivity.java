package com.example.opencv_entegre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraActivity;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends CameraActivity{

    public CameraBridgeViewBase mOpenCvCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPermission();

        mOpenCvCamera=findViewById(R.id.camera_view);
        mOpenCvCamera.setCvCameraViewListener(new CameraBridgeViewBase.CvCameraViewListener2() {
            @Override
            public void onCameraViewStarted(int width, int height) {

            }

            @Override
            public void onCameraViewStopped() {

            }

            @Override
            public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
                return inputFrame.rgba();
            }
        });

        if(OpenCVLoader.initDebug()){
            Log.d("OpenCV","Success ...");
            mOpenCvCamera.enableView();
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        mOpenCvCamera.enableView();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mOpenCvCamera.disableView();
    }
    @Override
    protected void onPause(){
        super.onPause();
        mOpenCvCamera.disableView();
    }

    @Override
    protected List<? extends CameraBridgeViewBase> getCameraViewList() {
        return Collections.singletonList(mOpenCvCamera);
    }

    public void getPermission(){
        if(checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CAMERA},101);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0 && grantResults[0]!=PackageManager.PERMISSION_GRANTED){
            getPermission();
        }
    }


}