package com.example.tiki.app_canhbao.entity;

import java.io.Serializable;

public class ThongBao implements Serializable {
    private String _id;
    private String _name;
    private String _nd;
    private String _time;
    private String _ngay;

    public ThongBao() {
    }

    public ThongBao(String _id, String _name, String _nd) {
        this._id = _id;
        this._name = _name;
        this._nd = _nd;
    }

    public ThongBao(String _id, String _name, String _nd, String _time, String _ngay) {
        this._id = _id;
        this._name = _name;
        this._nd = _nd;
        this._time = _time;
        this._ngay = _ngay;
    }

    @Override
    public String toString() {
        return "ThongBao{" +
                "_id='" + _id + '\'' +
                ", _name='" + _name + '\'' +
                ", _nd='" + _nd + '\'' +
                ", _time='" + _time + '\'' +
                ", _ngay='" + _ngay + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_nd() {
        return _nd;
    }

    public void set_nd(String _nd) {
        this._nd = _nd;
    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }

    public String get_ngay() {
        return _ngay;
    }

    public void set_ngay(String _ngay) {
        this._ngay = _ngay;
    }
}
