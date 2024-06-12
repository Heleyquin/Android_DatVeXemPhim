package com.example.datvexemphim.Model;

import java.io.Serializable;

public class Ve implements Serializable {
    private int idVe;
    private Ghe ghe;
    private SuatChieu suatChieu;
    private HoaDon hoaDon;

    public Ve() {
    }

    public Ve(int idVe, Ghe id_ghe, SuatChieu idSuatChieu, HoaDon idHoaDon) {
        this.idVe = idVe;
        this.ghe = id_ghe;
        this.suatChieu = idSuatChieu;
        this.hoaDon = idHoaDon;
    }

    public int getIdVe() {
        return idVe;
    }

    public void setIdVe(int idVe) {
        this.idVe = idVe;
    }

    public Ghe getId_ghe() {
        return ghe;
    }

    public void setId_ghe(Ghe id_ghe) {
        this.ghe = id_ghe;
    }

    public SuatChieu getIdSuatChieu() {
        return suatChieu;
    }

    public void setIdSuatChieu(SuatChieu idSuatChieu) {
        this.suatChieu = idSuatChieu;
    }

    public HoaDon getIdHoaDon() {
        return hoaDon;
    }

    public void setIdHoaDon(HoaDon idHoaDon) {
        this.hoaDon = idHoaDon;
    }
}
