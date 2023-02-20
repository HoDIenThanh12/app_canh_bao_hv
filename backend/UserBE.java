package com.example.tiki.app_canhbao.backend;

import static com.example.tiki.app_canhbao.constants.INSTANT.PATH_DTAREATIME_USER;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tiki.app_canhbao.entity.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserBE  {
    static UserBE _UserBE=null;
    public static UserBE getInstant(){
        if(_UserBE==null){
            _UserBE= (UserBE) new UserBE();
        }
        return _UserBE;
    }
    //get all list user
    public List<User> getAllUserBE(String id){
        List <User> lUser=new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference =database.getReference(PATH_DTAREATIME_USER);
          reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User utemp =dataSnapshot.getValue(User.class);
                    if(!utemp.get_id().equals("Z6SozJCa7bMJFco9fpkorXnVGH52")){
                        lUser.add(utemp);
                        //Log.e("user--> ",""+lUser);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Log.e("ds --> ","  "+lUser);
        for(int i=0;i<5;i++){
            lUser.add(new User("123","123","123",1,""));
        }
        return lUser;
    }

    public static void getKeyUserBE(String k){

    }
    public static void NewUserBE(){

    }
    public static void UpdateUserBE(){

    }
    public static void DeleteUserBE(){

    }
    public static void DeleteAllUserBE(){

    }
}
