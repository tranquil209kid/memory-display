package io.memory_display_mod.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.IntValue verticalOffset;
    public static final ForgeConfigSpec.IntValue horizontalOffset;

    static {
        BUILDER.push("Memory Display Settings");

        horizontalOffset = BUILDER
                .comment("Distance from right edge of screen in pixels")
                .defineInRange("horizontalOffset", 3, 0, 2000);

        verticalOffset = BUILDER
                .comment("Distance from bottom of screen in pixels")
                .defineInRange("verticalOffset", 150, 0, 2000);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    public static void register() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, SPEC);
    }

    public static int getHorizontalOffset() {
        return horizontalOffset.get();
    }

    public static int getVerticalOffset() {
        return verticalOffset.get();
    }
}