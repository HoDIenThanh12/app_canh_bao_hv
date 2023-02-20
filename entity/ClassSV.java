package com.example.tiki.app_canhbao.entity;

import java.io.Serializable;

public class ClassSV implements Serializable {
    private String _id;
    private String _nameClass;
    private int _SS=0;

    public ClassSV() {
    }

    public ClassSV(String _nameClass, int _SS) {
        this._nameClass = _nameClass;
        this._SS = _SS;
    }

    public ClassSV(String _id, String _nameClass, int _SS) {
        this._id = _id;
        this._nameClass = _nameClass;
        this._SS = _SS;
    }

    @Override
    public String toString() {
        return "ClassSV{" +
                "_id='" + _id + '\'' +
                ", _nameClass='" + _nameClass + '\'' +
                ", _SS=" + _SS +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_nameClass() {
        return _nameClass;
    }

    public void set_nameClass(String _nameClass) {
        this._nameClass = _nameClass;
    }


    public int get_SS() {
        return _SS;
    }

    public void set_SS(int _SS) {
        this._SS = _SS;
    }
}
