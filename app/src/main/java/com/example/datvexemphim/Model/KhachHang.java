package com.example.datvexemphim.Model;

import java.io.Serializable;

public class KhachHang implements Serializable {
    private int id_kh;
    private String ten;
    private String cccd;
    private String diachi;
    private boolean gioiTinh;
    private Account account;

    public KhachHang() {
    }

    public KhachHang(int id_kh, String ten, String cccd, String diachi, boolean gioiTinh) {
        this.id_kh = id_kh;
        this.ten = ten;
        this.cccd = cccd;
        this.diachi = diachi;
        this.gioiTinh = gioiTinh;
    }

    public KhachHang(int id_kh, String ten, String cccd, String diachi, boolean gioiTinh, Account account) {
        this.id_kh = id_kh;
        this.ten = ten;
        this.cccd = cccd;
        this.diachi = diachi;
        this.gioiTinh = gioiTinh;
        this.account = account;
    }

    public int getId_kh() {
        return id_kh;
    }

    public void setId_kh(int id_kh) {
        this.id_kh = id_kh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Account getId_acc() {
        return account;
    }

    public void setId_acc(Account account) {
        this.account = account;
    }
}
