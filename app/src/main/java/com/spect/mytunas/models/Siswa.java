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
    private String wa_number;
    private String fb_profile;
    private String ig_profile;
    private String twt_profile;

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

    public Siswa(String nis, String nama_lengkap, String password, String email, String imgUri, String jenis_kelamin, String status, String jurusan, String alamat, String wa_number, String fb_profile) {
        this.nis = nis;
        this.nama_lengkap = nama_lengkap;
        this.password = password;
        this.email = email;
        this.imgUri = imgUri;
        this.gender = jenis_kelamin;
        this.status = status;
        this.jurusan = jurusan;
        this.alamat = alamat;
        this.wa_number = wa_number;
        this.fb_profile = fb_profile;
    }

    public Siswa(String nis, String nama_lengkap, String password, String email, String imgUri, String jenis_kelamin, String status, String jurusan, String alamat, String wa_number, String fb_profile, String ig_profile) {
        this.nis = nis;
        this.nama_lengkap = nama_lengkap;
        this.password = password;
        this.email = email;
        this.imgUri = imgUri;
        this.gender = jenis_kelamin;
        this.status = status;
        this.jurusan = jurusan;
        this.alamat = alamat;
        this.wa_number = wa_number;
        this.fb_profile = fb_profile;
        this.ig_profile = ig_profile;
    }

    public Siswa(String nis, String nama_lengkap, String password, String email, String imgUri, String gender, String status, String jurusan, String alamat, String wa_number, String fb_profile, String ig_profile, String twt_profile) {
        this.nis = nis;
        this.nama_lengkap = nama_lengkap;
        this.password = password;
        this.email = email;
        this.imgUri = imgUri;
        this.gender = gender;
        this.status = status;
        this.jurusan = jurusan;
        this.alamat = alamat;
        this.wa_number = wa_number;
        this.fb_profile = fb_profile;
        this.ig_profile = ig_profile;
        this.twt_profile = twt_profile;
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

    public String getWa_number() {
        return wa_number;
    }

    public void setWa_number(String wa_number) {
        this.wa_number = wa_number;
    }

    public String getFb_profile() {
        return fb_profile;
    }

    public void setFb_profile(String fb_profile) {
        this.fb_profile = fb_profile;
    }

    public String getIg_profile() {
        return ig_profile;
    }

    public void setIg_profile(String ig_profile) {
        this.ig_profile = ig_profile;
    }

    public String getTwt_profile() {
        return twt_profile;
    }

    public void setTwt_profile(String twt_profile) {
        this.twt_profile = twt_profile;
    }
}
