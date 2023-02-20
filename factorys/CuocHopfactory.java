package com.example.tiki.app_canhbao.factorys;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tiki.app_canhbao.entity.ClassSV;
import com.example.tiki.app_canhbao.entity.DSClassQL;
import com.example.tiki.app_canhbao.viewmodel.CuocHopViewModule;
import com.example.tiki.app_canhbao.viewmodel.ListUserMeettingViewModel;
import com.example.tiki.app_canhbao.viewmodel.NotificationClassSVViewModule;
import com.example.tiki.app_canhbao.viewmodel.ThongTinCVViewModule;

public class CuocHopfactory implements ViewModelProvider.Factory {
    DSClassQL clas;

    public CuocHopfactory(DSClassQL clas) {
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
        if(modelClass.isAssignableFrom(CuocHopViewModule.class)){
            return (T) new CuocHopViewModule(getClas());
        }
        throw new IllegalArgumentException("Argument is invalid");
    }
}
