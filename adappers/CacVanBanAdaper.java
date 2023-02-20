package com.example.tiki.app_canhbao.adappers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.entity.ClassSVNotification;
import com.example.tiki.app_canhbao.entity.VanBanCVHT;
import com.example.tiki.app_canhbao.interfaces.ActionVanBan;

import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class CacVanBanAdaper extends RecyclerView.Adapter<CacVanBanAdaper.CacVanBanAdaperViewHodle> implements Filterable {
    List<VanBanCVHT> list1;
    List<VanBanCVHT> list2;
    Context context;
    ActionVanBan actionVanBan;
    public CacVanBanAdaper(Context context, ActionVanBan actionVanBan) {
        this.actionVanBan=actionVanBan;
        this.context = context;
    }

    public void setList1(List<VanBanCVHT> list1) {
        this.list1 = list1;
        this.list2=list1;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CacVanBanAdaperViewHodle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_cac_van_ban, parent, false);
        return new CacVanBanAdaperViewHodle(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CacVanBanAdaperViewHodle holder, int position) {
        VanBanCVHT vb=list1.get(position);
        if(vb==null)
            return;
        holder.tvMucVanBan.setText(vb.get_mucVanBan());
        holder.tvMucVanBan.setOnClickListener(v->{
            actionVanBan.showVanBan(vb);
        });
    }

    @Override
    public int getItemCount() {
        if(list1!=null)
            return list1.size();
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString().trim();
                if(key.isEmpty()){
                    list1=list2;
//                    Log.e("---> ","key rỗng: " + list.size());
                }
                else {
                    Log.e("---> ","key có" +key.toLowerCase());
                    List<VanBanCVHT> ml3 =new ArrayList<>();
                    for (VanBanCVHT u: list2) {
//                        Log.e("---> ","key có: "+ u.get_Name());
                        if(u.get_mucVanBan().toLowerCase().contains(key.toLowerCase())){
                            ml3.add(u);
                        }
                    }
                    list1=ml3;
                }
                FilterResults results = new FilterResults();
                results.values=list1;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list1= (List<VanBanCVHT>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class CacVanBanAdaperViewHodle extends RecyclerView.ViewHolder {
        TextView tvMucVanBan;
        public CacVanBanAdaperViewHodle(@NonNull View itemView) {
            super(itemView);
            tvMucVanBan=itemView.findViewById(R.id.tv_mucVanBan);
        }
    }
}
