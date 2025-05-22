package com.raindelay;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.raindelay.config.RainDelayConfig;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class RainDelay implements ModInitializer {
	public static final String MOD_ID = "rain-delay";
	public static RainDelayConfig CONFIG = RainDelayConfig.INSTANCE;
	public static IntProvider rainDelayProvider;
	public static IntProvider thunderDelayProvider;
	public static IntProvider rainDurationProvider;
	public static IntProvider thunderDurationProvider;

	@Override
	public void onInitialize() {
		initProviders();

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(CommandManager.literal("raindelay")
				.requires(source -> source.hasPermissionLevel(1))
				.then(CommandManager.literal("min_rain_delay")
					.then(CommandManager.argument("ticks", IntegerArgumentType.integer())
						.executes(context -> {
							int ticks = IntegerArgumentType.getInteger(context, "ticks");
							CONFIG.minTicksAfterRainEnds = ticks;
							CONFIG.save();
							context.getSource().sendFeedback(() -> Text.literal("Set min ticks after rain to " + ticks + " ticks."), false);
                    		return 1;
						})
					)
				)
				.then(CommandManager.literal("max_rain_delay")
					.then(CommandManager.argument("ticks", IntegerArgumentType.integer())
						.executes(context -> {
							int ticks = IntegerArgumentType.getInteger(context, "ticks");
							CONFIG.maxTicksAfterRainEnds = ticks;
							CONFIG.save();
							context.getSource().sendFeedback(() -> Text.literal("Set max ticks after rain to " + ticks + " ticks."), false);
                    		return 1;
						})
					)
				)
				.then(CommandManager.literal("min_thunder_delay")
					.then(CommandManager.argument("ticks", IntegerArgumentType.integer())
						.executes(context -> {
							int ticks = IntegerArgumentType.getInteger(context, "ticks");
							CONFIG.minTicksAfterThunderEnds = ticks;
							CONFIG.save();
							context.getSource().sendFeedback(() -> Text.literal("Set min ticks after thunder to " + ticks + " ticks."), false);
                    		return 1;
						})
					)
				)
				.then(CommandManager.literal("max_thunder_delay")
					.then(CommandManager.argument("ticks", IntegerArgumentType.integer())
						.executes(context -> {
							int ticks = IntegerArgumentType.getInteger(context, "ticks");
							CONFIG.maxTicksAfterThunderEnds = ticks;
							CONFIG.save();
							context.getSource().sendFeedback(() -> Text.literal("Set max ticks after thunder to " + ticks + " ticks."), false);
                    		return 1;
						})
					)
				)
				.then(CommandManager.literal("min_rain_duration")
					.then(CommandManager.argument("ticks", IntegerArgumentType.integer())
						.executes(context -> {
							int ticks = IntegerArgumentType.getInteger(context, "ticks");
							CONFIG.minRainDuration = ticks;
							CONFIG.save();
							context.getSource().sendFeedback(() -> Text.literal("Set min ticks of rain " + ticks + " ticks."), false);
                    		return 1;
						})
					)
				)
				.then(CommandManager.literal("max_rain_duration")
					.then(CommandManager.argument("ticks", IntegerArgumentType.integer())
						.executes(context -> {
							int ticks = IntegerArgumentType.getInteger(context, "ticks");
							CONFIG.maxRainDuration = ticks;
							CONFIG.save();
							context.getSource().sendFeedback(() -> Text.literal("Set max ticks of rain " + ticks + " ticks."), false);
                    		return 1;
						})
					)
				)
				.then(CommandManager.literal("min_thunder_duration")
					.then(CommandManager.argument("ticks", IntegerArgumentType.integer())
						.executes(context -> {
							int ticks = IntegerArgumentType.getInteger(context, "ticks");
							CONFIG.minThunderDuration = ticks;
							CONFIG.save();
							context.getSource().sendFeedback(() -> Text.literal("Set min ticks of thunder " + ticks + " ticks."), false);
                    		return 1;
						})
					)
				)
				.then(CommandManager.literal("max_thunder_duration")
					.then(CommandManager.argument("ticks", IntegerArgumentType.integer())
						.executes(context -> {
							int ticks = IntegerArgumentType.getInteger(context, "ticks");
							CONFIG.maxThunderDuration = ticks;
							CONFIG.save();
							context.getSource().sendFeedback(() -> Text.literal("Set max ticks of thunder " + ticks + " ticks."), false);
                    		return 1;
						})
					)
				)
			);
		});

		System.out.println("[RainDelay] loaded");
	}

	public static void initProviders() {
		rainDelayProvider = UniformIntProvider.create(CONFIG.minTicksAfterRainEnds, CONFIG.maxTicksAfterRainEnds);
		thunderDelayProvider = UniformIntProvider.create(CONFIG.minTicksAfterThunderEnds, CONFIG.maxTicksAfterThunderEnds);
		rainDurationProvider = UniformIntProvider.create(CONFIG.minRainDuration, CONFIG.maxRainDuration);
		thunderDurationProvider = UniformIntProvider.create(CONFIG.minThunderDuration, CONFIG.maxThunderDuration);
	}
}