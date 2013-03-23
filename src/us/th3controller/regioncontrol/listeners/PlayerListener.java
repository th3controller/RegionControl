package us.th3controller.regioncontrol.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChangedWorldEvent;
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
				Block clicked = event.getClickedBlock();
				String world = clicked.getWorld().getName();
				int x = clicked.getLocation().getBlockX();
				int y = clicked.getLocation().getBlockY();
				int z = clicked.getLocation().getBlockZ();
				plugin.pos1.put(event.getPlayer().getName(), world+":"+x+":"+y+":"+z);
			}
			else if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				Block clicked = event.getClickedBlock();
				String world = clicked.getWorld().getName();
				int x = clicked.getLocation().getBlockX();
				int y = clicked.getLocation().getBlockY();
				int z = clicked.getLocation().getBlockZ();
				plugin.pos2.put(event.getPlayer().getName(), world+":"+x+":"+y+":"+z);
			}
		}
	}
	@EventHandler
	public void PlayerWorldChange(PlayerChangedWorldEvent event) {
		if(plugin.pos1.containsKey(event.getPlayer().getName())) {
			plugin.pos1.remove(event.getPlayer().getName());
		}
		if(plugin.pos2.containsKey(event.getPlayer().getName())) {
			plugin.pos2.remove(event.getPlayer().getName());
		}
	}
}
