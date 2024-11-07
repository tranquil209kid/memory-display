package io.memory_display_mod;

import io.memory_display_mod.util.MemoryMonitor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class MemoryDisplayModFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            MemoryMonitor.update();
        });
    }
}