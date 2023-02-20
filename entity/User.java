package com.example.tiki.app_canhbao.entity;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class User implements Serializable {
    private String _id;
    private String _Name;
    private String _MSSV;
    private String _Khoa;
    private String _Class;
    private String _Image;
    private String _Email;
    private String _Pass;
    private String _Token;
    private int _category;
    private String _note;
    private String _sdt;

    public User() {
    }

    public User(String _Name, String _Email, String _Pass, int _category, String _Token) {
        this._Name = _Name;
        this._Email = _Email;
        this._Pass = _Pass;
        this._category = _category;
        this._Token =_Token;
    }
    public User(String _Name, String _Email, String _Pass, int _category, String _Token, String _Class) {
        this._Name = _Name;
        this._Email = _Email;
        this._Pass = _Pass;
        this._category = _category;
        this._Token =_Token;
        this._Class=_Class;
    }
    public User(String _id, String _Name, String _MSSV, String _Khoa, String _Class, String _Image, String _Email, String _Pass, int _category, String sdt) {
        this._id = _id;
        this._Name = _Name;
        this._MSSV = _MSSV;
        this._Khoa = _Khoa;
        this._Class = _Class;
        this._Image = _Image;
        this._Email = _Email;
        this._Pass = _Pass;
        this._category=_category;
        this._sdt=sdt;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", _Name='" + _Name + '\'' +
                ", _MSSV='" + _MSSV + '\'' +
                ", _Khoa='" + _Khoa + '\'' +
                ", _Class='" + _Class + '\'' +
                ", _Image='" + _Image + '\'' +
                ", _Email='" + _Email + '\'' +
                ", _Pass='" + _Pass + '\'' +
                ", _Token='" + _Token + '\'' +
                ", _category=" + _category +
                ", _note='" + _note + '\'' +
                ", _sdt='" + _sdt + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_Name() {
        return _Name;
    }

    public void set_Name(String _Name) {
        this._Name = _Name;
    }

    public String get_MSSV() {
        return _MSSV;
    }

    public void set_MSSV(String _MSSV) {
        this._MSSV = _MSSV;
    }

    public String get_Khoa() {
        return _Khoa;
    }

    public void set_Khoa(String _Khoa) {
        this._Khoa = _Khoa;
    }

    public String get_Class() {
        return _Class;
    }

    public void set_Class(String _Class) {
        this._Class = _Class;
    }

    public String get_Image() {
        return _Image;
    }

    public void set_Image(String _Image) {
        this._Image = _Image;
    }

    public String get_Email() {
        return _Email;
    }

    public void set_Email(String _Email) {
        this._Email = _Email;
    }

    public String get_Pass() {
        return _Pass;
    }

    public void set_Pass(String _Pass) {
        this._Pass = _Pass;
    }

    public int get_category() {
        return _category;
    }

    public void set_category(int _category) {
        this._category = _category;
    }

    public String get_Token() {
        return _Token;
    }

    public void set_Token(String _Token) {
        this._Token = _Token;
    }

    public String get_note() {
        return _note;
    }

    public void set_note(String _note) {
        this._note = _note;
    }

    public String get_sdt() {
        return _sdt;
    }

    public void set_sdt(String _sdt) {
        this._sdt = _sdt;
    }
}
