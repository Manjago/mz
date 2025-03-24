package com.temnenkov.mz.ports.tg;

import com.temnenkov.mz.Main;
import com.temnenkov.mz.ports.tg.dto.GetUpdatesRequest;
import com.temnenkov.mz.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class TgInbound {
    private static final Logger logger = LoggerFactory.getLogger(TgInbound.class);
    private static final String CONTENT_TYPE_VALUE = "application/json";
    private static final String CONTENT_TYPE = "Content-Type";
    private final HttpClient httpClient =
            HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(5L)).build();
    private final String url;

    public TgInbound(String token) {
        url = "https://api.telegram.org/bot" + token + "/sendMessage";
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
    }

}
