package com.raindelay.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raindelay.RainDelay;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RainDelayConfig {
    public int minTicksAfterRainEnds = 96000;
    public int maxTicksAfterRainEnds = 264000;

    public int minTicksAfterThunderEnds = 96000;
    public int maxTicksAfterThunderEnds = 264000;

    public int minRainDuration = 12000;
    public int maxRainDuration = 24000;

    public int minThunderDuration = 3600;
    public int maxThunderDuration = 15600;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("raindelay.json");

    public static RainDelayConfig INSTANCE = load();

    public static RainDelayConfig load() {
        if (Files.exists(CONFIG_FILE)) {
            try {
                String json = Files.readString(CONFIG_FILE);
                return GSON.fromJson(json, RainDelayConfig.class);
            } catch (IOException e) {
                System.err.println("Failed to read config: " + e);
            }
        }
        return new RainDelayConfig();
    }

    public void save() {
        try {
            Files.createDirectories(CONFIG_FILE.getParent());
            Files.writeString(CONFIG_FILE, GSON.toJson(this));
        } catch (IOException e) {
            System.err.println("Failed to save config: " + e);
        }
        RainDelay.initProviders();
    }

}
