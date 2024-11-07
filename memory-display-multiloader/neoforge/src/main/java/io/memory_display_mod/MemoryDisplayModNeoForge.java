package io.memory_display_mod;

import io.memory_display_mod.util.MemoryMonitor;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(MemoryDisplayModNeoForge.MOD_ID)
public class MemoryDisplayModNeoForge {
    public static final String MOD_ID = "memory_display_mod";

    public MemoryDisplayModNeoForge(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(new MemoryEventHandler());
    }

    @OnlyIn(Dist.CLIENT)
    public static class MemoryEventHandler {
        @SubscribeEvent
        public void onClientTick(ClientTickEvent.Post event) {
            MemoryMonitor.update();
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