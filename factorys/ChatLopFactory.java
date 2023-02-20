package com.example.tiki.app_canhbao.factorys;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tiki.app_canhbao.entity.ChatLop;
import com.example.tiki.app_canhbao.entity.DSClassQL;
import com.example.tiki.app_canhbao.viewmodel.ChatLopViewModule;
import com.example.tiki.app_canhbao.viewmodel.ListUserMeettingViewModel;
import com.example.tiki.app_canhbao.viewmodel.NotificationClassSVViewModule;

public class ChatLopFactory implements ViewModelProvider.Factory {
    ChatLop classQL;

    public ChatLopFactory(ChatLop classQL) {
        this.classQL = classQL;
    }

    public ChatLop getClassQL() {
        return classQL;
    }

    public void setClassQL(ChatLop classQL) {
        this.classQL = classQL;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ChatLopViewModule.class)){
            return (T) new ChatLopViewModule(getClassQL());
        }
        throw new IllegalArgumentException("Argument is invalid");
    }
}
