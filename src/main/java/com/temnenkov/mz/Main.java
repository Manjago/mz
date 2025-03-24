package com.temnenkov.mz;

import com.temnenkov.mz.config.Config;
import com.temnenkov.mz.config.ConfigLoader;
import com.temnenkov.mz.ports.tg.TgInbound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            final Config config = ConfigLoader.loadConfig(args[0]);
            new TgInbound(config.getToken()).loop();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Interrupted", e);
        } catch (Exception e) {
            logger.error("Exception happens", e);
        }
    }

}