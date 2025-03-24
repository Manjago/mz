package com.temnenkov.mz.config;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    @Contract("_ -> new")
    public static @NotNull Config loadConfig(@NotNull String fileName) throws IOException {
        final Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(fileName)) {
            properties.load(input);
            return new Config(properties.getProperty("token"));
        }
    }
}
