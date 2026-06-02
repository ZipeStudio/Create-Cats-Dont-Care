package me.zipestudio.ccdc.entrypoint;

//? if neoforge {

import me.zipestudio.ccdc.CCDCServer;
import net.neoforged.fml.common.Mod;

@Mod(CCDCServer.MOD_ID)
public class CommonEntrypoint {

	public CommonEntrypoint() {
		CCDCServer.onInitialize();
	}
}

//?}
