package com.lessrain.config;

import com.lessrain.LessRain;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LessRainConfig {
    public int minRainDuration = 96000;
    public int maxRainDuration = 264000;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File("config/" + LessRain.MOD_ID + ".json");

    public static LessRainConfig INSTANCE = new LessRainConfig();

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                INSTANCE = GSON.fromJson(reader, LessRainConfig.class);
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
