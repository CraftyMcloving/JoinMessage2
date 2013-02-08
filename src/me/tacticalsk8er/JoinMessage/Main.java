package me.tacticalsk8er.JoinMessage;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	Events events = new Events(this);
	public Permission permission = null;
	public Chat chat = null;
	
	@Override
	public void onDisable() { }
	
	@Override
	public void onEnable() { 
		this.getServer().getPluginManager().registerEvents(events, this);
		this.saveDefaultConfig();
		if (!Vault()) {
            getLogger().warning("\n" +
            		"+--------------------------------------------------------------+\n" +
            		"|               SoftDependency Not Found - VAULT               |\n" +
            		"|     Vault is what makes some features of Join Message work   |\n" +
            		"|      DOWNLOAD - http://dev.bukkit.org/server-mods/vault/     |\n" +
            		"+--------------------------------------------------------------+");
        }
	}
	
	public boolean Vault(){
		if(getServer().getPluginManager().getPlugin("Vault") == null){
			return false;
		} else {
			RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
	        if (chatProvider != null) {
	            chat = chatProvider.getProvider();
	        }
	        
	        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
	        if (permissionProvider != null) {
	            permission = permissionProvider.getProvider();
	        }
	        return (permission != null && chat != null);
		}
	}
	
	
}
