package com.example.tiki.app_canhbao.adappers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.interfaces.ActionNoteSV;
import com.example.tiki.app_canhbao.views.MainActivityNoteSinhVien;

import java.util.ArrayList;
import java.util.List;

public class UserClassSelectAdaper extends RecyclerView.Adapter<UserClassSelectAdaper.UserClassSelectAdaperViewHodler> implements Filterable {

    List<User> ml1;
    List<User> ml2;
    Context context;
    ActionNoteSV actionNoteSV;

    public UserClassSelectAdaper(Context context, ActionNoteSV actionNoteSV) {
        this.context = context;
        this.actionNoteSV = actionNoteSV;
    }

    public UserClassSelectAdaper(Context context) {
        this.context = context;
    }

    public void setMl(List<User> ml) {
        this.ml1 = ml;
        this.ml2 = ml;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserClassSelectAdaperViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_adduser,parent,false);
        return new UserClassSelectAdaperViewHodler(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull UserClassSelectAdaperViewHodler holder, int position) {
        User u =ml1.get(position);
        if(u==null){
            return;
        }
        else{
            holder.tvNameSVClassSV.setText(u.get_Name());
            if(u.get_MSSV().trim().equals("")){
                holder.tvMSSVClassSV.setText("Chưa cập nhật");
            }
            else {
                holder.tvMSSVClassSV.setText(u.get_MSSV());
            }
            if(!u.get_note().equals("")){
                holder.btnAN.setBackgroundResource(R.drawable.custom_btn_2);
            }
            holder.tvNameClassSVSelect.setText(u.get_Class());
            holder.btnAN.setOnClickListener(v->{
                actionNoteSV.noteSV(u);
            });
            holder.btnitemChatUser.setOnClickListener(v->{actionNoteSV.ChatSV(u);});
//            holder.btnAN.setVisibility(View.GONE);
            Log.e("data: ",""+u.get_Name()+" | "+u.get_MSSV()+" | "+u.get_Class());
        }
    }

    @Override
    public int getItemCount() {
        if(ml1!=null){
            return ml1.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString();
                if(key.isEmpty()){
                    ml1=ml2;
                    Log.e("---> ","key rỗng: " + ml1.size());
                }
                else {
                    Log.e("---> ","key có" +key.toLowerCase());
                    List<User> ml3 =new ArrayList<>();
                    for (User u: ml2) {
                        Log.e("---> ","key có: "+ u.get_Name());
                        if(u.get_Name().toLowerCase().contains(key.toLowerCase())
                            || u.get_MSSV().toLowerCase().contains(key.toLowerCase())){
                            ml3.add(u);
                        }
                    }
                    ml1=ml3;
                }
                FilterResults results = new FilterResults();
                results.values=ml1;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                ml1= (List<User>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class UserClassSelectAdaperViewHodler extends RecyclerView.ViewHolder {
        TextView tvNameSVClassSV, tvMSSVClassSV, tvNameClassSVSelect;

        Button  btnAN, btnitemChatUser;
        public UserClassSelectAdaperViewHodler(@NonNull View itemView) {
            super(itemView);
            tvNameSVClassSV = itemView.findViewById(R.id.tv_NameSVClassSelect);
            tvMSSVClassSV= itemView.findViewById(R.id.tv_MSSVUserClassSelect);
            tvNameClassSVSelect= itemView.findViewById(R.id.tv_NameClassSelect);
            btnAN= itemView.findViewById(R.id.btn_AddUserMeettingList);
            btnitemChatUser=itemView.findViewById(R.id.btn_itemChatUser);
        }
    }

}
