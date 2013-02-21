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
			//Checking argument 1
			if(args[0].equalsIgnoreCase("reload")){
				//Checking Permission
				if(sender.hasPermission("jm.reload")){
					plugin.reloadConfig();
					sender.sendMessage(ChatColor.GREEN + "[Join Message] Config Reloaded!");
					return true;
				}
				//Checking argument 1
				if(args[0].equalsIgnoreCase("set")){
					//Checking Permission
					if(sender.hasPermission("jm.set")){
						//Checking argument 2
						if(args[1].equalsIgnoreCase("join")){
							//Checking if Message Exists
							if(args.length > 2){
								StringBuilder sb = new StringBuilder();
								for(int i = 2; i > args.length; i++){
									sb.append(args[i]).append(' ');
								}
								plugin.getConfig().set("Message", sb.toString());
								plugin.saveConfig();
								sender.sendMessage(ChatColor.GREEN + "[JoinMessage] Global Join Message has been changed!");
								return true;
							} else {
								sender.sendMessage(ChatColor.RED + "[JoinMessage] Specify: Message");
								return true;
							}
						//Checking argument 2
						} else if(args[1].equalsIgnoreCase("quit")){
							//Checking if Message Exists
							if(args.length > 2){
								StringBuilder sb = new StringBuilder();
								for(int i =  2; i > args.length; i++){
									sb.append(args[i]).append(' ');
								}
								plugin.getConfig().set("QuitMessage", sb.toString());
								plugin.saveConfig();
								sender.sendMessage(ChatColor.GREEN + "[JoinMessage] Global Quit Message has been changed!");
								return true;
							} else {
								sender.sendMessage(ChatColor.RED + "[JoinMessage] Specify: Message");
								return true;
							}
						} else {
							sender.sendMessage(ChatColor.RED + "[JoinMesssage] Specify: Join/Quit");
							return true;
						}
					}
				}
				//Checking argument 1
				if(args[0].equalsIgnoreCase("info")){
					//checking Permission
					if(sender.hasPermission("jm.info")){
						sender.sendMessage(ChatColor.GREEN + "[JoinMessage] Join Message v2.3 is created by tacticalsk8er");
					}
				}
				if(args[0].equalsIgnoreCase("group")){
					if(sender.hasPermission("jm.set.group")){
						
					}
				}
				if(args[0].equalsIgnoreCase("player")){
					if(sender.hasPermission("jm.set.player")){
						
					}
				}
			}
		}
		return false;
	}
}
