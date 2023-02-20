package com.example.tiki.app_canhbao.backend;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tiki.app_canhbao.entity.User;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class NoteSVBE {
    public static void updateNoteSV(User u){
        String idU=u.get_id();
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference ref =database.getReference("Users/").child(idU);

        HashMap<String, Object > map=new HashMap<>();
        map.put("_note",u.get_note());
        ref.updateChildren(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Log.e("update---> ","success");
            }
        });
    }
}
