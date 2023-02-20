package com.example.tiki.app_canhbao.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.adappers.SpinnerQCCVHTAdaper;
import com.example.tiki.app_canhbao.adappers.SpinnerSoTaySinhVienAdaper;
import com.example.tiki.app_canhbao.backend.SoTayGVBE;
import com.example.tiki.app_canhbao.backend.SoTaySinhVienBE;
import com.example.tiki.app_canhbao.entity.SoTayCVHT;
import com.example.tiki.app_canhbao.entity.SotaySV;
import com.example.tiki.app_canhbao.fragments.FragmentQuyCheCVHT;
import com.example.tiki.app_canhbao.fragments.FragmentSoTayCVHT;
import com.example.tiki.app_canhbao.fragments.FragmentSoTaySinhVien;

import java.util.ArrayList;
import java.util.List;

public class MainActivitySotaySinhVien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sotay_sinh_vien);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        int key = getIntent().getIntExtra("key",1);
        getFragment(key);
//        inits();

    }

    private void getFragment(int k) {
        //Fragment fragment =new FragmentBlankGgMapTest2();
        Fragment fragment;
        if(k==1){
            fragment =new FragmentQuyCheCVHT();
        }else{
            fragment =new FragmentSoTaySinhVien();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frm_QuyCheCVHT_SotaySinhVien,fragment)
                .commit();
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