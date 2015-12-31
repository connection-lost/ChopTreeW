package me.crafter.mc.choptreew;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ChopTreeW extends JavaPlugin {

	public final ChopListener pl = new ChopListener();

	public void onEnable(){
    	getServer().getPluginManager().registerEvents(this.pl, this);
    }
	
	
    public void onDisable(){
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, final String[] args){
    	
    	return true;
    }
	
}
