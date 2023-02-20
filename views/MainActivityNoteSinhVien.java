package com.example.tiki.app_canhbao.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.adappers.ObjectDropDownAdaper;
import com.example.tiki.app_canhbao.backend.NoteSVBE;
import com.example.tiki.app_canhbao.entity.QuyCheVCHT;
import com.example.tiki.app_canhbao.entity.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivityNoteSinhVien extends AppCompatActivity {
    EditText edtNote;
    TextView tvNameSV, tvMSSV, tvLop, tvGmail, tvKhoa, sdtSV;
    RelativeLayout rlSaveNoteSV, rlCloseNoteSV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_custom_drop_down);
        inits();
    }

    private void inits() {
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        Bundle bundle= getIntent().getExtras();
        User uNhan = (User) bundle.getSerializable("users");
        edtNote= findViewById(R.id.edt_noteSV);
        tvNameSV= findViewById(R.id.tv_nameSV);
        tvMSSV= findViewById(R.id.tv_MSSV);
        tvLop= findViewById(R.id.tv_lopSV);
        sdtSV=findViewById(R.id.tv_sdtSV);
        tvGmail = findViewById(R.id._tv_gamilSV);
        tvKhoa= findViewById(R.id.tv_khoaSV);
        rlSaveNoteSV= findViewById(R.id.rl_SaveNoteSV);
        rlCloseNoteSV= findViewById(R.id.rl_CloseNoteSV);
        rlSaveNoteSV.setOnClickListener(v->{
            updateNote(uNhan);
        });
        rlCloseNoteSV.setOnClickListener(v->{
            Intent intent=getIntent();
            setResult(1, intent);
            finish();
        });
        getData(uNhan);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
//                Log.e("main chat: ","qauy lại");
                Intent intent=getIntent();

                setResult(1, intent);
                finish();
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void getData(User u){
        edtNote.setText(u.get_note());
        tvNameSV.setText(u.get_Name());
        if(!u.get_MSSV().equals(""))
            tvMSSV.setText(u.get_MSSV());
        else
            tvMSSV.setText("MSSV: "+"Chưa cập nhật");
        if(u.get_Class().equals(""))
            tvLop.setText("Lớp: "+"Chưa cập nhật");
        else
            tvLop.setText(u.get_Class());
        tvGmail.setText(u.get_Email());
        if(u.get_Khoa().equals(""))
            tvKhoa.setText("Khoa/Viện: "+"Chưa cập nhật");
        else
            tvKhoa.setText(u.get_Khoa());
        if(u.get_sdt().equals("")){
            sdtSV.setText("sdt: Chưa cập nhật");
        }else{
            sdtSV.setText(u.get_sdt());
        }
        sdtSV.setOnClickListener(v->{
            if(u.get_sdt().equals("")){
                Toast.makeText(this, "Chưa cập nhật", Toast.LENGTH_SHORT).show();
            }else{
                String tel="tel:"+sdtSV.getText().toString();
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(tel)));
//                String s= "https://zalo.me/"+u.get_sdt();
//                Intent url = new Intent(Intent.ACTION_DEFAULT);
//                url.setData(Uri.parse((s)));
//                Log.e("manin uri: "," "+url);
//                startActivity(url);
            }
        });
    }
    private void updateNote(User u){
        String note= edtNote.getText().toString().trim();
        u.set_note(note);
        NoteSVBE.updateNoteSV(u);
        Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
    }
}