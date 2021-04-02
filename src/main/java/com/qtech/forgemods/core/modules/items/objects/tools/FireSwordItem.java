package com.qtech.forgemods.core.modules.items.objects.tools;

import com.qtech.forgemods.core.modules.items.tools.ModTraits;
import com.qtech.forgemods.core.modules.items.tools.SwordTool;
import com.qtech.forgemods.core.modules.items.tools.trait.AbstractTrait;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

/**
 * Fire sword item class.
 *
 * @author Qboi123
 */
public class FireSwordItem extends SwordTool {
    public FireSwordItem(ItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, properties, () -> new AbstractTrait[]{ModTraits.BLAZE.get()});
    }
}
