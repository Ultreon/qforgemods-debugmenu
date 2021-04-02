package com.qtech.forgemods.core.modules.ui.groups;

import com.qtech.forgemods.core.modules.items.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Special miscellaneous group.
 *
 * @author Qboi123
 */
public class SpecialsItemGroup extends ItemGroup {
    public static final SpecialsItemGroup instance = new SpecialsItemGroup(ItemGroup.GROUPS.length, "qforgemod_specials");

    public SpecialsItemGroup(int index, String label) {
        super(index, label);
    }

    @Override
    public @NotNull ItemStack createIcon() {
        return new ItemStack(ModItems.FIRE_SWORD.get());
    }
}
