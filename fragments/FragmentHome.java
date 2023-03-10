package com.example.tiki.app_canhbao.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.MainActivityHomes;
import com.example.tiki.app_canhbao.adappers.ListClassSVHomeAdaper;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.backend.ClassSVBE;
import com.example.tiki.app_canhbao.constants.INSTANT;
import com.example.tiki.app_canhbao.entity.ClassSV;
import com.example.tiki.app_canhbao.entity.ClassSVNotification;
import com.example.tiki.app_canhbao.entity.DSClassQL;
import com.example.tiki.app_canhbao.entity.ThongBao;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.interfaces.EnventItemClassSV;
import com.example.tiki.app_canhbao.viewmodel.HomeViewModule;
import com.example.tiki.app_canhbao.views.MainActivityCacVanBan;
import com.example.tiki.app_canhbao.views.MainActivityNotificationClassSV;
import com.example.tiki.app_canhbao.views.MainActivityQuanLyChat;
import com.example.tiki.app_canhbao.views.MainActivitySotaySinhVien;
import com.example.tiki.app_canhbao.views.MainActivityUserListMeetting;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    //test broadcast
    private static  final String MY_KEY= "action_bro";
    private static  final String KEY_TEXT= "conten";
    ////
    RecyclerView rcvListClassSVHome;
    private View fView;
    private MainActivityHomes mMainActivityHomes;
    /////viewmodule
    ListClassSVHomeAdaper mAdaperClassSVHome;
    HomeViewModule mHomeViewModule;
    CardView cv_1, cv_2, cv_3, cv_4;
    Button btn, btnLoad;
    TextView tvcv_1,tvcv_2,tvcv_3,tvcv_4;
    ImageView imgcv_1, imgcv_2,imgcv_3,imgcv_4;
    int cate = 0;
    //ph???n braodcast receiver
    private BroadcastReceiver broadcastReceiver= new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(MY_KEY.equals(intent.getAction())){
                Log.e("BroadcastReceiver--> "," ???? nh???n ???????c r???i em");
                btn.setText("???? nh???n ???????c r???i em");
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter intentFilter =new IntentFilter(MY_KEY);
        mMainActivityHomes.registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fView= inflater.inflate(R.layout.fragment_home, container, false);
        mMainActivityHomes= (MainActivityHomes) getActivity();
        cate = AuthAccount.getInstant().userAccount.get_category();
        updateToken();
        inits();
        updateTvCardView();
        return fView;

    }

    private void updateTvCardView() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        if(cate==1){
            tvcv_1.setText("Quy ch???");
            tvcv_2.setText("S??? tay");
            tvcv_3.setText("C??c v??n b???n");
            tvcv_4.setText("Qu??n l?? tin nh???n");
            imgcv_1.setImageResource(R.drawable.icon_sotay);
            imgcv_2.setImageResource(R.drawable.ic_quyche);
            imgcv_3.setImageResource(R.drawable.icon_vanban);
            imgcv_4.setImageResource(R.drawable.icon_ql_chat_cv);
        }
        else{
            tvcv_1.setText("S??? tay sinh vi??n");
            tvcv_2.setText("Th??ng tin C?? v???n");
            tvcv_3.setText("Th??ng b??o");
            tvcv_4.setText("Cu???c h???p");
            imgcv_1.setImageResource(R.drawable.ic_quyche);
            imgcv_2.setImageResource(R.drawable.icon_thongtin_covan);
            imgcv_3.setImageResource(R.drawable.icon_notification);
            imgcv_4.setImageResource(R.drawable.icon_about);
        }
    }

    private void updateToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("---> ", "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        String token = task.getResult();
                        if (!token.isEmpty()){
                            User u = AuthAccount.getInstant().userAccount;
                            u.set_Token(token);
                            AuthAccount.getInstant().updateToken(u);
                            Log.d("---> ", "???? l???y ??c token--> : "+token);
                        }

                    }
                });
    }

    private void inits() {
        tvcv_1=fView.findViewById(R.id.tv_cv_1);
        tvcv_2=fView.findViewById(R.id.tv_cv_2);
        tvcv_3=fView.findViewById(R.id.tv_cv_3);
        tvcv_4=fView.findViewById(R.id.tv_cv_4);
        imgcv_1=fView.findViewById(R.id.img_cv_1);
        imgcv_2=fView.findViewById(R.id.img_cv_2);
        imgcv_3=fView.findViewById(R.id.img_cv_3);
        imgcv_4=fView.findViewById(R.id.img_cv_4);
//        btn=fView.findViewById(R.id.btn);
//        btnLoad=fView.findViewById(R.id.btnLoad);
//        List<ThongBao> l2=new ArrayList<>();
//        for (int i=0;i<3;i++){
//            l2.add(new ThongBao("123","123","123"));
//        }
//        btn.setOnClickListener(v->{
//            new AlertDialog.Builder(mMainActivityHomes)
//                    .setTitle("C???nh b??o!")
//                    .setMessage("B???n c?? ch???c ch???n mu???n x??a kh??ng?")
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    })
//                    .setNegativeButton("Cancel",null)
//                    .show();
//        });
//        final ClassSVNotification[] noti = new ClassSVNotification[1];
//        btnLoad.setOnClickListener(v->{
//            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            DatabaseReference ref = database.getReference().child("thongbaos/");
//
//            ref.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if(snapshot!=null){
//                        for (DataSnapshot snap :  snapshot.getChildren()) {
//                            ClassSVNotification notis = snap.getValue(ClassSVNotification.class);
//                            //Log.e("d?? li???u ch??a ki???m tra", "   " + notis.toString());
//                            if(notis.get_nameClass().equals("D18PM01")) {
//                                noti[0] = notis;
//                                Log.e("d?? li???u l???y v??? g??n", "   " + noti[0].toString());
//                            }
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        });
//        if (noti!=null)
//        Log.e("d?? li???u l???y v???", "   " + noti.toString());
        //c??c card view
        cv_1 = fView.findViewById(R.id.cv_1);
        cv_2 = fView.findViewById(R.id.cv_2);
        cv_3 = fView.findViewById(R.id.cv_3);
        cv_4 = fView.findViewById(R.id.cv_4);
        //cvQLLop =fView.findViewById(R.id.cv_QLClass);
        //ph???n viewmodule
        mAdaperClassSVHome= new ListClassSVHomeAdaper(mMainActivityHomes, new EnventItemClassSV() {
            @Override
            public void callbackClasSV(DSClassQL clas) {
                Intent intent = new Intent(mMainActivityHomes, MainActivityNotificationClassSV.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("classSV",clas);
                intent.putExtras(bundle);
                startActivity(intent);

            }

            @Override
            public void deleClassSV(DSClassQL clas) {
                deleClas(clas);
            }
        });
        rcvListClassSVHome = fView.findViewById(R.id.rcv_ListClassSVHome);
        LinearLayoutManager manager = new LinearLayoutManager(mMainActivityHomes);
        rcvListClassSVHome.setLayoutManager(manager);
//        RecyclerView.ItemDecoration item= new DividerItemDecoration(mMainActivityHomes, DividerItemDecoration.VERTICAL);
//        rcvListClassSVHome.addItemDecoration(item);
        rcvListClassSVHome.setVerticalScrollBarEnabled(false);
        rcvListClassSVHome.setHorizontalScrollBarEnabled(false);
        ////
        mHomeViewModule=new ViewModelProvider(this).get(HomeViewModule.class);
        //l???y d??? li???u firebase danh s??ch l???p
        getAllClaassSVHome();
        //c??c action
        cv_1.setOnClickListener(v->{ActionCardview(1);});
        cv_2.setOnClickListener(v->{ActionCardview(2);});
        cv_3.setOnClickListener(v->{ActionCardview(3);});
        cv_4.setOnClickListener(v->{ActionCardview(4);});
    }

    private void deleClas(DSClassQL clas) {
        new AlertDialog.Builder(mMainActivityHomes)
                .setTitle("C???nh b??o!")
                .setMessage("B???n c?? ch???c ch???n mu???n x??a kh??ng?")
                .setPositiveButton("Ch???p nh???n", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ClassSVBE.deleClassSV(clas);
//                        ClassSVBE.lk();
                        Toast.makeText(mMainActivityHomes, "X??a th??nh c??ng", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("Tho??t",null)
                .show();
    }

    private void ActionCardview(int key){
        int keyChuyen=1;
        //?????i v???i gi???ng vi??n: 1=s??? tay, 2=quy ch???, 3=c??c v??n b???n, 4=ql l???p
        //?????i v???i sinh vi??n: 1=quy ch???, 2=thong tin c??? v???n, 3= th??ng b??o, 4=th??ng tin t??i kho???n
        if(key==1){
            if(cate==1){
                keyChuyen=1;
                Intent intent =new Intent(mMainActivityHomes,MainActivitySotaySinhVien.class);
                intent.putExtra("key",keyChuyen);
                startActivity(intent);
            }
            else {
                ActionPage(INSTANT.Page_QC);
//                keyChuyen=2;
//                Intent intent =new Intent(mMainActivityHomes,MainActivitySotaySinhVien.class);
//                intent.putExtra("key",keyChuyen);
//                startActivity(intent);
            }
        }
        else if(key==2){
            if(cate==1){
                ActionPage(INSTANT.Page_QC);
            }else{
                ActionPage(INSTANT.Page_CLassSV);
                //Toast.makeText(mMainActivityHomes, "th??ng tin c??? v???n", Toast.LENGTH_SHORT).show();
            }
        }
        else if (key==3){
            if(cate==1){
                Intent intent = new Intent(mMainActivityHomes, MainActivityCacVanBan.class);
                startActivity(intent);
//                Toast.makeText(mMainActivityHomes, "C??c v??n b???n", Toast.LENGTH_SHORT).show();
            }else{
                page();
            }
        }
        else {
            Log.e("key"," th??ng b??o l???p sinh vi??n: "+cate);
            if(cate==1){
//                ActionPage(INSTANT.Page_CLassSV);
                Intent intent=new Intent(mMainActivityHomes, MainActivityQuanLyChat.class);
                startActivity(intent);
            }else{
                Intent intent=new Intent(mMainActivityHomes, MainActivityUserListMeetting.class);
                String nClass=AuthAccount.getInstant().userAccount.get_Class();
                DSClassQL classQL=new DSClassQL("","","",nClass,0);
                Bundle bundle=new Bundle();
                bundle.putSerializable("Class", classQL);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }
    private void page(){
            String nameClass=AuthAccount.getInstant().userAccount.get_Class();
            DSClassQL clas=new DSClassQL("","","",nameClass,0);
            Log.e("page--> ","page th??ng b??o cho sinh vi??n");
            Intent intent = new Intent(mMainActivityHomes, MainActivityNotificationClassSV.class);
            Bundle bundle= new Bundle();
            bundle.putSerializable("classSV",clas);
            intent.putExtras(bundle);
            startActivity(intent);
    }
    private void ActionPage(int page) {
        mMainActivityHomes.chuyenFragment(page);
    }
    private void getAllClaassSVHome(){
        mHomeViewModule.getmListMutableClassSVHome().observe(mMainActivityHomes, new Observer<List<DSClassQL>>() {
            @Override
            public void onChanged(List<DSClassQL> classSVS) {
//                Log.e("k???t qu??? l???y ??? fragment home: "," "+classSVS.size());
                if(classSVS!=null){
                    ganDanhSachLop(classSVS);
                }
            }
        });
    }
    private void ganDanhSachLop(List<DSClassQL> l){
        mAdaperClassSVHome.setmList(l);
        rcvListClassSVHome.setAdapter(mAdaperClassSVHome);
    }
}