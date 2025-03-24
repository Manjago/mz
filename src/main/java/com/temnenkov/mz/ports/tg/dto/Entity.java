package com.temnenkov.mz.ports.tg.dto;

import com.google.gson.annotations.SerializedName;

public class Entity {
    @SerializedName("offset")
    private int offset;

    @SerializedName("length")
    private int length;
    @SerializedName("type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "Entity{" + "offset=" + offset + ", length=" + length + ", type='" + type + '\'' + '}';
    }
}
