package com.spect.mytunas.models;

public class Siswa {
    private String nis;
    private String nama_lengkap;
    private String password;
    private String email;
    private String imgUri;
    private String gender;
    private String status;
    private String jurusan;
    private String kelas;
    private String alamat;
    private String wa;
    private String urlFb;
    private String urlIg;
    private String urlTwt;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getWa() {
        return wa;
    }

    public void setWa_number(String wa_number) {
        this.wa = wa_number;
    }

    public String getUrlFb() {
        return urlFb;
    }

    public void setFb_profile(String fb_profile) {
        this.urlFb = fb_profile;
    }

    public String getUrlIg() {
        return urlIg;
    }

    public void setUrlIg(String ig_profile) {
        this.urlIg = ig_profile;
    }

    public String getUrlTwt() {
        return urlTwt;
    }

    public void setTwt_profile(String twt_profile) {
        this.urlTwt = twt_profile;
    }
}
