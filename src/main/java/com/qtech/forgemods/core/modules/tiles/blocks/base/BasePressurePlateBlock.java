package com.qtech.forgemods.core.modules.tiles.blocks.base;

import net.minecraft.block.PressurePlateBlock;

/**
 * Game PC block class.
 *
 * @author Qboi123
 * @deprecated Use {@link PressurePlateBlock} instead.
 */
@Deprecated
public class BasePressurePlateBlock extends PressurePlateBlock {
    public BasePressurePlateBlock(Sensitivity sensitivity, Properties properties) {
        super(sensitivity, properties);
    }
}
