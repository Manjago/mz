package com.temnenkov.mz.config;

public class Config {
    private final String token;
    private final String proxyHost;
    private final Integer proxyPort;

    public Config(String token, String proxyHost, Integer proxyPort) {
        this.token = token;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public String getToken() {
        return token;
    }


}
