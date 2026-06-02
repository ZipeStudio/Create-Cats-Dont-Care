package me.zipestudio.ccdc.client;

import me.zipestudio.ccdc.CCDCServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CCDCClient {

	public static final Logger LOGGER = LoggerFactory.getLogger(CCDCServer.MOD_NAME + "/Client");

	public static void onInitializeClient() {
		LOGGER.info("{} Client Initialized", CCDCServer.MOD_NAME);
	}
}
