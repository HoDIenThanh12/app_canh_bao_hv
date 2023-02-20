package com.example.tiki.app_canhbao.entity;

public class VanBanCVHT {
    private int _id;
    private String _mucVanBan;
    private String _ndungMucVanBan;

    public VanBanCVHT() {
    }

    public VanBanCVHT(int _id, String _mucVanBan, String _ndungMucVanBan) {
        this._id = _id;
        this._mucVanBan = _mucVanBan;
        this._ndungMucVanBan = _ndungMucVanBan;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_mucVanBan() {
        return _mucVanBan;
    }

    public void set_mucVanBan(String _mucVanBan) {
        this._mucVanBan = _mucVanBan;
    }

    public String get_ndungMucVanBan() {
        return _ndungMucVanBan;
    }

    public void set_ndungMucVanBan(String _ndungMucVanBan) {
        this._ndungMucVanBan = _ndungMucVanBan;
    }
}
