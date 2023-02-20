package com.example.tiki.app_canhbao.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.adappers.CacVanBanAdaper;
import com.example.tiki.app_canhbao.constants.InstantCacVanBanCVHT;
import com.example.tiki.app_canhbao.entity.ClassSVNotification;
import com.example.tiki.app_canhbao.entity.VanBanCVHT;
import com.example.tiki.app_canhbao.interfaces.ActionVanBan;

import java.util.ArrayList;
import java.util.List;

public class MainActivityCacVanBan extends AppCompatActivity {
    RecyclerView rcvCacVanBanCVHT;
    CacVanBanAdaper adaper;
    List<VanBanCVHT> listVB;
    EditText edttimkiemVanBan;
    //dialog
    TextView tvMucVanBan, tvNdVanban;
    Button btnClose;
    ImageView imgcloseSearchCacVanBan;
    boolean isKeyBoard=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cac_van_ban);
        inits();
    }

    private void inits() {
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        imgcloseSearchCacVanBan=findViewById(R.id.img_closeSearchCacVanBan);
        edttimkiemVanBan=findViewById(R.id.edt_timkiemVanBan);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcvCacVanBanCVHT=findViewById(R.id.rcv_CacVanBanCVHT);
        rcvCacVanBanCVHT.setLayoutManager(manager);
        adaper=new CacVanBanAdaper(this, new ActionVanBan() {
            @Override
            public void showVanBan(VanBanCVHT vb) {
                openDialog(vb);
            }
        });
        listVB=new ArrayList<>();
        getVanBan();
        getSearchVanBan();
    }

    private void getSearchVanBan() {
        edttimkiemVanBan.setOnClickListener(v->{isKeyBoard=true;});
        imgcloseSearchCacVanBan.setOnClickListener(v->{
            if(isKeyBoard) {
                hideSoftKeyboard(this);
                isKeyBoard=false;
            }
        });
        edttimkiemVanBan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adaper.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private  void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
    private void getVanBan() {
        List<VanBanCVHT> l = InstantCacVanBanCVHT.getVB1();
        for (int i=0;i<l.size();i++){
            VanBanCVHT vb= l.get(i);
            vb.set_id(i+1);
            listVB.add(vb);
        }
        adaper.setList1(listVB);
        rcvCacVanBanCVHT.setAdapter(adaper);
    }
    private void openDialog(VanBanCVHT vb){
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_cac_van_ban);

        Window window= dialog.getWindow();
        if(window==null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        window.setAttributes(layoutParams);
        initDialog(dialog,vb);
        dialog.show();
    }

    private void initDialog(Dialog dialog, VanBanCVHT vb) {
        tvMucVanBan= dialog.findViewById(R.id.tv_MucVanBan);
        tvNdVanban=dialog.findViewById(R.id.tv_NdVanBan);
        btnClose=dialog.findViewById(R.id.btn_CloseDialogVanBan);
        tvMucVanBan.setText(vb.get_mucVanBan());
        tvNdVanban.setText(vb.get_ndungMucVanBan());
        btnClose.setOnClickListener(v->{dialog.dismiss();});
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
}