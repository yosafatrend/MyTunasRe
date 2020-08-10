package com.spect.mytunas;

class Siswa {
    private String nis;
    private String nama_lengkap;
    private String password;
    private String email;

    public Siswa() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Siswa(String nis, String nama_lengkap, String password, String email) {
        this.nis = nis;
        this.nama_lengkap = nama_lengkap;
        this.password = password;
        this.email = email;
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
