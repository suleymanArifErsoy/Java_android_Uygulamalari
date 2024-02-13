package com.example.wifi_permissions;


import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {

    private Switch wifiButton;
    private TextView wifiText;
    private WifiManager wifiManager;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

           wifiButton =findViewById(R.id.switchButton);
           wifiText= findViewById(R.id.wifiText);

           wifiManager =(WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

           wifiButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   if(isChecked){
                       wifiManager.setWifiEnabled(true);
                       wifiText.setText("Wifi Durumu: Açık");
                       wifiButton.setText("Wifi açık");
                   }
                   else{
                       wifiManager.setWifiEnabled(false);
                       wifiText.setText("Wifi Durumu : Kapalı");
                       wifiButton.setText("Wifi kapalı");
                   }
               }
           });
           if(wifiManager.isWifiEnabled()){
               wifiButton.setChecked(true);
               wifiText.setText("Wifi Durumu : Açık");
               wifiButton.setText("Wifi açık");
           }
           else{
               wifiButton.setChecked(false);
               wifiText.setText("Wifi Durumu : Kapalı");
               wifiButton.setText("Wifi kapalı");
           }
        }
        private BroadcastReceiver wifiDurumAlici= new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int wifiStateExtra =intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,WifiManager.WIFI_STATE_UNKNOWN);
                if(wifiStateExtra==WifiManager.WIFI_STATE_ENABLED){
                    wifiButton.setChecked(true);
                    wifiText.setText("Wifi Durumu : Açık");
                }
                else{
                    wifiButton.setChecked(false);
                    wifiText.setText("Wifi Durumu : Kapalı");
                }
            }
        };

}

