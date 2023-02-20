package com.example.tiki.app_canhbao.adappers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.Message;
import com.example.tiki.app_canhbao.interfaces.ActionQuanLyChat;

import java.util.List;

public class QuanLyChatAdaper extends RecyclerView.Adapter<QuanLyChatAdaper.QuanLyChatAdaperViewHodler> {

    List<Message> lMes;
    Context context;
    ActionQuanLyChat actionQuanLyChat;

    public QuanLyChatAdaper(Context context, ActionQuanLyChat actionQuanLyChat) {
        this.context = context;
        this.actionQuanLyChat = actionQuanLyChat;
    }

    public void setlMes(List<Message> lMes) {
        this.lMes = lMes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuanLyChatAdaperViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_qly_chat, parent,false);

        return new QuanLyChatAdaperViewHodler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuanLyChatAdaperViewHodler holder, int position) {
        Message mes = lMes.get(position);
        if(mes==null)
            return;
        String id= AuthAccount.getInstant().userAccount.get_id();
        String names = "";

        if(mes.get_idNguoiGui().equals(id)){
            names=mes.get_tenNguoiNhan();
        }
        else
            names=mes.get_tenNguoiGui();
        holder.tvNameUGui.setText(names);
        if(mes.get_noiDung().length()>=30){
            String chuoi = mes.get_noiDung().substring(0, 20);
            holder.tvNoiDungChat.setText(chuoi+".....");
        }else{
            holder.tvNoiDungChat.setText(mes.get_noiDung());
        }
        holder.tvNgayChat.setText(mes.get_ngaygio());
        holder.lncardItemChat.setOnClickListener(v->{
            actionQuanLyChat.chats(mes);
        });
    }

    @Override
    public int getItemCount() {
        if(lMes!=null)
            return lMes.size();
        return 0;
    }

    public class QuanLyChatAdaperViewHodler extends RecyclerView.ViewHolder {
        TextView tvNameUGui, tvNoiDungChat, tvNgayChat;
        LinearLayout lncardItemChat;
        public QuanLyChatAdaperViewHodler(@NonNull View itemView) {
            super(itemView);
            tvNameUGui = itemView.findViewById(R.id.tv_tenUGuiQLChat);
            tvNoiDungChat = itemView.findViewById(R.id.tv_noidungQlChat);
            tvNgayChat = itemView.findViewById(R.id.tv_ngayQlChat);
            lncardItemChat  =itemView.findViewById(R.id.ln_cardItemChat);
        }
    }
}
