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
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.entity.QuyCheVCHT;
import com.example.tiki.app_canhbao.entity.SotaySV;
import com.example.tiki.app_canhbao.interfaces.ActionSoTaySV;

import java.util.ArrayList;
import java.util.List;

public class ListSoTaySinhVienSearchAdaper extends RecyclerView.Adapter<ListSoTaySinhVienSearchAdaper.ListSoTaySinhVienSearchAdaperViewHodel> implements Filterable {
    List<SotaySV> list1;
    List<SotaySV> list2;
    Context context;
    ActionSoTaySV actionSoTaySV;

    public ListSoTaySinhVienSearchAdaper(Context context, ActionSoTaySV actionSoTaySV) {
        this.actionSoTaySV=actionSoTaySV;
        this.context = context;
    }

    public void setList1(List<SotaySV> list1) {
        this.list1 = list1;
        this.list2 = list1;
        notifyDataSetChanged();
    }





    @NonNull
    @Override
    public ListSoTaySinhVienSearchAdaperViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_custom_dropdown, parent, false);
        return new ListSoTaySinhVienSearchAdaperViewHodel(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListSoTaySinhVienSearchAdaperViewHodel holder, int position) {
        SotaySV st=list1.get(position);
        if(st==null)
            return;
        holder.tvMucQCCVHT.setText(st.get_mucSTSV());
        holder.tvMucQCCVHT.setOnClickListener(v->{
            actionSoTaySV.ShowSoTay(st);
        });
        Log.e("items sotay: "," name: "+st.get_mucSTSV());
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString();
                if(key.isEmpty()){
                    list1 = list2;
                    Log.e("---> ","key rỗng");
                }
                else {
                    Log.e("---> ","key có");
                    List<SotaySV> lkey=new ArrayList<>();
                    for(SotaySV u: list2){
                        if(u.get_mucSTSV().toLowerCase().contains(key.toLowerCase())
                                || u.get_NoiDungSTSV().toLowerCase().contains(key.toLowerCase())){
                            lkey.add(u);
                        }
                    }
                    list1=lkey;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=list1;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list1= (List<SotaySV>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    @Override
    public int getItemCount() {
        if(list1!=null)
            return list1.size();
        return 0;
    }

    public class ListSoTaySinhVienSearchAdaperViewHodel extends RecyclerView.ViewHolder {
        TextView tvMucQCCVHT;
        public ListSoTaySinhVienSearchAdaperViewHodel(@NonNull View itemView) {
            super(itemView);
            tvMucQCCVHT=itemView.findViewById(R.id.tv_item_dropdown);
        }
    }
}
