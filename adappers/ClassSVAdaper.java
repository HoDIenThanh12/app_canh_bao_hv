package com.example.tiki.app_canhbao.adappers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.entity.ClassCB;
import com.example.tiki.app_canhbao.entity.ClassSV;
import com.example.tiki.app_canhbao.entity.DSClassQL;
import com.example.tiki.app_canhbao.interfaces.EventClassSV;

import java.util.List;

public class ClassSVAdaper extends RecyclerView.Adapter<ClassSVAdaper.ClassSVAdaperViewHodle> {
    List<DSClassQL> mlist;
    Context context;
    EventClassSV eventClassSV;
    public ClassSVAdaper(Context context) {
        //this.eventClassSV= (EventClassSV) context;
        this.context = context;
    }

    public ClassSVAdaper(EventClassSV eventClassSV) {
        this.eventClassSV = eventClassSV;
    }

    public void setMlist(List<DSClassQL> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClassSVAdaperViewHodle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_meeting, parent, false);
        return new ClassSVAdaperViewHodle(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassSVAdaperViewHodle holder, int position) {
        DSClassQL c = mlist.get(position);
        if(c==null)
            return;
        holder.tvNameClasSV.setText("Lớp "+c.get_nameClass());
        holder.tvSSClasSV.setText("Sỉ số: "+c.get_siso());
        holder.rlQLClassSV.setOnClickListener(v->{
            eventClassSV.ShowListClassSV(c);
        });
        holder.rlDeleClassSV.setOnClickListener(v->{
            eventClassSV.DeleClassSV(c);
        });
        holder.itemCardviewClassSVHome.setOnClickListener(v->{
            Log.e(""," sang trang thông báo");
            eventClassSV.ChuyenThongBaoQLClass(c);
        });
    }

    @Override
    public int getItemCount() {
        if(mlist!=null)
            return mlist.size();
        return 0;
    }

    public class ClassSVAdaperViewHodle extends RecyclerView.ViewHolder{
        TextView tvNameClasSV;
        TextView tvSSClasSV;
        CardView itemCardviewClassSVHome;
        RelativeLayout rlQLClassSV, rlDeleClassSV;
        public ClassSVAdaperViewHodle(@NonNull View itemView) {
            super(itemView);
            tvNameClasSV=itemView.findViewById(R.id.tv_NameClassSV);
            tvSSClasSV=itemView.findViewById(R.id.tv_SSClassSV);
            rlQLClassSV = itemView.findViewById(R.id.rl_QLClassSV);
            rlDeleClassSV = itemView.findViewById(R.id.rl_DeleClassSV);
            itemCardviewClassSVHome=itemView.findViewById(R.id.item_cardview_classSV_Home);
        }
    }
}
