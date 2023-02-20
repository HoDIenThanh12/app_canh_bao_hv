package com.example.tiki.app_canhbao.notifications;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class NotificationApp extends Application {
    public static final String CHANEL_ID_1="ID1";
//    public static final String CHANEL_ID_2="ID2";

    @Override
    public void onCreate() {
        super.onCreate();
        notificatios();
    }

    private void notificatios() {
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
            Uri soundDefult= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationChannel chanel_1=new NotificationChannel(CHANEL_ID_1, "Thông báo đẩy",
                    NotificationManager.IMPORTANCE_DEFAULT);

            //distable music defaul
//            chanel_1.setSound(, );
            NotificationManager manager =getSystemService(NotificationManager.class);
            if(manager!=null)
                manager.createNotificationChannel(chanel_1);
        }
    }
}
