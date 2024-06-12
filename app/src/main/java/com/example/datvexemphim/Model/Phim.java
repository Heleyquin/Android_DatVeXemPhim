package com.example.datvexemphim.Model;

import java.io.Serializable;
import java.util.Date;

public class Phim implements Serializable {
    private int id;
    private String anh;
    private String ten;
    private String quocGia;
    private String namPhatHanh;
    private String trangthai;
    private int thoiLuong;
    private String moTa;
    private boolean doTuoi;
    private Admin admin;

    @Override
    public String toString() {
        return "Phim{" +
                "idPhim=" + id +
                ", anh='" + anh + '\'' +
                ", ten='" + ten + '\'' +
                ", quocGia='" + quocGia + '\'' +
                ", namPhatHanh='" + namPhatHanh + '\'' +
                ", trangthai='" + trangthai + '\'' +
                ", thoiLuong=" + thoiLuong +
                ", moTa='" + moTa + '\'' +
                ", doTuoi=" + doTuoi +
                ", admin=" + admin +
                '}';
    }

    public Phim() {
    }

    public Phim(int id, String anh, String ten, String quocGia, String namPhatHanh, String trangthai, int thoiLuong, String moTa, boolean doTuoi, Admin admin) {
        this.id = id;
        this.anh = anh;
        this.ten = ten;
        this.quocGia = quocGia;
        this.namPhatHanh = namPhatHanh;
        this.trangthai = trangthai;
        this.thoiLuong = thoiLuong;
        this.moTa = moTa;
        this.doTuoi = doTuoi;
        this.admin = admin;
    }

    public int getIdPhim() {
        return id;
    }

    public void setIdPhim(int id) {
        this.id = id;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public String getNamPhatHanh() {
        return namPhatHanh;
    }

    public void setNamPhatHanh(String namPhatHanh) {
        this.namPhatHanh = namPhatHanh;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public boolean isDoTuoi() {
        return doTuoi;
    }

    public void setDoTuoi(boolean doTuoi) {
        this.doTuoi = doTuoi;
    }

    public Admin getId_admin() {
        return admin;
    }

    public void setId_admin(Admin admin) {
        this.admin = admin;
    }
}
