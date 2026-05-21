package me.zipestudio.ccatsdontcare.client;

import me.zipestudio.ccatsdontcare.CDCServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CDCClient {

	public static final Logger LOGGER = LoggerFactory.getLogger(CDCServer.MOD_NAME + "/Client");

	public static void onInitializeClient() {
		LOGGER.info("{} Client Initialized", CDCServer.MOD_NAME);
	}
}
