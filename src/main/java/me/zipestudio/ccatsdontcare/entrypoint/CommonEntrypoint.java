package me.zipestudio.ccatsdontcare.entrypoint;

//? if neoforge {

import me.zipestudio.ccatsdontcare.CDCServer;
import net.neoforged.fml.common.Mod;

@Mod(CDCServer.MOD_ID)
public class CommonEntrypoint {

	public CommonEntrypoint() {
		CDCServer.onInitialize();
	}
}

//?}
