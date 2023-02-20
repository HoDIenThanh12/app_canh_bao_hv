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
import com.example.tiki.app_canhbao.entity.SoTayCVHT;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.interfaces.ActionListSoTayCVHTSearch;

import java.util.ArrayList;
import java.util.List;

public class ListSoTayCVHTSearchAdaper extends RecyclerView.Adapter<ListSoTayCVHTSearchAdaper.ListSoTayCVHTSearchAdaperViewHodle> implements Filterable {
    List<SoTayCVHT> list1;
    List<SoTayCVHT> list2;
    Context context;
    ActionListSoTayCVHTSearch soTayCVHTSearch;

//    public ListSoTayCVHTSearchAdaper(Context context) {
//        this.context = context;
//    }

    public ListSoTayCVHTSearchAdaper(Context context, ActionListSoTayCVHTSearch soTayCVHTSearch) {
        this.context = context;
        this.soTayCVHTSearch = soTayCVHTSearch;
    }
    public void setList1(List<SoTayCVHT> list1) {
        this.list1 = list1;
        this.list2=list1;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListSoTayCVHTSearchAdaperViewHodle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_custom_dropdown, parent, false);
        return new ListSoTayCVHTSearchAdaperViewHodle(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListSoTayCVHTSearchAdaperViewHodle holder, int position) {
        SoTayCVHT st=list1.get(position);
        if(st==null)
            return;
        holder.tv_MucSotayCVHT.setText(st.get_mucSoTay());
        holder.tv_MucSotayCVHT.setOnClickListener(v->{soTayCVHTSearch.selectSoTayCVHTSearch(st);});
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
                String key = constraint.toString();
                if(key.isEmpty()){
                    list1 = list2;
                    Log.e("---> ","key rỗng");
                }
                else {
                    Log.e("---> ","key có");
                    List<SoTayCVHT> lkey=new ArrayList<>();
                    for(SoTayCVHT u: list2){
                        if(u.get_mucSoTay().toLowerCase().contains(key.toLowerCase())
                                || u.get_ndSoTay().toLowerCase().contains(key.toLowerCase())){
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
                list1= (List<SoTayCVHT>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ListSoTayCVHTSearchAdaperViewHodle extends RecyclerView.ViewHolder {
        TextView tv_MucSotayCVHT;
        public ListSoTayCVHTSearchAdaperViewHodle(@NonNull View itemView) {
            super(itemView);
            tv_MucSotayCVHT=itemView.findViewById(R.id.tv_item_dropdown);
        }
    }
}
