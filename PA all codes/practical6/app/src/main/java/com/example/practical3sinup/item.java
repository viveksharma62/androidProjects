package com.example.practical3sinup;

public class item {
    String name;
    String emaill ;
    int images;

    public item(String name, String emaill, int images) {
        this.name = name;
        this.emaill = emaill;
        this.images = images;
    }

    public String getName() {
        return name;
    }


    public String getEmaill() {
        return emaill;
    }


    public int getImages() {
        return images;
    }

}
