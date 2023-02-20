package com.example.tiki.app_canhbao.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.viewmodel.RegisterViewModel;
import com.example.tiki.databinding.ActivityMainRegisterBinding;

public class MainActivityRegister extends AppCompatActivity {
    private ActivityMainRegisterBinding binding;
    private AuthAccount account;
    RegisterViewModel registerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_register);
        //1:User người dùng, 1:SV sinh viên, 2:GV giảng viên.
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main_register );
        registerViewModel=new ViewModelProvider(this).get(RegisterViewModel.class);
        registerViewModel.getContextLiveData().setValue(this);
        action();
    }
    private void action() {
        account=AuthAccount.getInstant();
        binding.cirRegisterButtonDK.setOnClickListener(v->{
            RegisterUser();
        });
        binding.btnimgRegister.setOnClickListener(v->{resumLog();});
        binding.tvBackL.setOnClickListener(v->{resumLog();});
    }
    private void resumLog() {
        finish();
    }

    private void RegisterUser(){
        String e=binding.edtEmail.getText().toString().trim();
        String p=binding.edtPass.getText().toString().trim();
        String n=binding.edtName.getText().toString().trim();
        String pe=binding.edtPassEnter.getText().toString().trim();

        int gt;
        int idRB=binding.grCategory.getCheckedRadioButtonId();
//        if(idRB == binding.rdsSV.getId()){
//            gt=1;
//        }
//        else{
//            gt=2;
//        }
        gt = idRB == binding.rdsSV.getId() ?2:1;
        registerViewModel.ActionRegister(n,e,p,pe);
        registerViewModel.getIsRegister().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    regis(n,e,p,pe, gt);
                    Toast.makeText(MainActivityRegister.this, "Tạo mới thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        if(!CheckInput(n,e,p,pe)){
//            return;
//        }
//        u=new User(n,e,p,gt,"");
//        //u=new User();
//        account.Register(this, u, MainActivityLogIn.class);
    }
    public void regis(String n, String e, String p, String pe, int gt){
        User u ;
        u=new User(n,e,p,gt,"");
        //u=new User();
        account.Register(MainActivityRegister.this, u, MainActivityLogIn.class);
        Log.e("main register ","user "+u.toString());
    }
}