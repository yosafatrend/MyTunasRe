package com.spect.mytunas.models;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String nama;
    private int nis;
    private String imgUri;

    public User(String email, String nama, int nis, String imgUri) {
        this.email = email;
        this.nama = nama;
        this.nis = nis;
        this.imgUri = imgUri;
    }

    public User() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNis() {
        return nis;
    }

    public void setNis(int nis) {
        this.nis = nis;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


//    @Override
//    public String toString() {
//        return " " + email + "\n" + " " + nama + "\n" + " " + nis + "\n";
//    }
}
