package com.qtech.forgemods.core.modules.ui;

import com.qtech.forgemods.core.client.gui.modules.ModuleCompatibility;
import com.qtech.forgemods.core.common.CoreModule;
import com.qtech.forgemods.core.common.ModuleSecurity;
import com.qtech.forgemods.core.init.Registration;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class UserInterfaceModule extends CoreModule {
    @Override
    public ModuleSecurity getSecurity() {
        return ModuleSecurity.SAFE;
    }

    @Override
    public void onEnable() {
        // Todo: add enabling for Main Module.
        ModContainers.CONTAINER_TYPES.register(modEventBus);
    }

    @Override
    public @NotNull String getName() {
        return "user_interface";
    }

    public DeferredRegister<ContainerType<?>> getDeferredRegister() {
        return Registration.CONTAINERS;
    }

    public Collection<RegistryObject<ContainerType<?>>> getContainers() {
        return Registration.CONTAINERS.getEntries();
    }

    @Override
    public @NotNull ModuleCompatibility getCompatibility() {
        return ModuleCompatibility.FULL;
    }
}
