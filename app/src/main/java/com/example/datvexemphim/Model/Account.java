package com.example.datvexemphim.Model;

import java.io.Serializable;

public class Account implements Serializable {
    private int id_acc;
    private String mk;
    private String tk;
    private String email;
    private String role;

    public Account() {
    }

    public Account(int id_acc, String mk, String tk, String email, String role) {
        this.id_acc = id_acc;
        this.mk = mk;
        this.tk = tk;
        this.email = email;
        this.role = role;
    }

    public int getId_acc() {
        return id_acc;
    }

    public void setId_acc(int id_acc) {
        this.id_acc = id_acc;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}