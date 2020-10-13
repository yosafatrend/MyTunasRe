package com.spect.mytunas.models;

public class Siswa {
    private String nis;
    private String nama_lengkap;
    private String password;
    private String email;
    private String imgUri;

    public Siswa() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public Siswa(String nis, String nama_lengkap, String password, String email) {
        this.nis = nis;
        this.nama_lengkap = nama_lengkap;
        this.password = password;
        this.email = email;
    }

    public Siswa(String nis, String nama_lengkap, String password, String email, String imgUri) {
        this.nis = nis;
        this.nama_lengkap = nama_lengkap;
        this.password = password;
        this.email = email;
        this.imgUri = imgUri;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }
}
