package us.th3controller.regioncontrol.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import us.th3controller.regioncontrol.RegionControl;

public class PlayerListener implements Listener {
	
	RegionControl plugin;
	
	public PlayerListener(RegionControl plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void WandInteract(PlayerInteractEvent event) {
		if(event.getItem().equals(Material.GHAST_TEAR)) {
			if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
				int x = event.getClickedBlock().getLocation().getBlockX();
				int y = event.getClickedBlock().getLocation().getBlockY();
				int z = event.getClickedBlock().getLocation().getBlockZ();
				plugin.pos1.put(event.getPlayer().getName(), x+" "+y+" "+z);
			}
		}
	}
}
