package com.example.tiki.app_canhbao.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.adappers.ListCuocHopAdaper;
import com.example.tiki.app_canhbao.adappers.ListUserAdaper;
import com.example.tiki.app_canhbao.adappers.ListUserMeettingSelectAdaper;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.backend.CuocHopBE;
import com.example.tiki.app_canhbao.backend.MeettingUserBE;
import com.example.tiki.app_canhbao.backend.NotificationClassBE;
import com.example.tiki.app_canhbao.entity.ClassSV;
import com.example.tiki.app_canhbao.entity.ClassSVNotification;
import com.example.tiki.app_canhbao.entity.DSClassQL;
import com.example.tiki.app_canhbao.entity.Meettings;
import com.example.tiki.app_canhbao.entity.NoticationClass;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.factorys.CuocHopfactory;
import com.example.tiki.app_canhbao.interfaces.ActionCuocHop;
import com.example.tiki.app_canhbao.interfaces.EventUserMeetting;
import com.example.tiki.app_canhbao.viewmodel.ClassSVViewModule;
import com.example.tiki.app_canhbao.viewmodel.CuocHopViewModule;
import com.example.tiki.app_canhbao.viewmodel.ListUserMeettingViewModel;
import com.example.tiki.app_canhbao.viewmodel.ListUserMetingFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainActivityUserListMeetting extends AppCompatActivity {
    TextView tvThoigianCuocHop, tvNgayCuocHop, tvSiSoCuocHop, tvNameMeettingList, tvSaveOrAddMeeting, tvabout;
    RelativeLayout  rlNewCuocHop;
    EditText edtNameCuocHop, edtNoiDungCuocHop, edtSearchCuocHop, edtIdPhongCuocHop ;
    RecyclerView rcvListMeetting;
    ImageView ingcloseSearch;
    //viewmodule
    //view module dialog
    RelativeLayout rlSaveOrNewCuocHop, rlCloseDialogCuocHop;
    DSClassQL clas;
    boolean isSave=false;
    List<Meettings> mListCuocHop;
    CuocHopViewModule mCuocHopViewModule;
    ListCuocHopAdaper mAdaperCuocHop;
    List<User> lUser;
    ClassSVViewModule mClassSVViewModule;
    int cate=0;
    boolean isKeyBoard=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_list_meetting);
        inits();
    }
    private void inits() {
        lUser=new ArrayList<>();
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        Intent intent=getIntent();
        Bundle bundle= intent.getExtras();
         clas = (DSClassQL) bundle.getSerializable("Class");
//        Log.e("Main cuochop: "," dữ liệu nhận: "+clas.toString());
        mListCuocHop=new ArrayList<>();
        rlNewCuocHop=findViewById(R.id.rl_NewCuocHop);
        edtSearchCuocHop=findViewById(R.id.txt_keyNameCuocHop);
        tvSiSoCuocHop=findViewById(R.id.tv_SiSoCuocHop);
        rlNewCuocHop.setOnClickListener(v->{isSave=false;openDialogCuocHop(1,clas, null); });
        rcvListMeetting=findViewById(R.id.rcv_ListCuocHop);
        tvNameMeettingList=findViewById(R.id.tv_NameMeettingList);
        tvNameMeettingList.setText("Quản lý cuộc họp "+clas.get_nameClass());
        ingcloseSearch=findViewById(R.id.ing_closeSearch);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        RecyclerView.ItemDecoration item= new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvListMeetting.setLayoutManager(manager);
//        rcvListMeetting.addItemDecoration(item);
        cate = AuthAccount.getInstant().userAccount.get_category();
        if(cate==2){
            rlNewCuocHop.setVisibility(View.GONE);
        }
        getListUser();
        getAllCuocHop(clas);
        getWitchSearch();

    }
    // hàm lấy ds sinh viên
    public List<User> listUserThongbao(DSClassQL clas){
        Log.e("NotificationClassBE: ","tên lớp sv "+clas.get_nameClass());
        List<User> l=new ArrayList<>();
        for (int i=0;i<lUser.size();i++) {
            if(lUser.get(i).get_Class().equals(clas.get_nameClass())
                    && lUser.get(i).get_category()==2){
                l.add(lUser.get(i));
            }
        }
        return l;
    }
    private void getListUser() {
        mClassSVViewModule=new ViewModelProvider(this).get(ClassSVViewModule.class);
        mClassSVViewModule.getmUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
//                Log.e("lenth list user: ", ""+users.size());
                if(users!=null) {
                    lUser.clear();
                    lUser.addAll(users);
//                    Log.e("main cuộc họp: ","size: "+lUser.size());

                }
            }
        });
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
    private void getWitchSearch() {
        edtSearchCuocHop.setOnClickListener(v->{isKeyBoard=true;});
        ingcloseSearch.setOnClickListener(v->{
            if(isKeyBoard) {
                closeSoftKeyboard(this);
                isKeyBoard=false;
            }
        });
        edtSearchCuocHop.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdaperCuocHop.getFilter().filter(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    public static void closeSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(activity!=null){
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
    private void getAllCuocHop(DSClassQL clas) {
        mAdaperCuocHop=new ListCuocHopAdaper(MainActivityUserListMeetting.this, new ActionCuocHop() {
            @Override
            public void chitietCuocHop(Meettings m) {
                openDialogCuocHop(3,clas, m);
            }

            @Override
            public void updatecuocHop(Meettings m) {
                isSave=true;
                openDialogCuocHop(2,clas, m);
            }

            @Override
            public void deleCuocHop(Meettings m) {
                new AlertDialog.Builder(MainActivityUserListMeetting.this)
                        .setTitle("Cảnh báo!")
                        .setMessage("Bạn có chắc chắn muốn xóa không?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                        CuocHopBE.deleteCuocHop(m );
                        Toast.makeText(MainActivityUserListMeetting.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .show();
            }
        });

        CuocHopfactory cuocHopfactory=new CuocHopfactory(clas);
        mCuocHopViewModule = new ViewModelProvider(this, cuocHopfactory).get(CuocHopViewModule.class);

        mCuocHopViewModule.getmClass().observe(this, new Observer<DSClassQL>() {
            @Override
            public void onChanged(DSClassQL classSV) {
                if(classSV==null)
                    Log.d("","sussec----> "+"id: ");
                else
                    Log.d("TAG","sussec----> "+"toang rồi: ");
                mCuocHopViewModule.setCHfactory(cuocHopfactory);
            }
        });

        mCuocHopViewModule.getmListCuocHop().observe(this, new Observer<List<Meettings>>() {
            @Override
            public void onChanged(List<Meettings> meettings) {
                mListCuocHop.clear();
                if(meettings!=null){
                    for (Meettings tb: meettings) {
                        if(tb.get_nameClass().equals(clas.get_nameClass())){
                            mListCuocHop.add(tb);
                        }
                    }
                    try {
                        sortData(mListCuocHop);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
//                    showCuocHop(mListCuocHop);
                }
            }
        });
    }
    private void sortData(List<Meettings> l) throws ParseException {
        List<ClassSVNotification> temp=new ArrayList<>();
        for (int i=0;i<l.size()-1;i++){
            for (int j=i+1;j<l.size();j++){
                if(checkDate(l.get(i).get_dayStart(), l.get(j).get_dayStart())){
                    Meettings noti= l.get(i);
                    l.set(i, l.get(j));
                    l.set(j, noti);
//                    Log.e("check ngày: "," "+l.get(i).get_datetime());
                }
            }
        }
        showCuocHop(l);
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
//        Log.e("check ngày: "," "+min+" | "+max);
        return max.compareTo(min)>=0;
    }
    private void showCuocHop(List< Meettings> l){
        tvSiSoCuocHop.setText("Số lượng: "+l.size());
        mAdaperCuocHop.setlCuocHop(l);
        rcvListMeetting.setAdapter(mAdaperCuocHop);
    }
    private void openDialogCuocHop(int key,DSClassQL clas, Meettings m){
        final Dialog dialog=new Dialog(MainActivityUserListMeetting.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_timetable);
        Window window= dialog.getWindow();
        if(window==null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        window.setAttributes(layoutParams);
        //click ra ngoài tắt dialog
//        if(Gravity.BOTTOM == g){
//            dialog.setCancelable(true);
//            //chonNgay(1,fView);
//        }
//        else {
//            //dialog.setCancelable(false);
//            //chonNgay(1,fView);
//        }
        initDialog(key,dialog, clas, m);
        dialog.show();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
    private void initDialog(int key,Dialog d, DSClassQL clas, Meettings m){
        tvabout=d.findViewById(R.id.tv_about);
        tvSaveOrAddMeeting=d.findViewById(R.id.tv_SaveOrAddMeeting);
        edtIdPhongCuocHop=d.findViewById(R.id.edt_maPhongCuocHop);
        edtNameCuocHop= d.findViewById(R.id.edt_NameCuocHop);
        edtNoiDungCuocHop= d.findViewById(R.id.edt_ndCuocHop);
        tvThoigianCuocHop= d.findViewById(R.id.tv_ThoiGianCuocHop);
        tvNgayCuocHop=d.findViewById(R.id.tv_NgayCuocHop);
        rlSaveOrNewCuocHop=d.findViewById(R.id.rl_SaveOrNewCuocHop);
        rlCloseDialogCuocHop=d.findViewById(R.id.rl_CloseDialogCuocHop);
        rlCloseDialogCuocHop.setOnClickListener(v->{d.dismiss();});

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date local= df.parse(df.format(currentTime));
            tvNgayCuocHop.setText(""+df.format(local));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(key==3){
            chitietCuocHop(m);
        }
        if(key==2){
            edtIdPhongCuocHop.setText(m.get_idPhong());
            edtNameCuocHop.setText(m.get_nameMeet());
            edtNoiDungCuocHop.setText(m.get_contentMeet());
            tvThoigianCuocHop.setText(m.get_timeStart());
            tvNgayCuocHop.setText(m.get_dayStart());
        }
        if(isSave)
            tvSaveOrAddMeeting.setText("Cập nhật");
        else
            tvSaveOrAddMeeting.setText("Thêm mới");

        rlSaveOrNewCuocHop.setOnClickListener(v->{
            if(!isSave){
                saveNewCuocHop(clas);
            }
            else {
                updateCuocHop(m);
            }
        });
        tvThoigianCuocHop.setOnClickListener(v->{
            chongio();
        });
        tvNgayCuocHop.setOnClickListener(v->{
            chonngay(!isSave?1:2);
        });
        edtIdPhongCuocHop.setOnClickListener(v->{
            openDeapLink(edtIdPhongCuocHop.getText().toString());
        });
        edtIdPhongCuocHop.setOnLongClickListener(v->{
            openClipBoard(edtIdPhongCuocHop.getText().toString());
            return false;
        });
    }

    private void chongio() {
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivityUserListMeetting.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String s=hourOfDay +":"+minute;
                int hourTam=hourOfDay;
                if(hourTam>12) {
                    tvThoigianCuocHop.setText(hourTam + ":" + minute);
                }else{
                    tvThoigianCuocHop.setText(hourTam + ":" + minute);
                }
                Log.e("main meeting ","chịn giờ: "+s);
                //lưu giờ thực vào tag
//                txtTime.setTag(s);
            }
        }, hour, minute, true);
        timePickerDialog.setTitle("Chọn thời gian");
        timePickerDialog.show();

    }

    private void chonngay(int loai) {
//khai báo ngày tùy biến theo thời gian thực
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        String s = "";
        //xây dựng DatePicker
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //gán thời gian người dùng lựa chọn
                calendar.set(year, month, dayOfMonth);
                //định dạng ngày theo kiểu ngày / tháng / năm
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                //đưa dữ liệu vào edittext
                if (loai == 2) {
                    tvNgayCuocHop.setText(simpleDateFormat.format(calendar.getTime()));
                    Log.e("ngày chọn: ---->> ",""+simpleDateFormat.format(calendar.getTime()));
                    //s="" +year+"/"+month+"/"+dayOfMonth;
                } else {
                    if(tvNgayCuocHop.getText().toString().isEmpty()){
                        Toast.makeText(MainActivityUserListMeetting.this, "Chưa chọn ngày bắt đầu?", Toast.LENGTH_SHORT).show();
                    }else {
                        try {
                            if(checkDayEnd(tvNgayCuocHop.getText().toString(), simpleDateFormat.format(calendar.getTime()))){
                                Log.e("ngày chọn: ---->> đạt chuẩn ", "" + simpleDateFormat.format(calendar.getTime()));

                                tvNgayCuocHop.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                            else {
                                Toast.makeText(MainActivityUserListMeetting.this, "Chọn ngày chưa hợp lệ!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
//                Log.e("ngày chọn: finsh ---->> ",""+year+"/"+month+"/"+dayOfMonth);
            }
        }, nam, thang, ngay);
        //hiển thị DatePicker
        datePickerDialog.show();
    }
    private boolean checkDayEnd(String d1, String d2) throws ParseException {
        final Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dStart= simpleDateFormat.parse(d1);
        Date dEnd= simpleDateFormat.parse(d2);
        Log.e("so sánh ngày: "," d1: "+simpleDateFormat.format(dStart)+"  | d2: "+simpleDateFormat.format(dEnd));
        if(simpleDateFormat.format(dEnd).compareTo(simpleDateFormat.format(dStart)) >= 0)
            return true;
        return false;
    }
    private void chitietCuocHop(Meettings m){
        tvabout.setText("Chi tiết cuộc họp");
        rlSaveOrNewCuocHop.setVisibility(View.GONE);
        edtIdPhongCuocHop.setText(m.get_idPhong());
        edtNameCuocHop.setText(m.get_nameMeet());
        edtNoiDungCuocHop.setText(m.get_contentMeet());
        tvThoigianCuocHop.setText(m.get_timeStart());
        tvNgayCuocHop.setText(m.get_dayStart());
        edtNameCuocHop.setFocusable(false);
        edtIdPhongCuocHop.setFocusable(false);
        edtNoiDungCuocHop.setFocusable(false);
        if (m.get_idPhong().length() < 5) {
        } else {
            edtIdPhongCuocHop.setBackgroundResource(R.drawable.custom_linnear_base);
        }
    }
    private void saveNewCuocHop(DSClassQL clas) {
        Log.e("Main cuochop: "," thêm mới");
        String maP=edtIdPhongCuocHop.getText().toString().trim();
        String nameCH=edtNameCuocHop.getText().toString().trim();
        String ndCH= edtNoiDungCuocHop.getText().toString().trim();
        String gioCH= tvThoigianCuocHop.getText().toString().trim();
        String ngayCH=tvNgayCuocHop.getText().toString().trim();
        if(maP.equals("")||nameCH.equals("") || ndCH.equals("") || gioCH.equals("")||ngayCH.equals("") ){
            Toast.makeText(MainActivityUserListMeetting.this, "Còn ô chưa nhâp?", Toast.LENGTH_SHORT).show();
        }else {
            Meettings CH = new Meettings(clas.get_id(), clas.get_nameClass(), maP, nameCH, ndCH, gioCH, ngayCH);
            CuocHopBE.NewCuocHop(CH);
            Toast.makeText(MainActivityUserListMeetting.this, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
            //phần thông báo cuôộ họp
            String noidungThongBao = ndCH.toString();
            String token = AuthAccount.getInstant().userAccount.get_Token();
            Log.e("class: ", "" + clas.toString());
            List<User> l = listUserThongbao(clas);
            NoticationClass noti = new NoticationClass(token, clas.get_nameClass(), noidungThongBao);
            NotificationClassBE.getInstant().notificationClassBE.MakeAPI(l, noti);
        }
    }

    private void updateCuocHop(Meettings m) {
        Log.e("Main cuochop: "," cập nhật");
        String maP=edtIdPhongCuocHop.getText().toString().trim();
        String nameCH=edtNameCuocHop.getText().toString().trim();
        String ndCH= edtNoiDungCuocHop.getText().toString().trim();
        String gioCH= tvThoigianCuocHop.getText().toString().trim();
        String ngayCH=tvNgayCuocHop.getText().toString().trim();
        m.set_idPhong(maP);
        m.set_nameMeet(nameCH);
        m.set_contentMeet(ndCH);
        m.set_timeStart(gioCH);
        m.set_dayStart(ngayCH);
        CuocHopBE.updateCuocHop(m);
        Toast.makeText(MainActivityUserListMeetting.this, "Sữa thành công", Toast.LENGTH_SHORT).show();

    }
    private void openClipBoard(String s){
        if(s.trim().length()<5){
            //Toast.makeText(this, "Học trực tiếp", Toast.LENGTH_SHORT).show();
        }else {
            ClipboardManager clipboardManager= (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData= ClipData.newPlainText("Coppy", s);
            clipboardManager.setPrimaryClip(clipData);
            clipData.getDescription();
            Toast.makeText(this, "Đã copy", Toast.LENGTH_SHORT).show();
        }
    }
    private void openDeapLink(String s){
        ClipboardManager clipboardManager= (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        if(s.trim().length()<5){
//            Toast.makeText(this, "Học trực tiếp", Toast.LENGTH_SHORT).show();
        }else {
//            Toast.makeText(this, "Học online", Toast.LENGTH_SHORT).show();
//
            Intent url = new Intent(Intent.ACTION_VIEW);
            url.setData(Uri.parse((s)));
            Log.e("manin uri: "," "+url);
            startActivity(url);
        }

    }
}