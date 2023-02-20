package com.example.tiki.app_canhbao.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
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
import com.example.tiki.app_canhbao.adappers.ListQuyCheCVHTSearchAdaper;
import com.example.tiki.app_canhbao.adappers.ListSoTayCVHTSearchAdaper;
import com.example.tiki.app_canhbao.adappers.SpinnerQCCVHTAdaper;
import com.example.tiki.app_canhbao.adappers.SpinnerSoTaySinhVienAdaper;
import com.example.tiki.app_canhbao.backend.CacQuyCheCVHTBE;
import com.example.tiki.app_canhbao.backend.SoTaySinhVienBE;
import com.example.tiki.app_canhbao.entity.QuyCheVCHT;
import com.example.tiki.app_canhbao.entity.SotaySV;
import com.example.tiki.app_canhbao.interfaces.ActionQuyCheCVHT;
import com.example.tiki.app_canhbao.views.MainActivitySotaySinhVien;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FragmentQuyCheCVHT extends Fragment {
    View fView;
    MainActivitySotaySinhVien mMain;
    Spinner spnQC1, spnQC2, spnQC3, spnQC4,spnQC5;
    TextView tvNameChuongQC, tvNameMucQC, tvNoiDungMucQC;
    Button btnCloseXemND;
    SpinnerQCCVHTAdaper adaperSpiner;
    List<QuyCheVCHT> lQCSearch;
    RecyclerView rcvlistSearchQuyCheCVHT;
    ListQuyCheCVHTSearchAdaper mAdaperSearchQCCVHT;
    ImageView imgcloseSearchQuytCheCVHT;
    EditText edtsearchQuyCheCVHT;
    boolean isHien=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fView= inflater.inflate(R.layout.fragment_quy_che_c_v_h_t, container, false);
        mMain= (MainActivitySotaySinhVien) getActivity();
        inits();
        return fView;
    }
    private void inits(){
        spnQC1=fView.findViewById(R.id.spn_QC1);
        spnQC2=fView.findViewById(R.id.spn_QC2);
        spnQC3=fView.findViewById(R.id.spn_QC3);
        spnQC4=fView.findViewById(R.id.spn_QC4);
        spnQC5=fView.findViewById(R.id.spn_QC5);
        lQCSearch=new ArrayList<>();
        mAdaperSearchQCCVHT=new ListQuyCheCVHTSearchAdaper(mMain, new ActionQuyCheCVHT() {
            @Override
            public void showQuyCheCVHT(QuyCheVCHT qc) {
                selectItemQCCVHT(qc);
            }
        });
        rcvlistSearchQuyCheCVHT=fView.findViewById(R.id.rcv_listSearchQuyCheCVHT);
        LinearLayoutManager manager = new LinearLayoutManager(mMain);
        RecyclerView.ItemDecoration item= new DividerItemDecoration(mMain, DividerItemDecoration.VERTICAL);
        rcvlistSearchQuyCheCVHT.setLayoutManager(manager);
        rcvlistSearchQuyCheCVHT.addItemDecoration(item);
        imgcloseSearchQuytCheCVHT=fView.findViewById(R.id.img_closeSearchQuytCheCVHT);
        edtsearchQuyCheCVHT=fView.findViewById(R.id.edt_searchQuyCheCVHT);
        getDataSpinner();
        getListSearchQuyCheCVHT();
        actionQuyCheCVHT();
    }
    private void getListSearchQuyCheCVHT(){
        mAdaperSearchQCCVHT.setList1(lQCSearch);
        rcvlistSearchQuyCheCVHT.setAdapter(mAdaperSearchQCCVHT);
    }
    private void actionQuyCheCVHT(){

        edtsearchQuyCheCVHT.setOnClickListener(v->{
            if(!isHien) {
                isHien = true;
                AnHienList(isHien);
            }
        });
        imgcloseSearchQuytCheCVHT.setOnClickListener(v->{
            if(isHien) {
                closeKeyBoard(Objects.requireNonNull(getActivity()));
                edtsearchQuyCheCVHT.setText("");
                isHien = false;
                AnHienList(isHien);
            }
        });
        closeKeyBoard(getActivity());
        edtsearchQuyCheCVHT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdaperSearchQCCVHT.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void closeKeyBoard(Activity activity){
        View view= mMain.getCurrentFocus();
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(view==null){
            View decorView = mMain.getWindow().getDecorView();
            View focusView = decorView.findViewWithTag("keyboardTagView");
            if (focusView == null) {
                view = new EditText(mMain.getWindow().getContext());
                view.setTag("keyboardTagView");
                ((ViewGroup) decorView).addView(view, 0, 0);
            } else {
                view = focusView;
            }
            view.requestFocus();
        }
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(activity!=null){
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
    public void AnHienList(boolean isHien){
        if(isHien){
            rcvlistSearchQuyCheCVHT.setVisibility(View.VISIBLE);
        }
        else{
            rcvlistSearchQuyCheCVHT.setVisibility(View.GONE);
//            hideSoftKeyboard(getActivity());
        }
    }
    private void getDataSpinner(){
        ///qc1
        List<QuyCheVCHT> l1= CacQuyCheCVHTBE.getQC1();
        lQCSearch.addAll(l1);
        ArrayList<QuyCheVCHT> arrayList1=new ArrayList<>();
        arrayList1.add(new QuyCheVCHT(0,"Những quy định chung",""));
        arrayList1.addAll(l1);
        adaperSpiner=new SpinnerQCCVHTAdaper(mMain, R.layout.item_select_custom_dropdown, arrayList1);
        spnQC1.setAdapter(adaperSpiner);
        spnQC1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("đã cchonj---->  ","ádasdas");
                QuyCheVCHT st = (QuyCheVCHT) parent.getItemAtPosition(position);
                Log.e("đã cchonj---->  ","vị trí "+position);
                if(position!=0){
                    selectItemQCCVHT(st);
                    spnQC1.setAdapter(adaperSpiner);
                    Log.e("màn hình sổ tay SV: "," "+st.get_mucQuyChe());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("đã nghỉ cchonj---->  ","ádasdas");
            }
        });
        ///qc2
        List<QuyCheVCHT> l22= CacQuyCheCVHTBE.getQC2();
        lQCSearch.addAll(l22);
        ArrayList<QuyCheVCHT> arrayList2=new ArrayList<>();
        arrayList2.add(new QuyCheVCHT(0,"TIÊU CHUẢN, NGUYÊN TẮC VÀ QUY TRÌNH " +
                "PHÂN CÔNG CÓ VÁN HỌC TẬP LỚP HỌC",""));
        arrayList2.addAll(l22);
        adaperSpiner=new SpinnerQCCVHTAdaper(mMain, R.layout.item_select_custom_dropdown, arrayList2);
        spnQC2.setAdapter(adaperSpiner);
        spnQC2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("đã cchonj---->  ","ádasdas");
                QuyCheVCHT st = (QuyCheVCHT) parent.getItemAtPosition(position);
                Log.e("đã cchonj---->  ","vị trí "+position);
                if(position!=0){
                    selectItemQCCVHT(st);
                    spnQC2.setAdapter(adaperSpiner);
                    Log.e("màn hình sổ tay SV: "," "+st.get_mucQuyChe());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("đã nghỉ cchonj---->  ","ádasdas");
            }
        });
        ///st 3
        List<QuyCheVCHT> l3= CacQuyCheCVHTBE.getQC3();
        lQCSearch.addAll(l3);
        ArrayList<QuyCheVCHT> arrayList3=new ArrayList<>();
        arrayList3.add(new QuyCheVCHT(0,"CHỨC NĂNG, NHIỆM VỤ VÀ QUYỀN HẠN " +
                "CỦA CỐ VÁN HỌC TẬP LỚP HỌC",""));
        arrayList3.addAll(l3);
        adaperSpiner=new SpinnerQCCVHTAdaper(mMain, R.layout.item_select_custom_dropdown, arrayList3);
        spnQC3.setAdapter(adaperSpiner);
        spnQC3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("đã cchonj---->  ","ádasdas");
                QuyCheVCHT st = (QuyCheVCHT) parent.getItemAtPosition(position);
                Log.e("đã cchonj---->  ","vị trí "+position);
                if(position!=0){
                    selectItemQCCVHT(st);
                    spnQC3.setAdapter(adaperSpiner);
                    Log.e("màn hình sổ tay SV: "," "+st.get_mucQuyChe());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("đã nghỉ cchonj---->  ","ádasdas");
            }
        });
        ///st 4
        List<QuyCheVCHT> l4= CacQuyCheCVHTBE.getQC4();
        lQCSearch.addAll(l4);
        ArrayList<QuyCheVCHT> arrayList4=new ArrayList<>();
        arrayList4.add(new QuyCheVCHT(0,"THỜI GIAN, NỘI DUNG LÀM VIỆC VÀ CHÉ ĐỘ BÁO CÁO " +
                "CỦA CÓ VÁN HỌC TẬP LỚP HỌC",""));
        arrayList4.addAll(l4);
        adaperSpiner=new SpinnerQCCVHTAdaper(mMain, R.layout.item_select_custom_dropdown, arrayList4);
        spnQC4.setAdapter(adaperSpiner);
        spnQC4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("đã cchonj---->  ","ádasdas");
                QuyCheVCHT st = (QuyCheVCHT) parent.getItemAtPosition(position);
                Log.e("đã cchonj---->  ","vị trí "+position);
                if(position!=0){
                    selectItemQCCVHT(st);
                    spnQC4.setAdapter(adaperSpiner);
                    Log.e("màn hình sổ tay SV: "," "+st.get_ndQuyChe());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("đã nghỉ cchonj---->  ","ádasdas");
            }
        });

    }
    private void selectItemQCCVHT(QuyCheVCHT st) {
        //Toast.makeText(fManActivityHomes, " "+qc.toString(), Toast.LENGTH_SHORT).show();
        if(isHien){
            isHien=false;
            AnHienList(isHien);
        }
        openDialog(Gravity.CENTER, st);
    }
    private void openDialog(int g, QuyCheVCHT qc){
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
    private void initDialog(Dialog d,QuyCheVCHT st){
        tvNameChuongQC =d.findViewById(R.id.tv_NameChuongDialogQCCVHT);
        tvNameMucQC =d.findViewById(R.id.tv_NameMucDialogQCCVHT);
        tvNoiDungMucQC =d.findViewById(R.id.tv_NoiDungMucDialogQCCVHT);
        btnCloseXemND=d.findViewById(R.id.btn_ThoatDialogQCCVHT);
        if(st!=null)
//        tvNameChuong.setText(spn1.getSelectedItemPosition());
            tvNameMucQC.setText(st.get_mucQuyChe());
        tvNoiDungMucQC.setText(st.get_ndQuyChe());
        btnCloseXemND.setOnClickListener(v->{d.dismiss();});
    }
}