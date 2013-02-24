package me.tacticalsk8er.JoinMessage;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	Events events = new Events(this);
	YAML database;
	public Permission permission = null;
	public Chat chat = null;
	
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }
	
	private boolean setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }
	
	@Override
	public void onDisable() { }
	
	@Override
	public void onEnable() { 
		this.getServer().getPluginManager().registerEvents(events, this);
		this.saveDefaultConfig();
		this.database = new YAML(this, "players.yml");
		database.getConfig().options().header(
				"This is where all the players who have joined your server goes.\n" +
				"Join Message uses this to determine if a player is new or not.");
		database.saveConfig();
		this.getCommand("jm").setExecutor(new CommandJM(this));
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
            getLogger().warning("\n" +
            		"+--------------------------------------------------------------+\n" +
            		"|               SoftDependency Not Found - VAULT               |\n" +
            		"|     Vault is what makes some features of Join Message work   |\n" +
            		"|      DOWNLOAD - http://dev.bukkit.org/server-mods/vault/     |\n" +
            		"+--------------------------------------------------------------+");
        } else {
        	setupPermissions();
        	setupChat();
        	getServer().getLogger().info("[JoinMessage] Vault Detected!");
        }
	}
	
}
