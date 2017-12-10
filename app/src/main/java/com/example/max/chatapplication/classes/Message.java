package com.example.max.chatapplication.classes;

import java.util.Date;

public class Message extends Note{

    public static String textMessage = Note.text;
    public static String author = Note.author;
    public static long timeMessage;

    public Message(String text, String author) {
        this.textMessage = text;
        this.author = author;
        this.timeMessage = new Date().getTime();
    }

    public Message(){

    }

    public String getTextMessage() {
        return textMessage;
    }

    private void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getAuthor() {
        return author;
    }

    private void setAuthor(String author) {
        this.author = author;
    }

    public long getTimeMessage() {
        return timeMessage;
    }

    private void setTimeMessage(long timeMessage) {
        this.timeMessage = timeMessage;
    }
}
