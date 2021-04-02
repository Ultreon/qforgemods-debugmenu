package com.qtech.forgemods.core.modules.items.tools;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.modules.items.tools.trait.*;
import lombok.experimental.UtilityClass;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

@UtilityClass
public final class ModTraits {


    public static final DeferredRegister<AbstractTrait> REGISTRY = DeferredRegister.create(AbstractTrait.class, QFMCore.modId);

    static {
        REGISTRY.makeRegistry("trait", () -> new RegistryBuilder<AbstractTrait>().disableOverrides());
    }


    public static final RegistryObject<RadioactiveTrait> RADIOACTIVE = register("radioactive", RadioactiveTrait::new);
    public static final RegistryObject<WitherTrait> WITHER = register("wither", WitherTrait::new);
    public static final RegistryObject<PoisonTrait> POISON = register("poison", PoisonTrait::new);
    public static final RegistryObject<EnderTrait> ENDER = register("ender", EnderTrait::new);
    public static final RegistryObject<BlazeTrait> BLAZE = register("blaze", BlazeTrait::new);
    public static final RegistryObject<HolyTrait> HOLY = register("holy", HolyTrait::new);
    public static <T extends AbstractTrait> RegistryObject<T> register(String name, Supplier<T> supplier) {
        return REGISTRY.register(name, supplier);
    }

    public static void register() {

    }
}
