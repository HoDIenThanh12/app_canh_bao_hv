package com.example.tiki.app_canhbao.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiki.app_canhbao.entity.Message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModule extends ViewModel {
    MutableLiveData<List<Message>> mMes;
    Context context;
    List<Message> list=new ArrayList<>();
    public ChatViewModule(Context context) {
        this.context = context;
        mMes=new MutableLiveData<>();
        MakeAPI();
    }

    public ChatViewModule() {
        mMes=new MutableLiveData<>();
        MakeAPI();
    }

    public ChatViewModule(MutableLiveData<List<Message>> mMes) {
        this.mMes = mMes;
    }

    private void MakeAPI(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref= database.getReference().child("Chats/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                if(snapshot!=null){
//                    Log.d("chat viewmodule: "," data: "+snapshot.getChildren());
                    for (DataSnapshot data: snapshot.getChildren()){
                        Message mes = data.getValue(Message.class);
                        list.add(mes);
                    }
                    gan(list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void gan(List<Message> l){
        mMes.setValue(l);
    }
    public MutableLiveData<List<Message>> getmMes() {
        return mMes;
    }

    public void setmMes(MutableLiveData<List<Message>> mMes) {
        this.mMes = mMes;
    }
}
