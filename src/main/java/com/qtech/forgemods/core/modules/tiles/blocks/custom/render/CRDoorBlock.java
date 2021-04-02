package com.qtech.forgemods.core.modules.tiles.blocks.custom.render;

import com.qtech.modlib.common.interfaces.IHasRenderType;
import net.minecraft.block.DoorBlock;

/**
 * Door block with custom render type.
 *
 * @author Qboi123
 */
public abstract class CRDoorBlock extends DoorBlock implements IHasRenderType {
    public CRDoorBlock(Properties builder) {
        super(builder);
    }
}
