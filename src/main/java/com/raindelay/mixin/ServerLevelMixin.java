package com.raindelay.mixin;

// import net.minecraft.class_3218; // serverlevel
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.raindelay.config.RainDelayConfig;

@Mixin(ServerWorld.class)
public abstract class ServerLevelMixin {
	private static final IntProvider rainDelayProvider = UniformIntProvider.create(RainDelayConfig.INSTANCE.minRainDuration, RainDelayConfig.INSTANCE.maxRainDuration);
	@Redirect(
		method = "tickWeather",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/math/intprovider/IntProvider;get(Lnet/minecraft/util/math/random/Random;)I",
			ordinal = 0
		)
	)
	private int redirectRainDuuration(IntProvider provider, Random random) {
		return rainDelayProvider.get(random);
	}
}