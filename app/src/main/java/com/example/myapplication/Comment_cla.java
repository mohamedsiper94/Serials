package com.example.myapplication;

public class Comment_cla {
     String comment,username;
   int code;
             String time;
    public Comment_cla(String username,String comment, int code,String time) {
        this.time=time;
        this.username=username;
         this.comment = comment;
        this.code = code;
    }

    public Comment_cla() {
    }

    public String getComment() {
        return comment;
    }

    public int getCode() {
        return code;
    }

    public String getUsername() {
        return username;
    }

    public String getTime() {
        return time;
    }
}
