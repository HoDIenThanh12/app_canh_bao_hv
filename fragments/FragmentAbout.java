package com.example.tiki.app_canhbao.fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.MainActivityHomes;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.views.MainActivityLogIn;
import com.example.tiki.app_canhbao.views.MainActivityUserListMeetting;

public class FragmentAbout extends Fragment {

    private View fView;
    private MainActivityHomes mMainActivityHomes;
    private EditText tvID, tvNameA, tvClass, tvMSSV, tvKhoa, edtSDTCoVan, tvEmail, tvPass;
    private LinearLayout ln1,ln2, lnCOVan;
    private RadioGroup rdsgCatelogy;
    private RadioButton rdstbn1, rdsbtn2;
    private Button btnUpdateEditor, btnSingOut;
    private boolean isbtn=false, isShow=false;
    ImageView imgDisOrEnaPass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fView= inflater.inflate(R.layout.fragment_about, container, false);
        mMainActivityHomes= (MainActivityHomes) getActivity();
        tvNameA=fView.findViewById(R.id.tv_NameA);
        inits();
        getAboutAccount();
        return fView;
    }
    private void inits(){
        edtSDTCoVan=fView.findViewById(R.id.edt_sdtCoVan);
        tvID=fView.findViewById(R.id.tv_IDAccountA);
        tvNameA=fView.findViewById(R.id.tv_NameA);
        tvKhoa=fView.findViewById(R.id.tv_KhoaA);
        tvClass=fView.findViewById(R.id.tv_ClassA);
        tvMSSV=fView.findViewById(R.id.tv_MSSVA);
        rdsgCatelogy=fView.findViewById(R.id.gr_CategoryA);
        tvEmail=fView.findViewById(R.id.tv_EmailA);
        tvPass=fView.findViewById(R.id.tv_PassA);
        btnUpdateEditor=fView.findViewById(R.id.btn_Update_Editor);
        btnSingOut=fView.findViewById(R.id.btn_SingOut);
        rdsbtn2=fView.findViewById(R.id.rds_SVA);
        rdstbn1=fView.findViewById(R.id.rds_GVA);
        enabledFalse();
        btnUpdateEditor.setOnClickListener(v->{updateAccount();});
        ln1=fView.findViewById(R.id.ln1);
        ln2=fView.findViewById(R.id.ln2);
        lnCOVan=fView.findViewById(R.id.lnCoVan);

        imgDisOrEnaPass=fView.findViewById(R.id.img_DisOrEnaPass);
        imgDisOrEnaPass.setOnClickListener(v->{DisOrEnaBlePassWord(!this.isShow);});
        btnSingOut.setOnClickListener(v->{
            singoutAuth();
        });
    }

    private void singoutAuth() {
        new AlertDialog.Builder(mMainActivityHomes)
                .setTitle("Cảnh báo!")
                .setMessage("Bạn muốn thoát ứng dụng?")
                .setPositiveButton("Chấp nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mMainActivityHomes.RemoveValueKey();
                        AuthAccount.getInstant().Singouts();
                        Intent intent=new Intent(mMainActivityHomes, MainActivityLogIn.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Thoát",null)
                .show();
    }

    public void getAboutAccount(){
        //rdsgCatelogy.get;
        User u=AuthAccount.getInstant().userAccount;
        tvID.setText(u.get_id()+"");
        tvNameA.setText(u.get_Name());
        tvKhoa.setText(u.get_Khoa());
        tvClass.setText(u.get_Class());
        tvMSSV.setText(u.get_MSSV());
        edtSDTCoVan.setText(u.get_sdt());
        //tvCategory.setText(String.valueOf(u.get_category()));
        if(u.get_category()==2){
            Log.e("---> "," SV");
            rdsbtn2.setChecked(true);
            sinhvien();
        }
        else
        {
            rdstbn1.setChecked(true);
            giangvien();
            Log.e("---> "," GV");
        }

        tvEmail.setText(u.get_Email());
        tvPass.setText(u.get_Pass());
    }
    private void updateAccount(){
        int cate= AuthAccount.getInstant().userAccount.get_category();
        if(isbtn){
            if(!checkInfo()){
                Toast.makeText(mMainActivityHomes, "Có thông tin bị lỗi?", Toast.LENGTH_SHORT).show();
            }
            else {
                isShow=false;
                DisOrEnaBlePassWord(isShow);
                User u=AuthAccount.getInstant().userAccount;
                u.set_Name(tvNameA.getText().toString().trim());
                u.set_sdt(edtSDTCoVan.getText().toString());
                if(cate==1){
                    u.set_Khoa(tvKhoa.getText().toString());
                }else {
                    u.set_Khoa(tvKhoa.getText().toString());
                    u.set_Class(tvClass.getText().toString().trim());
                    u.set_MSSV(tvMSSV.getText().toString());
                }

                if(edtSDTCoVan.getText().toString().equals("")){
                    btnUpdateEditor.setText("Sữa hồ sơ");
                    enabledFalse();
                    AuthAccount.getInstant().updateAccount(u);
                    isbtn=false;
                    Toast.makeText(mMainActivityHomes, "Sữa thành công", Toast.LENGTH_SHORT).show();
                }else if(checkPhone(edtSDTCoVan.getText().toString())){
                    btnUpdateEditor.setText("Sữa hồ sơ");
                    enabledFalse();
                    AuthAccount.getInstant().updateAccount(u);
                    isbtn=false;
                    Toast.makeText(mMainActivityHomes, "Sữa thành công", Toast.LENGTH_SHORT).show();
                }

            }
        }
        else {
            btnUpdateEditor.setText("Lưu hồ sơ");
            enabledTrue();
            isbtn=true;
        }
//        Toast.makeText(mMainActivityHomes, "isbtn "+isbtn, Toast.LENGTH_SHORT).show();
    }
    //check format sdt
// Ham kiem tra dinh dang so dien thoai
    public boolean checkPhone(String str)  {
        // Bieu thuc chinh quy mo ta dinh dang so dien thoai
        String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

        // Kiem tra dinh dang
        boolean kt = str.matches(reg);
        if (kt == false) {
            Toast.makeText(mMainActivityHomes, "Sai đinh dạng SDT", Toast.LENGTH_SHORT).show();
            System.out.println("Loi: Khong dung dinh dang!");
            return false;
        } else {
            System.out.println("Dung dinh dang so dien thoai!");
            return true;
        }
    }
    private void sinhvien(){
        rdstbn1.setEnabled(false);
        rdsbtn2.setEnabled(false);
        ln2.setVisibility(View.VISIBLE);
        lnCOVan.setVisibility(View.VISIBLE);
    }
    private void giangvien(){
        lnCOVan.setVisibility(View.VISIBLE);
        rdstbn1.setEnabled(false);
        rdsbtn2.setEnabled(false);
        ln1.setVisibility(View.GONE);
        ln2.setVisibility(View.GONE);
    }
    private void enabledTrue(){
        tvID.setEnabled(true);
        tvNameA.setEnabled(true);
        tvKhoa.setEnabled(true);
        edtSDTCoVan.setEnabled(true);
//        tvPass.setEnabled(true);
        tvMSSV.setEnabled(true);
//        rdstbn1.setEnabled(true);
//        rdsbtn2.setEnabled(true);
        //tvCategory.setEnabled(true);
//        tvEmail.setEnabled(true);
        tvClass.setEnabled(true);
    }
    private void enabledFalse(){
        tvID.setEnabled(false);
        tvNameA.setEnabled(false);
        tvKhoa.setEnabled(false);
        tvPass.setEnabled(false);
        tvMSSV.setEnabled(false);
        rdstbn1.setEnabled(false);
        rdsbtn2.setEnabled(false);
        //tvCategory.setEnabled(false);
        tvEmail.setEnabled(false);
        tvClass.setEnabled(false);
        edtSDTCoVan.setEnabled(false);
    }
    private void DisOrEnaBlePassWord(boolean is){
//        Log.e("main about: ","ân hiện: "+isShow);
        if(isbtn) {
            if (is) {
                imgDisOrEnaPass.setImageResource(R.drawable.enable_pass);
                tvPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                isShow = true;
            } else {
                imgDisOrEnaPass.setImageResource(R.drawable.disable_pass);
                tvPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                isShow = false;
            }
        }
    }
    private boolean checkInfo(){
        int cate = AuthAccount.getInstant().userAccount.get_category();
        if(cate==2) {
            if (tvNameA.getText().toString().trim().equals("") ||
                    edtSDTCoVan.getText().toString().trim().equals("") ||
                    tvClass.getText().toString().trim().equals("")) {
                return false;
            }
        }
        else if(cate==2){
            if (tvNameA.getText().toString().trim().equals("") ||
                    edtSDTCoVan.getText().toString().trim().equals("") ||
                    tvKhoa.getText().toString().trim().equals("")) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isbtn=false;
        enabledFalse();
    }
}