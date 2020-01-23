package com.example.myapplication;

class Epson  {
    String name,url;
    int number,code;

    public Epson(String name,  int number ,String url , int code) {
        this.name = name;
        this.number = number;
        this.url = url;
        this.code = code;
    }

    public Epson() {
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getNumber() {
        return number;
    }

    public int getCode() {
        return code;
    }
}
