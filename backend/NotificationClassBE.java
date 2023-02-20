package com.example.tiki.app_canhbao.backend;

import android.os.Handler;
import android.util.Log;

import com.example.tiki.app_canhbao.entity.ClassSVNotification;
import com.example.tiki.app_canhbao.entity.NoticationClass;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.severs.RetrofitAPI;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;

public class NotificationClassBE {
    public static NotificationClassBE notificationClassBE=null;
    public static NotificationClassBE getInstant(){
        if(notificationClassBE==null){
            notificationClassBE=new NotificationClassBE();
        }
        return notificationClassBE;
    }
    private void sendNotiFromSV(List<User> l, NoticationClass notiClas){

    }
    private void sendNoti(){

    }
    public void MakeAPI(List<User> l, NoticationClass noti){
        Log.e("NotificationClassBE: ","độ dài list user qua: "+l.size());
        for (int i=0;i<l.size();i++){
//            Log.e("NotificationClassBE: ","các phẩn tử "+l.get(i).get_Email());
//            Log.e("NotificationClassBE: ","các phẩn tử "+l.get(i).get_Class());
            if(l.get(i).get_category()==2){
                String token =l.get(i).get_Token();
                sendNotiUser(token, noti);
            }
        }

    }
    private void sendNotiUser(String token, NoticationClass noti){
        Log.e("NotificationClassBE: ","token: "+token);
        //token:
        noti.setToken(token);
        RetrofitAPI retrofitAPI = RetrofitAPI.getInstance();

        Call<NoticationClass> call = retrofitAPI.getApiNotification().PostCategory(noti.getToken(),noti.getNameClass(),noti.getNoidung());
        call.enqueue(new Callback<NoticationClass>() {
            @Override
            public void onResponse(Call<NoticationClass> call, retrofit2.Response<NoticationClass> response) {
                NoticationClass r =response.body();
            }

            @Override
            public void onFailure(Call<NoticationClass> call, Throwable t) {

            }
        });
    }
    public void deleteNoti(ClassSVNotification noti){
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("ThongBao/").child(noti.get_id());
        ref.removeValue();
    }
}
