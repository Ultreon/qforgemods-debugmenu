package com.qtech.forgemods.core.modules.debug;

public class OreProfiler {
    private static boolean active;

    public static void start() {
        active = true;
    }

    public static void stop() {
        active = false;
    }

    public static boolean isActive() {
        return active;
    }
}
