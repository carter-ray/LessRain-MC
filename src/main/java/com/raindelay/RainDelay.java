package com.raindelay;

import com.raindelay.config.RainDelayConfig;

import net.fabricmc.api.ModInitializer;

public class RainDelay implements ModInitializer {
	public static final String MOD_ID = "less-rain";

	@Override
	public void onInitialize() {
		RainDelayConfig.load();
	}
}