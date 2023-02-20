package com.example.tiki.app_canhbao.viewmodel;

import android.content.Context;

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

public class QuanLyChatViewModule extends ViewModel {

    MutableLiveData<List<Message>> mListMes;
    Context context;
    List<Message> list=new ArrayList<>();

    public QuanLyChatViewModule() {
        mListMes=new MutableLiveData<>();
        makeAPI();
    }

    public QuanLyChatViewModule(Context context) {
        this.context = context;
        mListMes=new MutableLiveData<>();
        makeAPI();
    }

    public QuanLyChatViewModule(MutableLiveData<List<Message>> mListMes) {
        this.mListMes = mListMes;
    }
    private void makeAPI(){
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
        mListMes.setValue(l);
    }
    public MutableLiveData<List<Message>> getmListMes() {
        return mListMes;
    }

    public void setmListMes(MutableLiveData<List<Message>> mListMes) {
        this.mListMes = mListMes;
    }
}
