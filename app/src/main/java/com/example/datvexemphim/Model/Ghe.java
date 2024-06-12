package com.example.datvexemphim.Model;

import java.io.Serializable;
import java.util.Objects;

public class Ghe implements Serializable {
    private int idGhe;
    private String loaiGhe;
    private String hangGhe;
    private String soGhe;
    private Phong phong;
//    private int trang_thai; //0: empty, 1: Đang chọn, 2: Đã bán

    public Ghe() {
    }

    public Ghe(int idGhe, String loaiGhe, String hangGhe, String soGhe, Phong phong) {
        this.idGhe = idGhe;
        this.loaiGhe = loaiGhe;
        this.hangGhe = hangGhe;
        this.soGhe = soGhe;
        this.phong = phong;
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

    public Phong getId_phong() {
        return phong;
    }

    public void setId_phong(Phong phong) {
        this.phong = phong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ghe)) return false;
        Ghe ghe = (Ghe) o;
        return getIdGhe() == ghe.getIdGhe();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdGhe());
    }
}
