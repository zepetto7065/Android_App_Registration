package com.example.registeration;

public class Notice {

    String content;
    String name;
    String date;

    public Notice(String content, String name, String date) {
        this.content = content;
        this.name = name;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
