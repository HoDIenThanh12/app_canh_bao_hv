package com.example.tiki.app_canhbao.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.ClassSV;
import com.example.tiki.app_canhbao.entity.ClassSVNotification;
import com.example.tiki.app_canhbao.entity.DSClassQL;
import com.example.tiki.app_canhbao.entity.ThongBao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationClassSVViewModule extends ViewModel {
    MutableLiveData<DSClassQL> mMutableLiveDataClassSV=new MutableLiveData<>();
    MutableLiveData<List<ClassSVNotification>> mMutableLiveDataNotifi;
    Context context;
    ClassSVFactory factory;

    public NotificationClassSVViewModule() {
        super();
        mMutableLiveDataClassSV=new MutableLiveData<>();
        mMutableLiveDataNotifi=new MutableLiveData<>();
    }

    public NotificationClassSVViewModule(ClassSVFactory factory) {
        this.factory = factory;
        getData(null);
    }

    public NotificationClassSVViewModule(DSClassQL clas) {
        mMutableLiveDataClassSV.setValue(clas);
       mMutableLiveDataNotifi= new MutableLiveData<>();
    }

    public void getData(DSClassQL clas){
        String idU= AuthAccount.getInstant().userAccount.get_id();
        if(clas!=null)
        Log.e("viewmodule noti: đường dẫn: ","--> "+clas.toString());
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        List<ClassSVNotification> l=new ArrayList<>();
        DatabaseReference ref = database.getReference().child("ThongBao/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                l.clear();
                if(snapshot!=null){
                    for (DataSnapshot snap :snapshot.getChildren()) {
                        if(snap!=null){

                            //Log.e("đường dẫn: ","--> "+snap);
                            ClassSVNotification tb =snap.getValue(ClassSVNotification.class);
                            Log.d("viewmodule noti: ","dữ liệu quét--> "+tb.get_nameClass());
                            if(tb.get_nameClass().equals(clas.get_nameClass())){
                                l.add(tb);
                                Log.e("viewmodule noti: "," có noti --> "+snap);
                            }
                        }
                    }
                    mMutableLiveDataNotifi.setValue(l);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public ClassSVFactory getFactory() {
        return factory;
    }

    public void setFactory(ClassSVFactory factory) {
        this.factory = factory;
        Log.e("viewmodule noti: ","setfactory: "+factory.getClasSV().toString());
        getData(factory.getClasSV());
    }

    public MutableLiveData<DSClassQL> getmMutableLiveDataClassSV() {
        return mMutableLiveDataClassSV;
    }
    public void setmMutableLiveDataClassSV(MutableLiveData<DSClassQL> mMutableLiveDataClassSV) {
        this.mMutableLiveDataClassSV = mMutableLiveDataClassSV;
    }
    public MutableLiveData<List<ClassSVNotification>> getmMutableLiveDataNotifi() {
        return mMutableLiveDataNotifi;
    }
    public void setmMutableLiveDataNotifi(MutableLiveData<List<ClassSVNotification>> mMutableLiveDataNotifi) {
        this.mMutableLiveDataNotifi = mMutableLiveDataNotifi;
    }
}
