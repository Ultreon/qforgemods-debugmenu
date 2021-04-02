package com.qtech.forgemods.core.modules.debugMenu.pages;

import com.qtech.forgemods.core.modules.debugMenu.DebugPage;
import net.minecraft.client.Minecraft;

public class PlayerPage extends DebugPage {
    public PlayerPage(String modId, String name) {
        super(modId, name);
    }

    @Override
    public boolean hasRequiredComponents() {
        return Minecraft.getInstance().player != null && Minecraft.getInstance().playerController != null;
    }
}
