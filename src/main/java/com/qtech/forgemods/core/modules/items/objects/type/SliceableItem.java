package com.qtech.forgemods.core.modules.items.objects.type;

import com.qtech.forgemods.core.common.interfaces.Sliceable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Sliceable item class.
 *
 * @author Qboi123
 */
public class SliceableItem extends Item implements Sliceable {
    private final SliceAction action;

    public SliceableItem(Properties properties, SliceAction action) {
        super(properties);

        this.action = action;
    }

    @Override
    public ItemStack onKnifeSlice(ItemStack stack) {
        return action.slice(stack);
    }

    @FunctionalInterface
    public interface SliceAction {
        ItemStack slice(ItemStack stack);
    }
}
