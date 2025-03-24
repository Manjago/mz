package com.temnenkov.mz.ports.tg.dto;

import com.google.gson.annotations.SerializedName;

public class MessageUpdate {
    @SerializedName("update_id")
    private long updateId;

    @SerializedName("message")
    private Message message;

    public long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(long updateId) {
        this.updateId = updateId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageUpdate{" + "updateId=" + updateId + ", message=" + message + '}';
    }
}
