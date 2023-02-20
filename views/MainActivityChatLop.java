package com.example.tiki.app_canhbao.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.adappers.ListChatLopAdapper;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.backend.ChatBE;
import com.example.tiki.app_canhbao.entity.ChatLop;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.factorys.ChatLopFactory;
import com.example.tiki.app_canhbao.viewmodel.ChatLopViewModule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivityChatLop extends AppCompatActivity {
    RecyclerView rcvChatLop;
    TextView nameChatClass;
    ImageView imgSendChatLop;
    EditText edtContent;
    //phần view module
    ListChatLopAdapper chatLopAdapper;
    ChatLopViewModule chatLopViewModule;
    ChatLopFactory factory;
    List<ChatLop> lChatLop;
    List<User> listUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat_lop);
        inits();
    }

    private void inits() {
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //hứng dữ liệu
        Intent intent=getIntent();
        Bundle bundle =intent.getExtras();
        ChatLop chatLop= (ChatLop) bundle.getSerializable("chatlop");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcvChatLop=findViewById(R.id.rcv_chatLop);
        rcvChatLop.setLayoutManager(manager);
        nameChatClass=findViewById(R.id.tv_nameChatClass);
        edtContent=findViewById(R.id.edt_contentChatLop);
        imgSendChatLop=findViewById(R.id.img_sendChatLop);
        imgSendChatLop.setOnClickListener(v->{action(chatLop);});
        //phần viewmodule
        lChatLop=new ArrayList<>();
        listUser=new ArrayList<>();
        chatLopAdapper=new ListChatLopAdapper(this);
        factory=new ChatLopFactory(chatLop);
        chatLopViewModule=new ViewModelProvider(this, factory).get(ChatLopViewModule.class);
        getAllChatLop(chatLop);
        getAllUser(chatLop);
    }



    private void action(ChatLop chatLop) {
        if(edtContent.getText().toString().isEmpty()){
            Toast.makeText(MainActivityChatLop.this, "Chưa nhập nội dung", Toast.LENGTH_SHORT).show();
        }
        else if(true){
            if(edtContent.equals("")){
                Toast.makeText(MainActivityChatLop.this, "Chưa nhập nội dung", Toast.LENGTH_SHORT).show();
                return;
            }
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            String dateFoemat= format.format(date);
            String idU = AuthAccount.getInstant().userAccount.get_id();
            int cate = AuthAccount.getInstant().userAccount.get_category();
            String token= AuthAccount.getInstant().userAccount.get_Token();
            chatLop.set_ngaygio(dateFoemat);
            chatLop.set_idNguoiGuiNhan(idU);
            if(cate==1)
                chatLop.set_idGV(idU);
            else if(cate==2){
                chatLop.set_idGV(chatLop.get_idGV());
            }
            chatLop.set_noiDung(edtContent.getText().toString().trim());
            ChatBE.chatLop(chatLop);
            ChatBE.sendAllUser(listUser, chatLop);
            edtContent.setText("");
//            Toast.makeText(MainActivityChatLop.this, "size: "+listUser.size()+" | "+dateFoemat, Toast.LENGTH_SHORT).show();
        }

    }

    public void getAllChatLop(ChatLop chatLop){

        chatLopViewModule.getmDSDsClassQLMutableLiveData().observe(this, new Observer<ChatLop>() {
            @Override
            public void onChanged(ChatLop chatLop) {
                if(chatLop!=null) {
                    chatLopViewModule.setFactory(factory);
                    nameChatClass.setText("Nhóm lớp "+chatLop.get_nameClass());
//                    Log.e("chat lớp ","data factory: "+chatLop.toString());
                }
            }
        });
        //tạo list chat của lớp
        List<ChatLop> listChatLop=new ArrayList<>();
        chatLopViewModule.getmChatLopLiveData().observe(this, new Observer<List<ChatLop>>() {
            @Override
            public void onChanged(List<ChatLop> chatLops) {
                if(chatLop!=null){
                    listChatLop.clear();
                    listChatLop.clear();
                    for (ChatLop clop: chatLops) {
                        if(clop.get_nameClass().equals(chatLop.get_nameClass())
                        && clop.get_idNguoiGuiNhan().equals(chatLop.get_idNguoiGuiNhan())
                        || clop.get_nameClass().equals(chatLop.get_nameClass())
                                && clop.get_idGV().equals(chatLop.get_idGV())){
                            listChatLop.add(clop);
                        }
                    }
                    lChatLop.addAll(listChatLop);
                }
                setAllChatLop(listChatLop);
            }
        });
//        getAllUser(chatLop);
        edtContent.setOnClickListener(v->{checkKeyBoard();});
    }
    //getAll user để gửi thông  báo
    private void getAllUser(ChatLop chatLop) {
        int cate = AuthAccount.getInstant().userAccount.get_category();
        String idU = AuthAccount.getInstant().userAccount.get_id();
        Log.e("get all list user", " vm ");
        chatLopViewModule.getmUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                listUser.clear();
                for (int i = 0; i < users.size(); i++) {
                    User u = users.get(i);
                    if(cate==2){
                        if(u.get_Class().equals(chatLop.get_nameClass()) && !u.get_id().equals(idU)
                        || u.get_id().equals(chatLop.get_idGV())  ){
                            listUser.add(u);
                        }
                    }
                    if(cate==1){
                        if(u.get_Class().equals(chatLop.get_nameClass())){
                            listUser.add(u);
                        }
                    }
                }
            }
        });
    }
    //set tất cả đoạn chat lên rcview
    private void setAllChatLop(List<ChatLop> l ){
        chatLopAdapper.setlMessageList(l);
        rcvChatLop.setAdapter(chatLopAdapper);
        rcvChatLop.scrollToPosition(l.size()-1);
    }
    //kiểm tra bàn phím ẩn hiện
    private void checkKeyBoard(){
        final View activityRoot =  findViewById(R.id.activityRoot);
        activityRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                activityRoot.getWindowVisibleDisplayFrame(rect);

                int heightDiff = activityRoot.getRootView().getHeight() - rect.height();
                //kiểm tra bàn phím đang hiện ahy ẩn
                if(heightDiff > 0.25*activityRoot.getRootView().getHeight()){
                    //nếu là hiệ
                    if(lChatLop.size() > 0){
                        setAllChatLop(lChatLop);
                        activityRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        });
    }
    //nút back trên cùng
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
}