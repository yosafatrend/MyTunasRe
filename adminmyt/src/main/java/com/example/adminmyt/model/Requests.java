package com.example.adminmyt.model;

import java.io.Serializable;


public class Requests implements Serializable {
    private String informasi;
    private String pengirim;
    private String imgUri;

    public Requests(String informasi, String pengirim, String imgUri) {
        this.informasi = informasi;
        this.pengirim = pengirim;
        this.imgUri = imgUri;
    }

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
        return " " +informasi+"\n"+" "+pengirim+"\n";
    }


}
