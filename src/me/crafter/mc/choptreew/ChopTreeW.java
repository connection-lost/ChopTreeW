package me.crafter.mc.choptreew;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ChopTreeW extends JavaPlugin {

	public final ChopListener pl = new ChopListener();

	public void onEnable(){
    	getServer().getPluginManager().registerEvents(this.pl, this);
    	new Storage(this);
    }
	
	
    public void onDisable(){
    	Storage.saveOff();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, final String[] args){
    	if (cmd.getName().equals("choptree")){
    		if (args.length < 1){
    			sender.sendMessage(ChatColor.RED + "/choptree <on/off>");
    			return true;
    		}
    		if (args[0].equals("on")){
    			if (!(sender instanceof Player)){
    				sender.sendMessage("控制台你想干啥?");
    			}
    			Player p = (Player)sender;
    			p.sendMessage(ChatColor.GOLD + "[ChopTreeW] " + ChatColor.GREEN + "你开启了一键砍树");
    			Storage.turn(p, true);
    			return true;
    		} else if (args[0].equals("off")){
    			if (!(sender instanceof Player)){
    				sender.sendMessage("控制台你想干啥?");
    			}
    			Player p = (Player)sender;
    			p.sendMessage(ChatColor.GOLD + "[ChopTreeW] " + ChatColor.RED + "你关闭了一键砍树");
    			Storage.turn(p, false);
    			return true;
    		} else {
    			sender.sendMessage(ChatColor.RED + "/choptree <on/off>");
    			return true;
    		}
    	}
    	return true;
    }
	
}
