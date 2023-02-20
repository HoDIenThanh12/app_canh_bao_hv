package com.example.tiki.app_canhbao.backend;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tiki.app_canhbao.entity.ClassSV;
import com.example.tiki.app_canhbao.entity.ClassSVNotification;
import com.example.tiki.app_canhbao.entity.DSClassQL;
import com.example.tiki.app_canhbao.entity.ThongBao;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ClassSVBE {
    public static List<String> listLop(){
        int key=0;
        List<String> lNameClass=new ArrayList<>();
        for (int i=16;i<=25;i++){
            for (int j=0;j<5;j++){
                String s="";
                for (int k=1;k<=5;k++){
                    if(j==0){
                        s="PM";
                    }
                    if(j==1){
                        s="HTTT";
                    }
                    if(j==2){
                        s="CNTT";
                    }
                    if(j==3){
                        s="CNOT";
                    }
                    if(j==4){
                        s="DDT";
                    }
                    if(k>=10)
                        lNameClass.add("D"+i+s+k);
                    else
                        lNameClass.add("D"+i+s+"0"+k);
                    key++;
                }
            }
        }
        return lNameClass;
    }

    public static void addClassSV(String id, String NClas, int ss){
        String nameGV =AuthAccount.getInstant().userAccount.get_Name();
        String idU =AuthAccount.getInstant().userAccount.get_id();
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("QlLops/");
        String s =reference.push().getKey();
        DSClassQL clas=new DSClassQL(s, idU, nameGV,NClas, ss);
        reference.child(s).setValue(clas);
    }
    public static void NewNotification(DSClassQL clas, String nd){
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy ", Locale.getDefault());
        String ngay =df.format(currentTime);
        String idU=AuthAccount.getInstant().userAccount.get_id();
        String idClas=clas.get_id();
        //Log.e("đường dẫn thông báo: ","-->  Users/"+idU+"/DSClass/"+clas.get_id()+"/Thongbao/");
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("ThongBao/");
        String idThongBao =reference.push().getKey();
        ClassSVNotification noti=new ClassSVNotification(clas.get_id(),clas.get_nameClass(),nd,ngay);
        noti.set_id(idThongBao);
        reference.child(idThongBao).setValue(noti);
    }
    public static void deleClassSV(DSClassQL clas){
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("QlLops/").child(clas.get_id());
        reference.removeValue();
    }
    public static void updateSiSoClass(DSClassQL clas, int ss){
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("QlLops/").child(clas.get_id());
        HashMap<String , Object > map=new HashMap<>();
        map.put("_siso",ss);
        reference.updateChildren(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

            }
        });
    }
}
