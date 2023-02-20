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

public class SpinnerClassSVAdaper extends ArrayAdapter<QuyCheVCHT> {
    LayoutInflater layoutInflater;
    public SpinnerClassSVAdaper(@NonNull Context context,  List<QuyCheVCHT> listQC) {
        super(context,0, listQC);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }
    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.items_quyche_spinner, parent, false
            );
        }

//        ImageView imageViewFlag = convertView.findViewById(R.id.image_view_flag);
        //if()
        TextView textViewName = convertView.findViewById(R.id.tv_spn_QuyCheCVHT);

        QuyCheVCHT qc = getItem(position);

        if (qc != null) {
            //textViewName.setText(qc.get_mucQuyChe());
        }

        return convertView;
    }
}
