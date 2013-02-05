package me.tacticalsk8er.JoinMessage;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	Events events = new Events(this);
	public Permission permission = null;
	
	private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }
	
	@Override
	public void onDisable() { }
	
	@Override
	public void onEnable() { 
		setupPermissions();
		this.getServer().getPluginManager().registerEvents(events, this);
		this.saveDefaultConfig();
	}
	
	
}
