package com.bluapp.androidview2.Firebase;

public class Data {
    public String title, content;

    public Data(){

    }

    public Data(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}