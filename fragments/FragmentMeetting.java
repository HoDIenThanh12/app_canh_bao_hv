package com.example.tiki.app_canhbao.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.MainActivityHomes;
import com.example.tiki.app_canhbao.adappers.ClassSVAdaper;
import com.example.tiki.app_canhbao.adappers.UserClassSelectAdaper;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.backend.ClassSVBE;
import com.example.tiki.app_canhbao.backend.NotificationClassBE;
import com.example.tiki.app_canhbao.entity.ChatLop;
import com.example.tiki.app_canhbao.entity.ClassSV;
import com.example.tiki.app_canhbao.entity.DSClassQL;
import com.example.tiki.app_canhbao.entity.NoticationClass;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.interfaces.ActionNoteSV;
import com.example.tiki.app_canhbao.interfaces.EventClassSV;
import com.example.tiki.app_canhbao.viewmodel.ClassSVViewModule;
import com.example.tiki.app_canhbao.views.MainActivityChat;
import com.example.tiki.app_canhbao.views.MainActivityChatLop;
import com.example.tiki.app_canhbao.views.MainActivityNoteSinhVien;
import com.example.tiki.app_canhbao.views.MainActivityNotificationClassSV;
import com.example.tiki.app_canhbao.views.MainActivityQuanLyChat;
import com.example.tiki.app_canhbao.views.MainActivityUserListMeetting;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FragmentMeetting extends Fragment {
    private View fView;
    private ImageButton fimgbtnNewClassSV;
    ImageView imgcloseSearchSinhVienSelect, imgSave;
    private RecyclerView rcvListClassSV;
    private MainActivityHomes mMainActivityHomes;
    RelativeLayout rlActionDanhSach, rlActionChatLop, rlActionThongBao, rlActionCuohop;
    TextView tvAddOrSaveNoti;
//    Button btnSenThongBao, btnCloseDialog;

    RelativeLayout rlSendThongBaoOrAddClass, rlCloseDialog;
    Spinner spnNameClass;
    //import recyclerview
    List<DSClassQL> mListClassSV;
    List<User> mListUser;
    List<User> mListUserNoti=new ArrayList<>();
    //viewmodule
    ClassSVViewModule mClassSVViewModule;
    ClassSVAdaper mAdaper;
    UserClassSelectAdaper adaper;
    //khai b??o hi???n th??? danh s??ch sinh vi??n
    TextView tvNewClassSVOrListUserSelect;
    LinearLayout lnSelectClassSV, ln_SearchSVInCLassSV, lnChucNangQL;
    EditText edtNameSVSerchInListClassSV, edtGhichu;
    RecyclerView rcvListUserSelect;
    boolean isSenThongBao=false, isKey=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fView= inflater.inflate(R.layout.fragment_meetting, container, false);
        mMainActivityHomes= (MainActivityHomes) getActivity();
        inits();
        return fView;
    }
    private void inits(){

        fimgbtnNewClassSV=fView.findViewById(R.id.bnt_NewMeettingF);
        fimgbtnNewClassSV.setOnClickListener(v->{
            isSenThongBao=false;
            newClassSV(1);
        });
        LinearLayoutManager manager = new LinearLayoutManager(mMainActivityHomes);
        rcvListClassSV = fView.findViewById(R.id.rcv_ListClassSV);
        rcvListClassSV.setLayoutManager(manager);
        mListClassSV= new ArrayList<>();
        mListUser=new ArrayList<>();
        mAdaper = new ClassSVAdaper(new EventClassSV() {
            @Override
            public void ShowListClassSV(DSClassQL clas) {
//                Log.e("adaper---> ","xem ds");
                openDialog(2, clas);
                getLisstUserClassSVSelct(clas);
            }

            @Override
            public void DeleClassSV(DSClassQL clas) {
                Log.e("adaper---> ","x??a ds");
//                String idU=AuthAccount.getInstant().userAccount.get_id();
//                String idClas = clas.get_id();
                new AlertDialog.Builder(mMainActivityHomes)
                        .setTitle("C???nh b??o!")
                        .setMessage("B???n c?? ch???c ch???n mu???n x??a kh??ng?")
                        .setPositiveButton("Ch???p nh??n", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ClassSVBE.deleClassSV(clas);
                                Toast.makeText(mMainActivityHomes, "X??a th??nh c??ng", Toast.LENGTH_SHORT).show();
//                        ClassSVBE.lk();
                            }
                        })
                        .setNegativeButton("Tho??t",null)
                        .show();
                //InstantSoTayCVHT.inItChuong4();
            }

            @Override
            public void ChuyenThongBaoQLClass(DSClassQL clas) {
//                Log.e("adaper---> ","chuy???n th??ng b??o QLClass");
                Intent intent = new Intent(mMainActivityHomes, MainActivityNotificationClassSV.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("classSV",clas);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        getViewModule();
    }
    //hi???n th??? danh s??ch sinh vi??n l??n recyclerview
    private void getLisstUserClassSVSelct(DSClassQL clas){
//        if(mListUser!=null){
//            List<User> l=new ArrayList<>();
//            l.clear();
//            for (int i=0;i<mListUser.size();i++) {
//                if(clas.get_nameClass().equals(mListUser.get(i).get_Class())
//                && mListUser.get(i).get_category()!=1){
//                    l.add(mListUser.get(i));
//                }
//            }
////            Log.e("ds---> ","size: "+mListUser.size());
//            if(l!=null){
                 adaper =new UserClassSelectAdaper(mMainActivityHomes, new ActionNoteSV() {
                    @Override
                    public void noteSV(User u) {
                        Intent intent =new Intent(mMainActivityHomes, MainActivityNoteSinhVien.class);
                        Bundle bundle =new Bundle();
                        bundle.putSerializable("users", u);
                        bundle.putSerializable("clas", clas);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, 1);
                    }

                    @Override
                    public void ChatSV(User u) {
//                        Toast.makeText(mMainActivityHomes, "Nh???n tin", Toast.LENGTH_SHORT).show();
                        User u2= AuthAccount.getInstant().userAccount;
                        Intent intent=new Intent(mMainActivityHomes, MainActivityChat.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("UserGui",u);
                        bundle.putSerializable("UseNhan",u2);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

                LinearLayoutManager manager = new LinearLayoutManager(mMainActivityHomes);
                rcvListUserSelect.setLayoutManager(manager);
//                RecyclerView.ItemDecoration item= new DividerItemDecoration(mMainActivityHomes, DividerItemDecoration.VERTICAL);
//                rcvListUserSelect.addItemDecoration(item);
                edtNameSVSerchInListClassSV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!isKey){
                            isKey=true;
                        }
                    }
                });
                edtNameSVSerchInListClassSV.addTextChangedListener(new TextWatcher() {
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
                getListUser(clas);
//            }
//        }
    }
    //l???y d??? li???u list class t??? vierw mocule
    private void getViewModule() {
//        ClassSVFactory factory =new ClassSVFactory()
        mClassSVViewModule=new ViewModelProvider(this).get(ClassSVViewModule.class);
        mClassSVViewModule.getMutableLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
//                Log.e("d??t", "co");
            }
        });
        List<DSClassQL> l=new ArrayList<>();
        mClassSVViewModule.getmClassSV().observe(this, new Observer<List<DSClassQL>>() {
             @Override
             public void onChanged(List<DSClassQL> classSVS) {
                 if(classSVS!=null){
                     l.clear();
                     mListClassSV.clear();
                     mListClassSV.addAll(classSVS);
                     for(int i=0;i<classSVS.size();i++){
                         if(classSVS.get(i).get_idGV().equals(AuthAccount.getInstant().userAccount.get_id())){
                             l.add(classSVS.get(i));
                         }
                     }
                     setRCView(l);
                 }
             }
        });
//        getListUser();
        //dsMau();
    }

    //l???y d???a li???u list sinh vi??n thu??c t??? viewmodule
    private void getListUser(DSClassQL clas){
//        Toast.makeText(mMainActivityHomes, "xem d??nh s??ch "+clas.get_nameClass(), Toast.LENGTH_SHORT).show();
        mListUser.clear();
        mClassSVViewModule.getmUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
//                Log.e("lenth list user: ", ""+users.size());
                if(users!=null) {
                    mListUser.clear();
//                    mListUser.addAll(users);
                    for (int i=0; i<users.size();i++ ) {
                        User u =users.get(i);
                        if(clas.get_nameClass().equals(u.get_Class()) && u.get_category()==2){
                            mListUser.add(u);
//                            Log.e("name class: ",""+u.get_Class());
                        }
                    }
                }
            }
        });
        setRECViewSinhVienSelect(mListUser, clas);
    }
    //??ss???m s??? s??? sinh vi??n
    private int CountSV(String ml){
        int ss=0;
        for (int i=0;i<mListUser.size();i++){
            if(mListUser.get(i).get_Class().equals(ml)){
                ss++;
            }
        }
        return ss;
    }
    //set d??? li???u l??n recvierw
    private void setRCView(List<DSClassQL> l){
        mAdaper.setMlist(l);
        rcvListClassSV.setAdapter(mAdaper);
    }
    //set danh s??ch sinh vi??n ch???n
    private void setRECViewSinhVienSelect(List<User> l , DSClassQL clas){
        ClassSVBE.updateSiSoClass(clas, l.size());
        adaper.setMl(l);
        rcvListUserSelect.setAdapter(adaper);
    }
    //ki???m tra l???p ???? c?? ch??a
    private boolean checkClassSV(String c){
        String idGV= AuthAccount.getInstant().userAccount.get_id();
        if(mListClassSV.size()==0)
            return true;
        for (int i=0;i<mListClassSV.size();i++){
            if(c.equals(mListClassSV.get(i).get_nameClass())) {
                if(idGV.equals(mListClassSV.get(i).get_idGV())){
                    Toast.makeText(mMainActivityHomes, "L???p ???? c??", Toast.LENGTH_SHORT).show();
                    return false;
                } else if(!idGV.equals(mListClassSV.get(i).get_idGV())){
                    Toast.makeText(mMainActivityHomes, "L???p ???? c?? ng?????i qu???n l??", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        return true;
    }
    private void newClassSV(int key) {
        openDialog(key, null); //key=1 new class, key = 2 xem danh sashc sv
        //Toast.makeText(mMainActivityHomes, "Th??m cu???c h???p", Toast.LENGTH_SHORT).show();
    }
    //m??? dinalog
    private void openDialog(int key, DSClassQL clas){
        //key=1 new class, key = 2 xem danh sashc sv
        final Dialog dialog=new Dialog(mMainActivityHomes);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_meeting);
        Window window=dialog.getWindow();
        if(window==null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams params =window.getAttributes();
        window.setAttributes(params);
        initDialog(dialog, key, clas);//key=1 new class, key = 2 xem danh sashc sv
        dialog.show();
    }
    //kh???i t???o dinalog
    private void initDialog(Dialog d, int key, DSClassQL clas){
        tvAddOrSaveNoti=d.findViewById(R.id.tv_AddOrSaveNoti);
        imgSave=d.findViewById(R.id.img_Save);
        //kh???i t???o ph???n th??m m???i class
        tvNewClassSVOrListUserSelect=d.findViewById(R.id.tv_NewClassSVOrListUserSelect);
        rlSendThongBaoOrAddClass = d.findViewById(R.id.rl_SendThongBaoOrAddClass);
        rlCloseDialog= d.findViewById(R.id.rl_CloseDialog);
        edtGhichu=d.findViewById(R.id.edt_GhiThongBao);
        //Kh???i t???o n??t ch???c n??ng
        rlActionDanhSach=d.findViewById(R.id.rl_ActionDanhSach);
        rlActionChatLop=d.findViewById(R.id.rl_ActionChatLop);
        rlActionThongBao=d.findViewById(R.id.rl_ActionThongBao);
        rlActionCuohop=d.findViewById(R.id.rl_ActionCuocHop);
        lnChucNangQL=d.findViewById(R.id.ln_ChucNangQL);
        //kh???i t???o ph???n xem danh s??ch
        imgcloseSearchSinhVienSelect=d.findViewById(R.id.img_closeSearchSinhVienSelect);
        lnSelectClassSV=d.findViewById(R.id.ln_SelectClassSV);
        ln_SearchSVInCLassSV=d.findViewById(R.id.ln_SearchSVInCLassSV);
        edtNameSVSerchInListClassSV=d.findViewById(R.id.txt_NameSVSerchInListClassSV);
        rcvListUserSelect=d.findViewById(R.id.rcv_ListUserSelect);
        spnNameClass=d.findViewById(R.id.spn_NameClass);

        if(key==1){
            Action(1);//t???o m???i l???p
        }
        else {
            Action(2);//ch???c n??ng qu???n l?? l???p
        }
        rlActionDanhSach.setOnClickListener(v->{
            Action(3);//ch???c n??ng xem danh s??ch l???p
            getListUser(clas);
            tvNewClassSVOrListUserSelect.setText("Danh s??ch l???p "+clas.get_nameClass());
        });
        rlActionThongBao.setOnClickListener(v->{
            isSenThongBao=true;
            tvNewClassSVOrListUserSelect.setText("G???i th??ng b??o ?????n l???p "+clas.get_nameClass());
            Action(4); // ch???c t???o m???i v?? g???i xem th??ng b??o
        });
        rlCloseDialog.setOnClickListener(v->{
            d.dismiss();
        });
        rlSendThongBaoOrAddClass.setOnClickListener(v->{
            //Action(5); // ch???c ??ng n??t l??u l?? l??u ho???c t???o m???i
            LuuThongbao(clas);
        });
        //t???o cu???c h???p
        rlActionCuohop.setOnClickListener(v->{
            Intent intent=new Intent(mMainActivityHomes, MainActivityUserListMeetting.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("Class", clas);
//            bundle.put
            intent.putExtras(bundle);
            startActivityForResult(intent, 2);
        });
        //hide search sinh vi??n select
        imgcloseSearchSinhVienSelect.setOnClickListener(v->{
//            Toast.makeText(mMainActivityHomes, "????ng keadbo", Toast.LENGTH_SHORT).show();
            hideSoftKeyboard(Objects.requireNonNull(getActivity()));
        });
        rlActionChatLop.setOnClickListener(v->{
            pageChatLop(clas);
//            Toast.makeText(mMainActivityHomes, "chat l???p", Toast.LENGTH_SHORT).show();
        });
    }
    //chat l???p
    private void pageChatLop(DSClassQL clas){
        User u =AuthAccount.getInstant().userAccount;
        ChatLop chatLop=new ChatLop(u.get_id(),u.get_id(),clas.get_nameClass(),u.get_Name(),"","");
        Intent intent =new Intent(mMainActivityHomes, MainActivityChatLop.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("chatlop", chatLop);
        intent.putExtras(bundle);
        startActivityForResult(intent,3);
    }
    // h??m l???y ds sinh vi??n
    public List<User> listUserThongbao(DSClassQL clas){
        Log.e("NotificationClassBE: ","t??n l???p sv "+clas.get_nameClass());
        List<User> l=new ArrayList<>();
        for (int i=0;i<mListUser.size();i++) {
            if(mListUser.get(i).get_Class().equals(clas.get_nameClass())
                && mListUser.get(i).get_category()==2){
                l.add(mListUser.get(i));
            }
        }
        return l;
    }
    //l??u g???i th??ng b??o, l??u th??m l???p m???i
    public void LuuThongbao(DSClassQL clas){
        //isSendThongBao==true=> g???i th??ng b??o ?????n l???p, ==false=> l??u th??m l???p m???i
        if(isSenThongBao){
            Log.e("action---> ","g???i th??ng b??o");
            String noidungThongBao = edtGhichu.getText().toString() ;
            String token = AuthAccount.getInstant().userAccount.get_Token();
            mListUserNoti = listUserThongbao(clas);
            NoticationClass noti=new NoticationClass(token, clas.get_nameClass(),noidungThongBao);
            ClassSVBE.NewNotification(clas, noidungThongBao);
            NotificationClassBE.getInstant().notificationClassBE.MakeAPI(mListUserNoti,noti );
            Toast.makeText(mMainActivityHomes, "T???o th??ng b??o th??nh c??ng", Toast.LENGTH_SHORT).show();
        }
        else {
            String idU=AuthAccount.getInstant().userAccount.get_id();
            String nameClas= spnNameClass.getSelectedItem().toString().trim();
            int ss=CountSV(nameClas);
//            ClassSVBE.addClassSV(idU, nameClas,ss);
//            Toast.makeText(mMainActivityHomes, "Th??m m???i th??nh c??ng", Toast.LENGTH_SHORT).show();
//            Log.e("action---> ","l??u th??m l???p m???i");
            saveData();
        }
    }


    //x??? l?? ch???c ??nng
    private void Action(int key){
        //key=1 ch???c n??ng th??m , key==2 Ch???c n??ng th??m danh s??ch , key = 3 th??ng b??o
        if(key==1){
            Log.e("action-->  ","Ch???c n??ng th??m");
            getSpiner(spnNameClass);
            tvNewClassSVOrListUserSelect.setText("Th??m l???p m???i");
            rcvListUserSelect.setVisibility(View.GONE);
            lnChucNangQL.setVisibility(View.GONE);
            edtGhichu.setVisibility(View.GONE);
            ln_SearchSVInCLassSV.setVisibility(View.GONE);
            imgSave.setImageResource(R.drawable.icon_check_true);
            //btnSenThongBao.setVisibility(View.VISIBLE);
            //btnSenThongBao.setText("L??u l???p");
        }else if(key==2){
            Log.e("action-->  ","hi???n th??? Ch???c n??ng c???n ch???n");
            tvNewClassSVOrListUserSelect.setText("Ch???n ch???c n??ng");
            rcvListUserSelect.setVisibility(View.GONE);
            lnChucNangQL.setVisibility(View.VISIBLE);
            edtGhichu.setVisibility(View.GONE);
            rlSendThongBaoOrAddClass.setVisibility(View.GONE);
            lnSelectClassSV.setVisibility(View.GONE);
            ln_SearchSVInCLassSV.setVisibility(View.GONE);
        }
        else if(key==3){
            Log.e("action-->  ","Ch???c n??ng danh s??ch");
            ln_SearchSVInCLassSV.setVisibility(View.VISIBLE);
            rcvListUserSelect.setVisibility(View.VISIBLE);
            lnSelectClassSV.setVisibility(View.GONE);
            rlSendThongBaoOrAddClass.setVisibility(View.GONE);
            lnChucNangQL.setVisibility(View.GONE);
        }else if(key==4){
            Log.e("action---> ","t???o th??ng b??o");
//            tvNewClassSVOrListUserSelect.setText("T???o th??ng b??o ?????n l???p");
            edtGhichu.setVisibility(View.VISIBLE);
            rlSendThongBaoOrAddClass.setVisibility(View.VISIBLE);
            tvAddOrSaveNoti.setText("G???i ");
            lnChucNangQL.setVisibility(View.GONE);
            rcvListUserSelect.setVisibility(View.GONE);
            ln_SearchSVInCLassSV.setVisibility(View.GONE);
        }
    }

    //????? d??? li???u spiner
    private void getSpiner(Spinner spn){
        ArrayList<String> l = (ArrayList<String>) ClassSVBE.listLop();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, l);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(arrayAdapter);
    }
    //ki???m tra danh s??ch l???p
    private void saveData(){
        String idU=AuthAccount.getInstant().userAccount.get_id();
        String nameClas= spnNameClass.getSelectedItem().toString().trim();
        int ss=CountSV(nameClas);
//        Log.e("class ",""+nameClas+": ss: "+ss);
        if(checkClassSV(nameClas)){
            ClassSVBE.addClassSV(idU, nameClas,ss);
            Toast.makeText(mMainActivityHomes, "Th??m l???p th??nh c??ng", Toast.LENGTH_SHORT).show();
            Log.e("class ",""+nameClas+": ss: "+ss);
        }
    }
    public  void hideSoftKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(activity!=null)
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Toast.makeText(mMainActivityHomes, "code v???: "+resultCode, Toast.LENGTH_SHORT).show();
        //ki???m tra ????? load l???i s??nh s??ch sinh vi??n trong l???p
        if(resultCode==1){
            DSClassQL clas= (DSClassQL) data.getSerializableExtra("clas");
            int ss=CountSV(clas.get_nameClass());
            Log.e("data "," data v???: size user: "+CountSV(clas.get_nameClass()));
            Log.e("data "," data v???: class : "+clas.toString());

            getListUser(clas);
            clas.set_siso(ss);
            ClassSVBE.updateSiSoClass(clas, ss);

        }
    }
}