package me.crafter.mc.choptreew;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Storage {
	
	private static Plugin plugin;
	private static List<String> off;
	
	
	public Storage(Plugin _plugin){
		plugin = _plugin;
		plugin.saveDefaultConfig();
		initializePlayer(plugin.getDataFolder());
		FileConfiguration offfile = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "player.yml"));
		off = offfile.getStringList("off");
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
	
	public static double moreDamageToTools(){
		return plugin.getConfig().getDouble("moredamagetotools", 0D);
	}
	
	public static boolean interruptIfToolWillBreak(){
		return plugin.getConfig().getBoolean("interruptiftoolwillbreak", true);
	}
	
	public static boolean considerToolEnchantment(){
		return plugin.getConfig().getBoolean("considertoolenchantment", true);
	}
	
	public static int popInterval(){
		return plugin.getConfig().getInt("popinterval", -1);
	}
	
	public static boolean isOff(Player p){
		return off.contains(p.getName());
	}
	
	public static void turn(Player p, boolean state){
		if (state){
			off.remove(p.getName());
		} else {
			if (!off.contains(p.getName())){
				off.add(p.getName());
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static boolean isAllowed(ItemStack item){
		return (plugin.getConfig().getList("allowedtools").contains(item.getType().name()) ||
				plugin.getConfig().getList("allowedtools").contains(String.valueOf(item.getType().getId())));
	}
	
	public static void saveOff(){
		FileConfiguration offfile = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "player.yml"));
		offfile.set("off", off);
		try {
			offfile.save(new File(plugin.getDataFolder(), "player.yml"));
		} catch (IOException e) {
		}
	}
	
	public static void initializePlayer(File folder){
		File item = new File(folder, "player.yml");
		if (!item.exists()){
			Bukkit.getLogger().info("[ChopTreeW] Creating player.yml");
			FileConfiguration itemyml = YamlConfiguration.loadConfiguration(item);
			List<String> def = new ArrayList<String>();
			def.add("player1");
			def.add("player2");
			def.add("player3");
			itemyml.set("off", def);
			try {
				itemyml.save(item);
			} catch (IOException e) {}
		}
	}

}
