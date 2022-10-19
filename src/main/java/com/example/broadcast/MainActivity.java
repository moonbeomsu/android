package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //사용자에게 권한 허용 요청
           int call =  checkSelfPermission(Manifest.permission.ACCESS_CHECKIN_PROPERTIES);
           int sms = checkSelfPermission(Manifest.permission.RECEIVE_SMS);
           if(call != PackageManager.PERMISSION_GRANTED|| sms!= PackageManager.PERMISSION_GRANTED){
               requestPermissions(new String[]{
                       Manifest.permission.PROCESS_OUTGOING_CALLS,
                       Manifest.permission.RECEIVE_SMS
               },0);
           }
        }
    }
}