package me.zipestudio.ccdc.entrypoint;

//? if neoforge {
import me.zipestudio.ccdc.CCDCServer;
import me.zipestudio.ccdc.client.CCDCClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = CCDCServer.MOD_ID, dist = Dist.CLIENT)
public class ClientEntrypoint {

	public ClientEntrypoint(ModContainer container) {
		CCDCClient.onInitializeClient();
	}

}

//?}
