package com.qtech.forgemods.core.modules.debugMenu;

import com.qtech.forgemods.core.modules.actionmenu.AbstractActionMenu;
import com.qtech.forgemods.core.modules.actionmenu.IActionMenuItem;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.Locale;

public class DebugMenuMenu extends AbstractActionMenu {
    public DebugMenuMenu() {
        for (DebugMenu.PAGE page : DebugMenu.PAGE.values()) {
            addItem(new IActionMenuItem() {
                @Override
                public void onActivate() {
                    DebugMenu.DEBUG_PAGE = page;
                }

                @Override
                public ITextComponent getText() {
                    return new StringTextComponent(page.name().toLowerCase(Locale.ROOT));
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }
            });
        }
    }
}
