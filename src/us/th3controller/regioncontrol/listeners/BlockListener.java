package us.th3controller.regioncontrol.listeners;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import us.th3controller.regioncontrol.RegionControl;
import us.th3controller.regioncontrol.util.Regions;

public class BlockListener implements Listener {
	
	RegionControl plugin;
	
	public BlockListener(RegionControl plugin) {
		this.plugin = plugin;
	}
	public Location toLocation(String location) {
		String[] loc = location.split(":");
		try {
			World world = Bukkit.getServer().getWorld(loc[0]);
			int x = Integer.parseInt(loc[1]);
			int y = Integer.parseInt(loc[2]);
			int z = Integer.parseInt(loc[3]);
			return new Location(world, x, y, z);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@EventHandler
	public void BlockBreak(BlockBreakEvent event) {
		Location loc = event.getBlock().getLocation();
		Set<String> regions = Regions.regionslist.getConfigurationSection("regions."+event.getBlock().getWorld().getName()).getKeys(false);
		for(String region : regions) {
			Location p1 = toLocation(Regions.regionslist.getString("regions."+event.getBlock().getWorld().getName()+"."+region+".position-1"));
			Location p2 = toLocation(Regions.regionslist.getString("regions."+event.getBlock().getWorld().getName()+"."+region+".position-2"));
			
			double maxX = (p1.getX() > p2.getX()) ? p1.getX() : p2.getX();
			double minX = (p1.getX() < p2.getX()) ? p1.getX() : p2.getX();
			if(loc.getX() <= maxX && loc.getX() >= minX) {
				double maxY = (p1.getY() > p2.getY()) ? p1.getY() : p2.getY();
				double minY = (p1.getY() < p2.getY()) ? p1.getY() : p2.getY();
				if(loc.getY() <= maxY && loc.getY() >= minY) {
					double maxZ = (p1.getZ() > p2.getZ()) ? p1.getZ() : p2.getZ();
					double minZ = (p1.getZ() < p2.getZ()) ? p1.getZ() : p2.getZ();
					if(loc.getZ() <= maxZ && loc.getZ() >= minZ) {
						
						if(!(event.getPlayer().isOp())) {
							event.setCancelled(true);
							event.getPlayer().sendMessage(ChatColor.RED+"You have insufficient flags to build in this area");
						}
					}
					continue;
				}
				continue;
			}
			continue;
		}
	}
}
