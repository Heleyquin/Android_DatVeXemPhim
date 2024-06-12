package com.example.datvexemphim.Model;

import java.io.Serializable;

public class HoaDon implements Serializable {
    private int idHoaDon;
    private KhachHang khachHang;
    
    public HoaDon() {
    }

    public HoaDon(int idHoaDon, KhachHang khachHang) {
        this.idHoaDon = idHoaDon;
        this.khachHang = khachHang;
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
