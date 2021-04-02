package com.qtech.forgemods.core.modules.tiles.blocks.custom.render;

import com.qtech.modlib.common.interfaces.IHasRenderType;
import net.minecraft.block.FlowerBlock;
import net.minecraft.potion.Effect;

/**
 * Door block with custom render type.
 *
 * @author Qboi123
 */
public abstract class CRFlowerBlock extends FlowerBlock implements IHasRenderType {
    public CRFlowerBlock(Effect effect, int effectDuration, Properties properties) {
        super(effect, effectDuration, properties);
    }
}
