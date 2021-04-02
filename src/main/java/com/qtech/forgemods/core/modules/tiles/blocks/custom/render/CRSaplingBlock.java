package com.qtech.forgemods.core.modules.tiles.blocks.custom.render;

import com.qtech.modlib.common.interfaces.IHasRenderType;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.Tree;

/**
 * Door block with custom render type.
 *
 * @author Qboi123
 */
public abstract class CRSaplingBlock extends SaplingBlock implements IHasRenderType {
    public CRSaplingBlock(Tree treeIn, Properties properties) {
        super(treeIn, properties);
    }
}
