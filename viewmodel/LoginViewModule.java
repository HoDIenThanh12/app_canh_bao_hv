package com.example.tiki.app_canhbao.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiki.BR;

public class LoginViewModule extends ViewModel {
    private MutableLiveData<String> _gmail;
    private MutableLiveData<String>  _pass;
    private MutableLiveData<Context> _coContext;
    private MutableLiveData<Boolean> isCheck ;

    public LoginViewModule() {
        Log.e("viewmodule ","khởi tọa với rỗng");
        this.isCheck=new MutableLiveData<>();
        this._gmail=new MutableLiveData<>();
        this._pass=new MutableLiveData<>();
        this._coContext=new MutableLiveData<>();
    }

    public void ActionLogin(String e, String p){
        Log.e("VIewmodule "," actiontlogin");
        _gmail.setValue(e);
        _pass.setValue(p);
        Log.e("VIewmodule "," sau gán "+_gmail.getValue() +" | "+_pass.getValue());
        if(CheckInput(_gmail, _pass)){
            isCheck.setValue(true);
        }else {
            isCheck.setValue(false);
        }
    }
    public boolean CheckInput(MutableLiveData<String> e, MutableLiveData<String> p){
        Log.e("VIewmodule "," actiontCheck");
        if(e.getValue().toString().equals("") || p.getValue().toString().equals("") ){
//            Log.e("VIewmodule ","có mục chưa nhập");
            Toast.makeText(_coContext.getValue(), "Còn ô chưa nhập", Toast.LENGTH_SHORT).show();
        }else if(checkEmail(e.getValue())){
//            Log.e("VIewmodule "," actiontCheck đúng định dạng email");
//            Toast.makeText(_coContext.getValue(), "Đăng nhập", Toast.LENGTH_SHORT).show();
            return true;
        } else {
//            Log.e("VIewmodule "," actiontCheck sai định dạng email");
//            Toast.makeText(_coContext.getValue(), "Sai định dạng gmail", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }
    private boolean checkEmail(String e){
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (e.matches(emailPattern))
            return true;
        return false;
    }
    public void setContext(Context context){
        _coContext.setValue(context);
    }
    public MutableLiveData<String> get_gmail() {
        return _gmail;
    }

    public MutableLiveData<String> get_pass() {
        return _pass;
    }

    public MutableLiveData<Boolean> getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(MutableLiveData<Boolean> isCheck) {
        this.isCheck = isCheck;
    }

    public MutableLiveData<Context> get_coContext() {
        return _coContext;
    }
}
