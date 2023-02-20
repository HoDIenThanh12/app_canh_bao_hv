package com.example.tiki.app_canhbao.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiki.app_canhbao.entity.ClassSV;
import com.example.tiki.app_canhbao.entity.ClassSVNotification;
import com.example.tiki.app_canhbao.entity.DSClassQL;
import com.example.tiki.app_canhbao.entity.Meettings;
import com.example.tiki.app_canhbao.factorys.CuocHopfactory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CuocHopViewModule extends ViewModel {
    private MutableLiveData<DSClassQL> mClass=new MutableLiveData<>();
    private MutableLiveData<List<Meettings>> mListCuocHop;
    private Context context;
    CuocHopfactory CHfactory;

    public CuocHopViewModule() {
        mListCuocHop=new MutableLiveData<>();
    }

    public CuocHopViewModule(CuocHopfactory CHfactory) {
        this.CHfactory = CHfactory;
        MakeAPI(null);
    }

    public CuocHopViewModule(DSClassQL clas) {
        mClass.setValue(clas);
        mListCuocHop=new MutableLiveData<>();
    }
    private void MakeAPI(DSClassQL clas){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        List<Meettings> l=new ArrayList<>();
        DatabaseReference ref = database.getReference().child("CuocHops/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                l.clear();
                if(snapshot!=null){
                    for (DataSnapshot snap :snapshot.getChildren()) {
                        if(snap!=null){
                            //Log.e("đường dẫn: ","--> "+snap);
                            Meettings m =snap.getValue(Meettings.class);
                            if(m!=null){
                                if(m.get_nameClass().equals(clas.get_nameClass())) {
                                    l.add(m);
                                    Log.e("dữ liệu thông báo lớp đã tìm thấy: ", "--> " + snap);
                                }
                            }

                        }
                    }
                    mListCuocHop.setValue(l);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public CuocHopfactory getCHfactory() {
        return CHfactory;
    }

    public void setCHfactory(CuocHopfactory CHfactory) {
        MakeAPI(CHfactory.getClas());
        this.CHfactory = CHfactory;
    }

    public MutableLiveData<DSClassQL> getmClass() {
        return mClass;
    }

    public void setmClass(MutableLiveData<DSClassQL> mClass) {
        this.mClass = mClass;
    }

    public MutableLiveData<List<Meettings>> getmListCuocHop() {
        return mListCuocHop;
    }

    public void setmListCuocHop(MutableLiveData<List<Meettings>> mListCuocHop) {
        this.mListCuocHop = mListCuocHop;
    }
}
