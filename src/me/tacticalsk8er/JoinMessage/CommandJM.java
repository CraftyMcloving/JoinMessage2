package me.tacticalsk8er.JoinMessage;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandJM implements CommandExecutor{
	
	Main plugin;
	
	public CommandJM(Main instance){
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("jm")){
			if(args[0].equalsIgnoreCase("reload")){
				if(sender.hasPermission("jm.reload")){
					plugin.reloadConfig();
					sender.sendMessage("[Join Message] Config Reloaded!");
				}
			}
		}
		return false;
	}
}
