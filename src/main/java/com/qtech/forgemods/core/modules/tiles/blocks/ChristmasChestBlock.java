package com.qtech.forgemods.core.modules.tiles.blocks;

import com.qtech.forgemods.core.modules.tiles.tileentities.ChristmasChestTileEntity;
import net.minecraft.block.ChestBlock;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

/**
 * Christmas chest block.
 *
 * @author Qboi123
 */
public class ChristmasChestBlock extends ChestBlock {
    public ChristmasChestBlock(Properties builder, Supplier<TileEntityType<? extends ChestTileEntity>> tileEntityTypeIn) {
        super(builder, tileEntityTypeIn);
    }

    @Override
    public ChristmasChestTileEntity createNewTileEntity(IBlockReader worldIn) {
        return new ChristmasChestTileEntity();
    }
}
