package com.raindelay.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raindelay.RainDelay;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RainDelayConfig {
    public int minRainDuration = 96000;
    public int maxRainDuration = 264000;

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
