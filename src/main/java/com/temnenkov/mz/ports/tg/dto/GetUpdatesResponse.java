package com.temnenkov.mz.ports.tg.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUpdatesResponse {

    @SerializedName("ok")
    private boolean ok;

    @SerializedName("result")
    private List<MessageUpdate> result;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<MessageUpdate> getResult() {
        return result;
    }

    public void setResult(List<MessageUpdate> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "GetUpdatesResponse{" + "ok=" + ok + ", result=" + result + '}';
    }
}
