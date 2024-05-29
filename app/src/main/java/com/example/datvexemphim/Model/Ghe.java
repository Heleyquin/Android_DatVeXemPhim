package com.example.datvexemphim.Model;

import java.io.Serializable;

public class Ghe implements Serializable {
    private int idGhe;
    private String loaiGhe;
    private String hangGhe;
    private String soGhe;
    private int id_phong;

    public Ghe() {
    }

    public Ghe(int idGhe, String loaiGhe, String hangGhe, String soGhe, int id_phong) {
        this.idGhe = idGhe;
        this.loaiGhe = loaiGhe;
        this.hangGhe = hangGhe;
        this.soGhe = soGhe;
        this.id_phong = id_phong;
    }

    public int getIdGhe() {
        return idGhe;
    }

    public void setIdGhe(int idGhe) {
        this.idGhe = idGhe;
    }

    public String getLoaiGhe() {
        return loaiGhe;
    }

    public void setLoaiGhe(String loaiGhe) {
        this.loaiGhe = loaiGhe;
    }

    public String getHangGhe() {
        return hangGhe;
    }

    public void setHangGhe(String hangGhe) {
        this.hangGhe = hangGhe;
    }

    public String getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(String soGhe) {
        this.soGhe = soGhe;
    }

    public int getId_phong() {
        return id_phong;
    }

    public void setId_phong(int id_phong) {
        this.id_phong = id_phong;
    }
}
