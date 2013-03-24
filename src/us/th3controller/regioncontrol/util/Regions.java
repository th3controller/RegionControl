package us.th3controller.regioncontrol.util;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Regions {
	
	private static File regionsfile;
	public static FileConfiguration regionslist;
	
	/**
	 * Creates/loads the regions.yml file
	 */
	public static void loadRegions() {
		regionsfile = new File("plugins/RegionControl", "regions.yml");
		if(!regionsfile.exists()) {
			try {
				regionsfile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		regionslist = YamlConfiguration.loadConfiguration(regionsfile);
	}
	public static void saveRegions() {
		try {
			regionslist.save(regionsfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void check() {
		if(regionsfile == null || regionslist == null) {
			loadRegions();
		}
	}
	public static void set(String path, Object value) {
		check();
		regionslist.set(path, value);
		saveRegions();
	}
}
