package me.tacticalsk8er.JoinMessage;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	Events events = new Events(this);
	
	@Override
	public void onDisable() { }
	
	@Override
	public void onEnable() { 
		this.getServer().getPluginManager().registerEvents(events, this);
		this.saveDefaultConfig();
	}
	
	
}
