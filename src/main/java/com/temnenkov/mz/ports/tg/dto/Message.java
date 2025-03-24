package com.temnenkov.mz.ports.tg.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Message {
    @SerializedName("message_id")
    private int messageId;

    @SerializedName("from")
    private User from;

    @SerializedName("chat")
    private Chat chat;

    @SerializedName("date")
    private int date;

    @SerializedName("text")
    private String text;

    @SerializedName("entities")
    private List<Entity> entities;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return "Message{" + "messageId=" + messageId + ", from=" + from + ", chat=" + chat + ", date=" + date + ", " +
                "text='" + text + '\'' + ", entities=" + entities + '}';
    }
}
