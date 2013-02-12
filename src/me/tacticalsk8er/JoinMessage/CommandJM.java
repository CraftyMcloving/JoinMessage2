package me.tacticalsk8er.JoinMessage;

import org.bukkit.ChatColor;
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
			//Checks if want to edit
			if(args[0].equalsIgnoreCase("edit")){
				//Checks what message to edit
				if(args[1].equalsIgnoreCase("join")){
					//Checks if sender has permission
					if(sender.hasPermission("jm.edit.join")){
						//Checks if a message is there
						if(args.length > 2){
							
						} else {
							sender.sendMessage(ChatColor.RED + "Not enought arguments!");
						}
					}
				} else if(args[1].equalsIgnoreCase("quit")){
					//Checks if sender has permission
					if(sender.hasPermission("jm.edit.quit")){
						
					}
				} else if(args[1].equalsIgnoreCase("group")){
					//Checks if sender has permission
					if(sender.hasPermission("jm.edit.group")){
						
					}
				}
			}
		}
		return false;
	}
}
