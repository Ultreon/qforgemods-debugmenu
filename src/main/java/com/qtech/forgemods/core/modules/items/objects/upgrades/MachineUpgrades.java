package com.qtech.forgemods.core.modules.items.objects.upgrades;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.api.IMachineUpgrade;
import com.qtech.forgemods.core.common.interfaces.INamed;
import com.qtech.forgemods.core.common.interfaces.Translatable;
import com.qtech.forgemods.core.init.Registration;
import com.qtech.forgemods.core.util.Constants;
import com.qtech.modlib.silentlib.registry.ItemRegistryObject;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;

import java.util.Locale;

public enum MachineUpgrades implements IItemProvider, IMachineUpgrade, INamed, Translatable {
    PROCESSING_SPEED(Constants.UPGRADE_PROCESSING_SPEED_AMOUNT, 0.5f),
    OUTPUT_CHANCE(Constants.UPGRADE_SECONDARY_OUTPUT_AMOUNT, 0.25f),
    ENERGY_CAPACITY(0, 0.0f, false),
    ENERGY_EFFICIENCY(Constants.UPGRADE_ENERGY_EFFICIENCY_AMOUNT, Constants.UPGRADE_ENERGY_EFFICIENCY_AMOUNT),
    RANGE(Constants.UPGRADE_RANGE_AMOUNT, 0.15f, false);

    private final float upgradeValue;
    private final float energyUsage;
    private final boolean displayValueAsPercentage;
    @SuppressWarnings("NonFinalFieldInEnum")
    private ItemRegistryObject<MachineUpgradeItem> item;

    MachineUpgrades(float upgradeValue, float energyUsage) {
        this(upgradeValue, energyUsage, true);
    }

    MachineUpgrades(float upgradeValue, float energyUsage, boolean displayValueAsPercentage) {
        this.upgradeValue = upgradeValue;
        this.energyUsage = energyUsage;
        this.displayValueAsPercentage = displayValueAsPercentage;
    }

    public static void register() {
        for (MachineUpgrades value : values()) {
            value.item = Registration.ITEMS.register(value.getStringName(), () ->
                    new MachineUpgradeItem(value));
        }
    }

    @Override
    public float getEnergyUsageMultiplier() {
        return energyUsage;
    }

    @Override
    public float getUpgradeValue() {
        return upgradeValue;
    }

    @Override
    public boolean displayValueAsPercentage() {
        return displayValueAsPercentage;
    }

    @Override
    public String getStringName() {
        return name().toLowerCase(Locale.ROOT) + "_upgrade";
    }

    @Override
    public Item asItem() {
        return item.get();
    }

    @Override
    public String getTranslationKey() {
        return "upgrade." + QFMCore.modId + "." + getStringName();
    }
}
