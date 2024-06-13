package com.example.datvexemphim.Model;

import java.io.Serializable;

public class HoaDon implements Serializable {
    private int idHoaDon;
    private KhachHang khachHang;
    private String tenPhim;
    private String tenRap;
    
    public HoaDon() {
    }

    public HoaDon(int idHoaDon, KhachHang khachHang) {
        this.idHoaDon = idHoaDon;
        this.khachHang = khachHang;
    }

    public HoaDon(int idHoaDon, KhachHang khachHang, String tenPhim, String tenRap) {
        this.idHoaDon = idHoaDon;
        this.khachHang = khachHang;
        this.tenPhim = tenPhim;
        this.tenRap = tenRap;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getTenRap() {
        return tenRap;
    }

    public void setTenRap(String tenRap) {
        this.tenRap = tenRap;
    }

    public int getIdhoadon() {
        return idHoaDon;
    }

    public void setIdhoadon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public KhachHang getId_kh() {
        return khachHang;
    }

    public void setId_kh(KhachHang khachHang) {
        this.khachHang = khachHang;
    }
}
