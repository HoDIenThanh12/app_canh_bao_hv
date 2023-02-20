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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.MainActivityHomes;
import com.example.tiki.app_canhbao.adappers.ListQuyCheCVHTSearchAdaper;
import com.example.tiki.app_canhbao.adappers.ListSoTayCVHTSearchAdaper;
import com.example.tiki.app_canhbao.adappers.ObjectDropDownAdaper;
import com.example.tiki.app_canhbao.adappers.SpinnerClassSVAdaper;
import com.example.tiki.app_canhbao.adappers.SpinnerQCCVHTAdaper;
import com.example.tiki.app_canhbao.adappers.SpinnerSoTayCVHTadaper;
import com.example.tiki.app_canhbao.backend.SoTayGVBE;
import com.example.tiki.app_canhbao.entity.QuyCheVCHT;
import com.example.tiki.app_canhbao.entity.SoTayCVHT;
import com.example.tiki.app_canhbao.interfaces.ActionListSoTayCVHTSearch;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FragmentSoTayCVHT extends Fragment {
    View fView;
    MainActivityHomes fManActivityHomes;
    Spinner spn1,spn2,spn3,spn4;
    Dialog fDialog;
    SpinnerSoTayCVHTadaper adaper1,adaper2,adaper3,adaper4 ;
    TextView tvNameChuong, tvNameMuc, tvNoiDungMuc;
    Button btnThoatXemNoiDung;
    EditText edtsearchQuyChe;
    ImageView imgcloseSearchQuytChe ;
    RecyclerView rcvlistSearchQuyChe;
    ListSoTayCVHTSearchAdaper adaperSearchSoTay;
    boolean isHien=false;
    boolean isKeyBord=false;
    List<SoTayCVHT> lSTSearch;
    public FragmentSoTayCVHT() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fView= inflater.inflate(R.layout.fragment_so_tay_c_v_h_t, container, false);
        fManActivityHomes= (MainActivityHomes) getActivity();
        inits(fView);
        return fView;
    }

    private void inits(View v) {
        lSTSearch=new ArrayList<>();
        spn1 = v.findViewById(R.id.spn_QCDTTHTTT1);
        spn2= v.findViewById(R.id.spn_QCCTSV2);
        spn3= v.findViewById(R.id.spn_QCCVHT3);
        spn4= v.findViewById(R.id.spn_QDPCCVHT4);
        edtsearchQuyChe = fView.findViewById(R.id.edt_searchQuyChe);
        imgcloseSearchQuytChe=fView.findViewById(R.id.img_closeSearchQuytChe);
        LinearLayoutManager manager = new LinearLayoutManager(fManActivityHomes);
        RecyclerView.ItemDecoration item= new DividerItemDecoration(fManActivityHomes, DividerItemDecoration.VERTICAL);
        rcvlistSearchQuyChe=fView.findViewById(R.id.rcv_listSearchQuyChe);
        rcvlistSearchQuyChe.setLayoutManager(manager);
        rcvlistSearchQuyChe.addItemDecoration(item);
        getDataSpinner();
        clickEditText();
        AnHienList(isHien);
        adaperSearchSoTay=new ListSoTayCVHTSearchAdaper(fManActivityHomes, new ActionListSoTayCVHTSearch() {
            @Override
            public void selectSoTayCVHTSearch(SoTayCVHT st) {
                selectItemQCCVHT(st);
            }
        });
        getMucSearchQuyCheCVHT();
    }
    private void clickEditText(){
        edtsearchQuyChe.setOnClickListener(v->{
//            Toast.makeText(fManActivityHomes, "Đang chọn", Toast.LENGTH_SHORT).show();
            Log.e("fragment sổ tay cv: ","đang trỏ tới để nhập tìm kiếm");
            isHien=true;
            AnHienList(isHien);
        });
        edtsearchQuyChe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("fragment sổ tay cv: ","trước nhập");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Log.e("fragment sổ tay cv: ","đang nhập");
//                if(s.toString().length()>0){
//                    isHien=true;
//                    AnHienList(isHien);
//                }else if(s.toString().length()==0){
//                    isHien=false;
//                    AnHienList(isHien);
//                }
                adaperSearchSoTay.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("fragment sổ tay cv: ","sau nập");
            }
        });
        edtsearchQuyChe.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                isHien=true;
                AnHienList(isHien);
                Log.e("fragment sổ tay cv: ","focus");
            }
        });
