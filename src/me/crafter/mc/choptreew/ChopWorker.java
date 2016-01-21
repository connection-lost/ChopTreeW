package me.crafter.mc.choptreew;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class ChopWorker {
	
	public static BlockFace[][] logfaces = {{BlockFace.UP}, 
			{BlockFace.UP, BlockFace.NORTH}, {BlockFace.UP, BlockFace.EAST}, {BlockFace.UP, BlockFace.SOUTH}, {BlockFace.UP, BlockFace.WEST},
			{BlockFace.UP, BlockFace.NORTH_EAST}, {BlockFace.UP, BlockFace.SOUTH_EAST}, {BlockFace.UP, BlockFace.NORTH_WEST}, {BlockFace.UP, BlockFace.SOUTH_WEST},
			{BlockFace.NORTH}, {BlockFace.EAST}, {BlockFace.WEST}, {BlockFace.SOUTH}, 
			{BlockFace.NORTH_EAST}, {BlockFace.SOUTH_EAST}, {BlockFace.NORTH_WEST}, {BlockFace.SOUTH_WEST}};
	public static BlockFace[] leaffaces = {BlockFace.UP, BlockFace.NORTH, BlockFace.EAST, BlockFace.WEST, BlockFace.SOUTH};
	
	public static boolean isTree(Block block){
		if (!isLog(block)) return false;
		Block nextblock = block.getRelative(BlockFace.SELF);
		int searchlimit = Storage.getHeightLimit();
		List<Block> searched = new ArrayList<Block>();
		while (searchlimit > 0){
			// Decrement limit first
			searchlimit --;
			// Prevent loop back to log
			if (!searched.contains(nextblock)){
				searched.add(nextblock);
				// Next log
				boolean found = false;
				for (BlockFace[] blockfacess : logfaces){
					Block newblock = nextblock;
					for (BlockFace blockfacesss : blockfacess){
						newblock = newblock.getRelative(blockfacesss);
					}
					if (isLog(newblock)){
						nextblock = newblock;
						found = true;
						break;
					}
				}
				if (found) continue;
			}
			
			// Fall to here means not more upper block
			// nextblock is the current highest block
			int leavescount = 0;
			for (BlockFace leafface : leaffaces){
				if (isLeaves(nextblock.getRelative(leafface))){
					leavescount ++;
				}
			}
			if (leavescount >= 2){
				return true;
			} else {
				// Pine fix
				for (BlockFace leafface : leaffaces){
					if (isLeaves(nextblock.getRelative(BlockFace.UP).getRelative(leafface))){
						leavescount ++;
					}
				}
				if (leavescount >= 4){
					return true;
				} else {
					return false;	
				}
			}
		}
		return false;
	}
	
	public static void chop(Block block){
		// Already went through isTree, chop the tree.
		Set<Block> logs = new HashSet<Block>();
		Set<Block> logsundone = new HashSet<Block>();
		logsundone.add(block);
		while (logsundone.size() > 0){
			Block log = logsundone.iterator().next();
			logsundone.remove(log);
			logs.add(log);
			
			for (BlockFace[] blockfacess : logfaces){
				Block newblock = log;
				for (BlockFace blockfacesss : blockfacess){
					newblock = newblock.getRelative(blockfacesss);
				}
				if (isLog(newblock)){
					if (!logs.contains(newblock) && !logsundone.contains(newblock)){
						logsundone.add(newblock);
					}
				}
			}
			if (logsundone.size() + logs.size() > Storage.getLogLimit()){
				return;
			}
		}
		List<Block> logsl = new ArrayList<Block>(logs);
		Collections.sort(logsl, new LogSorter(block));
		ChopTask choptask = new ChopTask(logsl);
		
		long speedo = 2L;
		if (logsl.size() > 50) speedo = 1L;
		if (Storage.popInterval() != -1){
			speedo = Storage.popInterval();
		}
		
		BukkitTask task = Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("ChopTreeW"), choptask, 0L, speedo);
		choptask.feedId(task.getTaskId());
	}
	
	public static boolean isLog(Block block){
		switch (block.getType()){
		case LOG:
		case LOG_2:
			return true;
		default:
			return false;
		}
	}
	
	public static boolean isLeaves(Block block){
		switch (block.getType()){
		case LEAVES:
		case LEAVES_2:
			return true;
		default:
			return false;
		}
	}
	
	public static boolean isLeavesOrVines(Block block){
		switch (block.getType()){
		case LEAVES:
		case LEAVES_2:
		case VINE:
			return true;
		default:
			return false;
		}
	}
	
	public static boolean checkPermission(Player p){
		if (p.hasPermission("choptree.chop")){
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isTool(ItemStack item){
		if (item == null) return false;
		return Storage.isAllowed(item);
	}
	
	
}
