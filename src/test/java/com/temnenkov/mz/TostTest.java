package com.temnenkov.mz;

import net.openhft.chronicle.core.OS;
import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.queue.ExcerptTailer;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TostTest {
    @Test
    void simple() {
        String basePath = OS.getTarget() + "/getting-started";
        try (ChronicleQueue queue = SingleChronicleQueueBuilder.single(basePath).build()) {
            // Obtain an ExcerptAppender
            ExcerptAppender appender = queue.createAppender();

            // Writes: {msg: TestMessage}
            appender.writeDocument(w -> w.write("msg").text("TestMessage"));

            // Writes: TestMessage
            appender.writeText("TestMessage");

            ExcerptTailer tailer = queue.createTailer();

            tailer.readDocument(w -> System.out.println("msg: " + w.read(()->"msg").text()));

            assertEquals("TestMessage", tailer.readText());
        }
    }
}