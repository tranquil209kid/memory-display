package io.memory_display_mod;

import io.memory_display_mod.config.Config;
import io.memory_display_mod.util.MemoryMonitor;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(MemoryDisplayMod.MOD_ID)
public class MemoryDisplayMod {
    public static final String MOD_ID = "memory_display_mod";

    public MemoryDisplayMod() {
        Config.register();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new MemoryEventHandler());
    }

    @OnlyIn(Dist.CLIENT)
    public static class MemoryEventHandler {
        @SubscribeEvent
        public void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                MemoryMonitor.update();
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRenderDebug(CustomizeGuiOverlayEvent.DebugText event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.getDebugOverlay().showDebugScreen()) {
            MemoryDisplay.render(event.getGuiGraphics());
        }
    }
}