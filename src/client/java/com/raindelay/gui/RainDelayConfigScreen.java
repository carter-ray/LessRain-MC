package com.raindelay.gui;

import com.raindelay.config.RainDelayConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class RainDelayConfigScreen {
        public static ConfigScreenFactory<Screen> getFactory() {
                return parent -> {
                        RainDelayConfig config = RainDelayConfig.INSTANCE;
                        ConfigBuilder builder = ConfigBuilder.create()
                                .setParentScreen(parent)
                                .setTitle(Text.literal("Rain Delay Config"));

                        ConfigCategory rain_ticks = builder.getOrCreateCategory(Text.of("Rain"));
                        ConfigCategory thunder_ticks = builder.getOrCreateCategory(Text.of("Thunder"));
                        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

                        rain_ticks.addEntry(entryBuilder.startIntField(Text.literal("Min Ticks until next Rain"), config.minTicksAfterRainEnds)
                                .setDefaultValue(96000)
                                .setSaveConsumer(value -> config.minTicksAfterRainEnds = value)
                                .build());

                        rain_ticks.addEntry(entryBuilder.startIntField(Text.literal("Max Ticks until next Rain"), config.maxTicksAfterRainEnds)
                                .setDefaultValue(264000)
                                .setSaveConsumer(value -> config.maxTicksAfterRainEnds = value)
                                .build());

                        thunder_ticks.addEntry(entryBuilder.startIntField(Text.literal("Min Ticks until next Thunder"), config.minTicksAfterThunderEnds)
                                .setDefaultValue(96000)
                                .setSaveConsumer(value -> config.minTicksAfterThunderEnds = value)
                                .build());

                        thunder_ticks.addEntry(entryBuilder.startIntField(Text.literal("Max Ticks until next Thunder"), config.maxTicksAfterThunderEnds)
                                .setDefaultValue(264000)
                                .setSaveConsumer(value -> config.maxTicksAfterThunderEnds = value)
                                .build());

                        rain_ticks.addEntry(entryBuilder.startIntField(Text.literal("Min Ticks of Rain"), config.minRainDuration)
                                .setDefaultValue(12000)
                                .setSaveConsumer(value -> config.minRainDuration = value)
                                .build());

                        rain_ticks.addEntry(entryBuilder.startIntField(Text.literal("Max Ticks of Rain"), config.maxRainDuration)
                                .setDefaultValue(24000)
                                .setSaveConsumer(value -> config.maxRainDuration = value)
                                .build());

                        thunder_ticks.addEntry(entryBuilder.startIntField(Text.literal("Min Ticks of Thunder"), config.minThunderDuration)
                                .setDefaultValue(3600)
                                .setSaveConsumer(value -> config.minThunderDuration = value)
                                .build());

                        thunder_ticks.addEntry(entryBuilder.startIntField(Text.literal("Max Ticks of Thunder"), config.maxThunderDuration)
                                .setDefaultValue(15600)
                                .setSaveConsumer(value -> config.maxThunderDuration = value)
                                .build());

                        builder.setSavingRunnable(config::save);
                        return builder.build();
                };
        }
}
