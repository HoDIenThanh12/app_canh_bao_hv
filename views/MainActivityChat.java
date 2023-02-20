package com.example.tiki.app_canhbao.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.adappers.MessageAdaper;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.backend.ChatBE;
import com.example.tiki.app_canhbao.entity.Message;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.fragments.FragmentQuyCheCVHT;
import com.example.tiki.app_canhbao.fragments.FragmentSoTaySinhVien;
import com.example.tiki.app_canhbao.viewmodel.ChatViewModule;
import com.example.tiki.app_canhbao.viewmodel.MessageViewModule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivityChat extends AppCompatActivity {
    RecyclerView rcvChat;
    EditText edtCantentChat;
    Button btnSend;
    ImageView imgSendChat;
    MessageAdaper mMessageAdaper;
    ChatViewModule viewModule;
    List<Message> mLisstMes;

    TextView tvnameNguoiChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);
        inits();

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
    private void inits() {
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
//        tvnameNguoiChat=findViewById(R.id.tv_nameNguoiChat);
        rcvChat=findViewById(R.id.rvc_chat);
        edtCantentChat=findViewById(R.id.edt_contentChat);
//        btnSend=findViewById(R.id.btn_sendChat);
        imgSendChat=findViewById(R.id.img_sendChat);
        mLisstMes=new ArrayList<>();

        LinearLayoutManager manager=new LinearLayoutManager(this);
        rcvChat.setLayoutManager(manager);

        Bundle bundle = getIntent().getExtras();
        User uGui  = (User) bundle.getSerializable("UserGui");
        User uNhan  = (User) bundle.getSerializable("UseNhan");


        mMessageAdaper=new MessageAdaper(this, uNhan);
        viewModule=new ViewModelProvider(this).get(ChatViewModule.class);


        rcvChat.setAdapter(mMessageAdaper);

        imgSendChat.setOnClickListener(v->{
            sendChat(uGui, uNhan);
        });
        String idU= AuthAccount.getInstant().userAccount.get_id();
        if(uGui.get_id().equals(idU)){
           actionBar.setTitle(uNhan.get_Name());
        }
        else {
            actionBar.setTitle(uGui.get_Name());
        }
//        assert getSupportActionBar() != null;   //null check
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getAllData(uGui, uNhan);
    }
    private void getAllData(User uGui, User uNhan){
//        Log.e("main chat: "," data ");

        viewModule.getmMes().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                mLisstMes.clear();
                if(messages!=null){
//                    Log.e("main chat: "," data "+messages.toString());
                    for (Message m: messages) {
                        if(m.get_idNguoiGui().equals(uGui.get_id())
                        && uNhan.get_id().equals(m.get_idNguoiNhan())){
                            mLisstMes.add(m);
                        }
                        else if(m.get_idNguoiGui().equals(uNhan.get_id())
                                && uGui.get_id().equals(m.get_idNguoiNhan())){
                            mLisstMes.add(m);
                        }
                    }
                    loadChat(mLisstMes);
                }
            }
        });
    }
    private void sendChat(User uGui, User uNhan) {
        String mes=edtCantentChat.getText().toString().trim();
        if(mes==null || TextUtils.isEmpty(mes)){
            return;
        }
        else{
            Toast.makeText(MainActivityChat.this, "Chat", Toast.LENGTH_SHORT).show();
            Date date=new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy ");
            String ngay= simpleDateFormat.format(date)+"";
            Message m = new Message(uGui.get_id(), uGui.get_Name(),uGui.get_Token(),
                                        uNhan.get_id(),uNhan.get_Name(),uNhan.get_Token(),
                                        mes,ngay);
            Message m1 =new Message("","","",
                    "","","","","");

            ChatBE.saveChat(m);
            ChatBE.sendNotifi(m);
            Log.d("main chat: "," nội dung: "+mes);
        }
    }
    private void loadChat(List<Message> l){
        mMessageAdaper.setlM(l);
        rcvChat.setAdapter(mMessageAdaper);
        rcvChat.scrollToPosition(mLisstMes.size()-1);
        edtCantentChat.setText("");
    }
}