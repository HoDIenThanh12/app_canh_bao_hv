package com.example.tiki.app_canhbao.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiki.app_canhbao.entity.User;

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<User> userLiveData;
    private MutableLiveData<Context> contextLiveData;
    private MutableLiveData<Boolean> isRegister;
    public RegisterViewModel() {
        this.userLiveData=new MutableLiveData<>();
        this.contextLiveData=new MutableLiveData<>();
        this.isRegister=new MutableLiveData<>();
    }

    public void ActionRegister(String n, String e, String p, String pe){
        if(!CheckInput(n,e,p,pe)){
            isRegister.setValue(false);
            return;
        }else{
            isRegister.setValue(true);
        }
    }
    private boolean CheckInput(String n, String e, String p, String pe ){

        if(e.equals("")||p.equals("")||n.equals("") || pe.equals("")){
            Toast.makeText(contextLiveData.getValue(), "Còn thông tin chưa nhập?", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            if(checkEmail(e)){
                if(!checkPass(p)  ){
                    Toast.makeText(contextLiveData.getValue(), "PassWord tối thiểu 6 ký tự?", Toast.LENGTH_SHORT).show();
                    return false;
                }else {
                    if (p.equals(pe)) {
                        return true;
                    } else {
                        Toast.makeText(contextLiveData.getValue(), "PassWord nhập lại chưa đúng?", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }
            else {
                Toast.makeText(contextLiveData.getValue(), "Email chưa đúng định dạng?", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }

    private boolean checkPass(String p) {
        return p.length() >= 6;
    }

    private boolean checkEmail(String e){
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (e.matches(emailPattern))
            return true;
        return false;
    }
    public MutableLiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public MutableLiveData<Context> getContextLiveData() {
        return contextLiveData;
    }

    public MutableLiveData<Boolean> getIsRegister() {
        return isRegister;
    }

}
