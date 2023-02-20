package com.example.tiki.app_canhbao.factorys;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tiki.app_canhbao.entity.DSClassQL;

public class ThongTinCVFactory implements ViewModelProvider.Factory{
    DSClassQL classQL;

    public ThongTinCVFactory(DSClassQL classQL) {
        this.classQL = classQL;
    }

    public DSClassQL getClassQL() {
        return classQL;
    }

    public void setClassQL(DSClassQL classQL) {
        this.classQL = classQL;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return null;
    }
}
