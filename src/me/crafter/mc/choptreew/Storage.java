package me.crafter.mc.choptreew;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Storage {
	
	private Plugin plugin;
	
	public Storage(Plugin _plugin){
		plugin = _plugin;
		plugin.saveDefaultConfig();
	}
	
	public int getHeightLimit(){
		return plugin.getConfig().getInt("heightlimit");
	}
	
	public int getLogLimit(){
		return plugin.getConfig().getInt("heightlimit");
	}
	
	public int popLeaves(){
		return plugin.getConfig().getInt("popleaves");
	}
	
	public boolean enableSound(){
		return plugin.getConfig().getBoolean("sound");
	}
	
	public boolean enableEffect(){
		return plugin.getConfig().getBoolean("effect");
	}
	
	public int popInterval(){
		return plugin.getConfig().getInt("popinterval");
	}
	
	public boolean isAllowed(ItemStack item){
		return plugin.getConfig().getList("allowedtools").contains(item.getType().name());
	}

}
