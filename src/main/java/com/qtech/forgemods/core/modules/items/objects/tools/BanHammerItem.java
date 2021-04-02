package com.qtech.forgemods.core.modules.items.objects.tools;

import com.mojang.authlib.GameProfile;
import com.qtech.forgemods.core.common.enums.TextColors;
import com.qtech.forgemods.core.modules.ui.ModItemGroups;
import com.qtech.forgemods.core.util.Targeter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.server.management.BanList;
import net.minecraft.server.management.ProfileBanEntry;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * Ban hammer item class.
 *
 * @author Qboi123
 */
public class BanHammerItem extends Item {
    public BanHammerItem() {
        super(new Item.Properties().group(ModItemGroups.OVERPOWERED).rarity(Rarity.EPIC));
    }

    @Override
    public boolean hitEntity(@NotNull ItemStack stack, @NotNull LivingEntity victim, @NotNull LivingEntity player) {
        if (victim instanceof PlayerEntity) {
            PlayerEntity playerVictim = (PlayerEntity) victim;
            if (playerVictim.world instanceof ServerWorld) {
                if (player.getServer() != null) {
                    BanList banlist = player.getServer().getPlayerList().getBannedPlayers();
                    GameProfile gameprofile = playerVictim.getGameProfile();

                    if (!banlist.isBanned(gameprofile)) {
                        ProfileBanEntry profilebanentry = new ProfileBanEntry(gameprofile, new Date(), player.getName().getString(), null, TextColors.LIGHT_RED + "The banhammer has spoken");
                        banlist.addEntry(profilebanentry);
                        player.sendMessage(new TranslationTextComponent("commands.ban.success", TextComponentUtils.getDisplayName(gameprofile), profilebanentry.getBanReason()), playerVictim.getUniqueID());
                        ServerPlayerEntity serverplayerentity = player.getServer().getPlayerList().getPlayerByUUID(gameprofile.getId());
                        if (serverplayerentity != null) {
                            serverplayerentity.connection.disconnect(new TranslationTextComponent("multiplayer.disconnect.banned"));
                        }
                    }
                }
            }
        }

        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        super.onItemRightClick(worldIn, playerIn, handIn);

        ItemStack stack = playerIn.getHeldItem(handIn);
        Entity entity = Targeter.getTarget(playerIn);
        if (entity == null) {
            return ActionResult.resultFail(stack);
        }

        if (!entity.world.isRemote && entity instanceof PlayerEntity) {
            PlayerEntity victim = (PlayerEntity) entity;
            if (victim.world instanceof ServerWorld) {
                if (playerIn.getServer() != null) {
                    BanList banlist = playerIn.getServer().getPlayerList().getBannedPlayers();
                    GameProfile gameprofile = victim.getGameProfile();

                    if (!banlist.isBanned(gameprofile)) {
                        ProfileBanEntry profilebanentry = new ProfileBanEntry(gameprofile, new Date(), playerIn.getName().getString(), null, TextColors.LIGHT_RED + "The banhammer has spoken");
                        banlist.addEntry(profilebanentry);
                        playerIn.sendMessage(new TranslationTextComponent("commands.ban.success", TextComponentUtils.getDisplayName(gameprofile), profilebanentry.getBanReason()), victim.getUniqueID());
                        ServerPlayerEntity serverplayerentity = playerIn.getServer().getPlayerList().getPlayerByUUID(gameprofile.getId());
                        if (serverplayerentity != null) {
                            serverplayerentity.connection.disconnect(new TranslationTextComponent("multiplayer.disconnect.banned"));
                            playerIn.addStat(Stats.ITEM_USED.get(this));

                            return ActionResult.resultSuccess(stack);
                        }
                    } else {
                        return ActionResult.resultFail(stack);
                    }
                }
            }
        } else if (!entity.world.isRemote) {
            if (entity.world instanceof ServerWorld) {
                entity.remove();
                playerIn.sendMessage(new TranslationTextComponent("commands.ban.success", entity.getName(), "The banhammer has spoken!"), entity.getUniqueID());
                playerIn.addStat(Stats.ITEM_USED.get(this));

                return ActionResult.resultSuccess(stack);
            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (!entity.world.isRemote && entity instanceof PlayerEntity) {
            PlayerEntity victim = (PlayerEntity) entity;
            if (victim.world instanceof ServerWorld) {
                if (player.getServer() != null) {
                    BanList banlist = player.getServer().getPlayerList().getBannedPlayers();
                    GameProfile gameprofile = victim.getGameProfile();

                    if (!banlist.isBanned(gameprofile)) {
                        ProfileBanEntry profilebanentry = new ProfileBanEntry(gameprofile, new Date(), player.getName().getString(), null, TextColors.LIGHT_RED + "The banhammer has spoken");
                        banlist.addEntry(profilebanentry);
                        player.sendMessage(new TranslationTextComponent("commands.ban.success", TextComponentUtils.getDisplayName(gameprofile), profilebanentry.getBanReason()), victim.getUniqueID());
                        ServerPlayerEntity serverplayerentity = player.getServer().getPlayerList().getPlayerByUUID(gameprofile.getId());
                        if (serverplayerentity != null) {
                            serverplayerentity.connection.disconnect(new TranslationTextComponent("multiplayer.disconnect.banned"));
                            player.addStat(Stats.ITEM_USED.get(this));
                        }
                    }
                }
            }
        } else if (!entity.world.isRemote) {
            if (entity.world instanceof ServerWorld) {
                entity.remove();
                player.sendMessage(new TranslationTextComponent("commands.ban.success", entity.getName(), "The banhammer has spoken!"), entity.getUniqueID());
            }
        }

        return false;
    }
}
