package com.example.tiki.app_canhbao.viewmodel;

import static com.example.tiki.app_canhbao.constants.INSTANT.PATH_DTAREATIME_USER;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.ClassSV;
import com.example.tiki.app_canhbao.entity.DSClassQL;
import com.example.tiki.app_canhbao.entity.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassSVViewModule extends ViewModel {
    private MutableLiveData<String> mutableLiveData;
    private MutableLiveData<List<DSClassQL>> mClassSV=new MutableLiveData<>();
    private MutableLiveData<List<User>> mUser= new MutableLiveData<>();
    private Context context;
    List<DSClassQL> lClas=new ArrayList<>();

    public ClassSVViewModule(Context context) {
        this.context = context;
        //getData();
    }
    public ClassSVViewModule(ClassSV classv) {
        mutableLiveData=new MutableLiveData<>();
        getData();
        //getUser();
    }
    public ClassSVViewModule() {
        mutableLiveData=new MutableLiveData<>();
        getData();
        getUser();
    }

    public void getData(){
        lClas.clear();
        String idU= AuthAccount.getInstant().userAccount.get_id();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference =database.getReference().child("QlLops/");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.e("-lay-> ",""+snapshot.getValue().getClass());
                lClas.clear();
                for (DataSnapshot k : snapshot.getChildren()){
                    mutableLiveData.setValue(k.toString());
                    //Log.e("-lay-> ",""+k.getKey());
                    if(k!=null){
                        DSClassQL c =k.getValue(DSClassQL.class);
                        lClas.add(c);
                    }
                }
                gan(lClas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    //get danh sách user
    private void getUser(){
        List<User> lu= new ArrayList<>();
        lu.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference =database.getReference().child("Users/");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               //Log.e("-lay user -> ",""+snapshot.getChildren());
                lu.clear();
                for (DataSnapshot u : snapshot.getChildren()){
                    if(u!=null){
                        User utemp  =u.getValue(User.class);
                        lu.add(utemp);
                        //Log.e("-lay user -> ",""+utemp.get_Name());
                    }
                }
                ganUser(lu);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    //gán list class quá list mutableLiveData
    private void gan(List<DSClassQL> l){
//        Log.e("độ dài list classSV: ",""+l.size());
        mClassSV.setValue(l);
    }
    //gán list user quá list mutableLiveData
    private void ganUser(List<User> l){
//        Log.e("độ dài list classSV: ",""+l.size());
        mUser.setValue(l);
    }
    public MutableLiveData<String> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setMutableLiveData(MutableLiveData<String> mutableLiveData) {
        this.mutableLiveData = mutableLiveData;
    }

    public MutableLiveData<List<DSClassQL>> getmClassSV() {
        return mClassSV;
    }

    public void setmClassSV(MutableLiveData<List<DSClassQL>> mClassSV) {
        this.mClassSV = mClassSV;
    }

    public MutableLiveData<List<User>> getmUser() {
        return mUser;
    }

    public void setmUser(MutableLiveData<List<User>> mUser) {
        this.mUser = mUser;
    }
}
