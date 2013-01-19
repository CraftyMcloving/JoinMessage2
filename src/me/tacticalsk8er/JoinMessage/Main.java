package me.tacticalsk8er.JoinMessage;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	Events Events = new Events(this);
	
	@Override
	public void onDisable() { }
	
	@Override
	public void onEnable() { 
		this.getServer().getPluginManager().registerEvents(Events, this);
	}
	
	
}
