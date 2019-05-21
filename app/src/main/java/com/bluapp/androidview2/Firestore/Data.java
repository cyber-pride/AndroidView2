package com.bluapp.androidview2.Firestore;

public class Data {
    public String title, body;

    public Data(){

    }

    public Data(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

}