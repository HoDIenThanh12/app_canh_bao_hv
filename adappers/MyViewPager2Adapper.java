package com.example.tiki.app_canhbao.adappers;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.fragments.FragmentAbout;
import com.example.tiki.app_canhbao.fragments.FragmentAboutGV;
import com.example.tiki.app_canhbao.fragments.FragmentHome;
import com.example.tiki.app_canhbao.fragments.FragmentMeetting;
import com.example.tiki.app_canhbao.fragments.FragmentSoTayCVHT;
import com.example.tiki.app_canhbao.fragments.FragmentSoTaySinhVien;

public class MyViewPager2Adapper extends FragmentStateAdapter {

    public MyViewPager2Adapper(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
         int cate =AuthAccount.getInstant().userAccount.get_category();
//        if(cate ==2 ){
//            switch (position){
//                case 0:
//                    return new FragmentHome();
//                case 1:
//                    return new FragmentSoTayCVHT();
//                case 2:
//                    return new FragmentAbout();
////                    return new FragmentHome();
//
//                default:
//                    return new FragmentHome();
//            }
//        }else
            switch (position){
            case 0:
                return new FragmentHome();
            case 1: {
                if(cate==1)
                    return new FragmentMeetting();
                return new FragmentAboutGV();
            }
            case 2: {
                if (cate == 1)
                    return new FragmentSoTayCVHT();
                return new FragmentSoTaySinhVien();
            }
            case 3:
                return new FragmentAbout();
            default:
                return new FragmentSoTayCVHT();
        }
    }

    @Override
    public int getItemCount() {
        int id= AuthAccount.getInstant().userAccount.get_category();
//        return id==2?4:3;return id==2?4:3;
        return 4;
    }
}
