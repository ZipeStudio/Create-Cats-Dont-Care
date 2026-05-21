package me.zipestudio.ccatsdontcare.entrypoint;

//? if neoforge {
import me.zipestudio.ccatsdontcare.CDCServer;
import me.zipestudio.ccatsdontcare.client.CDCClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = CDCServer.MOD_ID, dist = Dist.CLIENT)
public class ClientEntrypoint {

	public ClientEntrypoint(ModContainer container) {
		CDCClient.onInitializeClient();
	}

}

//?}
