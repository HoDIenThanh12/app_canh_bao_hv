package com.example.tiki.app_canhbao.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tiki.app_canhbao.entity.Meettings;
import com.example.tiki.app_canhbao.views.MainActivityUserListMeetting;

public class ListUserMetingFactory implements ViewModelProvider.Factory {
    private Meettings mFactory;

    public ListUserMetingFactory(Meettings mFactory) {
        this.mFactory = mFactory;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ListUserMeettingViewModel.class)){
            return (T) new ListUserMeettingViewModel(getmFactory());
        }
        throw new IllegalArgumentException("Argument is invalid");
    }

    public Meettings getmFactory() {
        return mFactory;
    }

    public void setmFactory(Meettings mFactory) {
        this.mFactory = mFactory;
    }
}
