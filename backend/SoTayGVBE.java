package com.example.tiki.app_canhbao.backend;

import com.example.tiki.app_canhbao.constants.InstantSoTayCVHT;
import com.example.tiki.app_canhbao.entity.QuyCheVCHT;
import com.example.tiki.app_canhbao.entity.SoTayCVHT;

import java.util.ArrayList;
import java.util.List;

public class SoTayGVBE {
    private static List<SoTayCVHT> l;
     //get các dann sách sổ tay
    public static List<SoTayCVHT> getQC1(){
        l= new ArrayList<>();
        l.addAll(InstantSoTayCVHT.inItChuong1());
        return l;
    }
    public static List<SoTayCVHT> getQC2(){
        l= new ArrayList<>();
        l.addAll(InstantSoTayCVHT.inItChuong2());
        return l;
    }
    public static List<SoTayCVHT> getQC3(){
        l= new ArrayList<>();
        l.addAll(InstantSoTayCVHT.inItChuong3());
        return l;
    }
    public static List<SoTayCVHT> getQC4(){
        l= new ArrayList<>();
        l.addAll(InstantSoTayCVHT.inItChuong4());
        return l;
    }
}
