package com.example.tiki.app_canhbao.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.MainActivityHomes;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.viewmodel.LoginViewModule;
import com.example.tiki.databinding.ActivityMainLogInBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivityLogIn extends AppCompatActivity {
    private ActivityMainLogInBinding binding;
    private LoginViewModule loginViewModule;
    private AuthAccount account;
    private boolean isCheckSaveLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_log_in);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main_log_in);
//        loginViewModule=new LoginViewModule(this);
        loginViewModule= new ViewModelProvider(this).get(LoginViewModule.class);
        loginViewModule.setContext(this);

//        binding.edtEmail.setText("luongvq@tdmu.edu.vn");
//        binding.edtPass.setText("123456");
        binding.cirLoginButtonDangnhap.setOnClickListener(v->{
            actionLog(false);
        });
        binding.btnRegister.setOnClickListener(v->{
            actionRegister();
        });
        binding.rdsSaveLogin.setOnClickListener(v->{
            isCheckSaveLogin=!isCheckSaveLogin;
            if(isCheckSaveLogin){
                binding.rdsSaveLogin.setChecked(true);
                String e= binding.edtEmail.getText().toString().trim();
                String p= binding.edtPass.getText().toString().trim();
                CreateVlueKey(e, p);
            }else if(!isCheckSaveLogin){
                binding.rdsSaveLogin.setChecked(false);
                RemoveValueKey();
            }
            Log.e("main login "," change check "+isCheckSaveLogin);
        });
        getValuKey();
    }
    private void actionRegister() {
        account=AuthAccount.getInstant();
        account.Navigations(this, MainActivityRegister.class);
    }

    private void actionLog(boolean isLoad) {
        account=AuthAccount.getInstant();
        String e= binding.edtEmail.getText().toString().trim();
        String p= binding.edtPass.getText().toString().trim();
        if(isLoad){
            account.Log(MainActivityLogIn.this, e,p, MainActivityHomes.class);
        }else {
            loginViewModule.ActionLogin(e, p);
            loginViewModule.getIsCheck().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if (aBoolean) {
                        //login app
                        account.Log(MainActivityLogIn.this, e, p, MainActivityHomes.class);
                    }
                }
            });
        }
    }
    //test value-key
    private void CreateVlueKey(String email, String pass){
        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyKeyLogin",MODE_PRIVATE);

        // Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // Storing the key and its value as the data fetched from edittext
        myEdit.putString("email", email.trim());
        myEdit.putString("pass", pass.trim());

        // Once the changes have been made,
            // we need to commit to apply those changes made,
        // otherwise, it will throw an error
        myEdit.commit();
        Log.e("main login "," t???o key ");
    }
    //get value-key
    private void getValuKey(){
        // Retrieving the value using its keys the file name
        // must be same in both saving and retrieving the data
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MyKeyLogin", MODE_APPEND);

        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show

        String e = sh.getString("email", "");
        String p = sh.getString("pass", "");
        if(e.equals("") || p.equals("")){
            return;
        }else {
            binding.edtEmail.setText(e);
            binding.edtPass.setText(p);
            actionLog(true);
        }
        // We can then use the data
        Log.e("main login: "," value-key: "+e+" | "+p);
    }

    //remove value-key
    private void RemoveValueKey(){
        //remove
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MyKeyLogin", MODE_APPEND);
        sh.edit().clear().commit();
        Log.e("main login: "," remove value-key: ");
    }
    private void addDataMau() {
        List<User> l=new ArrayList<>();
        l.add(new User("Nguy???n Phi H??ng","1824801030033@student.tdmu.edu.vn","123456",2,"","D18PM01"));
        l.add(new User("Nguy???n Gia B???o","1824801030055@student.tdmu.edu.vn","123456",2,"","D18PM01"));
        l.add(new User("Hu???nh Nh???t C?????ng","1824801030057@student.tdmu.edu.vn","123456",2,"","D18PM01"));
        l.add(new User("V?? Thanh B??nh ","1824801030056@student.tdmu.edu.vn","123456",2,"","D18PM01"));
        l.add(new User("Nguy???n Tr?????ng Duy","1824801030058@student.tdmu.edu.vn","123456",2,"","D18PM01"));
        l.add(new User("L?? Th??nh ?????t","1824801030060@student.tdmu.edu.vn","123456",2,"","D18PM03"));
        l.add(new User("Phan Ph?????c ?????t ","1824801030046@student.tdmu.edu.vn","123456",2,"","D18PM03"));
        l.add(new User("Nguy???n Huy ????ng","1824801030040@student.tdmu.edu.vn","123456",2,"","D18PM03"));
        l.add(new User("????? M??? H???nh","1824801030033@studen.tdmut.edu.vn","123456",2,"","D18PM03"));
        l.add(new User("?????ng Ng??n H??o","1824801030063@student.tdmu.edu.vn","123456",2,"","D18PM03"));
        l.add(new User("Nguy???n Anh H??o","1824801030064@student.tdmu.edu.vn","123456",2,"","D18HT01"));
        l.add(new User("Tr???n Minh Hi???u","1824801030067@student.tdmu.edu.vn","123456",2,"","D18HT01"));
        l.add(new User("Ng???y Gia Huy ","1824801030050@student.tdmu.edu.vn","123456",2,"","D18HT01"));
        l.add(new User("L?? Minh H??ng","1824801030036@student.tdmu.edu.vn","123456",2,"","D18HT01"));
        l.add(new User("Tr???n Nh???t H??ng","1824801030071 @student.tdmu.edu.vn","123456",2,"","D18HT01"));
        l.add(new User("H??? Duy Khang ","1824801030010 @student.tdmu.edu.vn","123456",2,"","D16DT01"));
        l.add(new User("Nguy???n Ho??ng Duy Khang ","1824801030072@student.tdmu.edu.vn","123456",2,"","D16DT01"));
        l.add(new User("Nguy???n Ng???c Minh","1824801030015@student.tdmu.edu.vn","123456",2,"","D16DT01"));
        l.add(new User("Nguy???n Ho??i Nam","1824801030027@student.tdmu.edu.vn","123456",2,"","D16DT01"));
        l.add(new User("Nguy???n Tr???n H???ng Nam ","1824801030001@student.tdmu.edu.vn","123456",2,"","D16DT01"));
        l.add(new User("Ph???m Ph?? Ngh??a","1824801030043@student.tdmu.edu.vn","123456",2,"","D16PM01"));
        l.add(new User("????? Th??? H???ng Nhung","1824801030045@student.tdmu.edu.vn","123456",2,"","D16PM01"));
        l.add(new User("Nguy???n Nh?? Ph?????c ","1824801030008@student.tdmu.edu.vn","123456",2,"","D16PM01"));
        l.add(new User("Tr???n Minh Qu??n","1824801030037@student.tdmu.edu.vn","123456",2,"","D16PM01"));
        l.add(new User("Tr???n L?? T???n T??i","1824801030018@student.tdmu.edu.vn","123456",2,"","D16PM01"));
        l.add(new User("Nguy???n M???nh Ho??i Thanh ","1824801030049@student.tdmu.edu.vn","123456",2,"","D16PM02"));
        l.add(new User("Ph???m Ng???c Minh Ti???n","1824801030026@student.tdmu.edu.vn","123456",2,"","D16PM02"));
        l.add(new User("D????ng L?? Ph?????c Trung ","1824801030053@student.tdmu.edu.vn","123456",2,"","D16PM02"));
        l.add(new User("B??? Minh T??ng","1824801030004@student.tdmu.edu.vn","123456",2,"","D16PM02"));
        l.add(new User("B??? Minh T??ng","1824801030004@student.tdmu.edu.vn","123456",2,"","D16PM02"));
        l.add(new User(" Nguy???n H???u Thanh T??ng ","1824801030007@student.tdmu.edu.vn","123456",2,"","D16PM02"));
        l.add(new User("V?? Qu???c L????ng","luongvq@tdmu.edu.vn","123456",1,"",""));
        l.add(new User("Tr???nh Qu???c Thanh","thanhtq@tdmu.edu.vn","123456",1,"",""));
        l.add(new User("Tr???n V??n Nam","namtv@tdmu.edu.vn","123456",1,"",""));
        l.add(new User("Tr???n V??n T??i","taitv@tdmu.edu.vn","123456",1,"",""));
        l.add(new User("H??? ?????c H??ng","hunghd@tdmu.edu.vn","123456",1,"",""));

        for (int i=0;i<l.size();i++){
            account=AuthAccount.getInstant();
            account.Register(this, l.get(i), MainActivityLogIn.class);
        }
    }


}