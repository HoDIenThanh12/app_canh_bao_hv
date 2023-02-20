package com.example.tiki.app_canhbao.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClassSVNotification {
    @SerializedName("_id")
    private String _id;
    @SerializedName("_idClass")
    private String _idClass;
    @SerializedName("_nameClass")
    private String _nameClass;
    @SerializedName("_nd")
    private String _nd;
    @SerializedName("_datetime")
    private String _datetime;

    public ClassSVNotification() {
    }

    public ClassSVNotification(String _idClass, String _nameClass, String _nd, String _datetime) {
        this._idClass = _idClass;
        this._nameClass = _nameClass;
        this._nd = _nd;
        this._datetime = _datetime;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_idClass() {
        return _idClass;
    }

    public void set_idClass(String _idClass) {
        this._idClass = _idClass;
    }

    public String get_nameClass() {
        return _nameClass;
    }

    public void set_nameClass(String _nameClass) {
        this._nameClass = _nameClass;
    }

    public String get_nd() {
        return _nd;
    }

    public void set_nd(String _nd) {
        this._nd = _nd;
    }

    public String get_datetime() {
        return _datetime;
    }

    public void set_datetime(String _datetime) {
        this._datetime = _datetime;
    }
}
