package com.example.tiki.app_canhbao.backend;

import com.example.tiki.app_canhbao.constants.InstanrSoTaySinhVien;
import com.example.tiki.app_canhbao.constants.InstantSoTayCVHT;
import com.example.tiki.app_canhbao.entity.SoTayCVHT;
import com.example.tiki.app_canhbao.entity.SotaySV;

import java.util.ArrayList;
import java.util.List;

public class SoTaySinhVienBE {
    private static List<SotaySV> l;
    //get các dann sách sổ tay
    public static List<SotaySV> getST1(){
        l= new ArrayList<>();
        l.addAll(InstanrSoTaySinhVien.inItSotayChuong1());
        return l;
    }
    public static List<SotaySV> getST2(){
        l= new ArrayList<>();
        l.addAll(InstanrSoTaySinhVien.inItSoTayChuong2());
        return l;
    }
    public static List<SotaySV> getST3(){
        l= new ArrayList<>();
        l.addAll(InstanrSoTaySinhVien.inItSoTayChuong3());
        return l;
    }
    public static List<SotaySV> getST4(){
        l= new ArrayList<>();
        l.addAll(InstanrSoTaySinhVien.inItChuong4());
        return l;
    }
    public static List<SotaySV> getST5(){
        l= new ArrayList<>();
        l.addAll(InstanrSoTaySinhVien.inItSoTayChuong5());
        return l;
    }
}
