package com.example.tiki.app_canhbao.entity;

import java.io.Serializable;

public class ChatLop implements Serializable {
    private String _id;
    private String _idGV;
    private String _idNguoiGuiNhan;
    private String _nameClass;
    private String _tenNguoiGuiNhan;
    private String _noiDung;
    private String _ngaygio;

    public ChatLop() {
    }

    public ChatLop(String _idGV, String _idNguoiGuiNhan, String _nameClass, String _tenNguoiGuiNhan,  String _noiDung, String _ngaygio) {
        this._idGV = _idGV;
        this._idNguoiGuiNhan = _idNguoiGuiNhan;
        this._nameClass = _nameClass;
        this._tenNguoiGuiNhan = _tenNguoiGuiNhan;
        this._noiDung = _noiDung;
        this._ngaygio = _ngaygio;
    }

    @Override
    public String toString() {
        return "ChatLop{" +
                "_id='" + _id + '\'' +
                ", _idGV='" + _idGV + '\'' +
                ", _idNguoiGuiNhan='" + _idNguoiGuiNhan + '\'' +
                ", _nameClass='" + _nameClass + '\'' +
                ", _tenNguoiGuiNhan='" + _tenNguoiGuiNhan + '\'' +
                ", _noiDung='" + _noiDung + '\'' +
                ", _ngaygio='" + _ngaygio + '\'' +
                '}';
    }

    public String get_nameClass() {
        return _nameClass;
    }

    public void set_nameClass(String _nameClass) {
        this._nameClass = _nameClass;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_idGV() {
        return _idGV;
    }

    public void set_idGV(String _idGV) {
        this._idGV = _idGV;
    }

    public String get_idNguoiGuiNhan() {
        return _idNguoiGuiNhan;
    }

    public void set_idNguoiGuiNhan(String _idNguoiGuiNhan) {
        this._idNguoiGuiNhan = _idNguoiGuiNhan;
    }

    public String get_tenNguoiGuiNhan() {
        return _tenNguoiGuiNhan;
    }

    public void set_tenNguoiGuiNhan(String _tenNguoiGuiNhan) {
        this._tenNguoiGuiNhan = _tenNguoiGuiNhan;
    }



    public String get_noiDung() {
        return _noiDung;
    }

    public void set_noiDung(String _noiDung) {
        this._noiDung = _noiDung;
    }

    public String get_ngaygio() {
        return _ngaygio;
    }

    public void set_ngaygio(String _ngaygio) {
        this._ngaygio = _ngaygio;
    }
}
