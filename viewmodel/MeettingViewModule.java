package com.example.tiki.app_canhbao.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.Meettings;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MeettingViewModule extends ViewModel {
    private Context context;
    private MutableLiveData<List<Meettings>> mListMeetting;
    private List<Meettings> lMeting;

    public MeettingViewModule() {
        this.lMeting=new ArrayList<>();
        this.mListMeetting=new MutableLiveData<>();
        getAllMeting();
    }

    public MeettingViewModule(Context context, MutableLiveData<List<Meettings>> mListMeetting) {
        this.context = context;
        this.mListMeetting = mListMeetting;
    }

    public MeettingViewModule(Context context) {
        lMeting=new ArrayList<>();
        this.context = context;
    }

    public void setmListMeetting(MutableLiveData<List<Meettings>> mListMeetting) {
        this.mListMeetting = mListMeetting;
        notify();
    }
    //xử lý logic
    private void getAllMeting(){
        String idU=AuthAccount.getInstant().userAccount.get_id();
        Log.e("có data--> ",""+idU);
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Users/"+idU+"/Meetting/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Log.e("có data--> ",""+snapshot);
                if(snapshot!=null){
                    lMeting.clear();
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        Meettings m = dataSnapshot.getValue(Meettings.class);
                        if(m!=null){

                            lMeting.add(m);
                            Log.e("danh sách sau add--> ",""+lMeting.size());
                        }
                    }
                    getListM(lMeting);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("load data--> ","thất bại");
            }
        });
    }
    private void getListM(List<Meettings> l){
        mListMeetting.setValue(l);
    }
    ///////////////

    public MutableLiveData<List<Meettings>> getmListMeetting() {
        return mListMeetting;
    }
}
