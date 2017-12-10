package com.example.max.chatapplication.classes;

public class ChatMessage extends Message {

    private static String textChatMessage = Message.textMessage;
    private static String authorChatMessage = Message.author;
    private static long timeChatMessage = Message.timeMessage;
    public boolean left;

    public ChatMessage(String text, String author) {
        super(text, author);
        this.left = true;
    }

    public ChatMessage(){

    }

    @Override
    public String getTextMessage() {
        return super.getTextMessage();
    }

    @Override
    public String getAuthor() {
        return super.getAuthor();
    }

    @Override
    public long getTimeMessage() {
        return super.getTimeMessage();
    }
}
