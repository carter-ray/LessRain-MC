package com.lessrain;

import com.lessrain.config.LessRainConfig;
import net.fabricmc.api.ModInitializer;

public class LessRain implements ModInitializer {
	public static final String MOD_ID = "less-rain";

	@Override
	public void onInitialize() {
		LessRainConfig.load();
	}
}