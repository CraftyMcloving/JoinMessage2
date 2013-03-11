package me.tacticalsk8er.JoinMessage;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener{
	
	public static Main plugin;
	
	public Events(Main instance){
		plugin = instance;
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPlayerLoginHIGHEST(PlayerLoginEvent e){
		Player p = e.getPlayer();
		String message = "";
		if(e.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST){
			if(plugin.getConfig().getBoolean("UseWhitelistMessage")){
				//Formatting
				message = plugin.getConfig().getString("WhitelistMessage");
				message = message.replaceAll("%player%", p.getName());
				//Set Message
				e.setKickMessage(message);
			}
		} else if(e.getResult() == PlayerLoginEvent.Result.KICK_FULL){
			if(plugin.getConfig().getBoolean("UseFullServerMessage")){
				message = plugin.getConfig().getString("FullServerMessage");
				message = message.replaceAll("%player%", p.getName());
				e.setKickMessage(message);
			}
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		//Initial Setup
		Boolean SendMotd = false;
		Player p = e.getPlayer();
		FileConfiguration config = plugin.getConfig();
		String message = config.getString("Message");
		String pName = p.getName();
		//Group Messages
		if(config.getBoolean("Groups")){
			String group = plugin.permission.getPrimaryGroup(p);
			message = config.getString("Group." + group + ".Message");
		}
		//Player Messages
		if(config.getBoolean("Players")){
			message = config.getString("Player." + pName + ".Message");
		}
		//Checks if new player
		if(!plugin.database.getConfig().contains(pName)){
			plugin.database.getConfig().set(pName + ".FirstJoin", false);
			plugin.database.saveConfig();
			//First Join Messages
			if(config.getBoolean("UseNewPlayerMessage")){
				message = config.getString("NewPlayerMessage");
			}
			//First Join Motd
			if(config.getBoolean("UseNewPlayerMotd")){
				SendMotd = true;
			}
		} else if(plugin.database.getConfig().getBoolean(pName + ".FirstJoin")){
			plugin.database.getConfig().set(pName + ".FirstJoin", false);
			//First Join Messages
			if(config.getBoolean("UseNewPlayerMessage")){
				message = config.getString("NewPlayerMessage");
			}
			//First Join Motd
			if(config.getBoolean("UseNewPlayerMotd")){
				SendMotd = true;
			}
		}
		//Add Prefix
		if(config.getBoolean("Prefix")){
			String prefix = plugin.chat.getPlayerPrefix(p);
			prefix = prefix.concat(pName);
			pName = prefix;
		}
		//Add Suffix
		if(config.getBoolean("Suffix")){
			String suffix = plugin.chat.getPlayerSuffix(p);
			pName = pName.concat(suffix);
		}
		//Formating
		if(!(message.equals(""))){
			message = message.replaceAll("%player%", pName);
			message = ChatColor.translateAlternateColorCodes('&', message);
		}
		//Set Message
		if(p.hasPermission("jm.silentjoin")){
			e.setJoinMessage("");
		} else {
			e.setJoinMessage(message);
		}
		//Sends New Player Motd
		if(SendMotd){
			String motd = config.getString("NewPlayerMotd");
			//Formating
			if(!(motd.equals(""))){
				motd = message.replaceAll("%player%", pName);
				motd = ChatColor.translateAlternateColorCodes('&', motd);
			}
			//Send Motd
			p.sendMessage(motd);
		}
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e){
		//Initial Setup
		Player p = e.getPlayer();
		FileConfiguration config = plugin.getConfig();
		String message = config.getString("QuitMessage");
		String pName = p.getName();
		//Group Messages
		if(config.getBoolean("Groups")){
			String group = plugin.permission.getPrimaryGroup(p);
			message = config.getString("Group." + group + ".QuitMessage");
		}
		//Player Messages
		if(config.getBoolean("Players")){
			message = config.getString("Player." + pName + ".QuitMessage");
		}
		//Add Prefix
		if(config.getBoolean("Prefix")){
			String prefix = plugin.chat.getPlayerPrefix(p);
			prefix = prefix.concat(pName);
			pName = prefix;
		}
		//Add Suffix
		if(config.getBoolean("Suffix")){
			String suffix = plugin.chat.getPlayerSuffix(p);
			pName = pName.concat(suffix);
		}
		//Formating
		if(!(message.equals(""))){
			message = message.replaceAll("%player%", pName);
			message = ChatColor.translateAlternateColorCodes('&', message);
		}
		//Set Message
		if(p.hasPermission("jm.silentquit")){
			e.setQuitMessage("");
		} else {
			e.setQuitMessage(message);
		}
	}
}
