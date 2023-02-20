package com.example.tiki.app_canhbao.views;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.MainActivityHomes;
import com.example.tiki.app_canhbao.adappers.NotificationAdaper;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.backend.NotificationClassBE;
import com.example.tiki.app_canhbao.entity.ClassSV;
import com.example.tiki.app_canhbao.entity.ClassSVNotification;
import com.example.tiki.app_canhbao.entity.DSClassQL;
import com.example.tiki.app_canhbao.entity.QuyCheVCHT;
import com.example.tiki.app_canhbao.entity.ThongBao;
import com.example.tiki.app_canhbao.interfaces.ActionNotiClass;
import com.example.tiki.app_canhbao.viewmodel.ClassSVFactory;
import com.example.tiki.app_canhbao.viewmodel.NotificationClassSVViewModule;
import com.example.tiki.databinding.ActivityMainNotificationClassSvBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivityNotificationClassSV extends AppCompatActivity {
    ActivityMainNotificationClassSvBinding binding;
    NotificationClassSVViewModule mNotificationClassSVViewModule;
    List<ClassSVNotification> mListThongBao=new ArrayList<>();
    NotificationAdaper mAdaper;

    TextView  tvTitleDialog, tvNdungDialog, tvNameClassDialog;
//    TextView tvClassThongBao;
//    RecyclerView mRcvThongBao;
//    EditText edttimkiemThongBao;
//    ImageView imgcloseSearchNoti;
//    CardView cv_noti;
    Button btnCloseDialog;
    DSClassQL clas;
    boolean isKeyBoard=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_notification_class_sv);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main_notification_class_sv);
        inits();
    }

    private void inits() {
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
//        imgcloseSearchNoti=findViewById(R.id.img_closeSearchNoti);
//        cv_noti=findViewById(R.id.cv_item_noti);
//        tvClassThongBao=findViewById(R.id.tv_NameClassThongBao);
//        mRcvThongBao=findViewById(R.id.rcv_ThongBao);
//        edttimkiemThongBao=findViewById(R.id.edt_timkiemThongBao);
        LinearLayoutManager manager = new LinearLayoutManager(this);
//        mRcvThongBao.setLayoutManager(manager);
        binding.rcvThongBao.setLayoutManager(manager);
        Bundle bundle = getIntent().getExtras();
        clas = (DSClassQL) bundle.getSerializable("classSV");
//        tvClassThongBao.setText("Danh sách thông báo lớp "+clas.get_nameClass());
        binding.tvNameClassThongBao.setText("Danh sách thông báo lớp "+clas.get_nameClass());
//        Log.d("sussec: ","sussec----> "+"id: "+clas.toString());

        mAdaper=new NotificationAdaper(this, new ActionNotiClass() {
            @Override
            public void showDataNoti(ClassSVNotification noti) {
                openDialog(noti);
            }

            @Override
            public void deleteNoti(ClassSVNotification noti) {
                new AlertDialog.Builder(MainActivityNotificationClassSV.this)
                        .setTitle("Cảnh báo!")
                        .setMessage("Bạn muốn thoát ứng dụng?")
                        .setPositiveButton("Chấp nhận", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                NotificationClassBE.getInstant().deleteNoti(noti);
                                Toast.makeText(MainActivityNotificationClassSV.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Thoát",null)
                        .show();
            }
        });

        ClassSVFactory factory =new ClassSVFactory(clas);
        mNotificationClassSVViewModule= new ViewModelProvider(this, factory).get(NotificationClassSVViewModule.class);
        mNotificationClassSVViewModule.getmMutableLiveDataClassSV().observe(this, new Observer<DSClassQL>() {
            @Override
            public void onChanged(DSClassQL classSV) {
                Log.d("sussec: ","sussec----> "+"viewmodule: "+classSV.toString());
                if(classSV==null)
                    Log.d("sussec: ","sussec----> "+"id: k có ");
                else {
                    Log.d("sussec: ", "sussec----> " + "có dữ liệu: ");
                   mNotificationClassSVViewModule.setFactory(factory);
                }
            }
        });
        mNotificationClassSVViewModule.getmMutableLiveDataNotifi().observe(this, new Observer<List<ClassSVNotification>>() {
            @Override
            public void onChanged(List<ClassSVNotification> thongBaos) {
                mListThongBao.clear();
                if(thongBaos!=null){
                    mListThongBao.addAll(thongBaos);

                    try {
                        sortData(mListThongBao);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                for (ClassSVNotification tb: thongBaos) {
                    Log.e("quét: ","data: "+tb.toString());
                }
            }
        });
        timKiem();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
//                Log.e("main chat: ","qauy lại");
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void timKiem(){
//        edttimkiemThongBao.setOnClickListener(v->{isKeyBoard=true ;});
//        imgcloseSearchNoti.setOnClickListener(v->{
//            if(isKeyBoard){
//                hideSoftKeyboard(this);
////                Log.e("isKeyBoard"," "+isKeyBoard);
//                isKeyBoard=false;
//            }
//        });
//        edttimkiemThongBao.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mAdaper.getFilter().filter(s.toString().trim());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        binding.edtTimkiemThongBao.setOnClickListener(v->{isKeyBoard=true ;});
        binding.imgCloseSearchNoti.setOnClickListener(v->{
            if(isKeyBoard){
                hideSoftKeyboard(this);
//                Log.e("isKeyBoard"," "+isKeyBoard);
                isKeyBoard=false;
            }
        });
        binding.edtTimkiemThongBao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdaper.getFilter().filter(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager==null){

        }else {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
    private void sortData(List<ClassSVNotification> l) throws ParseException {
        List<ClassSVNotification> temp=new ArrayList<>();
        for (int i=0;i<l.size()-1;i++){
            for (int j=i+1;j<l.size();j++){
                if(checkDate(l.get(i).get_datetime(), l.get(j).get_datetime())){
                    ClassSVNotification noti= l.get(i);
                    l.set(i, l.get(j));
                    l.set(j, noti);
                    Log.e("check ngày: "," "+l.get(i).get_datetime());
                }
            }
        }
        loadData(mListThongBao);
    }
    private boolean checkDate(String d1, String d2)  {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date min= null;
        try {
            min = simpleDateFormat.parse(d1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date max= null;
        try {
            max = simpleDateFormat.parse(d2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e("check ngày: "," "+min+" | "+max);
        return max.compareTo(min)>=0;
    }
    private void loadData(List<ClassSVNotification> l) {
        mAdaper.setList(l);
//        mRcvThongBao.setAdapter(mAdaper);
        binding.rcvThongBao.setAdapter(mAdaper);
    }
    private void openDialog(ClassSVNotification noti){
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layou_dialog_qc_cvht);

        Window window= dialog.getWindow();
        if(window==null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        window.setAttributes(layoutParams);
        initDialog(dialog,noti);
        dialog.show();
    }
    private void initDialog(Dialog d,ClassSVNotification noti){
        tvTitleDialog =d.findViewById(R.id.tv_NameChuongDialogQCCVHT);
        tvNameClassDialog =d.findViewById(R.id.tv_NameMucDialogQCCVHT);
        tvNdungDialog =d.findViewById(R.id.tv_NoiDungMucDialogQCCVHT);
        btnCloseDialog=d.findViewById(R.id.btn_ThoatDialogQCCVHT);
        if(noti!=null) {
            tvTitleDialog.setText("Thông báo");
            tvNameClassDialog.setText(clas.get_nameClass());
            tvNdungDialog.setText(noti.get_nd());
        }
        btnCloseDialog.setOnClickListener(v->{d.dismiss();});
    }
    private boolean checkDialog(){
        final boolean[] isCheck = {false};
        new AlertDialog.Builder(MainActivityNotificationClassSV.this)
                .setTitle("Cảnh báo!")
                .setMessage("Bạn muốn thoát ứng dụng?")
                .setPositiveButton("Chấp nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isCheck[0] =  true;
                    }
                })
                .setNegativeButton("Thoát",null)
                .show();
        return isCheck[0];
    }
//    @Override
//    public void onBackPressed() {
//
//    }
}