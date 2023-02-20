package com.example.tiki.app_canhbao.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.MainActivityHomes;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.ChatLop;
import com.example.tiki.app_canhbao.entity.DSClassQL;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.factorys.ThongTinCVFactory;
import com.example.tiki.app_canhbao.viewmodel.ThongTinCVViewModule;
import com.example.tiki.app_canhbao.views.MainActivityChat;
import com.example.tiki.app_canhbao.views.MainActivityChatLop;

public class FragmentAboutGV extends Fragment {
    View fView;
    MainActivityHomes main;
    TextView tvNameCV, tvKhoavienCV, tvGmailCV, tvSdtCV;
    RelativeLayout rlChatCoVan, rlChatLop;
    //viewmodule
    ThongTinCVViewModule fCvViewModule;
    User u;
    boolean isChat;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fView= inflater.inflate(R.layout.fragment_about_g_v, container, false);
        main= (MainActivityHomes) getActivity();
        inits();
        return fView;

    }

    private void inits() {
        tvNameCV= fView.findViewById(R.id.tv_nameCV);
        tvKhoavienCV= fView.findViewById(R.id.tv_khoavienCV);
        tvGmailCV= fView.findViewById(R.id.tv_gmailCV);
        tvSdtCV= fView.findViewById(R.id.tv_sdtCV);
        rlChatCoVan = fView.findViewById(R.id.rl_ChatCoVan);
        rlChatLop= fView.findViewById(R.id.rl_ChatLop);
        getData();
        rlChatCoVan.setOnClickListener(v->{chatCB(u); });
        rlChatLop.setOnClickListener(v->{chatLop(u);});
        tvSdtCV.setOnClickListener(v->{CallGV();});
    }

    private void CallGV() {
        String sdt= tvSdtCV.getText().toString();
        if(sdt.equals("Chưa cập nhật")){
            Toast.makeText(main, "Chưa cập nhật", Toast.LENGTH_SHORT).show();
        }else {
//            Toast.makeText(main, "gọi cố vấn", Toast.LENGTH_SHORT).show();
            String tel="tel:"+sdt;
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(tel)));
        }
    }

    private void getData(){
        fCvViewModule=new ViewModelProvider(this).get(ThongTinCVViewModule.class);
        fCvViewModule.getmDSClassQL().observe(main, new Observer<DSClassQL>() {
            @Override
            public void onChanged(DSClassQL dsClassQL) {
                Log.e("fragment thong tin CV--> ",""+dsClassQL.get_nameClass());
            }
        });
        fCvViewModule.getmUserData().observe(main, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                //Log.e("fragment thong tin CV--> ",""+user.toString());
                if(user!=null){
                    u=user;
                    showTTCV(user);
                }
            }
        });
    }
    private void showTTCV(User cv){
        if(cv!=null) {
            Log.e("fragment ttcv: ","user: "+u.toString());
            isChat=true;
            tvNameCV.setText(cv.get_Name());
            if(cv.get_Name().equals("")){
                tvKhoavienCV.setText("Chưa cập nhật");
            }else {
                tvKhoavienCV.setText(cv.get_Khoa());
            }
            if(cv.get_sdt().equals("")){
                tvSdtCV.setText("Chưa cập nhật");
            }else {
                tvSdtCV.setText(cv.get_sdt());
            }
            tvGmailCV.setText(cv.get_Email());
        }
    }
    private void chatCB(User u2) {
        User u1= AuthAccount.getInstant().userAccount;
        if(isChat){
            Intent intent=new Intent(main, MainActivityChat.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("UserGui",u1);
            bundle.putSerializable("UseNhan",u2);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else{
            Toast.makeText(main, "Chưa có cố vấn quản lý", Toast.LENGTH_SHORT).show();
        }
    }
    private void chatLop(User u2){
        if(isChat){
            User u1= AuthAccount.getInstant().userAccount;
            if(u1.get_Class().equals("")){
                Toast.makeText(main, "Chưa cập nhật lớp", Toast.LENGTH_SHORT).show();
            }else if(!u1.get_Class().equals("")) {
                ChatLop chatLop = new ChatLop(u2.get_id(), u1.get_id(), u1.get_Class(), u1.get_Name(), "", "");
                Intent intent = new Intent(main, MainActivityChatLop.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("chatlop", chatLop);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        } else{
            Toast.makeText(main, "Chưa có cố vấn quản lý", Toast.LENGTH_SHORT).show();
        }
    }
}