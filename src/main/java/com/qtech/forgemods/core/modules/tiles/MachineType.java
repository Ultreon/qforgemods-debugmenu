package com.qtech.forgemods.core.modules.tiles;

import com.qtech.forgemods.core.common.enums.MachineTier;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.AbstractMachineBaseTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.alloysmelter.AlloySmelterContainer;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.alloysmelter.AlloySmelterTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.arcaneescalator.ArcaneEscalatorContainer;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.arcaneescalator.ArcaneEscalatorTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.crusher.CrusherContainer;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.crusher.CrusherTileEntity;
import com.qtech.modlib.silentlib.util.Lazy;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;

import java.util.Objects;
import java.util.function.Supplier;

public class MachineType<T extends AbstractMachineBaseTileEntity, B extends T, S extends T, C extends Container> {
    public static final MachineType<ArcaneEscalatorTileEntity, ArcaneEscalatorTileEntity.Basic, ArcaneEscalatorTileEntity, ArcaneEscalatorContainer> ARCANE_ESCALATOR = new MachineType<>(
            () -> TileEntityType.Builder.create(ArcaneEscalatorTileEntity.Basic::new, ModBlocks.BASIC_ARCANE_ESCALATOR.get()),
            () -> TileEntityType.Builder.create(ArcaneEscalatorTileEntity::new, ModBlocks.ARCANE_ESCALATOR.get()),
            (id, inv) -> new ArcaneEscalatorContainer(id, inv, MachineTier.BASIC),
            (id, inv) -> new ArcaneEscalatorContainer(id, inv, MachineTier.STANDARD)
    );
    public static final MachineType<AlloySmelterTileEntity, AlloySmelterTileEntity.Basic, AlloySmelterTileEntity, AlloySmelterContainer> ALLOY_SMELTER = new MachineType<>(
            () -> TileEntityType.Builder.create(AlloySmelterTileEntity.Basic::new, ModBlocks.BASIC_ALLOY_SMELTER.get()),
            () -> TileEntityType.Builder.create(AlloySmelterTileEntity::new, ModBlocks.ALLOY_SMELTER.get()),
            (id, inv) -> new AlloySmelterContainer(id, inv, MachineTier.BASIC),
            (id, inv) -> new AlloySmelterContainer(id, inv, MachineTier.STANDARD)
    );
    public static final MachineType<CrusherTileEntity, CrusherTileEntity.Basic, CrusherTileEntity, CrusherContainer> CRUSHER = new MachineType<>(
            () -> TileEntityType.Builder.create(CrusherTileEntity.Basic::new, ModBlocks.BASIC_CRUSHER.get()),
            () -> TileEntityType.Builder.create(CrusherTileEntity::new, ModBlocks.CRUSHER.get()),
            (id, inv) -> new CrusherContainer(id, inv, MachineTier.BASIC),
            (id, inv) -> new CrusherContainer(id, inv, MachineTier.STANDARD)
    );
//    public static final MachineType<AbstractMachineBaseTileEntity, T, T, Container> ENCHANTER = new MachineType<>(
//            () -> TileEntityType.Builder.create(EnchanterTileEntity.Basic::new, ModBlocks.BASIC_ALLOY_SMELTER.get()),
//    );

    private final Lazy<TileEntityType<B>> basicTileEntityType;
    private final Lazy<TileEntityType<S>> standardTileEntityType;
    private final Lazy<ContainerType<C>> basicContainerType;
    private final Lazy<ContainerType<C>> standardContainerType;

    public MachineType(
            Supplier<TileEntityType.Builder<B>> basic,
            Supplier<TileEntityType.Builder<S>> standard,
            ContainerType.IFactory<C> basicContainer,
            ContainerType.IFactory<C> standardContainer
    ) {
        this.basicTileEntityType = Lazy.of(() -> basic.get().build(null));
        this.standardTileEntityType = Lazy.of(() -> standard.get().build(null));
        this.basicContainerType = Lazy.of(() -> new ContainerType<>(basicContainer));
        this.standardContainerType = Lazy.of(() -> new ContainerType<>(standardContainer));
    }

    public TileEntityType<? extends T> getTileEntityType(MachineTier tier) {
        switch (tier) {
            case BASIC:
                return basicTileEntityType.get();
            case STANDARD:
                return standardTileEntityType.get();
            default:
                throw new IllegalArgumentException("Unknown MachineTier: " + tier);
        }
    }

    public TileEntityType<B> getBasicTileEntityType() {
        return basicTileEntityType.get();
    }

    public TileEntityType<S> getStandardTileEntityType() {
        return standardTileEntityType.get();
    }

    public T create(MachineTier tier) {
        return Objects.requireNonNull(getTileEntityType(tier).create());
    }

    public ContainerType<C> getContainerType(MachineTier tier) {
        switch (tier) {
            case BASIC:
                return basicContainerType.get();
            case STANDARD:
                return standardContainerType.get();
            default:
                throw new IllegalArgumentException("Unknown MachineTier: " + tier);
        }
    }
}
