package me.crafter.mc.choptreew;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Storage {
	
	private static Plugin plugin;
	
	public Storage(Plugin _plugin){
		plugin = _plugin;
		plugin.saveDefaultConfig();
	}
	
	public static int getHeightLimit(){
		return plugin.getConfig().getInt("heightlimit", 50);
	}
	
	public static int getLogLimit(){
		return plugin.getConfig().getInt("loglimit", 500);
	}
	
	public static int popLeaves(){
		return plugin.getConfig().getInt("popleaves", 2);
	}
	
	public static boolean enableSound(){
		return plugin.getConfig().getBoolean("sound", true);
	}
	
	public static boolean enableEffect(){
		return plugin.getConfig().getBoolean("effect", true);
	}
	
	public static int popInterval(){
		return plugin.getConfig().getInt("popinterval", -1);
	}
	
	@SuppressWarnings("deprecation")
	public static boolean isAllowed(ItemStack item){
		return (plugin.getConfig().getList("allowedtools").contains(item.getType().name()) ||
				plugin.getConfig().getList("allowedtools").contains(String.valueOf(item.getType().getId())));
	}

}
