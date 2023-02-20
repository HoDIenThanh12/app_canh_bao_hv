package com.example.tiki.app_canhbao.entity;

import retrofit2.http.Query;

public class NoticationClass {
    private String token;
    private String nameClass;
    private String noidung;
    private String ngaygio;

    public NoticationClass() {
    }

    public NoticationClass(String token, String nameClass, String noidung) {
        this.token = token;
        this.nameClass = nameClass;
        this.noidung = noidung;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getNgaygio() {
        return ngaygio;
    }

    public void setNgaygio(String ngaygio) {
        this.ngaygio = ngaygio;
    }
}
