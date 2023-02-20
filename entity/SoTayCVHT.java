package com.example.tiki.app_canhbao.entity;

public class SoTayCVHT {
    int _id;
    String _mucSoTay;
    String _ndSoTay;

    public SoTayCVHT() {
    }

    public SoTayCVHT(int _id, String _mucSoTay, String _ndSoTay) {
        this._id = _id;
        this._mucSoTay = _mucSoTay;
        this._ndSoTay = _ndSoTay;
    }

    @Override
    public String toString() {
        return "QuyCheVCHT{" +
                "_id=" + _id +
                ", _mucQuyChe='" + _mucSoTay + '\'' +
                ", _ndQuyChe='" + _ndSoTay + '\'' +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_mucSoTay() {
        return _mucSoTay;
    }

    public void set_mucSoTay(String _mucSoTay) {
        this._mucSoTay = _mucSoTay;
    }

    public String get_ndSoTay() {
        return _ndSoTay;
    }

    public void set_ndSoTay(String _ndSoTay) {
        this._ndSoTay = _ndSoTay;
    }
}
