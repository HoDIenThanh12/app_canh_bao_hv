package com.example.tiki.app_canhbao.entity;

import java.io.Serializable;

public class Meettings implements Serializable {
    private String _id;
    private String _idClass;
    private String _nameClass;
    private String _idPhong;
    private String _nameMeet;
    private String _contentMeet;
    private String _timeStart;
    private String _dayStart;

    public Meettings() {
    }

    public Meettings(String _idClass, String _nameClass, String _idPhong, String _nameMeet, String _contentMeet, String _timeStart, String _dayStart) {
        this._idClass = _idClass;
        this._nameClass = _nameClass;
        this._idPhong = _idPhong;
        this._nameMeet = _nameMeet;
        this._contentMeet = _contentMeet;
        this._timeStart = _timeStart;
        this._dayStart = _dayStart;
    }

    @Override
    public String toString() {
        return "Meettings{" +
                "_id='" + _id + '\'' +
                ", _idClass='" + _idClass + '\'' +
                ", _nameClass='" + _nameClass + '\'' +
                ", _idPhong='" + _idPhong + '\'' +
                ", _nameMeet='" + _nameMeet + '\'' +
                ", _contentMeet='" + _contentMeet + '\'' +
                ", _timeStart='" + _timeStart + '\'' +
                ", _dayStart='" + _dayStart + '\'' +
                '}';
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

    public String get_idPhong() {
        return _idPhong;
    }

    public void set_idPhong(String _idPhong) {
        this._idPhong = _idPhong;
    }

    public String get_nameMeet() {
        return _nameMeet;
    }

    public void set_nameMeet(String _nameMeet) {
        this._nameMeet = _nameMeet;
    }

    public String get_contentMeet() {
        return _contentMeet;
    }

    public void set_contentMeet(String _contentMeet) {
        this._contentMeet = _contentMeet;
    }

    public String get_timeStart() {
        return _timeStart;
    }

    public void set_timeStart(String _timeStart) {
        this._timeStart = _timeStart;
    }

    public String get_dayStart() {
        return _dayStart;
    }

    public void set_dayStart(String _dayStart) {
        this._dayStart = _dayStart;
    }
}
