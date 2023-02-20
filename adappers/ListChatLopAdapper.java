package com.example.tiki.app_canhbao.adappers;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.ChatLop;
import com.example.tiki.app_canhbao.entity.Message;

import java.util.List;

public class ListChatLopAdapper extends RecyclerView.Adapter<ListChatLopAdapper.ListChatLopAdapperViewHodler> {
    List<ChatLop> lMessageList;
    Context context;

    public ListChatLopAdapper(Context context) {
        this.context = context;
    }

    public void setlMessageList(List<ChatLop> lMessageList) {
        this.lMessageList = lMessageList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListChatLopAdapperViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_message,parent, false);
        return new ListChatLopAdapperViewHodler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListChatLopAdapperViewHodler holder, int position) {
        ChatLop m =lMessageList.get(position);
        if(m==null)
            return;
        holder.contentChat.setText(m.get_noiDung());
        holder.dateChat.setText(m.get_ngaygio());
        LinearLayout.LayoutParams lay = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        String idUGui= AuthAccount.getInstant().userAccount.get_id();
        if (m.get_idNguoiGuiNhan().equals(idUGui)){
            holder.tvNameNguoiGuiNhan.setText(m.get_tenNguoiGuiNhan());
            lay.gravity = Gravity.END;
            holder.ln.setGravity(Gravity.END);
//            holder.ln.setBackgroundColor(R.color.register_bk_color);
            holder.ln.setBackgroundResource(R.drawable.items_custom_mesage_nguoigui);
            holder.lnbaoItemChat.setGravity(Gravity.END);
            holder.relitemChat.setGravity(Gravity.END);
        }
        else{
            holder.tvNameNguoiGuiNhan.setText(m.get_tenNguoiGuiNhan());
            holder.ln.setBackgroundResource(R.drawable.items_custom_message_nguoinhan);
        }
    }

    @Override
    public int getItemCount() {
        if(lMessageList!=null)
            return lMessageList.size();
        return 0;
    }

    public class ListChatLopAdapperViewHodler extends RecyclerView.ViewHolder {
        TextView tvNameNguoiGuiNhan, dateChat, contentChat;
        LinearLayout ln, lnbaoItemChat;
        RelativeLayout relitemChat;
        public ListChatLopAdapperViewHodler(@NonNull View itemView) {
            super(itemView);
            tvNameNguoiGuiNhan=itemView.findViewById(R.id.tv_nameNguoiGuiNhan);
            contentChat=itemView.findViewById(R.id.tv_ndMes);
            dateChat=itemView.findViewById(R.id.tv_ngaygioMes);
            ln= itemView.findViewById(R.id.layout_content);
            lnbaoItemChat=itemView.findViewById(R.id.ln_baoitem);
            relitemChat=itemView.findViewById(R.id.rel_baoitem);
        }
    }
}
