package com.example.datvexemphim.Model;

import java.io.Serializable;
import java.util.Date;

public class SuatChieu implements Serializable {
    private int idSuatChieu;
    private Date giaBatDau;
    private int thoiLuong;
    private String ngonNgu;
    private Date ngayChieu;
    private String sub;
    private double gia;
    private int id_admin;
    private int id_phim;

    public SuatChieu() {
    }

    public SuatChieu(int idSuatChieu, Date giaBatDau, int thoiLuong, String ngonNgu, Date ngayChieu, String sub, double gia, int id_admin, int id_phim) {
        this.idSuatChieu = idSuatChieu;
        this.giaBatDau = giaBatDau;
        this.thoiLuong = thoiLuong;
        this.ngonNgu = ngonNgu;
        this.ngayChieu = ngayChieu;
        this.sub = sub;
        this.gia = gia;
        this.id_admin = id_admin;
        this.id_phim = id_phim;
    }

    public int getIdSuatChieu() {
        return idSuatChieu;
    }

    public void setIdSuatChieu(int idSuatChieu) {
        this.idSuatChieu = idSuatChieu;
    }

    public Date getGiaBatDau() {
        return giaBatDau;
    }

    public void setGiaBatDau(Date giaBatDau) {
        this.giaBatDau = giaBatDau;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public String getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    public Date getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(Date ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public int getId_phim() {
        return id_phim;
    }

    public void setId_phim(int id_phim) {
        this.id_phim = id_phim;
    }
}
