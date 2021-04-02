package com.qtech.forgemods.core.modules.items.objects.type;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

/**
 * Base item class.
 *
 * @author Qboi123
 * @deprecated Use {@link Item} instead.
 */
@Deprecated
public class BaseItem extends Item {
    public BaseItem(ItemGroup group) {
        super(new Item.Properties().group(group));
    }
}
