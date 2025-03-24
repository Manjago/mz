package com.temnenkov.mz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainLoop {
    private static final Logger logger = LoggerFactory.getLogger(MainLoop.class);
    public static void loop() {
        final CountDownLatch latch;
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {

            latch = new CountDownLatch(1);

            executor.submit(() -> runTask("Task 1"));
            executor.submit(() -> runTask("Task 2"));

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.info("Shutdown initiated...");
                executor.shutdown();
                try {
                    if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                        logger.info("Executor did not terminate - forced shutdown");
                        executor.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    executor.shutdownNow();
                }
                logger.info("Shutdown complete.");
                latch.countDown();
            }));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        logger.info("Exit");
    }

    private static void runTask(String taskName) {
        while (!Thread.interrupted()) {
            logger.info(taskName + " is running");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}