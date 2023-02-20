package com.example.tiki.app_canhbao.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.MainActivityHomes;
import com.example.tiki.app_canhbao.adappers.ListQuyCheCVHTSearchAdaper;
import com.example.tiki.app_canhbao.adappers.ListSoTaySinhVienSearchAdaper;
import com.example.tiki.app_canhbao.adappers.SpinnerSoTaySinhVienAdaper;
import com.example.tiki.app_canhbao.backend.SoTaySinhVienBE;
import com.example.tiki.app_canhbao.entity.QuyCheVCHT;
import com.example.tiki.app_canhbao.entity.SotaySV;
import com.example.tiki.app_canhbao.interfaces.ActionSoTaySV;
import com.example.tiki.app_canhbao.views.MainActivitySotaySinhVien;

import java.util.ArrayList;
import java.util.List;


public class FragmentSoTaySinhVien extends Fragment {
    View fView;
    MainActivitySotaySinhVien mMain1;
    MainActivityHomes mMain;
    Spinner spnSotay1, spnSotay2, spnSotay3, spnSotay4,spnSotay5;
    TextView tvNameChuongSt, tvNameMucSt, tvNoiDungMucSt;
    Button btnCloseXemND;
    SpinnerSoTaySinhVienAdaper adaperSpiner;
    List<SotaySV> lSTSearch;
    RecyclerView rcvlistSearchSoTaySV;
    ListSoTaySinhVienSearchAdaper mAdaperSearchSoTaySV;
    ImageView imgcloseSearchSoTaySV;
    EditText edtsearchSoTaySV;
    boolean isHien=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fView = inflater.inflate(R.layout.fragment_so_tay_sinh_vien, container, false);
        //mMain= (MainActivitySotaySinhVien) getActivity();
        mMain= (MainActivityHomes) getActivity();
        inits();
        return fView;
    }
    private void inits(){
        spnSotay1=fView.findViewById(R.id.spn_SoTay1);
        spnSotay2=fView.findViewById(R.id.spn_SoTay2);
        spnSotay3=fView.findViewById(R.id.spn_SoTay3);
        spnSotay4=fView.findViewById(R.id.spn_SoTay4);
        spnSotay5=fView.findViewById(R.id.spn_SoTay5);
        lSTSearch=new ArrayList<>();
        mAdaperSearchSoTaySV=new ListSoTaySinhVienSearchAdaper(mMain, new ActionSoTaySV() {
            @Override
            public void ShowSoTay(SotaySV st) {
                openDialog(Gravity.CENTER, st);
            }
        });
        rcvlistSearchSoTaySV=fView.findViewById(R.id.rcv_listSearchSoTaySV);
        LinearLayoutManager manager = new LinearLayoutManager(mMain);
        RecyclerView.ItemDecoration item= new DividerItemDecoration(mMain, DividerItemDecoration.VERTICAL);
        rcvlistSearchSoTaySV.setLayoutManager(manager);
        rcvlistSearchSoTaySV.addItemDecoration(item);
        imgcloseSearchSoTaySV=fView.findViewById(R.id.img_closeSearchSotaySV);
        edtsearchSoTaySV=fView.findViewById(R.id.edt_searchSotaySV);
        getDataSpinner();
        actionQuyCheCVHT();
        getListSearchQuyCheCVHT();
    }
    private void getListSearchQuyCheCVHT(){
        Log.e("độ dài sotay: "," "+lSTSearch.size());
        mAdaperSearchSoTaySV.setList1(lSTSearch);
        rcvlistSearchSoTaySV.setAdapter(mAdaperSearchSoTaySV);
    }
    private void actionQuyCheCVHT(){
        edtsearchSoTaySV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                isHien=true;
                AnHienList(isHien);
            }
        });
        edtsearchSoTaySV.setOnClickListener(v->{
            isHien=true;
            AnHienList(isHien);
        });
        imgcloseSearchSoTaySV.setOnClickListener(v->{
            if(isHien) {
                hideSoftKeyboard(getActivity());
                edtsearchSoTaySV.setText("");
                isHien = false;
                AnHienList(isHien);
            }
        });
        edtsearchSoTaySV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdaperSearchSoTaySV.getFilter().filter(s.toString());
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
            if(activity.getCurrentFocus()==null){

            }else {
                inputMethodManager.hideSoftInputFromWindow(
                        activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }
    public void AnHienList(boolean isHien){
        if(isHien){
            rcvlistSearchSoTaySV.setVisibility(View.VISIBLE);
        }
        else{
            rcvlistSearchSoTaySV.setVisibility(View.GONE);
//            hideSoftKeyboard(getActivity());
        }
    }
    private void getDataSpinner(){
        ///qc1
        List<SotaySV> l1= SoTaySinhVienBE.getST1();
        ArrayList<SotaySV> arrayList1=new ArrayList<>();
        arrayList1.add(new SotaySV(0,"Tên trường",""));
        arrayList1.addAll(l1);
        lSTSearch.addAll(l1);
        adaperSpiner=new SpinnerSoTaySinhVienAdaper(mMain, R.layout.item_select_custom_dropdown, arrayList1);
        spnSotay1.setAdapter(adaperSpiner);
        spnSotay1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("đã cchonj---->  ","ádasdas");
                SotaySV st = (SotaySV) parent.getItemAtPosition(position);
                Log.e("đã cchonj---->  ","vị trí "+position);
                if(position!=0){
                    selectItemQCCVHT(st);
                    spnSotay1.setAdapter(adaperSpiner);
                    Log.e("màn hình sổ tay SV: "," "+st.get_NoiDungSTSV());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("đã nghỉ cchonj---->  ","ádasdas");
            }
        });
        ///qc2
        List<SotaySV> l2= SoTaySinhVienBE.getST2();
        ArrayList<SotaySV> arrayList2=new ArrayList<>();
        arrayList2.add(new SotaySV(0,"Chiến lược phát triển ",""));
        arrayList2.addAll(l2);
        lSTSearch.addAll(l2);
        adaperSpiner=new SpinnerSoTaySinhVienAdaper(mMain, R.layout.item_select_custom_dropdown, arrayList2);
        spnSotay2.setAdapter(adaperSpiner);
        spnSotay2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("đã cchonj---->  ","ádasdas");
                SotaySV st = (SotaySV) parent.getItemAtPosition(position);
                Log.e("đã cchonj---->  ","vị trí "+position);
                if(position!=0){
                    selectItemQCCVHT(st);
                    spnSotay2.setAdapter(adaperSpiner);
                    Log.e("màn hình sổ tay SV: "," "+st.get_NoiDungSTSV());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("đã nghỉ cchonj---->  ","ádasdas");
            }
        });
        ///st 3
        List<SotaySV> l3= SoTaySinhVienBE.getST3();
        ArrayList<SotaySV> arrayList3=new ArrayList<>();
        arrayList3.add(new SotaySV(0,"Giá trị cốt lõi",""));
        arrayList3.addAll(l3);
        lSTSearch.addAll(l3);
        adaperSpiner=new SpinnerSoTaySinhVienAdaper(mMain, R.layout.item_select_custom_dropdown, arrayList3);
        spnSotay3.setAdapter(adaperSpiner);
        spnSotay3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("đã cchonj---->  ","ádasdas");
                SotaySV st = (SotaySV) parent.getItemAtPosition(position);
                Log.e("đã cchonj---->  ","vị trí "+position);
                if(position!=0){
                    selectItemQCCVHT(st);
                    spnSotay3.setAdapter(adaperSpiner);
                    Log.e("màn hình sổ tay SV: "," "+st.get_NoiDungSTSV());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("đã nghỉ cchonj---->  ","ádasdas");
            }
        });
        ///st 4
        List<SotaySV> l4= SoTaySinhVienBE.getST4();
        ArrayList<SotaySV> arrayList4=new ArrayList<>();
        arrayList4.add(new SotaySV(0,"Triết lý giáo dục",""));
        arrayList4.addAll(l4);
        lSTSearch.addAll(l4);
        adaperSpiner=new SpinnerSoTaySinhVienAdaper(mMain, R.layout.item_select_custom_dropdown, arrayList4);
        spnSotay4.setAdapter(adaperSpiner);
        spnSotay4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("đã cchonj---->  ","ádasdas");
                SotaySV st = (SotaySV) parent.getItemAtPosition(position);
                Log.e("đã cchonj---->  ","vị trí "+position);
                if(position!=0){
                    selectItemQCCVHT(st);
                    spnSotay4.setAdapter(adaperSpiner);
                    Log.e("màn hình sổ tay SV: "," "+st.get_NoiDungSTSV());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("đã nghỉ cchonj---->  ","ádasdas");
            }
        });
        ///qc1
        List<SotaySV> l5= SoTaySinhVienBE.getST5();
        ArrayList<SotaySV> arrayList5=new ArrayList<>();
        arrayList5.add(new SotaySV(0,"KHÁI QUÁT LỊCH SỬ PHÁT TRIỂN",""));
        arrayList5.addAll(l5);
        lSTSearch.addAll(l5);
        adaperSpiner=new SpinnerSoTaySinhVienAdaper(mMain, R.layout.item_select_custom_dropdown, arrayList5);
        spnSotay5.setAdapter(adaperSpiner);
        spnSotay5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("đã cchonj---->  ","ádasdas");
                SotaySV st = (SotaySV) parent.getItemAtPosition(position);
                Log.e("đã cchonj---->  ","vị trí "+position);
                if(position!=0){
                    selectItemQCCVHT(st);
                    spnSotay5.setAdapter(adaperSpiner);
                    Log.e("màn hình sổ tay SV: "," "+st.get_NoiDungSTSV());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("đã nghỉ cchonj---->  ","ádasdas");
            }
        });
    }
    private void selectItemQCCVHT(SotaySV st) {
        //Toast.makeText(fManActivityHomes, " "+qc.toString(), Toast.LENGTH_SHORT).show();
        if(isHien){
            isHien = false;
            AnHienList(isHien);
        }
        openDialog(Gravity.CENTER, st);

    }
    private void openDialog(int g, SotaySV qc){
        final Dialog dialog=new Dialog(mMain);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layou_dialog_qc_cvht);

        Window window= dialog.getWindow();
        if(window==null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity =g;
        window.setAttributes(layoutParams);
        initDialog(dialog,qc);
        dialog.show();
    }
    private void initDialog(Dialog d,SotaySV st){
        tvNameChuongSt =d.findViewById(R.id.tv_NameChuongDialogQCCVHT);
        tvNameMucSt =d.findViewById(R.id.tv_NameMucDialogQCCVHT);
        tvNoiDungMucSt =d.findViewById(R.id.tv_NoiDungMucDialogQCCVHT);
        btnCloseXemND=d.findViewById(R.id.btn_ThoatDialogQCCVHT);
        if(st!=null)
//        tvNameChuong.setText(spn1.getSelectedItemPosition());
            tvNameMucSt.setText(st.get_mucSTSV());
        tvNoiDungMucSt.setText(st.get_NoiDungSTSV());
        btnCloseXemND.setOnClickListener(v->{d.dismiss();});
    }
}