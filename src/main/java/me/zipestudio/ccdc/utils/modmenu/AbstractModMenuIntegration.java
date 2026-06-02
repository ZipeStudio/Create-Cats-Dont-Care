package me.zipestudio.ccdc.utils.modmenu;

import me.zipestudio.ccdc.CCDCServer;
import me.zipestudio.ccdc.client.CCDCClient;
import net.neoforged.fml.*;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.apache.maven.artifact.versioning.*;

//? if >=1.21 {
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
 //?} else {
/*import net.neoforged.neoforge.client.ConfigScreenHandler;
*///?}

public abstract class AbstractModMenuIntegration {

	public void register(ModContainer container) {

		//? if >=1.21 {

		container.registerExtensionPoint(IConfigScreenFactory.class, (modContainer, parent) -> {
			if (isModLoaded("yet_another_config_lib_v3", false)) {
			ModContainer yacl = ModList.get().getModContainerById("yet_another_config_lib_v3").orElseThrow();
			ArtifactVersion version = yacl.getModInfo().getVersion();
			try {
				ArtifactVersion requestsVersion = new DefaultArtifactVersion(CCDCServer.YACL_DEPEND_VERSION);
				if (version.compareTo(requestsVersion) >= 0) {
					return this.createConfigScreen(parent);
				}
			} catch (Exception e) {
				CCDCClient.LOGGER.error("Failed to compare YACL version, tell mod author about this error: ", e);
			}
			return NoConfigLibraryScreen.createScreenAboutOldVersion(parent, version.getQualifier());
		}
		return NoConfigLibraryScreen.createScreen(parent);
	});

	//?} else {

		/*container.registerExtensionPoint(
				ConfigScreenHandler.ConfigScreenFactory.class,
				() -> new ConfigScreenHandler.ConfigScreenFactory((mc, parent) -> {

					if (isModLoaded("yet_another_config_lib_v3", false)) {
						ModContainer yacl = ModList.get().getModContainerById("yet_another_config_lib_v3").orElseThrow();
						ArtifactVersion version = yacl.getModInfo().getVersion();

						try {
							ArtifactVersion requestsVersion = new DefaultArtifactVersion(CCDCServer.YACL_DEPEND_VERSION);

							if (version.compareTo(requestsVersion) >= 0) {
								return this.createConfigScreen(parent);
							}
						} catch (Exception e) {
							CCDCClient.LOGGER.error("Failed to compare YACL version, tell mod author about this error: ", e);
						}

						return NoConfigLibraryScreen.createScreenAboutOldVersion(parent, version.getQualifier());
					}

					return NoConfigLibraryScreen.createScreen(parent);
				})
		);

		*///?}

	}

	protected abstract Screen createConfigScreen(Screen parent);

	public static boolean isModLoaded(String modid, boolean loadingPhase) {
		if (loadingPhase) {
			//? if >=1.21.9 {
			/*return FMLLoader.getCurrent().getLoadingModList().getModFileById(modid) != null;
			 *///?} else {
			return FMLLoader.getLoadingModList().getModFileById(modid) != null;
			//?}
		} else {
			return ModList.get().isLoaded(modid);
		}
	}

}