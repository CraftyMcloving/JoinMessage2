package me.tacticalsk8er.JoinMessage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener{
	
	public static Main plugin;
	
	public Events(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		FileConfiguration config = plugin.getConfig();
		String message = config.getString("Message");
		String pName = p.getName();
		message.replace("%player%", pName);
		
		e.setJoinMessage(message);
		plugin.getLogger().info(message);
	}
}
