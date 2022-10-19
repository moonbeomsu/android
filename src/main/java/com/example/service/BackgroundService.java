package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BackgroundService extends Service {

    //서비스가 백그라운드에서 실행될 떄 해야되는 목록
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Handler handler = new Handler();
        try {
            //5초 쉬다가 5초뒤에 다시 메인엑티비티 띄움
            for(int i=1;i<=5;i++) {
                Thread.sleep(1000);
                Toast.makeText(this, i+"초 쉬는중",Toast.LENGTH_SHORT).show();
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    Intent startActivityIntent = new Intent(BackgroundService.this,MainActivity.class);
                    startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startActivityIntent);
                }
            });



        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
