package com.qtech.forgemods.debugmenu.pages;

import com.qtech.forgemods.debugmenu.QFMDebugMenu;
import com.qtech.forgemods.debugmenu.DebugPage;

public class DefaultPage extends DebugPage {
    public DefaultPage() {
        super(QFMDebugMenu.getModId(), "default");
    }

    @Override
    public boolean hasRequiredComponents() {
        return true;
    }
}
