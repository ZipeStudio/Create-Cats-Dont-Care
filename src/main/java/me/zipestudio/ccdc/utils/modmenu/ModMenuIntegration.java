package me.zipestudio.ccdc.utils.modmenu;

import me.zipestudio.ccdc.config.YACLConfigurationScreen;
import net.minecraft.client.gui.screens.Screen;

public class ModMenuIntegration extends AbstractModMenuIntegration {

	@Override
	protected Screen createConfigScreen(Screen parent) {
		return YACLConfigurationScreen.createScreen(parent);
	}

}