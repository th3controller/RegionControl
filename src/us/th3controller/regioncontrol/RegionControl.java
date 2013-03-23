package us.th3controller.regioncontrol;

import java.util.HashMap;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import us.th3controller.regioncontrol.commands.CmdRegionControl;
import us.th3controller.regioncontrol.listeners.PlayerListener;
import us.th3controller.regioncontrol.util.Log;

public class RegionControl extends JavaPlugin {
	
	PluginDescriptionFile pdfile;
	
	public HashMap<String, String> pos1 = new HashMap<String, String>();
	public HashMap<String, String> pos2 = new HashMap<String, String>();
	
	public void onEnable() {
		pdfile = getDescription();
		Log.info("Initializing RegionControl...");
		getCommand("regioncontrol").setExecutor(new CmdRegionControl(this));
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
	}
	public void onDisable() {
		
	}
}
