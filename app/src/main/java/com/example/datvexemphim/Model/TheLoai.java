package com.example.datvexemphim.Model;

import java.io.Serializable;
import java.util.List;

public class TheLoai implements Serializable {
    private int idTheLoai;
    private String theLoai;
    private List<Phim> phim;

    public TheLoai() {
    }

    public TheLoai(int idTheLoai, String theLoai, List<Phim> phim) {
        this.idTheLoai = idTheLoai;
        this.theLoai = theLoai;
        this.phim = phim;
    }

    public int getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(int idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public List<Phim> getPhim() {
        return phim;
    }

    public void addPhim(Phim id_phim) {
        this.phim.add(id_phim);
    }
}
