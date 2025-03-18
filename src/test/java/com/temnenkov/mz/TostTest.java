package com.temnenkov.mz;

import net.openhft.chronicle.core.OS;
import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.queue.ExcerptTailer;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

            tailer.readDocument(w -> System.out.println("msg: " + w.read(() -> "msg").text()));

            assertEquals("TestMessage", tailer.readText());
        }
    }

    @Test
    void strange() {
        // Запись в очередь
        String pathName = OS.getTarget() + "/queue-directory";
        try (ChronicleQueue queue = ChronicleQueue.single(pathName)) {
            ExcerptAppender appender = queue.createAppender();
            MyEventWriter writer = appender.methodWriter(MyEventWriter.class);

            // Использование интерфейса для записи сообщений в очередь
            writer.writeEvent("Hello, Chronicle Queue!", 42);
            writer.writeEvent("Another message", 100);
        }

        // Чтение из очереди
        try (ChronicleQueue queue = ChronicleQueue.single(pathName)) {
            ExcerptTailer tailer = queue.createTailer();

            while (true) {
                boolean found = tailer.readDocument(wire -> {
                    wire.read("writeEvent").marshallable(m -> {
                        String message = m.read("arg0").text();
                        int value = m.read("arg1").int32();

                        // Обработка сообщения
                        System.out.println("Read message: " + message + ", value: " + value);
                    });
                });

                if (!found) {
                    break; // Выход из цикла, если больше нет сообщений
                }
            }
        }
    }
}
