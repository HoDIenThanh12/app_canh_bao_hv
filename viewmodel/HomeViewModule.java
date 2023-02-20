package com.example.tiki.app_canhbao.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.ClassSV;
import com.example.tiki.app_canhbao.entity.DSClassQL;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeViewModule extends ViewModel {
    MutableLiveData<List<DSClassQL>> mListMutableClassSVHome;
    List<DSClassQL> lClassSVHome=new ArrayList<>();
    Context context;
    public HomeViewModule(MutableLiveData<List<DSClassQL>> mListMutableClassSVHome) {
        this.mListMutableClassSVHome = mListMutableClassSVHome;
    }

    public HomeViewModule(Context context) {
        this.context = context;
        getFirebaseClassSVHome();
        //MakeAPI();
    }

    public HomeViewModule() {
        mListMutableClassSVHome= new MutableLiveData<>();
        getFirebaseClassSVHome();
        //MakeAPI();
    }

    private void getFirebaseClassSVHome(){
        String idU = AuthAccount.getInstant().userAccount.get_id();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref =database.getReference().child("QlLops/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lClassSVHome.clear();
                if(snapshot!=null){
                    for (DataSnapshot k : snapshot.getChildren()){
                        Log.e("-lay-> ",""+k.getKey());
                        if(k!=null){
                            DSClassQL c =k.getValue(DSClassQL.class);
                            if(c.get_idGV().equals(idU))
                                lClassSVHome.add(c);
                        }
                    }
                    ganList(lClassSVHome);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
//    public void MakeAPI(){
//        //token:
//        String tk =AuthAccount.getInstant().userAccount.get_Token();
//        NoticationClass notiClas=new NoticationClass(tk,
//                "23423423","jsdfhjsd");
//        RetrofitAPI retrofitAPI = RetrofitAPI.getInstance();
//        Call<NoticationClass> call = retrofitAPI.getApiNotification().PostCategory(notiClas.getToken(),notiClas.getNameGV(),notiClas.getNoidung());
//        call.enqueue(new Callback<NoticationClass>() {
//            @Override
//            public void onResponse(Call<NoticationClass> call, retrofit2.Response<NoticationClass> response) {
//                NoticationClass r =response.body();
//            }
//
//            @Override
//            public void onFailure(Call<NoticationClass> call, Throwable t) {
//
//            }
//        });
//    }
    private void ganList(List<DSClassQL> l){
        mListMutableClassSVHome.setValue(l);
    }
    public MutableLiveData<List<DSClassQL>> getmListMutableClassSVHome() {
        return mListMutableClassSVHome;
    }

    public void setmListMutableClassSVHome(MutableLiveData<List<DSClassQL>> mListMutableClassSVHome) {
        this.mListMutableClassSVHome = mListMutableClassSVHome;
    }
}
