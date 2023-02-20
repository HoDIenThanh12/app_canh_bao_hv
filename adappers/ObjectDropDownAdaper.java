package com.example.tiki.app_canhbao.adappers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.entity.QuyCheVCHT;

import java.util.List;

public class ObjectDropDownAdaper extends ArrayAdapter<QuyCheVCHT> {
    LayoutInflater layoutInflater;
    public ObjectDropDownAdaper(@NonNull Context context, int resource, @NonNull List<QuyCheVCHT> objects) {
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
        if(idR==1)
            textViewName= convertView.findViewById(R.id.tv_item_select_dropdown);
        if(idR==2)
            textViewName= convertView.findViewById(R.id.tv_item_dropdown);

        QuyCheVCHT qc = getItem(position);

        if (qc != null) {
            //textViewName.setText(qc.get_mucQuyChe());
        }

        return convertView;
    }
}
