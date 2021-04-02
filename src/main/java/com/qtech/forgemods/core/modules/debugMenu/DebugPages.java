package com.qtech.forgemods.core.modules.debugMenu;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.modules.debugMenu.pages.WindowPage;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = QFMCore.modId, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DebugPages {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        DebugMenu.registerPage(new WindowPage(QFMCore.modId, "window"));
//        DebugScreen.registerPage(new PlayerPage1(QForgeMod.MOD_ID, "player1"));
    }
}
