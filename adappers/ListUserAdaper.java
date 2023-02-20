package com.example.tiki.app_canhbao.adappers;

import android.content.Context;
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
import com.example.tiki.app_canhbao.interfaces.EventUserMeetting;

import java.util.ArrayList;
import java.util.List;

public class ListUserAdaper extends RecyclerView.Adapter<ListUserAdaper.ListUserAdaperViewHolder> implements Filterable {
    boolean isAdd=true;
    List<User> lUser;
    List<User> lUserKey;
    Context context;
    EventUserMeetting eventUserMeetting;
    public ListUserAdaper(Context context, EventUserMeetting eventUserMeetting) {
        this.context = context;
        this.eventUserMeetting=eventUserMeetting;
    }

//    public void setlUser(List<User> lUser) {
////        this.lUser = lUser;
////        this.lUserKey = lUser;
////        notifyDataSetChanged();
//    }

    public ListUserAdaper(List<User> lUser, EventUserMeetting eventUserMeetting) {
        this.lUser = lUser;
        this.eventUserMeetting = eventUserMeetting;
        this.lUserKey = lUser;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListUserAdaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_user_list_meetting, parent,false);
        return new ListUserAdaperViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListUserAdaperViewHolder holder, int position) {
        User u = lUser.get(position);
        if(u==null)
            return;
        if(isAdd){
            holder.btnAddUserMeetting.setText("Thêm");
            holder.tvNameUserMeetting.setText(u.get_Name());
            if(u.get_category()==1){
                holder.tvMSSVUserMeetting.setText(u.get_MSSV()+"");
                holder.tvClassUserMeetting.setText(u.get_Class());
            }
            else {
                holder.tvClassUserMeetting.setVisibility(View.GONE);
                holder.tvMSSVUserMeetting.setText("Giảng viên");
            }
            holder.btnAddUserMeetting.setOnClickListener(v->{
                if(isAdd){
                    eventUserMeetting.addUserMetting(u);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(lUser!=null)
            return lUser.size();
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString();
                if(key.isEmpty()){
                    lUser=lUserKey;
                    Log.e("---> ","key rỗng");
                }
                else {
                    Log.e("---> ","key có");
                    List<User> ListK=new ArrayList<>();
                    for (User u: lUserKey){
                        if(u.get_Name().toLowerCase().contains(key.toLowerCase())
                           || u.get_MSSV().toLowerCase().contains(key.toLowerCase())){
                            ListK.add(u);
                        }
                    }
                    lUser=ListK;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=lUser;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                lUser= (List<User>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ListUserAdaperViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameUserMeetting, tvMSSVUserMeetting, tvClassUserMeetting, tvNoteUserMeetting;
        Button btnDeleteUserMeetting, btnAddUserMeetting;
        public ListUserAdaperViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameUserMeetting = itemView.findViewById(R.id.tv_NameUserMeetting);
            tvClassUserMeetting = itemView.findViewById(R.id.tv_ClassUserMeetting);
            tvNoteUserMeetting =  itemView.findViewById(R.id.tv_MSSVorNoteUserMeetting);
            tvMSSVUserMeetting =  itemView.findViewById(R.id.tv_MSSVorNoteUserMeetting);
            btnAddUserMeetting =  itemView.findViewById(R.id.btn_AddOrDeleUserMeetting);
            btnAddUserMeetting =  itemView.findViewById(R.id.btn_AddOrDeleUserMeetting);
        }
    }
}
