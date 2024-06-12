package com.example.datvexemphim.Model;

import java.io.Serializable;

public class SuatChieu implements Serializable {
    private int idSuatChieu;
    private String gioBatDau;
    private String ngonNgu;
    private String ngayChieu;
    private String sub;
    private double gia;
    private int id_admin;
    private Phim phim;
    private Phong phong;

    public SuatChieu() {
    }

    public SuatChieu(int idSuatChieu, String gioBatDau, String ngonNgu, String ngayChieu, String sub, double gia, int id_admin, Phim phim, Phong idPhong) {
        this.idSuatChieu = idSuatChieu;
        this.gioBatDau = gioBatDau;
        this.ngonNgu = ngonNgu;
        this.ngayChieu = ngayChieu;
        this.sub = sub;
        this.gia = gia;
        this.id_admin = id_admin;
        this.phim = phim;
        phong = idPhong;
    }
    public Phong getId_phong() {
        return phong;
    }

    public void setId_phong(Phong phong) {
        this.phong = phong;
    }
    public int getIdSuatChieu() {
        return idSuatChieu;
    }

    public void setIdSuatChieu(int idSuatChieu) {
        this.idSuatChieu = idSuatChieu;
    }

    public String getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(String giaBatDau) {
        this.gioBatDau = giaBatDau;
    }

    public String getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    public String getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(String ngayChieu) {
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

    public Phim getId_phim() {
        return phim;
    }

    public void setId_phim(Phim phim) {
        this.phim = phim;
    }
}
