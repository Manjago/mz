package com.temnenkov.mz;

import net.openhft.chronicle.core.OS;
import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;

public class Tost {
    public static void test() {
        String basePath = OS.getTarget() + "/getting-started";
        ChronicleQueue queue = SingleChronicleQueueBuilder.single(basePath).build();
    }
}
