package com.temnenkov.mz.config;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    private ConfigLoader() {
    }

    @Contract("_ -> new")
    public static @NotNull Config loadConfig(@NotNull String fileName) throws IOException {
        final Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(fileName)) {
            properties.load(input);
            return new Config(
                    properties.getProperty("token"),
                    properties.getProperty("proxy.host"),
                    Integer.parseInt(properties.getProperty("proxy.port", "0"))
            );
        }
    }
}
