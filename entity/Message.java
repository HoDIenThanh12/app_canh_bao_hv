package com.example.tiki.app_canhbao.entity;

import java.io.Serializable;

public class Message implements Serializable {
    private String _id;
    private String _idNguoiGui;
    private String _tenNguoiGui;
    private String _tokenNguoiGui;
    private String _idNguoiNhan;
    private String _tenNguoiNhan;
    private String _tokenNguoiNhan;
    private String _noiDung;
    private String _ngaygio;


    public Message() {
    }

    public Message(String _idNguoiGui, String _tenNguoiGui, String _tokenNguoiGui, String _idNguoiNhan, String _tenNguoiNhan, String _tokenNguoiNhan, String _noiDung, String _ngaygio) {
        this._idNguoiGui = _idNguoiGui;
        this._tenNguoiGui = _tenNguoiGui;
        this._tokenNguoiGui = _tokenNguoiGui;
        this._idNguoiNhan = _idNguoiNhan;
        this._tenNguoiNhan = _tenNguoiNhan;
        this._tokenNguoiNhan = _tokenNguoiNhan;
        this._noiDung = _noiDung;
        this._ngaygio = _ngaygio;
    }

    @Override
    public String toString() {
        return "Message{" +
                ", _idNguoiGui='" + _idNguoiGui + '\'' +
                ", _tenNguoiGui='" + _tenNguoiGui + '\'' +
                ", _idNguoiNhan='" + _idNguoiNhan + '\'' +
                ", _tenNguoiNhan='" + _tenNguoiNhan + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_idNguoiGui() {
        return _idNguoiGui;
    }

    public void set_idNguoiGui(String _idNguoiGui) {
        this._idNguoiGui = _idNguoiGui;
    }

    public String get_tenNguoiGui() {
        return _tenNguoiGui;
    }

    public void set_tenNguoiGui(String _tenNguoiGui) {
        this._tenNguoiGui = _tenNguoiGui;
    }

    public String get_idNguoiNhan() {
        return _idNguoiNhan;
    }

    public void set_idNguoiNhan(String _idNguoiNhan) {
        this._idNguoiNhan = _idNguoiNhan;
    }

    public String get_tenNguoiNhan() {
        return _tenNguoiNhan;
    }

    public void set_tenNguoiNhan(String _tenNguoiNhan) {
        this._tenNguoiNhan = _tenNguoiNhan;
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

    public String get_tokenNguoiGui() {
        return _tokenNguoiGui;
    }

    public void set_tokenNguoiGui(String _tokenNguoiGui) {
        this._tokenNguoiGui = _tokenNguoiGui;
    }

    public String get_tokenNguoiNhan() {
        return _tokenNguoiNhan;
    }

    public void set_tokenNguoiNhan(String _tokenNguoiNhan) {
        this._tokenNguoiNhan = _tokenNguoiNhan;
    }
}
