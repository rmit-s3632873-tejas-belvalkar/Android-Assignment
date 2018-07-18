package com.example.tejas.myapplication;

import java.net.URL;

public class Trackable {
    int id;
    String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCusine() {
        return cusine;
    }

    public String getUrl() {
        return url;
    }

    String cusine;
    String url;

    Trackable(int id, String name, String cusine, String url){
        this.id=id;
        this.name=name;
        this.cusine=cusine;
        this.url=url;
    }
}
