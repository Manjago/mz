package com.temnenkov.mz.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.Nullable;

public final class JsonUtils {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private JsonUtils() {
    }

    public static String objectToJson(@Nullable Object object) {
        return gson.toJson(object);
    }
}
