package com.example.tiki.app_canhbao.backend;

import com.example.tiki.app_canhbao.constants.InstantQuyCheCVHT;
import com.example.tiki.app_canhbao.constants.InstantSoTayCVHT;
import com.example.tiki.app_canhbao.entity.QuyCheVCHT;
import com.example.tiki.app_canhbao.entity.SoTayCVHT;

import java.util.ArrayList;
import java.util.List;

public class CacQuyCheCVHTBE {
    private static List<QuyCheVCHT> l;
    //get danh sách quy chế
    public static List<QuyCheVCHT> getQC1(){
        l= new ArrayList<>();
        l.addAll(InstantQuyCheCVHT.inItQuyChe1());
        return l;
    }
    public static List<QuyCheVCHT> getQC2(){
        l= new ArrayList<>();
        l.addAll(InstantQuyCheCVHT.inItQuyChe2());
        return l;
    }
    public static List<QuyCheVCHT> getQC3(){
        l= new ArrayList<>();
        l.addAll(InstantQuyCheCVHT.inItQuyChe3());
        return l;
    }
    public static List<QuyCheVCHT> getQC4(){
        l= new ArrayList<>();
        l.addAll(InstantQuyCheCVHT.inItQuyChe4());
        return l;
    }
}
