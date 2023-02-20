package com.example.tiki.app_canhbao.backend;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.MutableLiveData;

import com.example.tiki.app_canhbao.entity.Meettings;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CuocHopBE {
    public static void NewCuocHop( Meettings m){
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("CuocHops/");
        String s =reference.push().getKey();
        m.set_id(s);
        reference.child(s).setValue(m);
        Log.e("ok ",": thêm cuộc họp thành công id: "+s);
    }
    public static void updateCuocHop(Meettings m){
        String idM = m.get_id();
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("CuocHops/").child(m.get_id());
        Map<String, Object> map=new HashMap<>();
        map.put("_idPhong",m.get_idPhong());
        map.put("_nameMeet",m.get_nameMeet());
        map.put("_contentMeet", m.get_contentMeet());
        map.put("_timeStart",m.get_timeStart());
        map.put("_dayStart", m.get_dayStart());
//        map.put("")
        ref.updateChildren(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Log.e("update---> ","success");
            }
        });
        Log.e("ok ",": sữa thành công id: ");
    }
    public static void deleteCuocHop(Meettings m){
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("CuocHops/").child(m.get_id());
        ref.removeValue();
    }
}
