package com.example.tiki.app_canhbao.adappers;

import android.annotation.SuppressLint;
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
import com.example.tiki.app_canhbao.entity.Message;
import com.example.tiki.app_canhbao.entity.User;

import java.util.List;

public class MessageAdaper extends RecyclerView.Adapter<MessageAdaper.MessageAdaperViewHodle> {
    List<Message> lM;
    Context context;
    User u;
    public MessageAdaper(Context context, User u) {
        this.context = context;
        this.u=u;
    }

    public void setlM(List<Message> lM) {
        this.lM = lM;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageAdaperViewHodle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_message,parent, false);
        return new MessageAdaperViewHodle(v);
    }

    @SuppressLint({"WrongConstant", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull MessageAdaperViewHodle holder, int position) {
        Message m =lM.get(position);
        if(m==null)
            return;
        holder.tvNd.setText(m.get_noiDung());
        holder.tvNgayGio.setText(m.get_ngaygio());
        LinearLayout.LayoutParams lay = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        String idUGui=AuthAccount.getInstant().userAccount.get_id();
        if (m.get_idNguoiGui().equals(idUGui)){
            holder.tvNameNguoiGuiNhan.setText(m.get_tenNguoiGui());
            lay.gravity = Gravity.END;
            holder.ln.setGravity(Gravity.END);
//            holder.ln.setBackgroundColor(R.color.register_bk_color);
            holder.ln.setBackgroundResource(R.drawable.items_custom_mesage_nguoigui);
            holder.lnbaoItemChat.setGravity(Gravity.END);
            holder.relitemChat.setGravity(Gravity.END);
        }
        else{
            holder.tvNameNguoiGuiNhan.setText(m.get_tenNguoiGui());
            holder.ln.setBackgroundResource(R.drawable.items_custom_message_nguoinhan);
        }
    }

    @Override
    public int getItemCount() {
        if(lM!=null)
            return lM.size();
        return 0;
    }

    public class MessageAdaperViewHodle extends RecyclerView.ViewHolder {
        TextView tvNd, tvNgayGio, tvNameNguoiGuiNhan;
        LinearLayout ln, lnbaoItemChat;
        RelativeLayout relitemChat;
        public MessageAdaperViewHodle(@NonNull View itemView) {
            super(itemView);
            tvNameNguoiGuiNhan=itemView.findViewById(R.id.tv_nameNguoiGuiNhan);
            tvNd=itemView.findViewById(R.id.tv_ndMes);
            tvNgayGio=itemView.findViewById(R.id.tv_ngaygioMes);
            ln= itemView.findViewById(R.id.layout_content);
            lnbaoItemChat=itemView.findViewById(R.id.ln_baoitem);
            relitemChat=itemView.findViewById(R.id.rel_baoitem);
        }
    }
}
