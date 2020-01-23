package com.example.myapplication;

public class Sup_mosalsal  {
private  String name,url_img;
private  int code;

    public Sup_mosalsal(String name, int code,String url_img) {
        this.name = name;
        this.code = code;
        this.url_img=url_img;
    }

    public String getUrl_img() {
        return url_img;
    }

    public Sup_mosalsal(String name) {
        this.name = name;
    }

    public Sup_mosalsal() {
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
