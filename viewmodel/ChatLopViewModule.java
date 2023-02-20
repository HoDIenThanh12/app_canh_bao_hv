package com.example.tiki.app_canhbao.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiki.app_canhbao.entity.ChatLop;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.factorys.ChatLopFactory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatLopViewModule extends ViewModel {
    MutableLiveData<List<ChatLop>> mChatLopLiveData;
    MutableLiveData<ChatLop> mDSDsClassQLMutableLiveData;
    private MutableLiveData<List<User>> mUser =new MutableLiveData<>();
    Context context;
    ChatLopFactory factory;
    public ChatLopViewModule() {
        mChatLopLiveData=new MutableLiveData<>();
        MakeAPIUser();
    }
    public ChatLopViewModule(ChatLopFactory factory) {
        this.factory = factory;
        MakeAPIUser();
//        MakeAPI(null);
    }


    public ChatLopViewModule(ChatLop classQL) {
        this.mUser=new MutableLiveData<>();
        this.mDSDsClassQLMutableLiveData=new MutableLiveData<>();
        this.mDSDsClassQLMutableLiveData.setValue(classQL);
        mChatLopLiveData=new MutableLiveData<>();
    }
    private void MakeAPI(ChatLop clas){
        List<ChatLop> l=new ArrayList<>();
//        Log.e("chat lớp vm ","data: "+clas.toString());
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("ChatLop/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                l.clear();
                for (DataSnapshot snap: snapshot.getChildren()) {
                    ChatLop chatLop = snap.getValue(ChatLop.class);
                    if(chatLop!=null)
                        l.add(chatLop);
                }
                ganList(l);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        MakeAPIUser();
    }
    private void MakeAPIUser(){
//        Log.e("chap lớp vm: "," vô get user");
        List<User> lu= new ArrayList<>();
        lu.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference =database.getReference().child("Users/");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Log.e("-lay user -> ",""+snapshot.getChildren());
                lu.clear();
                for (DataSnapshot u : snapshot.getChildren()){
                    if(u!=null){
                        User utemp  =u.getValue(User.class);
                        lu.add(utemp);
                        //Log.e("-lay user -> ",""+utemp.get_Name());
                    }
                }
                ganListUser(lu);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void ganListUser(List<User> l ){
        mUser.setValue(l);
    }
    private void ganList(List<ChatLop> l){
        mChatLopLiveData.setValue(l);
    }

    public MutableLiveData<List<ChatLop>> getmChatLopLiveData() {
        return mChatLopLiveData;
    }

    public void setmChatLopLiveData(MutableLiveData<List<ChatLop>> mChatLopLiveData) {
        this.mChatLopLiveData = mChatLopLiveData;

    }

    public MutableLiveData<ChatLop> getmDSDsClassQLMutableLiveData() {
        return mDSDsClassQLMutableLiveData;
    }

    public void setmDSDsClassQLMutableLiveData(MutableLiveData<ChatLop> mDSDsClassQLMutableLiveData) {
        this.mDSDsClassQLMutableLiveData = mDSDsClassQLMutableLiveData;
        MakeAPI(mDSDsClassQLMutableLiveData.getValue());
    }

    public ChatLopFactory getFactory() {
        return factory;
    }

    public void setFactory(ChatLopFactory factory) {
        this.factory = factory;
        MakeAPI(factory.getClassQL());
    }

    public MutableLiveData<List<User>> getmUser() {
        return mUser;
    }

    public void setmUser(MutableLiveData<List<User>> mUser) {
        this.mUser = mUser;
    }
}
