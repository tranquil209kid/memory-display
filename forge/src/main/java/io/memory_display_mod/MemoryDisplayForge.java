package io.memory_display_mod;

import io.memory_display_mod.config.ConfigForge;
import io.memory_display_mod.util.MemoryMonitor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

public class MemoryDisplayForge {
    private static Minecraft minecraft;

    public static void render(GuiGraphics graphics) {
        if (minecraft == null) {
            minecraft = Minecraft.getInstance();
        }

        int displayWidth = minecraft.getWindow().getGuiScaledWidth();
        int displayHeight = minecraft.getWindow().getGuiScaledHeight();

        float luminance = 1.0F - (float)((double)(System.currentTimeMillis() - MemoryMonitor.getStartTimeMs()) / 1000.0);
        luminance = limit(luminance, 0.0F, 1.0F);

        int memColR = (int)Mth.lerp(luminance, 180.0F, 255.0F);
        int memColG = (int)Mth.lerp(luminance, 110.0F, 155.0F);
        int memColB = (int)Mth.lerp(luminance, 15.0F, 20.0F);
        int memoryColor = memColR << 16 | memColG << 8 | memColB;

        String memoryText = " " + MemoryMonitor.getGcRateMb() + " MB/s";
        int memTextWidth = minecraft.font.width(memoryText);

        int memPosX = displayWidth - memTextWidth - ConfigForge.getHorizontalOffset();
        int memPosY = displayHeight - ConfigForge.getVerticalOffset();

        graphics.fill(memPosX - 1, memPosY - 1,
                memPosX + memTextWidth + 1, memPosY + 10,
                -1605349296);
        graphics.drawString(minecraft.font, memoryText, memPosX, memPosY, memoryColor);
    }

    public static float limit(float val, float min, float max) {
        if (val < min) {
            return min;
        } else {
            return val > max ? max : val;
        }
    }
}