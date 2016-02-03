package me.crafter.mc.choptreew;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;

public class ChopTask implements Runnable{

	public List<Block> tochop;
	public int taskId;
	public boolean instant = false;
	public int lowesty;
	
	public ChopTask(List<Block> feed){
		tochop = feed;
		lowesty = tochop.get(0).getY();
	}
	
	public void setInstant(boolean instant_){
		instant = instant_;
	}
	
	public void feedId(int id){
		taskId = id;
	}
	
	int at = 0;
	
	@Override
	public void run() {
		if (instant){
			while (tochop.size() > 0){
				chop();
			}
			Bukkit.getScheduler().cancelTask(taskId);
			return;
		} else {
			if (tochop.size() > 0){
				chop();
			} else {
				Bukkit.getScheduler().cancelTask(taskId);
				return;
			}
		}
	}
	
	public void chop(){
		at ++;
		Block log = tochop.remove(0);
		if (log.getY() == lowesty && (ChopWorker.isLog(log) || log.getType() == Material.AIR) && Storage.autoReplant() > -1){
			int replantdelay = Storage.autoReplant();
			Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("ChopTreeW"), new ReplantTask(log), replantdelay);
		}
		if (ChopWorker.isLog(log)){
			log.breakNaturally();
			if (Storage.enableSound()) log.getWorld().playSound(log.getLocation(), Sound.DIG_WOOD, 0.95F, 0.6F + Math.min(0.02F*(float)(at), 1.8F));
			if (Storage.enableEffect()) log.getWorld().spigot().playEffect(log.getLocation(), Effect.TILE_BREAK, 17, 0, 0.3F, 0.3F, 0.3F, 0.12F, 32, 64);
		}
		boolean sounded = false;
		int r = Storage.popLeaves();
		for (int x = -r; x <= r; x ++){
			for (int y = 0; y <= r; y ++){
				for (int z = -r; z <= r; z ++){
					Block relative = log.getRelative(x, y, z);
					if (ChopWorker.isLeavesOrVines(relative)){
						relative.breakNaturally();
						relative.getWorld().spigot().playEffect(relative.getLocation(), Effect.TILE_BREAK, 18, 0, 0.3F, 0.3F, 0.3F, 0.12F, 8, 64);
						if (!sounded){
							sounded = true;
							relative.getWorld().playSound(relative.getLocation(), Sound.DIG_GRASS, 0.12F, 0.9F);
						}
					}
				}
			}
		}
	}
	
}
