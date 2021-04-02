package com.qtech.forgemods.core.modules.tiles.blocks.custom.render;

import com.qtech.modlib.common.interfaces.IHasRenderType;
import net.minecraft.block.DoublePlantBlock;

/**
 * Door block with custom render type.
 *
 * @author Qboi123
 */
public abstract class CRDoublePlantBlock extends DoublePlantBlock implements IHasRenderType {
    public CRDoublePlantBlock(Properties builder) {
        super(builder);
    }
}
