package com.example.tiki.app_canhbao.notifications;


import static com.example.tiki.app_canhbao.notifications.NotificationApp.CHANEL_ID_1;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.tiki.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class MyServiceFirebaseClass extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        RemoteMessage.Notification notification=remoteMessage.getNotification();
        if(notification==null)
            return;
        String title1= notification.getTitle();
        String mes1=notification.getBody();
//        Log.d("customnotification---> "," "+notification.getBody());
//        Log.d("customnotification---> "," "+notification.getTitle());
////        String title= notification.getTitle();
////        String mes=notification.getBody();
//        Map<String,String> strMap =remoteMessage.getData();
//
//        String title= strMap.get("nameGV");
//        String mes=strMap.get("noidung");
//
//        Log.d("customnotification---> "," phần strMap: "+strMap);
//        Log.d("customnotification---> "," phần map: "+title);
//        Log.d("customnotification---> "," phần map: "+mes);
        sendNotification(title1, mes1);
    }

    private void sendNotification(String title, String mes) {
        Intent intent=new Intent(this, NotificationApp.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MyServiceFirebaseClass.this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //icon
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.notification_panner);
        //nhạc chuong default
        Uri soundDefult=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //nhạc chuông castom
//        Uri soundCusstom=Uri.parse("android.resource:://"+getPackageName()+"/"+R.raw.....)
        NotificationCompat.Builder builder =new NotificationCompat.Builder(getApplicationContext(), CHANEL_ID_1)
                .setSmallIcon(R.drawable.notification_panner)
                .setContentTitle(title)
                .setContentText(mes)
                .setSound(soundDefult)
                .setContentIntent(pendingIntent);



        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(manager!=null){
            manager.notify(1, notification);
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("TAG","--->>> "+ token);
    }
}
