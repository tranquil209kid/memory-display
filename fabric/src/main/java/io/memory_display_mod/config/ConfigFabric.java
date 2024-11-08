package io.memory_display_mod.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

@Config(name = "memory_display")
public class ConfigFabric implements ConfigData {
    @ConfigEntry.BoundedDiscrete(min = 0, max = 2000)
    @ConfigEntry.Gui.Tooltip
    public int horizontalOffset = 3;

    @ConfigEntry.BoundedDiscrete(min = 0, max = 2000)
    @ConfigEntry.Gui.Tooltip
    public int verticalOffset = 150;

    private static ConfigFabric INSTANCE;

    public static void register() {
        AutoConfig.register(ConfigFabric.class, GsonConfigSerializer::new);
        INSTANCE = AutoConfig.getConfigHolder(ConfigFabric.class).getConfig();
    }

    public static int getHorizontalOffset() {
        return INSTANCE.horizontalOffset;
    }

    public static int getVerticalOffset() {
        return INSTANCE.verticalOffset;
    }

    @Override
    public void validatePostLoad() {
        horizontalOffset = Math.min(Math.max(horizontalOffset, 0), 2000);
        verticalOffset = Math.min(Math.max(verticalOffset, 0), 2000);
    }
}