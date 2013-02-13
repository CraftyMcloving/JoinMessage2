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
							StringBuilder sb = new StringBuilder();
							for(int i = 2; i < args.length; i++){
								sb.append(args[i]).append(' ');
							}
							plugin.getConfig().set("Message", sb.toString());
							plugin.saveConfig();
							return true;
						} else {
							sender.sendMessage(ChatColor.RED + "Not enough arguments!");
						}
					}
				} else if(args[1].equalsIgnoreCase("quit")){
					//Checks if sender has permission
					if(sender.hasPermission("jm.edit.quit")){
						//Checks if message is there
						if(args.length > 2){
							StringBuilder sb = new StringBuilder();
							for(int i = 2; i < args.length; i++){
								sb.append(args[i]).append(' ');
							}
							plugin.getConfig().set("QuitMessage", sb.toString());
							plugin.saveConfig();
							return true;
						} else {
							sender.sendMessage(ChatColor.RED + "Not enough arguments!");
						}
					}
				} else if(args[1].equalsIgnoreCase("group")){
					//Checks if sender has permission
					if(sender.hasPermission("jm.edit.group")){
						if(args[2].equalsIgnoreCase("join")){
							if(args.length > 4){
								String group = args[3];
								StringBuilder sb = new StringBuilder();
								for(int i = 4; i < args.length; i++){
									sb.append(args[i]).append(' ');
								}
								plugin.getConfig().set("Group." + group + ".Message", sb.toString());
								plugin.saveConfig();
								return true;
							} else {
								sender.sendMessage(ChatColor.RED + "Not enough arguments!");
							}
						} else if(args[2].equalsIgnoreCase("quit")){
							if(args.length > 4){
								String group = args[3];
								StringBuilder sb = new StringBuilder();
								for(int i = 4; i < args.length; i++){
									sb.append(args[i]).append(' ');
								}
								plugin.getConfig().set("Group." + group + ".QuitMessage", sb.toString());
								plugin.saveConfig();
								return true;
							} else {
								sender.sendMessage(ChatColor.RED + "Not enough arguments!");
							}
						}
					}
				}
			} else if(args[0].equalsIgnoreCase("reload")){
				plugin.reloadConfig();
				return true;
			} else if(args[0].equalsIgnoreCase("help")){
				//TODO Help pages
			} else if(args.length == 0){
				//TODO Copy help pages
			}
		}
		return false;
	}
}
