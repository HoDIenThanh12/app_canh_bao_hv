package com.example.tiki.app_canhbao.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.adappers.QuanLyChatAdaper;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.Message;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.interfaces.ActionQuanLyChat;
import com.example.tiki.app_canhbao.viewmodel.QuanLyChatViewModule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivityQuanLyChat extends AppCompatActivity {
    RecyclerView recQuanLyChat;
    //Viewmodule
    QuanLyChatAdaper mAdaper;
    QuanLyChatViewModule mViewModule;
    List<Message > mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quan_ly_chat);
        inits();
    }

    private void inits() {
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        mList=new ArrayList<>();
        recQuanLyChat=findViewById(R.id.rcv_quanlyChat);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recQuanLyChat.setLayoutManager(manager);
        mAdaper=new QuanLyChatAdaper(this, new ActionQuanLyChat() {
            @Override
            public void chats(Message mes) {
                chuyPage(mes);
            }
        });
        mViewModule=new ViewModelProvider(this).get(QuanLyChatViewModule.class);
        getAllData();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
//                Log.e("main chat: ","qauy lại");
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void getAllData() {
        mViewModule.getmListMes().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                for (int i=0;i<messages.size();i++) {
//                    Log.e("main ql chat: ", "data " + messages.get(i));
                }
                mList.clear();
                String idU = AuthAccount.getInstant().userAccount.get_id();
                for (int i=(messages.size()-1);i>= 0;i--){
                    if(mList.size()<1){
                        if(messages.get(i).get_idNguoiGui().equals(idU)
                                || messages.get(i).get_idNguoiNhan().equals(idU)) {
                            mList.add(messages.get(i));
                        }
                    }
                    else {
                        boolean isNull=true;
                       for (int j = 0;j<mList.size();j++){
                           if(mList.get(j).get_idNguoiNhan().equals(messages.get(i).get_idNguoiNhan())
                           && idU.equals(messages.get(i).get_idNguoiGui())){
                               isNull=false;
                           }
                           if(mList.get(j).get_idNguoiNhan().equals(messages.get(i).get_idNguoiGui())
                           && idU.equals(messages.get(i).get_idNguoiNhan())){
                               isNull=false;
                           }
                           if(mList.get(j).get_idNguoiGui().equals(messages.get(i).get_idNguoiNhan())
                           && idU.equals(messages.get(i).get_idNguoiGui())){
                               isNull=false;
                           }
                           if(mList.get(j).get_idNguoiGui().equals(messages.get(i).get_idNguoiGui())
                           && idU.equals(messages.get(i).get_idNguoiNhan())){
                               isNull=false;
                           }
                       }
                       if(isNull){
                           if(messages.get(i).get_idNguoiGui().equals(idU)
                           || messages.get(i).get_idNguoiNhan().equals(idU)) {
                               mList.add(messages.get(i));
                           }
                       }
                    }
                }
                showAllData(mList);
            }
        });
    }
    private void showAllData(List<Message> l){
        mAdaper.setlMes(l);
        recQuanLyChat.setAdapter(mAdaper);
    }
    private void chuyPage(Message m){
        Date day= new Date();

        User u1= AuthAccount.getInstant().userAccount;
        boolean isNhan=false;
        User u2;
        if(m.get_idNguoiGui().equals(u1.get_id())){
            u2 = new User(m.get_tenNguoiNhan(),"","",0,m.get_tokenNguoiNhan());
            u2.set_id(m.get_idNguoiNhan());
        }
        else{
            u2= new User(m.get_tenNguoiGui(),"","",0,m.get_tokenNguoiGui());
            u2.set_id(m.get_idNguoiGui());
        }
        Intent intent=new Intent(MainActivityQuanLyChat.this, MainActivityChat.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("UserGui",u1);
        bundle.putSerializable("UseNhan",u2);
        intent.putExtras(bundle);
        startActivity(intent);
        Log.e("main ql chat","u1: "+u1.toString());
        Log.e("main ql chat","u2: "+u2.toString());
    }
}