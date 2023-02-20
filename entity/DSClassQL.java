package com.example.tiki.app_canhbao.entity;

import java.io.Serializable;

public class DSClassQL implements Serializable {
    private String _id;
    private String _idGV;
    private String _nameGV;
    private String _nameClass;
    private int _siso;

    public DSClassQL() {
    }


    public DSClassQL(String _id, String _idGV, String _nameGV, String _nameClass, int _siso) {
        this._id = _id;
        this._idGV = _idGV;
        this._nameGV = _nameGV;
        this._nameClass = _nameClass;
        this._siso = _siso;
    }

    @Override
    public String toString() {
        return "DSClassQL{" +
                "_id='" + _id + '\'' +
                ", _idGV='" + _idGV + '\'' +
                ", _nameGV='" + _nameGV + '\'' +
                ", _nameClass='" + _nameClass + '\'' +
                ", _siso=" + _siso +
                '}';
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

    public String get_nameGV() {
        return _nameGV;
    }

    public void set_nameGV(String _nameGV) {
        this._nameGV = _nameGV;
    }

    public String get_nameClass() {
        return _nameClass;
    }

    public void set_nameClass(String _nameClass) {
        this._nameClass = _nameClass;
    }

    public int get_siso() {
        return _siso;
    }

    public void set_siso(int _siso) {
        this._siso = _siso;
    }
}