//        edtsearchQuyChe.a
        imgcloseSearchQuytChe.setOnClickListener(v->{
            if(isHien){
                hideSoftKeyboard(fManActivityHomes);
                edtsearchQuyChe.setText("");
                isHien=false;
                AnHienList(isHien);
            }

        });
    }
    public void AnHienList(boolean isHien){
        if(isHien){
            rcvlistSearchQuyChe.setVisibility(View.VISIBLE);
        }
        else{
            rcvlistSearchQuyChe.setVisibility(View.GONE);
//            hideSoftKeyboard(getActivity());
        }
    }
    private void getMucSearchQuyCheCVHT(){
        for (int i=0;i<lSTSearch.size();i++){
            lSTSearch.get(i).set_id(i+1);
        }
        adaperSearchSoTay.setList1(lSTSearch);
        rcvlistSearchQuyChe.setAdapter(adaperSearchSoTay);
    }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
    private void getDataSpinner() {
        ///qc1
        List<SoTayCVHT> l1=SoTayGVBE.getQC1();
        lSTSearch.addAll(l1);
        ArrayList<SoTayCVHT> arrayList1=new ArrayList<>();
        arrayList1.add(new SoTayCVHT(0,"Chương 1",""));
        arrayList1.addAll(l1);
        adaper1=new SpinnerSoTayCVHTadaper(fManActivityHomes, R.layout.item_select_custom_dropdown, arrayList1);
        spn1.setAdapter(adaper1);
        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("đã cchonj---->  ","ádasdas");

                SoTayCVHT qc = (SoTayCVHT) parent.getItemAtPosition(position);
                Log.e("đã cchonj---->  ","vị trí "+position);
                if(position!=0){
//                    hideSoftKeyboard(getActivity());
                    selectItemQCCVHT(qc);
                    spn1.setAdapter(adaper1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("đã nghỉ cchonj---->  ","ádasdas");
            }
        });
        //qc2///////////////////////
        List<SoTayCVHT> l2=SoTayGVBE.getQC2();
        lSTSearch.addAll(l2);
        ArrayList<SoTayCVHT> arrayList2=new ArrayList<>();
        arrayList2.add(new SoTayCVHT(0,"Chương 2",""));
        arrayList2.addAll(l2);
        adaper2=new SpinnerSoTayCVHTadaper(fManActivityHomes, R.layout.item_select_custom_dropdown, arrayList2);
        spn2.setAdapter(adaper2);
        spn2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("đã cchonj---->  ","ádasdas");
                SoTayCVHT qc = (SoTayCVHT) parent.getItemAtPosition(position);
                if(position!=0) {
//                    hideSoftKeyboard(fManActivityHomes);
                    selectItemQCCVHT(qc);
                    spn2.setAdapter(adaper2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Log.e("đã nghỉ cchonj---->  ","ádasdas");
            }
        });
        //qc3/////////////////////////
        List<SoTayCVHT> l3=SoTayGVBE.getQC3();
        lSTSearch.addAll(l3);
        ArrayList<SoTayCVHT> arrayList3=new ArrayList<>();
        arrayList3.add(new SoTayCVHT(0,"Chương 3",""));
        arrayList3.addAll(l3);
        adaper3=new SpinnerSoTayCVHTadaper(fManActivityHomes, R.layout.item_select_custom_dropdown, arrayList3);
        spn3.setAdapter(adaper3);
        spn3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("đã cchonj---->  ","ádasdas");
                SoTayCVHT qc = (SoTayCVHT) parent.getItemAtPosition(position);
                if(position!=0){
//                    hideSoftKeyboard(getActivity());
                    selectItemQCCVHT(qc);
                    spn3.setAdapter(adaper3);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Log.e("đã nghỉ cchonj---->  ","ádasdas");
            }
        });
        //qc4////////////////////////
        List<SoTayCVHT> l4=SoTayGVBE.getQC4();
        lSTSearch.addAll(l4);
        ArrayList<SoTayCVHT> arrayList4=new ArrayList<>();
        arrayList4.add(new SoTayCVHT(0,"Chương 4",""));
        arrayList4.addAll(l4);
        adaper4=new SpinnerSoTayCVHTadaper(fManActivityHomes, R.layout.item_select_custom_dropdown, arrayList4);
        spn4.setAdapter(adaper4);
        spn4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.e("đã cchonj---->  ","ádasdas");
                SoTayCVHT qc = (SoTayCVHT) parent.getItemAtPosition(position);
                if(position!=0) {
//                    hideSoftKeyboard(getActivity());
                    selectItemQCCVHT(qc);
                    spn4.setAdapter(adaper4);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Log.e("đã nghỉ cchonj---->  ","ádasdas");
            }
        });
//        Log.e("fragment so tay cvht: ","size: "+lSTSearch.size());
//        spn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                hideSoftKeyboard(getActivity());
//            }
//        });

    }

    private void selectItemQCCVHT(SoTayCVHT qc) {
        //Toast.makeText(fManActivityHomes, " "+qc.toString(), Toast.LENGTH_SHORT).show();
//        hideSoftKeyboard(getActivity());
        if(isHien){
            isHien=false;
            AnHienList(isHien);
        }
        openDialog(Gravity.CENTER, qc);
//        hideSoftKeyboard(getActivity());
    }
    private void openDialog(int g,SoTayCVHT qc){
        final Dialog dialog=new Dialog(fManActivityHomes);
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
    private void initDialog(Dialog d,SoTayCVHT st){
        tvNameChuong =d.findViewById(R.id.tv_NameChuongDialogQCCVHT);
        tvNameMuc =d.findViewById(R.id.tv_NameMucDialogQCCVHT);
        tvNoiDungMuc =d.findViewById(R.id.tv_NoiDungMucDialogQCCVHT);
        btnThoatXemNoiDung=d.findViewById(R.id.btn_ThoatDialogQCCVHT);
        if(st!=null)
//        tvNameChuong.setText(spn1.getSelectedItemPosition());
        tvNameMuc.setText(st.get_mucSoTay());
        tvNoiDungMuc.setText(st.get_ndSoTay());
        btnThoatXemNoiDung.setOnClickListener(v->{d.dismiss();});
    }
}