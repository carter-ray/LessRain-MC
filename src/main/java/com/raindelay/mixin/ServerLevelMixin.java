package com.raindelay.mixin;

// import net.minecraft.class_3218; // serverlevel
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.intprovider.IntProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.raindelay.RainDelay;

@Mixin(ServerWorld.class)
public abstract class ServerLevelMixin {

	@Redirect(
		method = "tickWeather",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/math/intprovider/IntProvider;get(Lnet/minecraft/util/math/random/Random;)I",
			ordinal = 0
		)
	)
	private int redirectThunderDuration(IntProvider provider, Random random) {
		return RainDelay.thunderDurationProvider.get(random);
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
		return RainDelay.thunderDelayProvider.get(random);
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
		return RainDelay.rainDurationProvider.get(random);
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
		return RainDelay.rainDelayProvider.get(random);
	}

}