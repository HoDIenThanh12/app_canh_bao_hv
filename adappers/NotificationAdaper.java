package com.example.tiki.app_canhbao.adappers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.ClassSV;
import com.example.tiki.app_canhbao.entity.ClassSVNotification;
import com.example.tiki.app_canhbao.entity.ThongBao;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.app_canhbao.interfaces.ActionNotiClass;
import com.example.tiki.databinding.ItemsNotificationBinding;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class NotificationAdaper extends RecyclerView.Adapter<NotificationAdaper.NotificationAdaperViewHodler> implements Filterable {
    Context context;
    List<ClassSVNotification> list;
    List<ClassSVNotification> listKey;
    ActionNotiClass actionNotiClass;
    public NotificationAdaper(Context context, ActionNotiClass actionNotiClass) {
        this.context = context;
        this.actionNotiClass=actionNotiClass;
    }

    public void setList(List<ClassSVNotification> list) {
        this.list = list;
        this.listKey =list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotificationAdaperViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_notification,parent, false);
//
//        return new NotificationAdaperViewHodler(v);
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        ItemsNotificationBinding itemsNotificationBinding =ItemsNotificationBinding.inflate(layoutInflater, parent, false);
        return new NotificationAdaperViewHodler(itemsNotificationBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdaperViewHodler holder, int position) {
        ClassSVNotification tb=list.get(position);
        if(tb==null)
            return;
        holder.getBinDing(tb);
//        int cate= AuthAccount.getInstant().userAccount.get_category();
//        if(cate==2){
//            holder.imgDeleItemNotifi.setVisibility(View.GONE);
//        }
//        if(tb.get_nd().length()<=15){
//            holder.tvNdThongBao.setText(tb.get_nd());
//        }
//        else {
//            String chuoi = tb.get_nd().substring(0, 10);
//            holder.tvNdThongBao.setText(chuoi+" .....");
//        }
//        holder.tvNgayThongBao.setText(tb.get_datetime());
//        holder.cv.setOnClickListener(v->{actionNotiClass.showDataNoti(tb);});
//        holder.imgDeleItemNotifi.setOnClickListener(v->{actionNotiClass.deleteNoti(tb);});
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString().trim();
                if(key.isEmpty()){
                    list=listKey;
                    Log.e("---> ","key rỗng: " + list.size());
                }
                else {
                    Log.e("---> ","key có" +key.toLowerCase());
                    List<ClassSVNotification> ml3 =new ArrayList<>();
                    for (ClassSVNotification u: listKey) {
//                        Log.e("---> ","key có: "+ u.get_Name());
                        if(u.get_nd().toLowerCase().contains(key.toLowerCase())){
                            ml3.add(u);
                        }
                    }
                    list=ml3;
                }
                FilterResults results = new FilterResults();
                results.values=list;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list= (List<ClassSVNotification>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    //    private
    public class NotificationAdaperViewHodler extends RecyclerView.ViewHolder {
        TextView tvNdThongBao, tvNgayThongBao;
        CardView cv;
        ImageView imgDeleItemNotifi;
        private ItemsNotificationBinding binding;
        public NotificationAdaperViewHodler(@NonNull ItemsNotificationBinding itemsNotificationBinding){
            super(itemsNotificationBinding.getRoot());
            this.binding=itemsNotificationBinding;
        }
        public void getBinDing(ClassSVNotification tb){
            this.binding.setItemNotificationClass(tb);
            int cate= AuthAccount.getInstant().userAccount.get_category();
            if(cate==2){
//                holder.imgDeleItemNotifi.setVisibility(View.GONE);
                binding.imgDeleItemNotifi.setVisibility(View.GONE);
            }
            if(tb.get_nd().length()<=15){
//                holder.tvNdThongBao.setText(tb.get_nd());
                binding.tvNoiDungThongBao.setText(tb.get_nd());
            }
            else {
                String chuoi = tb.get_nd().substring(0, 10);
//                holder.tvNdThongBao.setText(chuoi+" .....");
                binding.tvNoiDungThongBao.setText(chuoi+".....");
            }
//            holder.tvNgayThongBao.setText(tb.get_datetime());
            binding.tvNgayThongBao.setText(tb.get_datetime());
//            holder.cv.setOnClickListener(v->{actionNotiClass.showDataNoti(tb);});
//            holder.imgDeleItemNotifi.setOnClickListener(v->{actionNotiClass.deleteNoti(tb);});
            this.binding.cvItemNoti.setOnClickListener(v->{
                actionNotiClass.showDataNoti(tb);
            });
            this.binding.imgDeleItemNotifi.setOnClickListener(v->{actionNotiClass.deleteNoti(tb);});
        }
//        public NotificationAdaperViewHodler(@NonNull View itemView) {
//            super(itemView);
//            tvNdThongBao=itemView.findViewById(R.id.tv_NoiDungThongBao);
//            tvNgayThongBao=itemView.findViewById(R.id.tv_ngayThongBao);
//            cv=itemView.findViewById(R.id.cv_item_noti);
//            imgDeleItemNotifi=itemView.findViewById(R.id.img_DeleItemNotifi);
//        }
    }
}
