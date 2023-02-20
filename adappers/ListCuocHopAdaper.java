package com.example.tiki.app_canhbao.adappers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.ClassSVNotification;
import com.example.tiki.app_canhbao.entity.Meettings;
import com.example.tiki.app_canhbao.interfaces.ActionCuocHop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListCuocHopAdaper extends RecyclerView.Adapter<ListCuocHopAdaper.ListCuocHopAdaperViewHodle> implements Filterable {
    List<Meettings> lCuocHop;
    List<Meettings> lKey;
    Context context;
    ActionCuocHop actionCuocHop;
    public ListCuocHopAdaper(Context context, ActionCuocHop action) {
        this.context = context;
        this.actionCuocHop=action;
    }

    public void setlCuocHop(List<Meettings> lCuocHop) {
        this.lCuocHop = lCuocHop;
        this.lKey=lCuocHop;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListCuocHopAdaperViewHodle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_cuoc_hop, parent, false);
        return new ListCuocHopAdaperViewHodle(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ListCuocHopAdaperViewHodle holder, int position) {
        final Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format =new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dayNow=String.valueOf(calendar.getTime());

        Meettings m = lCuocHop.get(position);
        if(m==null)
            return;
        int cate= AuthAccount.getInstant().userAccount.get_category();
        holder.tvTenCuocHop.setText(m.get_nameMeet());
        holder.tvgioCuocHop.setText(m.get_timeStart());
        holder.tvgioCuocHop.setTextColor(context.getColor(R.color.maudo));
        holder.tvgioCuocHop.setFocusable(false);
        holder.tvngayCuocHop.setText(m.get_dayStart());
        holder.tvngayCuocHop.setTextColor(context.getColor(R.color.mau_chu_ten));
        if(cate==1){

            holder.rlEdit.setOnClickListener(v->{
                Log.e("adaper cuochop: "," sữa cuộc họp");
                actionCuocHop.updatecuocHop(m);
            });
            holder.rlDele.setOnClickListener(v->{
                Log.e("adaper cuochop: "," xóa cuộc họp");
                actionCuocHop.deleCuocHop(m);
            });

        }
        else {
            holder.rlEdit.setVisibility(View.GONE);
            holder.rlDele.setVisibility(View.GONE);
        }
//        String daytimes;
        Date dte = null;
        try {
            dte = format.parse(m.get_dayStart()+" "+m.get_timeStart());
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Log.e("apdaer--> "," ngày forrmat: " +dte);
        if(CheckDate(dte, dayNow)<0) {
            holder.tvTrangThai.setText("Đã họp");
            holder.rlEdit.setVisibility(View.GONE);
        }
        else if(CheckDate(dte, dayNow)>=0){
            if(countDay(dte, dayNow)>0
            && countDay(dte, dayNow) <7) {
                holder.tvTrangThai.setText("Chưa tới ngày họp");
            }
            else{
                Log.e("adaper CH: "," số ngày: "+CheckDate(dte, dayNow));
                holder.tvTrangThai.setText("Sắp diễn ra");
            }
            holder.tvTrangThai.setTextColor(context.getColor(R.color.maudo));
        }
        holder.cv.setOnClickListener(v->{
            actionCuocHop.chitietCuocHop(m);
        });
    }

    @Override
    public int getItemCount() {
        if(lCuocHop!=null)
            return  lCuocHop.size();
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString().trim();
                if(key.isEmpty()){
                    lCuocHop=lKey;
                    Log.e("---> ","key rỗng: " + lCuocHop.size());
                }
                else {
                    Log.e("---> ","key có" +key.toLowerCase());
                    List<Meettings> ml3 =new ArrayList<>();
                    for (Meettings u: lKey) {
//                        Log.e("---> ","key có: "+ u.get_Name());
                        if(u.get_nameMeet().toLowerCase().contains(key.toLowerCase())){
                            ml3.add(u);
                        }
                    }
                    lCuocHop=ml3;
                }
                FilterResults results = new FilterResults();
                results.values=lCuocHop;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                lCuocHop= (List<Meettings>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    public int CheckDate(Date d1, String d2)  {
        final Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format =new SimpleDateFormat("dd/MM/yyyy HH:mm");

//        Date day1 =  new Date(d1) ;
        Date day2= new Date(d2);
        int key = d1.compareTo(day2);

//        Log.e("ngày hiện tại: "," "+format.format(d1)+" | ngày sết: "+format.format(day2)+" | ss key: "+key);

        return key;
    }
    public int countDay(Date d1, String d2){
        final Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format =new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date day2= new Date(d2);
        int count  = (int) Math.abs(d1.getTime() - day2.getTime());
        Log.e("ngày hiện tại: "," "+format.format(day2)+
                  " | ngày csdl: "+format.format(d1)+" | ss count: ");

//        Log.e("đếm ngày: "," số ngày đã đếm "+ count);
        return count;
    }

    public class ListCuocHopAdaperViewHodle extends RecyclerView.ViewHolder {
        TextView tvTenCuocHop,tvgioCuocHop, tvngayCuocHop, tvTrangThai;
        RelativeLayout rlEdit, rlDele;
        CardView cv;
        public ListCuocHopAdaperViewHodle(@NonNull View itemView) {
            super(itemView);
            tvTenCuocHop=itemView.findViewById(R.id.tv_NameCuocHop);
            tvgioCuocHop=itemView.findViewById(R.id.tv_thoigianCuocHop);
            tvngayCuocHop=itemView.findViewById(R.id.tv_ngayCuocHop);
            rlEdit=itemView.findViewById(R.id.rl_Edit);
            rlDele=itemView.findViewById(R.id.rl_Dele);
            cv=itemView.findViewById(R.id.item_CuocHop);
            tvTrangThai=itemView.findViewById(R.id.tv_trangthai);
        }
    }
}
