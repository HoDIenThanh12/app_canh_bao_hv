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
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.entity.ClassSV;
import com.example.tiki.app_canhbao.entity.DSClassQL;
import com.example.tiki.app_canhbao.interfaces.EnventItemClassSV;

import java.util.List;

public class ListClassSVHomeAdaper extends RecyclerView.Adapter<ListClassSVHomeAdaper.ListClassSVHomeAdaperViewHolder> {
    List<DSClassQL> mList;
    Context context;
    EnventItemClassSV itemClassSV;
    public ListClassSVHomeAdaper(Context context, EnventItemClassSV even) {
        this.context = context;
        itemClassSV= even;
    }

    public void setmList(List<DSClassQL> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListClassSVHomeAdaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_meeting,parent,false);
        return new ListClassSVHomeAdaperViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListClassSVHomeAdaperViewHolder holder, int position) {
        DSClassQL clas= mList.get(position);
        if(clas==null)
            return ;
        else {
            holder.tvNameClassSVHome.setText(clas.get_nameClass());
            holder.tvSSClassSVHome.setText("Sỉ số: " +clas.get_siso()+"");
            holder.rlDeleClassSV.setOnClickListener(v->{
                Log.e("xóa lớp->> ", "home");
                itemClassSV.deleClassSV(clas);
            });
            holder.rlQLClassSV.setVisibility(View.GONE);
            holder.tvan.setVisibility(View.GONE);
            holder.cv.setOnClickListener(v->{
                Log.e("nhấn card view"," nhấn" + position);
                itemClassSV.callbackClasSV(clas);
            });
        }
    }

    @Override
    public int getItemCount() {
        if(mList!=null){
            return mList.size();
        }
        return 0;
    }

    public class ListClassSVHomeAdaperViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameClassSVHome, tvSSClassSVHome, tvan;
        CardView cv;
        RelativeLayout rlQLClassSV, rlDeleClassSV;
        public ListClassSVHomeAdaperViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameClassSVHome= itemView.findViewById(R.id.tv_NameClassSV);
            tvSSClassSVHome= itemView.findViewById(R.id.tv_SSClassSV);
            rlQLClassSV = itemView.findViewById(R.id.rl_QLClassSV);
            rlDeleClassSV = itemView.findViewById(R.id.rl_DeleClassSV);
            cv=itemView.findViewById(R.id.item_cardview_classSV_Home);
            tvan=itemView.findViewById(R.id.tv_DateMeetting);
        }
    }
}
