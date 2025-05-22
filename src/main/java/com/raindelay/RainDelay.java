package com.raindelay;

import com.raindelay.config.RainDelayConfig;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class RainDelay implements ModInitializer {
	public static final String MOD_ID = "rain-delay";
	public static RainDelayConfig CONFIG = new RainDelayConfig();
	public static IntProvider rainDelayProvider;
	public static IntProvider thunderDelayProvider;
	public static IntProvider rainDurationProvider;
	public static IntProvider thunderDurationProvider;

	@Override
	public void onInitialize() {
		RainDelayConfig.INSTANCE = RainDelayConfig.load();
		initProviders();
		System.out.println("[RainDelay] loaded");
	}

	public static void initProviders() {
		rainDelayProvider = UniformIntProvider.create(CONFIG.minTicksAfterRainEnds, CONFIG.maxTicksAfterRainEnds);
		thunderDelayProvider = UniformIntProvider.create(CONFIG.minTicksAfterThunderEnds, CONFIG.maxTicksAfterThunderEnds);
		rainDurationProvider = UniformIntProvider.create(CONFIG.minRainDuration, CONFIG.maxRainDuration);
		thunderDurationProvider = UniformIntProvider.create(CONFIG.minThunderDuration, CONFIG.maxThunderDuration);
	}
}