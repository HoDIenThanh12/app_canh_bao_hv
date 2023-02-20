package com.example.tiki.app_canhbao.factorys;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tiki.app_canhbao.entity.ClassSV;
import com.example.tiki.app_canhbao.entity.DSClassQL;
import com.example.tiki.app_canhbao.viewmodel.HomeViewModule;
import com.example.tiki.app_canhbao.viewmodel.ListUserMeettingViewModel;
import com.example.tiki.app_canhbao.viewmodel.NotificationClassSVViewModule;

public class ClassSVFactory implements ViewModelProvider.Factory {
    DSClassQL clas;

    public ClassSVFactory(DSClassQL clas) {
        this.clas = clas;
    }

    public DSClassQL getClas() {
        return clas;
    }

    public void setClas(DSClassQL clas) {
        this.clas = clas;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ListUserMeettingViewModel.class)){
            return (T) new NotificationClassSVViewModule(getClas());
        }
        throw new IllegalArgumentException("Argument is invalid");
    }
}
