package com.example.tiki.app_canhbao.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.DSClassQL;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.factorys.ThongTinCVFactory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThongTinCVViewModule extends ViewModel {
    ThongTinCVFactory cvFactory;
    MutableLiveData<User>  mUserData;
    MutableLiveData<DSClassQL> mDSClassQL=new MutableLiveData<>();
    Context context;

    public ThongTinCVViewModule() {
        mUserData=new MutableLiveData<>();
        CheckLop();
    }

    public ThongTinCVViewModule(Context context) {
        this.context = context;
        CheckLop();
    }

    public ThongTinCVViewModule(ThongTinCVFactory cvFactory) {
        this.cvFactory = cvFactory;
        CheckLop();
    }

    public ThongTinCVViewModule(DSClassQL classQL) {
        mDSClassQL.setValue(classQL);
        mUserData=new MutableLiveData<>();
        CheckLop();
    }
    private void CheckLop(){
        String nameClas= AuthAccount.getInstant().userAccount.get_Class();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref =database.getReference().child("QlLops/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot!=null){
                    for (DataSnapshot snap: snapshot.getChildren()){
                        DSClassQL clas=snap.getValue(DSClassQL.class);
                        //Log.e("thong tin viewmodule: "," "+snap.getKey());
                        if(clas.get_nameClass().equals(nameClas)){
                            tempClass(clas);
                            Log.e("thong tin viewmodule: "," so sánh: "+clas.get_nameClass()
                            +"  |   class sv: "+nameClas);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void tempClass(DSClassQL clas){
        mDSClassQL.setValue(clas);
        if(mDSClassQL!=null){
            getAboutUser(mDSClassQL.getValue());
        }
    }
    private void getAboutUser(DSClassQL clas){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref =database.getReference().child("Users/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot!=null){
                    for (DataSnapshot snap: snapshot.getChildren()){
                        User u=snap.getValue(User.class);
                        //Log.e("thong tin viewmodule: "," u "+snap.getKey());
                        if(clas.get_idGV().equals(u.get_id())){
                            mUserData.setValue(u);
                            //Log.e("thong tin viewmodule: "," user"+u.get_Name());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public ThongTinCVFactory getCvFactory(DSClassQL clas) {
        return cvFactory;
    }

    public void setCvFactory(ThongTinCVFactory cvFactory) {
        getAboutUser(cvFactory.getClassQL());
        this.cvFactory = cvFactory;
    }

    public MutableLiveData<User> getmUserData() {
        return mUserData;
    }

    public void setmUserData(MutableLiveData<User> mUserData) {
        this.mUserData = mUserData;
    }

    public MutableLiveData<DSClassQL> getmDSClassQL() {
        return mDSClassQL;
    }

    public void setmDSClassQL(MutableLiveData<DSClassQL> mDSClassQL) {
        this.mDSClassQL = mDSClassQL;
    }
}
