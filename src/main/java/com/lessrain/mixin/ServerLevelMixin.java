package com.lessrain.mixin;

import com.lessrain.config.LessRainConfig;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net/minecraft/server/level/ServerLevel")
public abstract class ServerLevelMixin {
	@Redirect(
		method = "advanceWeatherCycle",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/valueproviders/IntProvider;sample(Lnet/minecraft/util/RandomSource;)I",
			ordinal = 0
		)
	)
	private int redirectRainDuuration(IntProvider provider, RandomSource random) {
		IntProvider lessRainProvider = UniformInt.of(LessRainConfig.INSTANCE.minRainDuration, LessRainConfig.INSTANCE.maxRainDuration);
		return lessRainProvider.sample(random);
	}
}