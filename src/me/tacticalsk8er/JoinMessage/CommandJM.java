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
			//Checks if argument 1 exists
			if(args.length > 0){
				//Checking argument 1
				if(args[0].equalsIgnoreCase("reload")){
					//Checking Permission
					if(sender.hasPermission("jm.reload")){
						plugin.reloadConfig();
						sender.sendMessage(ChatColor.GREEN + "[Join Message] Config Reloaded!");
						return true;
					}
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
									for(int i = 2; i < args.length; i++){
										sb.append(args[i]).append(' ');
									}
									plugin.getConfig().set("Message", sb.toString());
									plugin.getServer().getLogger().info(sb.toString());
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
									for(int i =  2; i < args.length; i++){
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
							sender.sendMessage(ChatColor.GREEN + "[JoinMessage] Join Message v" + plugin.getDescription().getVersion() + " is created by tacticalsk8er");
							return true;
						}
					}
					if(args[0].equalsIgnoreCase("group")){
						//Checks Permission
						if(sender.hasPermission("jm.set.group")){
							//Checks for group
							if(args.length > 1){
								String group = args[1];
								//Checks for set/prefix/suffix
								if(args[2].equalsIgnoreCase("set")){
									//Checks for Join/Quit
									if(args[3].equalsIgnoreCase("join")){
										//Checks if message exists
										if(args.length > 4){
											StringBuilder sb = new StringBuilder();
											for(int i = 4; i < args.length; i++){
												sb.append(args[i]).append(' ');
											}
											plugin.getConfig().set("Group." + group + ".Message", sb.toString());
											plugin.saveConfig();
											sender.sendMessage(ChatColor.GREEN + "[JoinMessage] Group Join Message has been changed!");
											return true;
										} else {
											sender.sendMessage(ChatColor.RED + "[JoinMessage] Specify: Message");
											return true;
										}
									} else if (args[3].equalsIgnoreCase("quit")){
										//Checks if message exists
										if(args.length > 4){
											StringBuilder sb = new StringBuilder();
											for(int i = 4; i < args.length; i++){
												sb.append(args[i]).append(' ');
											}
											plugin.getConfig().set("Group." + group + ".QuitMessage", sb.toString());
											plugin.saveConfig();
											sender.sendMessage(ChatColor.GREEN + "[JoinMessage] Group Quit Message has been changed!");
											return true;
										} else {
											sender.sendMessage(ChatColor.RED + "[JoinMessage] Specify: Message");
										}
									} else {
										sender.sendMessage(ChatColor.RED + "[JoinMessage] Specify: Join/Quit");
										return true;
									}
								} else if(args[2].equalsIgnoreCase("prefix")){
									//Checks if Prefix exists
									if(args.length > 3){
										StringBuilder sb = new StringBuilder();
										for(int i = 3; i < args.length; i++){
											sb.append(args[i]).append(' ');
										}
										plugin.chat.setGroupPrefix("", group, sb.toString());
										sender.sendMessage(ChatColor.GREEN + "[JoinMessage] Group Prefix has been changed!");
										return true;
									} else {
										sender.sendMessage(ChatColor.RED + "[JoinMessage] Specify: Prefix");
										return true;
									}
								} else if(args[2].equalsIgnoreCase("suffix")){
									//Checks is Suffix exists
									if(args.length > 3){
										StringBuilder sb = new StringBuilder();
										for(int i = 3; i < args.length; i++){
											sb.append(args[i]).append(' ');
										}
										plugin.chat.setGroupPrefix("", group, sb.toString());
										sender.sendMessage(ChatColor.GREEN + "[JoinMessage] Group Suffix has been change!");
										return true;
									} else {
										sender.sendMessage(ChatColor.RED + "[JoinMessage] Specify: Suffix");
										return true;
									}
								} else {
									sender.sendMessage(ChatColor.RED + "[JoinMessage] Specify: Set/Prefix/Suffix");
									return true;
								}
							} else {
								sender.sendMessage(ChatColor.RED + "[JoinMessage] Specify: Group");
								return true;
							}
						}
					}
					if(args[0].equalsIgnoreCase("player")){
						//Checks Permission
						if(sender.hasPermission("jm.set.player")){
							//Checks for Player
							if(args.length > 1){
								String player = args[1];
								//Checks for set/prefix/suffix
								if(args[2].equalsIgnoreCase("set")){
									//Checks for Join/Quit
									if(args[3].equalsIgnoreCase("join")){
										//Checks if message exists
										if(args.length > 4){
											StringBuilder sb = new StringBuilder();
											for(int i = 4; i < args.length; i++){
												sb.append(args[i]).append(' ');
											}
											plugin.getConfig().set("Player." + player + ".Message", sb.toString());
											plugin.saveConfig();
											sender.sendMessage(ChatColor.GREEN + "[JoinMessage] Player Join Message has been changed!");
											return true;
										} else {
											sender.sendMessage(ChatColor.RED  + "[JoinMessage] Specify: Message");
											return true;
										}
									} else if(args[3].equalsIgnoreCase("quit")){
										//Checks if message exists
										if(args.length > 4){
											StringBuilder sb = new StringBuilder();
											for(int i = 4; i < args.length; i++){
												sb.append(args[i]).append(' ');
											}
											plugin.getConfig().set("Player." + player + ".QuitMessage", sb.toString());
											plugin.saveConfig();
											sender.sendMessage(ChatColor.GREEN + "[JoinMessage] PLayer Quit Message has been changed!");
											return true;
										} else {
											sender.sendMessage(ChatColor.RED + "[JoinMessage] Specify: Message");
											return true;
										}
									} else {
										sender.sendMessage(ChatColor.RED + "[JoinMessage] Specify: Join/Quit");
										return true;
									}
								} else if(args[2].equalsIgnoreCase("prefix")){
									//Checks if prefix exists
									if(args.length > 3){
										StringBuilder sb = new StringBuilder();
										for(int i = 3; i < args.length; i++){
											sb.append(args[i]).append(' ');
										}
										plugin.chat.setPlayerPrefix("", player, sb.toString());
										sender.sendMessage(ChatColor.GREEN + "[JoinMessage] Player Prefix has been changed!");
										return true;
									} else {
										sender.sendMessage(ChatColor.RED + "[JoinMessage] Specify: Prefix");
										return true;
									}
								} else if(args[2].equalsIgnoreCase("suffix")){
									//Checks if suffix exists
									if(args.length > 3){
										StringBuilder sb = new StringBuilder();
										for(int i = 3; i < args.length; i++){
											sb.append(args[i]).append(' ');
										}
										plugin.chat.setPlayerPrefix("", player, sb.toString());
										sender.sendMessage(ChatColor.RED + "[JoinMessage] Player Suffix has been changed!");
										return true;
									} else {
										sender.sendMessage(ChatColor.RED + "[JoinMessage] Specify: Suffix");
										return true;
									}
								} else {
									sender.sendMessage(ChatColor.RED + "[JoinMessage] Specify: Set/Prefix/Suffix");
									return true;
								}
							} else {
								sender.sendMessage(ChatColor.RED + "[JoinMessage] Specify: Player");
								return true;
							}
						}
					}
					if(args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help")){
						//Checks Permission
						if(sender.hasPermission("jm.help")){
							if(args.length == 1 || args[1].equalsIgnoreCase("1")){
								sender.sendMessage(ChatColor.GREEN + "JoinMessage Help: Page 1 of 4");
								sender.sendMessage(ChatColor.GREEN + "Cmd: /jm set [join/quit] [message]");
								sender.sendMessage(ChatColor.GREEN + "Desc: Sets Global Join or Quit Message.");
								sender.sendMessage(ChatColor.GREEN + "Cmd: /jm [group] set [join/quit] [message]");
								sender.sendMessage(ChatColor.GREEN + "Desc: Sets Group Join or Quit Message.");
								return true;
							}
							if(args[1].equalsIgnoreCase("2")){
								sender.sendMessage(ChatColor.GREEN + "JoinMessage Help: Page 2 of 4");
								sender.sendMessage(ChatColor.GREEN + "Cmd: /jm [group] prefix [prefix]");
								sender.sendMessage(ChatColor.GREEN + "Desc: Sets Group Prefix.");
								sender.sendMessage(ChatColor.GREEN + "Cmd: /jm [group] suffix [suffix]");
								sender.sendMessage(ChatColor.GREEN + "Desc: Sets Group Suffix.");
								return true;
							}
							if(args[1].equalsIgnoreCase("3")){
								sender.sendMessage(ChatColor.GREEN + "JoinMessage Help: Page 3 of 4");
								sender.sendMessage(ChatColor.GREEN + "Cmd: /jm [player] set [join/quit] [message]");
								sender.sendMessage(ChatColor.GREEN + "Desc: Sets Player Join or Quit Message.");
								sender.sendMessage(ChatColor.GREEN + "Cmd: /jm [player] prefix [prefix]");
								sender.sendMessage(ChatColor.GREEN + "Desc: Sets Player Preffix.");
								return true;
							}
							if(args[1].equalsIgnoreCase("4")){
								sender.sendMessage(ChatColor.GREEN + "JoinMessage Help: Page 4 of 4");
								sender.sendMessage(ChatColor.GREEN + "Cmd: /jm [player] suffix [suffix]");
								sender.sendMessage(ChatColor.GREEN + "Desc: Sets Player Suffix.");
								sender.sendMessage(ChatColor.GREEN + "Cmd: /jm info");
								sender.sendMessage(ChatColor.GREEN + "Desc: Plugin Info (Author and Version)");
								return true;
							}
						}
					}
				}
			}
		return false;
	}
}
