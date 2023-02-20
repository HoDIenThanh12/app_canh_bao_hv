package com.example.tiki.app_canhbao.adappers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.List;

public class ListUserMeettingSelectAdaper extends RecyclerView.Adapter<ListUserMeettingSelectAdaper.ListUserMeettingSelectViewHolder> implements Filterable {
    List<User> lUser;
    List<User> lUserKey;
    Context context;
    boolean isListUserSelect=true;

    public ListUserMeettingSelectAdaper(Context context) {
        this.context = context;
    }

    public void setlUser(List<User> lUser) {
        this.lUser = lUser;
        this.lUserKey= lUser;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListUserMeettingSelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_user_list_meetting, parent,false);
        return new ListUserMeettingSelectViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListUserMeettingSelectViewHolder holder, int position) {
        User u= lUser.get(position);
        if(u==null){
            return;
        }
        else {
            if(isListUserSelect){
                holder.btnAddUserMeetting.setText("Xóa");
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
                    if(isListUserSelect){
                        //eventUserMeetting.addUserMetting(u);
                        deleteUser(position);
                    }
                });
            }
        }
    }
    private void deleteUser(int idex){
        new AlertDialog.Builder(context)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        lUser.remove(idex);
                        setlUser(lUser);
                        Log.e("-->","xóa user");
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
    @Override
    public int getItemCount() {
        if(lUser!=null){
            return lUser.size();
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
                    lUser = lUserKey;
                    Log.e("---> ","key rỗng");
                }
                else {
                    Log.e("---> ","key có");
                    List<User> lkey=new ArrayList<>();
                    for(User u: lUserKey){
                        if(u.get_Name().toLowerCase().contains(key.toLowerCase())
                                || u.get_MSSV().toLowerCase().contains(key.toLowerCase())){
                            lkey.add(u);
                        }
                    }
                    lUser=lkey;
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

    public class ListUserMeettingSelectViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameUserMeetting, tvMSSVUserMeetting, tvClassUserMeetting, tvNoteUserMeetting;
        Button btnDeleteUserMeetting, btnAddUserMeetting;
        public ListUserMeettingSelectViewHolder(@NonNull View itemView) {
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
