package com.example.tiki.app_canhbao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.adappers.MyViewPager2Adapper;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.backend.ClassSVBE;
import com.example.tiki.app_canhbao.fragments.FragmentSoTayCVHT;
import com.example.tiki.app_canhbao.views.MainActivityLogIn;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivityHomes extends AppCompatActivity {
    private BottomNavigationView mNavigationBarView;
    private ViewPager2 mViewPager2;
    private MyViewPager2Adapper mAdapper;
    public static boolean isLoadApp=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_homes2);
        inits();

    }
    @SuppressLint("NonConstantResourceId")
    private void inits() {
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        mNavigationBarView =findViewById(R.id.bottom_navigation);
        mViewPager2=findViewById(R.id.viewpage2_fragment);

            mAdapper=new MyViewPager2Adapper(this);
            mViewPager2.setAdapter(mAdapper);
            isLoadApp=false;
            mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    int caterogy =AuthAccount.getInstant().userAccount.get_category();
//                    if(caterogy==1) {
                        switch (position) {
                            case 0: {
                                mNavigationBarView.getMenu().findItem(R.id.page_Home).setChecked(true);
                                break;
                            }
                            case 1: {
                                mNavigationBarView.getMenu().findItem(R.id.page_Meetting).setChecked(true);
                                break;
                            }
                            case 2: {
                                mNavigationBarView.getMenu().findItem(R.id.page_QuyCheCVHT).setChecked(true);
                                break;
                            }
                            case 3: {
                                Log.e("--> ", "id màn hình about: " + mViewPager2.getCurrentItem());
                                mNavigationBarView.getMenu().findItem(R.id.page_About).setChecked(true);
                                break;
                            }
                        }
//                    }
//                    else {
//                        switch (position) {
//                            case 0: {
//                                mNavigationBarView.getMenu().findItem(R.id.page_Home).setChecked(true);
//                                break;
//                            }
//                            case 1: {
//                                mNavigationBarView.getMenu().findItem(R.id.page_QuyCheCVHT).setChecked(true);
//                                break;
//                            }
//                            case 2: {
//                                mNavigationBarView.getMenu().findItem(R.id.page_About).setChecked(true);
//                                break;
//                            }
//                        }
                    }
                //}
            });


        int idCatelogy =  AuthAccount.getInstant().userAccount.get_category();
        if(idCatelogy==2){
            //mNavigationBarView.getMenu().findItem(R.id.page_Meetting).setVisible(false);
            mNavigationBarView.getMenu().findItem(R.id.page_Meetting).setTitle("Thông itn GV");
//            Log.e("Gv---->>  ","---> "+AuthAccount.getInstant().userAccount.get_category());
        }
//        else {
//            Log.e("Gv---->>  ","---> "+AuthAccount.getInstant().userAccount.get_category());
//        }


//        mNavigationBarView.getMenu().;
        mNavigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Log.e("đanh nhấn bottom--> ","  "+item.getItemId());
//                if(idCatelogy==1){
////                    mNavigationBarView.getMenu().findItem(R.id.page_Meetting).setVisible(false);
//                    switch (item.getItemId()){
//                        case R.id.page_Home:{
//                            mViewPager2.setCurrentItem(0);
//                            break;
//                        }
//                        case R.id.page_QuyCheCVHT:{
//                            mViewPager2.setCurrentItem(1);
//                            break;
//                        }
//                        case R.id.page_About:{
//                            mViewPager2.setCurrentItem(2);
//                            break;
//                        }
//                    }
//                }
//                else{
                    switch (item.getItemId()){
                        case R.id.page_Home:{
                            mViewPager2.setCurrentItem(0);
                            break;
                        }
                        case R.id.page_Meetting:{
                                mViewPager2.setCurrentItem(1);

                            break;
                        }
                        case R.id.page_QuyCheCVHT:{
                            mViewPager2.setCurrentItem(2);
                            break;
                        }
                        case R.id.page_About:{
                            mViewPager2.setCurrentItem(3);
                            break;
                        }

                    }
                //}
                chuyenFragment(item.getItemId());
                return true;
            }
        });

//        mNavigationBarView.setOnItemSelectedListener(i->{
//            Fragment fragment;
//            switch (i.getItemId()){
//                case R.id.page_Home:{
//                    Toast.makeText(this, "Homes", Toast.LENGTH_SHORT).show();
//                    fragment =new FragmentLogin();
//                    getFragment(fragment);
//                    return true;
//                }
//                case R.id.page_2:{
//                    Toast.makeText(this, "xếp lịch", Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//                case R.id.page_3:{
//                    Toast.makeText(this, "Danh sách", Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//                case R.id.page_About:{
//                    fragment=new FragmentAbout();
////                    String ID= AuthAccount.getInstant().getIdUser();
////                    Bundle bundle =new Bundle();
////                    bundle.putString(ID_ACCOUNT, ID);
////                    fragment.setArguments(Bundle);
//                    getFragment(fragment);
//                    Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//            }
//            return false;
//        });
    }
//    private void getFragment(Fragment f){
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.frame_page,f)
//                .commit();
//    }
    public void chuyenFragment(int item){
        int idCte=AuthAccount.getInstant().userAccount.get_category();
//        if(idCte==1) {
            switch (item) {
                case R.id.page_Home: {
                    mViewPager2.setCurrentItem(0);
                    break;
                }
                case R.id.page_Meetting: {
                    mViewPager2.setCurrentItem(1);
                    break;
                }
                case R.id.page_QuyCheCVHT: {
                    mViewPager2.setCurrentItem(2);
                    break;
                }
                case R.id.page_About: {
                    mViewPager2.setCurrentItem(3);
                    break;
                }
            }
//        }
//        else{
//            switch (item) {
//                case R.id.page_Home: {
//                    mViewPager2.setCurrentItem(0);
//                    break;
//                }
//                case R.id.page_QuyCheCVHT: {
//                    mViewPager2.setCurrentItem(1);
//                    break;
//                }
//                case R.id.page_About: {
//                    mViewPager2.setCurrentItem(3);
//                    break;
//                }
//            }
       // }
        return;
    }
    public void RemoveValueKey(){
        //remove
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MyKeyLogin", MODE_APPEND);
        sh.edit().clear().commit();
        Log.e("main login: "," remove value-key: ");
    }
    @Override
    public void onBackPressed() {
        final boolean[] isExit = new boolean[1];
        new AlertDialog.Builder(this)
                .setTitle("Cảnh báo!")
                .setMessage("Bạn muốn thoát ứng dụng?")
                .setPositiveButton("Chấp nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AuthAccount.getInstant().Singouts();
                        RemoveValueKey();
                        Intent intent=new Intent(MainActivityHomes.this, MainActivityLogIn.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Thoát",null)
                .show();
    }
}