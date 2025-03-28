package com.temnenkov.mz.ports.tg;

import com.temnenkov.mz.Main;
import com.temnenkov.mz.ports.tg.dto.GetUpdatesRequest;
import com.temnenkov.mz.ports.tg.dto.GetUpdatesResponse;
import com.temnenkov.mz.utils.JsonUtils;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class TgInbound {
    private static final Logger logger = LoggerFactory.getLogger(TgInbound.class);
    private static final String CONTENT_TYPE_VALUE = "application/json";
    private static final String CONTENT_TYPE = "Content-Type";
    private final HttpClient httpClient;
    private final String url;

    public TgInbound(String token, @Nullable String proxyHost, int proxyPort) {

        final HttpClient.Builder builder = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(5L));

        if (proxyHost != null) {
            builder.proxy(ProxySelector.of(new InetSocketAddress(proxyHost, proxyPort)));
        }

        httpClient = builder.build();

        url = "https://api.telegram.org/bot" + token + "/getUpdates";
    }

    public void loop() throws IOException, InterruptedException {

        long offset = 0;

        final GetUpdatesRequest request = new GetUpdatesRequest();
        request.setOffset(offset);
        request.setTimeout(30);

        final HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(url)).header(CONTENT_TYPE,
                CONTENT_TYPE_VALUE).timeout(Duration.ofSeconds(35)).POST(HttpRequest.BodyPublishers.ofString(JsonUtils.objectToJson(request))).build();

        logger.info("Sent {}", request);
        final HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        final String body = httpResponse.body();
        logger.info("Received {} {}", httpResponse.statusCode(), body);
        if (httpResponse.statusCode() == 200) {
            final GetUpdatesResponse getUpdatesResponse = JsonUtils.jsonToObject(body, GetUpdatesResponse.class);
            logger.info("Parsed to {}", getUpdatesResponse);
        }
    }

}
