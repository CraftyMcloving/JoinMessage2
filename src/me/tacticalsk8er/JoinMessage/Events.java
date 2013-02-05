package me.tacticalsk8er.JoinMessage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener{
	
	public static Main plugin;
	
	public Events(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		//Initial Setup
		Player p = e.getPlayer();
		FileConfiguration config = plugin.getConfig();
		String message = config.getString("Message");
		String pName = p.getName();
		//Group Messages
		if(config.getBoolean("Groups")){
			String group = plugin.permission.getPrimaryGroup(p);
			message = config.getString("Group." + group + ".Message");
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
		message = message.replaceAll("%player%", pName);
		message = message.replaceAll("&((?i)[0-9a-fk-or])", "§$1");
		//Set Message
		e.setJoinMessage(message);
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
		message = message.replaceAll("%player%", pName);
		message = message.replaceAll("&((?i)[0-9a-fk-or])", "§$1");
		//Set Message
		e.setQuitMessage(message);
	}
}
