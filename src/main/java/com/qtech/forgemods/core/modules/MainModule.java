package com.qtech.forgemods.core.modules;

import com.qtech.forgemods.core.client.gui.modules.ModuleCompatibility;
import com.qtech.forgemods.core.common.CoreModule;
import com.qtech.forgemods.core.common.ModuleSecurity;
import org.jetbrains.annotations.NotNull;

public final class MainModule extends CoreModule {
    @Override
    public ModuleSecurity getSecurity() {
        return ModuleSecurity.SAFE;
    }

    @Override
    public void onEnable() {
        // Todo: add enabling for Main Module.
    }

    @Override
    public @NotNull String getName() {
        return "main";
    }

    @Override
    public @NotNull ModuleCompatibility getCompatibility() {
        return ModuleCompatibility.FULL;
    }
}
