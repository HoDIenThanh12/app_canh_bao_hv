package com.example.tiki.app_canhbao.backend;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tiki.app_canhbao.entity.Meettings;
import com.example.tiki.app_canhbao.entity.User;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.core.Path;

import java.util.List;

public class MeettingUserBE {
    public static void getAllMeetting(String id){

    }
    public static void getKeyMeetting(String k){

    }
    public static void NewMeetting(String idUser, Meettings m){
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Users/"+idUser+"/Meetting/");
        String s =reference.push().getKey();
        m.set_id(s);
        reference.child(s).setValue(m);
        Log.e("ok ",": thêm cuộc họp thành công id: "+s);
        //Query u = reference.orderByKey();

    }
    public static void NewListUserMeetting(String idUser,String idMeting, List<User> lU){

    }
    public static void UpdateMeetting(){

    }
    public static void DeleteMeetting(){

    }
    public static void DeleteAllMeetting(){

    }
}
