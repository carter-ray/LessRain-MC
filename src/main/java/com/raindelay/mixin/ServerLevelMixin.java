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
	private static final IntProvider rainDelayProvider = UniformIntProvider.create(RainDelayConfig.INSTANCE.minTicksAfterRainEnds, RainDelayConfig.INSTANCE.maxTicksAfterRainEnds);
	private static final IntProvider thunderDelayProvider = UniformIntProvider.create(RainDelayConfig.INSTANCE.minTicksAfterThunderEnds, RainDelayConfig.INSTANCE.maxTicksAfterThunderEnds);

	private static final IntProvider rainDurationProvider = UniformIntProvider.create(RainDelayConfig.INSTANCE.minRainDuration, RainDelayConfig.INSTANCE.maxRainDuration);
	private static final IntProvider thunderDurationProvider = UniformIntProvider.create(RainDelayConfig.INSTANCE.minThunderDuration, RainDelayConfig.INSTANCE.maxThunderDuraton);

	@Redirect(
		method = "tickWeather",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/math/intprovider/IntProvider;get(Lnet/minecraft/util/math/random/Random;)I",
			ordinal = 0
		)
	)
	private int redirectThunderDuration(IntProvider provider, Random random) {
		return thunderDurationProvider.get(random);
	}

	@Redirect(
		method = "tickWeather",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/math/intprovider/IntProvider;get(Lnet/minecraft/util/math/random/Random;)I",
			ordinal = 1
		)
	)
	private int redirectThunderDelay(IntProvider provider, Random random) {
		return thunderDelayProvider.get(random);
	}

	@Redirect(
		method = "tickWeather",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/math/intprovider/IntProvider;get(Lnet/minecraft/util/math/random/Random;)I",
			ordinal = 2
		)
	)
	private int redirectRainDuration(IntProvider provider, Random random) {
		return rainDurationProvider.get(random);
	}

	@Redirect(
		method = "tickWeather",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/math/intprovider/IntProvider;get(Lnet/minecraft/util/math/random/Random;)I",
			ordinal = 3
		)
	)
	private int redirectRainDelay(IntProvider provider, Random random) {
		return rainDelayProvider.get(random);
	}

	
}