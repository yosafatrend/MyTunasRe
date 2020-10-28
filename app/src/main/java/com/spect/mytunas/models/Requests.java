package com.spect.mytunas.models;

import java.io.Serializable;


public class Requests implements Serializable {
    private String informasi;
    private String pengirim;
    private String imgUri;
    private String topik;
    private String tanggal;

    public Requests(String informasi, String pengirim, String imgUri,String topik,String tanggal) {
        this.informasi = informasi;
        this.pengirim = pengirim;
        this.imgUri = imgUri;
        this.topik = topik;
        this.tanggal = tanggal;
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
    public Requests () {
    }
}