package com.example.tiki.app_canhbao.viewmodel;

import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tiki.app_canhbao.entity.ClassSV;
import com.example.tiki.app_canhbao.entity.DSClassQL;

public class ClassSVFactory implements ViewModelProvider.Factory{
    DSClassQL clasSV;

    public ClassSVFactory(DSClassQL clasSV) {
        this.clasSV = clasSV;
    }

    public ClassSVFactory() {
    }
    public DSClassQL getClasSV() {
        return clasSV;
    }

    public void setClasSV(DSClassQL clasSV) {
        this.clasSV = clasSV;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(NotificationClassSVViewModule.class)){
            return (T) new NotificationClassSVViewModule(getClasSV());
        }
        throw new IllegalArgumentException("Argument is invalid");
    }


}
