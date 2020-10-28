package com.example.adminmyt.model;

import java.io.Serializable;


public class Requests implements Serializable {
    private String informasi;
    private String pengirim;
    private String imgUri;
    private String topik;
    private String tanggal;
    private String jurusan;
    private String kelas;

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

    public Requests(String informasi, String pengirim, String imgUri, String topik, String tanggal, String kelas, String jurusan) {
        this.informasi = informasi;
        this.pengirim = pengirim;
        this.imgUri = imgUri;
        this.topik = topik;
        this.tanggal = tanggal;
        this.kelas = kelas;
        this.jurusan = jurusan;
    }

    public void setTopik(String topik) {
        this.topik = topik;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    public String getTopik() { return topik; }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getInformasi() {
        return informasi;
    }

    public void setInformasi(String informasi) {
        this.informasi = informasi;
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }
    public Requests () {}
    @Override
    public String toString(){
        return " " +pengirim+"\n"+" "+topik+"\n"+" "+informasi+"\n";
    }

}
