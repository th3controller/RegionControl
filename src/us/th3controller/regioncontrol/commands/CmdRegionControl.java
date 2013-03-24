package us.th3controller.regioncontrol.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import us.th3controller.regioncontrol.RegionControl;
import us.th3controller.regioncontrol.util.Regions;

public class CmdRegionControl implements CommandExecutor {
	
	RegionControl plugin;
	
	public CmdRegionControl(RegionControl plugin) {
		this.plugin = plugin;
	}
	
	PluginDescriptionFile pdfile;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 2) {
			Player player = (Player)sender;
			if(args[0].equalsIgnoreCase("create")) {
				if(plugin.pos1.containsKey(player.getName()) && plugin.pos2.containsKey(player.getName())) {
					//Create region information in regions.yml
					Regions.set("regions."+player.getWorld().getName()+"."+args[1]+".position-1", plugin.pos1.get(player.getName()));
					Regions.set("regions."+player.getWorld().getName()+"."+args[1]+".position-2", plugin.pos2.get(player.getName()));
					Regions.set("regions."+player.getWorld().getName()+"."+args[1]+".flags.build", "false");
					Regions.set("regions."+player.getWorld().getName()+"."+args[1]+".flags.use", "false");
					Regions.set("regions."+player.getWorld().getName()+"."+args[1]+".flags.tnt", "false");
					Regions.set("regions."+player.getWorld().getName()+"."+args[1]+".players."+player.getName()+".build", "true");
					Regions.set("regions."+player.getWorld().getName()+"."+args[1]+".players."+player.getName()+".use", "true");
				}
			}
		}
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help")) {
				ChatColor yellow = ChatColor.YELLOW;
				ChatColor white = ChatColor.WHITE;
				ChatColor green = ChatColor.GREEN;
				sender.sendMessage(yellow+"---------"+white+" Help: RegionControl "+yellow+"---------------------");
				sender.sendMessage(yellow+"Every argument listed here starts with "+green+"/rc"+yellow+", e.g. /rc create");
				sender.sendMessage(yellow+"create <name> "+white+"- creates a region between your selections");
				sender.sendMessage(yellow+"flag <flag> <boolean> "+white+"- add flags to allow or deny certain events from happening inside this region");
				sender.sendMessage(yellow+"remove <region name> "+white+"- removes a region using its name");
				sender.sendMessage(yellow+"pflag "+white+"- adds a flag only affecting that player");
			}
		}
		if(args.length == 0) {
			ChatColor green = ChatColor.GREEN;
			ChatColor yellow = ChatColor.YELLOW;
			sender.sendMessage(yellow+"RegionControl "+green+pdfile.getVersion());
			sender.sendMessage(yellow+"Type "+green+"/rc ?"+yellow+" for help");
		}
		return false;
	}
}
