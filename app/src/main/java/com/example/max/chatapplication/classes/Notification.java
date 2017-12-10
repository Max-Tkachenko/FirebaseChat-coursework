package com.example.max.chatapplication.classes;

import java.util.Date;

public class Notification extends Note {

    public static String text = Note.text;
    public static String author = Note.author;
    public static String status;
    public static long time;

    public Notification(String text, String author, String status) {
        this.text = text;
        this.author = author;
        this.status = status;

        this.time = new Date().getTime();
    }

    public Notification(){

    }

    public String getText() {
        return text;
    }
    public String getAuthor() {
        return author;
    }
    public String getStatus() {
        return status;
    }
    public long getTime() {
        return time;
    }
}
