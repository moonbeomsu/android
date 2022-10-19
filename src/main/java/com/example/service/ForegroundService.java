package com.example.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Timer;
import java.util.TimerTask;

public class ForegroundService extends Service {
    int progress = 0;
    NotificationManager nm;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            nm = getSystemService(NotificationManager.class );
            NotificationChannel channel = new NotificationChannel("1","download",NotificationManager.IMPORTANCE_DEFAULT);
            nm.createNotificationChannel(channel);
            Notification notification = getDownloadNotification(progress);

            nm.notify(1,notification);
            startForeground(1,notification); //foreground 로 등록
        }

        Timer timer = new Timer();
        TimerTask timerTask= new TimerTask(){
            @Override
            public void run() {
                progress +=10;
                Notification notification = getDownloadNotification(progress);
                nm.notify(1,notification);

                if (progress==100){
                    timer.cancel();
                    Intent startIntent = new Intent(ForegroundService.this,MainActivity.class);
                    startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(ForegroundService.this,
                            0,startIntent,PendingIntent.FLAG_IMMUTABLE);//완료된 알림 클릭시 메인 엑티비티로 전환

                    notification = new Notification.Builder(ForegroundService.this)
                            .setContentTitle("다운로드 완료")
                            .setContentText("다운로드가 완료 되었습니다.")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setChannelId("1")
                            .setContentIntent(pendingIntent)
                            .build();
                    nm.cancel(1);
                    nm.notify(2,notification);
                }
            }
        };
        timer.schedule(timerTask,1000,1000);


        return super.onStartCommand(intent, flags, startId);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification getDownloadNotification(int progress){
        Notification notification = new Notification.Builder(this)
                .setContentTitle("다운로드")
                .setContentText("다운로드 중입니다.")
                .setProgress(100,progress,false)
                .setSmallIcon(R.mipmap.ic_launcher )
                .setChannelId("1")
                .build();

        return notification;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
