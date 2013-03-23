package us.th3controller.regioncontrol.util;

import java.util.logging.Logger;

public class Log {
	
	private static Logger log = Logger.getLogger("Minecraft");
	
	public static void info(String string) {
		log.info("[RegionControl]"+string);
	}
	public static void warning(String string) {
		log.warning("[RegionControl]"+string);
	}
	public static void severe(String string) {
		log.severe("[RegionControl]"+string);
	}
}
