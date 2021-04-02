package com.qtech.forgemods.core.modules.tiles;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.init.Registration;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.batterybox.BatteryBoxTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.compressor.CompressorTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.dryingrack.DryingRackBlock;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.dryingrack.DryingRackTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.dryingrack.DryingRackTileEntityRenderer;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.electricfurnace.ElectricFurnaceTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.generator.coal.CoalGeneratorTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.generator.diesel.DieselGeneratorTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.generator.lava.LavaGeneratorTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.infuser.InfuserTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.mixer.MixerTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.pipe.PipeTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.pump.PumpTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.quarry.QuarryTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.refinery.RefineryTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.solidifier.SolidifierTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.wire.WireTileEntity;
import com.qtech.forgemods.core.util.ExceptionUtil;
import com.qtech.modlib.silentlib.block.IBlockProvider;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public final class ModMachineTileEntities {
    public static TileEntityType<BatteryBoxTileEntity> batteryBox;
    public static TileEntityType<CoalGeneratorTileEntity> coalGenerator;
    public static TileEntityType<CompressorTileEntity> compressor;
    public static TileEntityType<DieselGeneratorTileEntity> dieselGenerator;
    public static TileEntityType<DryingRackTileEntity> dryingRack;
    public static TileEntityType<ElectricFurnaceTileEntity> electricFurnace;
    public static TileEntityType<InfuserTileEntity> infuser;
    public static TileEntityType<LavaGeneratorTileEntity> lavaGenerator;
    public static TileEntityType<MixerTileEntity> mixer;
    public static TileEntityType<PipeTileEntity> pipe;
    public static TileEntityType<PumpTileEntity> pump;
    public static TileEntityType<QuarryTileEntity> quarry;
    public static TileEntityType<RefineryTileEntity> refinery;
    public static TileEntityType<SolidifierTileEntity> solidifier;
    public static TileEntityType<WireTileEntity> wire;

    private ModMachineTileEntities() {
        throw ExceptionUtil.utilityConstructor();
    }

    /**
     * Register all tile entities of this mod.
     *
     * @param event registry event.
     */
    public static void registerAll(RegistryEvent.Register<TileEntityType<?>> event) {
        register("basic_alloy_smelter", MachineType.ALLOY_SMELTER.getBasicTileEntityType());
        register("alloy_smelter", MachineType.ALLOY_SMELTER.getStandardTileEntityType());
        register("basic_arcane_escalator", MachineType.ARCANE_ESCALATOR.getBasicTileEntityType());
        register("arcane_escalator", MachineType.ARCANE_ESCALATOR.getStandardTileEntityType());
        register("basic_crusher", MachineType.CRUSHER.getBasicTileEntityType());
        register("crusher", MachineType.CRUSHER.getStandardTileEntityType());
        batteryBox = register("battery_box", BatteryBoxTileEntity::new, ModBlocks.BATTERY_BOX);
        coalGenerator = register("coal_generator", CoalGeneratorTileEntity::new, ModBlocks.COAL_GENERATOR);
        compressor = register("compressor", CompressorTileEntity::new, ModBlocks.COMPRESSOR);
        dieselGenerator = register("diesel_generator", DieselGeneratorTileEntity::new, ModBlocks.DIESEL_GENERATOR);
        dryingRack = register("drying_rack", DryingRackTileEntity::new, Registration.getBlocks(DryingRackBlock.class).toArray(new Block[0]));
        electricFurnace = register("electric_furnace", ElectricFurnaceTileEntity::new, ModBlocks.ELECTRIC_FURNACE);
        lavaGenerator = register("lava_generator", LavaGeneratorTileEntity::new, ModBlocks.LAVA_GENERATOR);
        mixer = register("mixer", MixerTileEntity::new, ModBlocks.MIXER);
        infuser = register("infuser", InfuserTileEntity::new, ModBlocks.INFUSER);
        pipe = register("pipe", PipeTileEntity::new, ModBlocks.PIPE);
        pump = register("pump", PumpTileEntity::new, ModBlocks.PUMP);
        quarry = register("quarry", QuarryTileEntity::new, ModBlocks.QUARRY);
        refinery = register("refinery", RefineryTileEntity::new, ModBlocks.REFINERY);
        solidifier = register("solidifier", SolidifierTileEntity::new, ModBlocks.SOLIDIFIER);
        wire = register("wire", WireTileEntity::new, ModBlocks.WIRE);
    }

    private static <T extends TileEntity> TileEntityType<T> register(String name, Supplier<T> tileFactory, IBlockProvider block) {
        return register(name, tileFactory, block.asBlock());
    }

    private static <T extends TileEntity> TileEntityType<T> register(String name, Supplier<T> tileFactory, Block... blocks) {
        TileEntityType<T> type = TileEntityType.Builder.create(tileFactory, blocks).build(null);
        return register(name, type);
    }

    private static <T extends TileEntity> TileEntityType<T> register(String name, TileEntityType<T> type) {
        if (type.getRegistryName() == null) {
            type.setRegistryName(QFMCore.rl(name));
        }
        ForgeRegistries.TILE_ENTITIES.register(type);
        return type;
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderers(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(dryingRack, DryingRackTileEntityRenderer::new);
    }

    public static void register() {

    }
}
