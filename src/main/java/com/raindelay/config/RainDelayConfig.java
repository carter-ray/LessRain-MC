package com.raindelay.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raindelay.RainDelay;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RainDelayConfig {
    public int minTicksAfterRainEnds = 96000;
    public int maxTicksAfterRainEnds = 264000;

    public int minTicksAfterThunderEnds = 96000;
    public int maxTicksAfterThunderEnds = 264000;

    public int minRainDuration = 12000;
    public int maxRainDuration = 24000;

    public int minThunderDuration = 3600;
    public int maxThunderDuraton = 15600;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File("config/" + RainDelay.MOD_ID + ".json");

    public static RainDelayConfig INSTANCE = new RainDelayConfig();

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                INSTANCE = GSON.fromJson(reader, RainDelayConfig.class);
            } catch (IOException e) {
                System.err.println("Failed to read config: " + e);
            }
        } else {
            save(); // create default config
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(INSTANCE, writer);
        } catch (IOException e) {
            System.err.println("Failed to save config: " + e);
        }
    }
}
