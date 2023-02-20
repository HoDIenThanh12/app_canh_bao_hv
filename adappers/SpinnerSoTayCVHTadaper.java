package com.example.tiki.app_canhbao.adappers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.entity.SoTayCVHT;

import java.util.List;

public class SpinnerSoTayCVHTadaper extends ArrayAdapter<SoTayCVHT> {
    public SpinnerSoTayCVHTadaper(@NonNull Context context, int resource, @NonNull List<SoTayCVHT> objects) {
        super(context, resource, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent, 1);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent,2);
    }
    @SuppressLint("ResourceAsColor")
    private View initView(int position, View convertView, ViewGroup parent, int idR) {
        if (convertView == null) {
            if(idR==2) {
                convertView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item_custom_dropdown, parent, false
                );
            }
            else{
                convertView = LayoutInflater.from(getContext()).inflate(
                        R.layout.item_select_custom_dropdown, parent, false
                );
            }
        }

//        ImageView imageViewFlag = convertView.findViewById(R.id.image_view_flag);
        //if()

        TextView textViewName = null;
        if(idR==1) {
            textViewName = convertView.findViewById(R.id.tv_item_select_dropdown);
        }
        if(idR==2) {
            textViewName = convertView.findViewById(R.id.tv_item_dropdown);
        }
        SoTayCVHT st = getItem(position);

        if (st != null) {
            textViewName.setText(st.get_mucSoTay());
            if(position==0 && idR==2){
                textViewName.setBackgroundColor(R.color.chuong_background);
            }
        }
        return convertView;
    }
}
