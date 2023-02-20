package com.example.tiki.app_canhbao.backend;

import android.util.Log;

import com.example.tiki.app_canhbao.entity.ChatLop;
import com.example.tiki.app_canhbao.entity.Message;
import com.example.tiki.app_canhbao.entity.NoticationClass;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.severs.RetrofitAPI;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ChatBE {
    public static void saveChat(Message mes){
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference ref =database.getReference().child("Chats/");
        String id = ref.push().getKey();
        mes.set_id(id);
        ref.child(id).setValue(mes);
    }
    public static void sendNotifi(Message mes){
        String token= AuthAccount.getInstant().userAccount.get_Token();
        if(token.equals(mes.get_tokenNguoiGui())){
            token=mes.get_tokenNguoiNhan();
        }else{
            token=mes.get_tokenNguoiGui();
        }
        NoticationClass noti=new NoticationClass(token, mes.get_tenNguoiGui(), mes.get_noiDung());
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
    public static void chatLop(ChatLop chatLop){
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference ref =database.getReference().child("ChatLop/");
        String id = ref.push().getKey();
        chatLop.set_id(id);
        ref.child(id).setValue(chatLop);
    }
    public static void sendAllUser(List<User> l, ChatLop chatLop){
//        Log.e("Chat BE: "," size: "+l.size());
        NoticationClass noti = new NoticationClass("",chatLop.get_nameClass(),chatLop.get_noiDung());
        for (int i=0;i<l.size();i++){
            Log.e("Chat BE: "," data: "+l.get(i).toString());
            sendUserChatLop(l.get(i).get_Token(), noti);
        }
    }
    private static void sendUserChatLop(String token, NoticationClass noti){

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
}
