package com.example.tiki.app_canhbao.entity;

public class NotifiChat {
    private String token;
    private String nameGV;
    private String noidung;
    private String ngaygio;

    public NotifiChat() {
    }

    public NotifiChat(String token, String nameGV, String noidung) {
        this.token = token;
        this.nameGV = nameGV;
        this.noidung = noidung;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNameGV() {
        return nameGV;
    }

    public void setNameGV(String nameGV) {
        this.nameGV = nameGV;
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
