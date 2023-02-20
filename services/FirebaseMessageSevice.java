package com.example.tiki.app_canhbao.services;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.notifications.NotificationApp;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class FirebaseMessageSevice extends FirebaseMessagingService {
    private String KEY = NotificationApp.CHANEL_ID_1;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
       RemoteMessage.Notification notification=remoteMessage.getNotification();
//        if(notification==null)
//            return;
        String title= notification.getTitle();
        String mes=notification.getBody();
//        Map<String,String> strMap =remoteMessage.getData();
//        String title= strMap.get("user_name");
//        String mes=strMap.get("pass");
        sendNotification(title, mes);
    }
    private void sendNotification(String title, String mes ){
        //Intent intent=new Intent(this, MyApplicationFirebase.class);
        Intent intent=new Intent(this, NotificationApp.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder =new NotificationCompat.Builder(this, KEY)
                .setContentTitle(title)
                .setContentText(mes)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent);

        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(manager!=null){
            manager.notify(1, notification);
        }
        Log.i("token lấy về: ","--->>> "+"đang gửi thông báo đén app");
    }
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("token lấy về: ","--->>> "+ token);
    }
}
