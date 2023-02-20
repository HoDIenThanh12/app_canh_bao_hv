package com.example.tiki.app_canhbao.entity;

public class QuyCheVCHT {
    int _id;
    String _mucQuyChe;
    String _ndQuyChe;

    public QuyCheVCHT() {
    }

    public QuyCheVCHT(int _id, String _mucQuyChe, String _ndQuyChe) {
        this._id = _id;
        this._mucQuyChe = _mucQuyChe;
        this._ndQuyChe = _ndQuyChe;
    }

    @Override
    public String toString() {
        return "QuyCheVCHT{" +
                "_id=" + _id +
                ", _mucQuyChe='" + _mucQuyChe + '\'' +
                ", _ndQuyChe='" + _ndQuyChe + '\'' +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_mucQuyChe() {
        return _mucQuyChe;
    }

    public void set_mucQuyChe(String _mucQuyChe) {
        this._mucQuyChe = _mucQuyChe;
    }

    public String get_ndQuyChe() {
        return _ndQuyChe;
    }

    public void set_ndQuyChe(String _ndQuyChe) {
        this._ndQuyChe = _ndQuyChe;
    }
}
