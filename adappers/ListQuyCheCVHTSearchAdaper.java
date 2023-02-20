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
import com.example.tiki.app_canhbao.entity.SoTayCVHT;
import com.example.tiki.app_canhbao.interfaces.ActionQuyCheCVHT;

import java.util.ArrayList;
import java.util.List;

public class ListQuyCheCVHTSearchAdaper extends RecyclerView.Adapter<ListQuyCheCVHTSearchAdaper.ListQuyCheCVHTSearchViewHodel > implements Filterable {
    List<QuyCheVCHT> list1;
    List<QuyCheVCHT> list2;
    Context context;
    ActionQuyCheCVHT Aqc;
    public ListQuyCheCVHTSearchAdaper(Context context, ActionQuyCheCVHT qc) {
        this.Aqc=qc;
        this.context = context;
    }

    public void setList1(List<QuyCheVCHT> list1) {
        this.list1 = list1;
        this.list2 = list1;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListQuyCheCVHTSearchViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_custom_dropdown, parent, false);
        return new ListQuyCheCVHTSearchViewHodel(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListQuyCheCVHTSearchViewHodel holder, int position) {
        QuyCheVCHT qc=list1.get(position);
        if(qc==null)
            return;
        holder.tvMucQCCVHT.setText(qc.get_mucQuyChe());
        holder.tvMucQCCVHT.setOnClickListener(v->{
            Aqc.showQuyCheCVHT(qc);
        });
    }

    @Override
    public int getItemCount() {
        if (list1!=null)
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
                    List<QuyCheVCHT> lkey=new ArrayList<>();
                    for(QuyCheVCHT u: list2){
                        if(u.get_mucQuyChe().toLowerCase().contains(key.toLowerCase())
                                || u.get_ndQuyChe().toLowerCase().contains(key.toLowerCase())){
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
                list1= (List<QuyCheVCHT>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ListQuyCheCVHTSearchViewHodel extends RecyclerView.ViewHolder {
        TextView tvMucQCCVHT;
        public ListQuyCheCVHTSearchViewHodel(@NonNull View itemView) {
            super(itemView);
            tvMucQCCVHT=itemView.findViewById(R.id.tv_item_dropdown);
        }
    }
}
