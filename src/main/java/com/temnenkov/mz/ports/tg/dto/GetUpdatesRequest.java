package com.temnenkov.mz.ports.tg.dto;

public class GetUpdatesRequest {
    private Long offset;
    private int timeout;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "GetUpdatesRequest{" + "offset=" + offset + ", timeout=" + timeout + '}';
    }
}
