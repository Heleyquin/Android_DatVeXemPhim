package com.example.datvexemphim.Model;

import java.io.Serializable;
import java.util.List;

public class TheLoai implements Serializable {
    private int idTheLoai;
    private String theLoai;
    private List<Integer> phim;

    public TheLoai() {
    }

    public TheLoai(int idTheLoai, String theLoai, List<Integer> phim) {
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

    public List<Integer> getPhim() {
        return phim;
    }

    public void addPhim(int id_phim) {
        this.phim.add(id_phim);
    }
}
