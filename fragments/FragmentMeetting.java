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
    //khai báo hiện thị danh sách sinh viên
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
                Log.e("adaper---> ","xóa ds");
//                String idU=AuthAccount.getInstant().userAccount.get_id();
//                String idClas = clas.get_id();
                new AlertDialog.Builder(mMainActivityHomes)
                        .setTitle("Cảnh báo!")
                        .setMessage("Bạn có chắc chắn muốn xóa không?")
                        .setPositiveButton("Chấp nhân", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ClassSVBE.deleClassSV(clas);
                                Toast.makeText(mMainActivityHomes, "Xóa thành công", Toast.LENGTH_SHORT).show();
//                        ClassSVBE.lk();
                            }
                        })
                        .setNegativeButton("Thoát",null)
                        .show();
                //InstantSoTayCVHT.inItChuong4();
            }

            @Override
            public void ChuyenThongBaoQLClass(DSClassQL clas) {
//                Log.e("adaper---> ","chuyển thông báo QLClass");
                Intent intent = new Intent(mMainActivityHomes, MainActivityNotificationClassSV.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("classSV",clas);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        getViewModule();
    }
    //hiện thị danh sách sinh viên lên recyclerview
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
//                        Toast.makeText(mMainActivityHomes, "Nhắn tin", Toast.LENGTH_SHORT).show();
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
    //lấy dữ liệu list class từ vierw mocule
    private void getViewModule() {
//        ClassSVFactory factory =new ClassSVFactory()
        mClassSVViewModule=new ViewModelProvider(this).get(ClassSVViewModule.class);
        mClassSVViewModule.getMutableLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
//                Log.e("dât", "co");
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

    //lấy dữa liệu list sinh viên thuôc từ viewmodule
    private void getListUser(DSClassQL clas){
//        Toast.makeText(mMainActivityHomes, "xem dánh sách "+clas.get_nameClass(), Toast.LENGTH_SHORT).show();
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
    //đssếm sỉ số sinh viên
    private int CountSV(String ml){
        int ss=0;
        for (int i=0;i<mListUser.size();i++){
            if(mListUser.get(i).get_Class().equals(ml)){
                ss++;
            }
        }
        return ss;
    }
    //set dữ liệu lên recvierw
    private void setRCView(List<DSClassQL> l){
        mAdaper.setMlist(l);
        rcvListClassSV.setAdapter(mAdaper);
    }
    //set danh sách sinh viên chọn
    private void setRECViewSinhVienSelect(List<User> l , DSClassQL clas){
        ClassSVBE.updateSiSoClass(clas, l.size());
        adaper.setMl(l);
        rcvListUserSelect.setAdapter(adaper);
    }
    //kiểm tra lớp đã có chưa
    private boolean checkClassSV(String c){
        String idGV= AuthAccount.getInstant().userAccount.get_id();
        if(mListClassSV.size()==0)
            return true;
        for (int i=0;i<mListClassSV.size();i++){
            if(c.equals(mListClassSV.get(i).get_nameClass())) {
                if(idGV.equals(mListClassSV.get(i).get_idGV())){
                    Toast.makeText(mMainActivityHomes, "Lớp đã có", Toast.LENGTH_SHORT).show();
                    return false;
                } else if(!idGV.equals(mListClassSV.get(i).get_idGV())){
                    Toast.makeText(mMainActivityHomes, "Lớp đã có người quản lý", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        return true;
    }
    private void newClassSV(int key) {
        openDialog(key, null); //key=1 new class, key = 2 xem danh sashc sv
        //Toast.makeText(mMainActivityHomes, "Thêm cuộc họp", Toast.LENGTH_SHORT).show();
    }
    //mở dinalog
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
    //khởi tạo dinalog
    private void initDialog(Dialog d, int key, DSClassQL clas){
        tvAddOrSaveNoti=d.findViewById(R.id.tv_AddOrSaveNoti);
        imgSave=d.findViewById(R.id.img_Save);
        //khởi tạo phần thêm mới class
        tvNewClassSVOrListUserSelect=d.findViewById(R.id.tv_NewClassSVOrListUserSelect);
        rlSendThongBaoOrAddClass = d.findViewById(R.id.rl_SendThongBaoOrAddClass);
        rlCloseDialog= d.findViewById(R.id.rl_CloseDialog);
        edtGhichu=d.findViewById(R.id.edt_GhiThongBao);
        //Khởi tạo nút chức năng
        rlActionDanhSach=d.findViewById(R.id.rl_ActionDanhSach);
        rlActionChatLop=d.findViewById(R.id.rl_ActionChatLop);
        rlActionThongBao=d.findViewById(R.id.rl_ActionThongBao);
        rlActionCuohop=d.findViewById(R.id.rl_ActionCuocHop);
        lnChucNangQL=d.findViewById(R.id.ln_ChucNangQL);
        //khởi tạo phần xem danh sách
        imgcloseSearchSinhVienSelect=d.findViewById(R.id.img_closeSearchSinhVienSelect);
        lnSelectClassSV=d.findViewById(R.id.ln_SelectClassSV);
        ln_SearchSVInCLassSV=d.findViewById(R.id.ln_SearchSVInCLassSV);
        edtNameSVSerchInListClassSV=d.findViewById(R.id.txt_NameSVSerchInListClassSV);
        rcvListUserSelect=d.findViewById(R.id.rcv_ListUserSelect);
        spnNameClass=d.findViewById(R.id.spn_NameClass);

        if(key==1){
            Action(1);//tạo mới lớp
        }
        else {
            Action(2);//chức năng quản lý lớp
        }
        rlActionDanhSach.setOnClickListener(v->{
            Action(3);//chức năng xem danh sách lớp
            getListUser(clas);
            tvNewClassSVOrListUserSelect.setText("Danh sách lớp "+clas.get_nameClass());
        });
        rlActionThongBao.setOnClickListener(v->{
            isSenThongBao=true;
            tvNewClassSVOrListUserSelect.setText("Gửi thông báo đến lớp "+clas.get_nameClass());
            Action(4); // chức tạo mới và gửi xem thông báo
        });
        rlCloseDialog.setOnClickListener(v->{
            d.dismiss();
        });
        rlSendThongBaoOrAddClass.setOnClickListener(v->{
            //Action(5); // chức ăng nút lưu là lưu hoặc tạo mới
            LuuThongbao(clas);
        });
        //tạo cuộc họp
        rlActionCuohop.setOnClickListener(v->{
            Intent intent=new Intent(mMainActivityHomes, MainActivityUserListMeetting.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("Class", clas);
//            bundle.put
            intent.putExtras(bundle);
            startActivityForResult(intent, 2);
        });
        //hide search sinh viên select
        imgcloseSearchSinhVienSelect.setOnClickListener(v->{
//            Toast.makeText(mMainActivityHomes, "đóng keadbo", Toast.LENGTH_SHORT).show();
            hideSoftKeyboard(Objects.requireNonNull(getActivity()));
        });
        rlActionChatLop.setOnClickListener(v->{
            pageChatLop(clas);
//            Toast.makeText(mMainActivityHomes, "chat lớp", Toast.LENGTH_SHORT).show();
        });
    }
    //chat lớp
    private void pageChatLop(DSClassQL clas){
        User u =AuthAccount.getInstant().userAccount;
        ChatLop chatLop=new ChatLop(u.get_id(),u.get_id(),clas.get_nameClass(),u.get_Name(),"","");
        Intent intent =new Intent(mMainActivityHomes, MainActivityChatLop.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("chatlop", chatLop);
        intent.putExtras(bundle);
        startActivityForResult(intent,3);
    }
    // hàm lấy ds sinh viên
    public List<User> listUserThongbao(DSClassQL clas){
        Log.e("NotificationClassBE: ","tên lớp sv "+clas.get_nameClass());
        List<User> l=new ArrayList<>();
        for (int i=0;i<mListUser.size();i++) {
            if(mListUser.get(i).get_Class().equals(clas.get_nameClass())
                && mListUser.get(i).get_category()==2){
                l.add(mListUser.get(i));
            }
        }
        return l;
    }
    //lưu gửi thông báo, lưu thêm lớp mới
    public void LuuThongbao(DSClassQL clas){
        //isSendThongBao==true=> gửi thông báo đến lớp, ==false=> lưu thêm lớp mới
        if(isSenThongBao){
            Log.e("action---> ","gửi thông báo");
            String noidungThongBao = edtGhichu.getText().toString() ;
            String token = AuthAccount.getInstant().userAccount.get_Token();
            mListUserNoti = listUserThongbao(clas);
            NoticationClass noti=new NoticationClass(token, clas.get_nameClass(),noidungThongBao);
            ClassSVBE.NewNotification(clas, noidungThongBao);
            NotificationClassBE.getInstant().notificationClassBE.MakeAPI(mListUserNoti,noti );
            Toast.makeText(mMainActivityHomes, "Tạo thông báo thành công", Toast.LENGTH_SHORT).show();
        }
        else {
            String idU=AuthAccount.getInstant().userAccount.get_id();
            String nameClas= spnNameClass.getSelectedItem().toString().trim();
            int ss=CountSV(nameClas);
//            ClassSVBE.addClassSV(idU, nameClas,ss);
//            Toast.makeText(mMainActivityHomes, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
//            Log.e("action---> ","lưu thêm lớp mới");
            saveData();
        }
    }


    //xử lý chức ănng
    private void Action(int key){
        //key=1 chức năng thêm , key==2 Chức năng thêm danh sách , key = 3 thông báo
        if(key==1){
            Log.e("action-->  ","Chức năng thêm");
            getSpiner(spnNameClass);
            tvNewClassSVOrListUserSelect.setText("Thêm lớp mới");
            rcvListUserSelect.setVisibility(View.GONE);
            lnChucNangQL.setVisibility(View.GONE);
            edtGhichu.setVisibility(View.GONE);
            ln_SearchSVInCLassSV.setVisibility(View.GONE);
            imgSave.setImageResource(R.drawable.icon_check_true);
            //btnSenThongBao.setVisibility(View.VISIBLE);
            //btnSenThongBao.setText("Lưu lớp");
        }else if(key==2){
            Log.e("action-->  ","hiện thị Chức năng cần chọn");
            tvNewClassSVOrListUserSelect.setText("Chọn chức năng");
            rcvListUserSelect.setVisibility(View.GONE);
            lnChucNangQL.setVisibility(View.VISIBLE);
            edtGhichu.setVisibility(View.GONE);
            rlSendThongBaoOrAddClass.setVisibility(View.GONE);
            lnSelectClassSV.setVisibility(View.GONE);
            ln_SearchSVInCLassSV.setVisibility(View.GONE);
        }
        else if(key==3){
            Log.e("action-->  ","Chức năng danh sách");
            ln_SearchSVInCLassSV.setVisibility(View.VISIBLE);
            rcvListUserSelect.setVisibility(View.VISIBLE);
            lnSelectClassSV.setVisibility(View.GONE);
            rlSendThongBaoOrAddClass.setVisibility(View.GONE);
            lnChucNangQL.setVisibility(View.GONE);
        }else if(key==4){
            Log.e("action---> ","tạo thông báo");
//            tvNewClassSVOrListUserSelect.setText("Tạo thông báo đến lớp");
            edtGhichu.setVisibility(View.VISIBLE);
            rlSendThongBaoOrAddClass.setVisibility(View.VISIBLE);
            tvAddOrSaveNoti.setText("Gửi ");
            lnChucNangQL.setVisibility(View.GONE);
            rcvListUserSelect.setVisibility(View.GONE);
            ln_SearchSVInCLassSV.setVisibility(View.GONE);
        }
    }

    //đổ dữ liệu spiner
    private void getSpiner(Spinner spn){
        ArrayList<String> l = (ArrayList<String>) ClassSVBE.listLop();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, l);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(arrayAdapter);
    }
    //kiểm tra danh sách lớp
    private void saveData(){
        String idU=AuthAccount.getInstant().userAccount.get_id();
        String nameClas= spnNameClass.getSelectedItem().toString().trim();
        int ss=CountSV(nameClas);
//        Log.e("class ",""+nameClas+": ss: "+ss);
        if(checkClassSV(nameClas)){
            ClassSVBE.addClassSV(idU, nameClas,ss);
            Toast.makeText(mMainActivityHomes, "Thêm lớp thành công", Toast.LENGTH_SHORT).show();
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
//        Toast.makeText(mMainActivityHomes, "code về: "+resultCode, Toast.LENGTH_SHORT).show();
        //kiểm tra để load lại sánh sách sinh viên trong lớp
        if(resultCode==1){
            DSClassQL clas= (DSClassQL) data.getSerializableExtra("clas");
            int ss=CountSV(clas.get_nameClass());
            Log.e("data "," data về: size user: "+CountSV(clas.get_nameClass()));
            Log.e("data "," data về: class : "+clas.toString());

            getListUser(clas);
            clas.set_siso(ss);
            ClassSVBE.updateSiSoClass(clas, ss);

        }
    }
}