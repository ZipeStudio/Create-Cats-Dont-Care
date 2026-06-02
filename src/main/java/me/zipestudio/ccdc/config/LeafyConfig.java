package me.zipestudio.ccdc.config;

import lombok.*;
import me.zipestudio.ccdc.CCDCServer;
import me.zipestudio.ccdc.utils.CodecUtils;
import me.zipestudio.ccdc.utils.ConfigUtils;
import org.slf4j.*;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.nio.file.Path;

import java.io.*;
import java.util.concurrent.CompletableFuture;
import static me.zipestudio.ccdc.utils.CodecUtils.option;

@Getter
@Setter
@AllArgsConstructor
public class LeafyConfig {

	public static final Codec<LeafyConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			option("enable_mod", true, Codec.BOOL, LeafyConfig::isEnableMod),
			option("belt_max_speed", 128, Codec.INT, LeafyConfig::getBeltMaxSpeed)
	).apply(instance, LeafyConfig::new));

	private static final File CONFIG_FILE = getConfigDir().resolve(CCDCServer.MOD_ID + ".json5").toFile();

	private static final Logger LOGGER = LoggerFactory.getLogger(CCDCServer.MOD_NAME + "/Config");
	private static LeafyConfig INSTANCE;

	private boolean enableMod;
	private int beltMaxSpeed;

	private LeafyConfig() {
		throw new IllegalArgumentException();
	}

	public static LeafyConfig getInstance() {
		return INSTANCE == null ? reload() : INSTANCE;
	}

	public static LeafyConfig reload() {
		return INSTANCE = LeafyConfig.read();
	}

	public static LeafyConfig getNewInstance() {
		return CodecUtils.parseNewInstanceHacky(CODEC);
	}

	private static LeafyConfig read() {
		return ConfigUtils.readConfig(CODEC, CONFIG_FILE, LOGGER);
	}

	public void saveAsync() {
		CompletableFuture.runAsync(this::save);
	}

	public void save() {
		ConfigUtils.saveConfig(this, CODEC, CONFIG_FILE, LOGGER);
	}

	public static Path getConfigDir() {
		return net.neoforged.fml.loading.FMLPaths.CONFIGDIR.get();
	}

}
