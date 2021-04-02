package com.qtech.forgemods.core.modules.items.objects.energy;

import com.qtech.forgemods.core.modules.ui.ModItemGroups;
import net.minecraft.item.Rarity;

public class BatteryItem extends EnergyStoringItem {
    private static final int MAX_ENERGY = 500_000;
    private static final int MAX_TRANSFER = 500;

    public BatteryItem() {
        super(new Properties().group(ModItemGroups.MACHINES).maxStackSize(1).rarity(Rarity.UNCOMMON), MAX_ENERGY, MAX_TRANSFER);
    }
}
