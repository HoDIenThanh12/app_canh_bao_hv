package com.example.tiki.app_canhbao.entity;

public class SotaySV {
    private int _id;
    private String _mucSTSV;
    private String _NoiDungSTSV;

    public SotaySV() {
    }

    public SotaySV(int _id, String _mucSTSV, String _NoiDungSTSV) {
        this._id = _id;
        this._mucSTSV = _mucSTSV;
        this._NoiDungSTSV = _NoiDungSTSV;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_mucSTSV() {
        return _mucSTSV;
    }

    public void set_mucSTSV(String _mucSTSV) {
        this._mucSTSV = _mucSTSV;
    }

    public String get_NoiDungSTSV() {
        return _NoiDungSTSV;
    }

    public void set_NoiDungSTSV(String _NoiDungSTSV) {
        this._NoiDungSTSV = _NoiDungSTSV;
    }
}
