package com.example.tiki.app_canhbao.backend;

import static com.example.tiki.app_canhbao.constants.INSTANT.PATH_DTAREATIME_USER;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.tiki.R;
import com.example.tiki.app_canhbao.entity.ClassSV;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.entity.kkk;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class AuthAccount {
    public static AuthAccount _AuthEmails = null;
    public User userAccount =null;
    private  FirebaseAuth auth;
    private  FirebaseUser user;
    private  boolean isCreate;

    private ProgressDialog dialog ;
    //desgin partent
    public static AuthAccount getInstant(){
        if(_AuthEmails==null){
            _AuthEmails =new AuthAccount();
            _AuthEmails.userAccount=new User();
        }
        return _AuthEmails;
    }
    //điều hướng navigation
    public void Navigations(Context mF, Class<?> clas ){
        Intent intent =new Intent(mF,clas);
        mF.startActivity(intent);
        //finishAffinity((Activity) mF);
    }
    //login account
    public void Log(Context mContext,String e, String p, Class<?> clas ){
        final boolean[] isLoad = {false};
        FirebaseAuth auth =FirebaseAuth.getInstance();
//        dialog.dismiss();
        if(isLoad[0]==true){
            dialog.dismiss();
            Log.w("TAG---->  ", "kiểm tra load: "+isLoad[0]);
        }
        openDialogLoad(mContext);
        auth.signInWithEmailAndPassword(e, p)
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("---> ", "signInWithEmail: success");
                                FirebaseUser user = auth.getCurrentUser();
                                _AuthEmails.userAccount.set_id(user.getUid());
                                isLoad[0] =true;
                            _AuthEmails.GetAccount(user.getUid()+"", mContext, clas);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG---->  ", "signInWithEmail: fail");
                            closeDialogLoad();
                                Toast.makeText(mContext, "Đăng nhập thất bại.",
                                        Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//        closeDialogLoad();
    }
    //register account
    public void Register(Context mFirst, User u, Class<?> mLast ){
        auth=FirebaseAuth.getInstance();
        openDialogLoad(mFirst);
        auth.createUserWithEmailAndPassword(u.get_Email(), u.get_Pass())
                .addOnCompleteListener((Activity) mFirst, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d("cr---> ", "createUserWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            PushAboutAccount(user,u);
                            Toast.makeText(mFirst, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();
                            setCreate(true);
                            closeDialogLoad();
                            Navigations(mFirst,mLast);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("cr---> ", "createUserWithEmail:failure", task.getException());
                            closeDialogLoad();
                            Toast.makeText(mFirst, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            setCreate(false);
                            //isCreate=false;
                            //updateUI(null);
                        }
                    }
                });
    }
    //create user on firebase
    public void PushAboutAccount(FirebaseUser u,User us){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(PATH_DTAREATIME_USER);
        //String userId = myRef.push().getKey();
//        Log.w("cr---> ", "id đăng ký "+u.getUid());
        User user=new User(u.getUid(), us.get_Name(), "","",
                "","",u.getEmail(),us.get_Pass(),us.get_category(),"");
        //_AuthEmails.userAccount=us;
        myRef.child(u.getUid()).setValue(user);
    //cách khác
//        myRef.setValue(u, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//
//            }
//        });
    }
    //get account login
    public void GetAccount(String id,Context mContext, Class<?> clas ){
        final boolean[] isload = {true};
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference =database.getReference(PATH_DTAREATIME_USER);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User utemp =dataSnapshot.getValue(User.class);
                    if(utemp.get_id().equals(_AuthEmails.userAccount.get_id())){
                        _AuthEmails.userAccount=utemp;
                        //dialog.dismiss();
                        if(isload[0]) {
                            isload[0] =false;
                            closeDialogLoad();
                            Navigations(mContext, clas);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    User utemp =dataSnapshot.getValue(User.class);
//                    if(utemp.get_id().equals(_AuthEmails.userAccount.get_id())){
//                        _AuthEmails.userAccount=utemp;
//                        dialog.dismiss();
//                        Navigations(mContext,clas);
//                        dialog=null;
//                        //_AuthEmails.userAccount.set_Name(utemp.get_Name());
////                        Log.e("user--> "," IDobj: "+id);
////                        Log.e("user--> "," obj: "+_AuthEmails.userAccount);
////                        Log.e("user--> "," id: "+utemp.get_id()+
////                                " id: "+utemp.get_Name()+
////                                " id: "+utemp.get_MSSV());
//                    }
//                }
//
////               if(utemp.get_id().equals(id)){
////                   _AuthEmails.userAccount=utemp;
////               }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
    //update account
    public void updateAccount(User u){
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_DTAREATIME_USER).child(u.get_id());
        //cách 1 setvalues đối tượng ==> update cả đối tượng
//        reference.setValue(u, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//
//            }
//        });
        //cách 2
//        reference= database.getReference(PATH_DTAREATIME_USER+"/"+"thuộc tính");
//        reference.setValue("giá trị", new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//
//            }
//        });
        //có thể dùng reference.child("đường dẫn thay đổi thuộc tính");
        //cách 3 update những giá trị cần update
        Map<String, Object> map=new HashMap<>();
        int cate=AuthAccount.getInstant().userAccount.get_category();
        if(cate==2){
            map.put("_Name",u.get_Name());
            map.put("_Khoa", u.get_Khoa());
            map.put("_Class",u.get_Class());
            map.put("_MSSV", u.get_MSSV());
            map.put("_sdt", u.get_sdt());
        }
        else {
            map.put("_Name",u.get_Name());
            map.put("_sdt",u.get_sdt());
            map.put("_Khoa", u.get_Khoa());
            map.put("_sdt", u.get_sdt());
        }
//        map.put("")
        reference.updateChildren(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Log.e("update---> ","success");
            }
        });
    }
    public void updateToken(User u){
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_DTAREATIME_USER).child(u.get_id());
        Map<String, Object> map=new HashMap<>();
        map.put("_Token",u.get_Token());
        reference.updateChildren(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Log.e("update---> ","success");
            }
        });
    }
    public void openDialogLoad(Context mContext){
        dialog=new ProgressDialog(mContext);
        dialog.show();
        //set layout ProgressDialog
        dialog.setContentView(R.layout.layout_wait_load);
        // set tranparent ProgressDialog
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
    }
    public void closeDialogLoad(){
        dialog.dismiss();
    }
    public void Singouts(){
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.signOut();
    }
    public void PushDataRealtime(){

    }
    public boolean getisCreate() {
        return isCreate;
    }
    public ArrayList<String> getSpinnerLession(){
        ArrayList<String> list =new ArrayList<>();
        for (int i=2;i<=8;i++){
            list.add(i==8 ?"Chủ nhật": "Thứ "+i);
        }
        return list;
    }
    public ArrayList<String> getStartLearning(){
        ArrayList<String> list =new ArrayList<String>();
        for (int i=1;i<=14;i++){
            list.add("Tiết " +i);
        }
        return list;
    }
    public void setCreate(boolean create) {
        isCreate = create;
    }
    ///cập nhật tất cả user
    public  static  void Capnhat(User u){
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_DTAREATIME_USER).child(u.get_id());
        Map<String, Object> map=new HashMap<>();
        map.put("_note","");
        map.put("_sdt","");
        reference.updateChildren(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Log.e("update---> ","success");
            }
        });
    }
}

