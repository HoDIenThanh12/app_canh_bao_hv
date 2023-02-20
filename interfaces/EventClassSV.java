package com.example.tiki.app_canhbao.interfaces;

import com.example.tiki.app_canhbao.entity.ClassSV;
import com.example.tiki.app_canhbao.entity.DSClassQL;

public interface EventClassSV {
    void ShowListClassSV(DSClassQL clas);
    void DeleClassSV(DSClassQL clas);
    void ChuyenThongBaoQLClass (DSClassQL clas);
}
