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
		return plugin.getConfig().getInt("heightlimit");
	}
	
	public static int getLogLimit(){
		return plugin.getConfig().getInt("loglimit");
	}
	
	public static int popLeaves(){
		return plugin.getConfig().getInt("popleaves");
	}
	
	public static boolean enableSound(){
		return plugin.getConfig().getBoolean("sound");
	}
	
	public static boolean enableEffect(){
		return plugin.getConfig().getBoolean("effect");
	}
	
	public static int popInterval(){
		return plugin.getConfig().getInt("popinterval");
	}
	
	public static boolean isAllowed(ItemStack item){
		return plugin.getConfig().getList("allowedtools").contains(item.getType().name());
	}

}
