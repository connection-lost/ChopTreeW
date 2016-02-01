package me.crafter.mc.choptreew;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class ChopListener implements Listener {
	
	// Check order: isLog -> isTool -> isTree
	@EventHandler(priority = EventPriority.MONITOR)
	public void onChopTree(BlockBreakEvent event){
		if (event.isCancelled()) return;
		ItemStack item = event.getPlayer().getItemInHand();
		if (ChopWorker.isLog(event.getBlock()) && ChopWorker.isTool(item)
				&& ChopWorker.checkPermission(event.getPlayer()) && !Storage.isOff(event.getPlayer())
				&& ChopWorker.isTree(event.getBlock())){
			Block block = event.getBlock();
			List<Block> logsl = ChopWorker.getLogsToPop(block);
			if (logsl.size() == 0) return;
			int logsamount = logsl.size();
			if (ChopWorker.checkDurability(event.getPlayer().getItemInHand(), logsamount)){
				ChopWorker.pop(logsl, block);
				if (item.getDurability() > item.getType().getMaxDurability()){
					event.getPlayer().setItemInHand(null);
				}
			} else {
			}
		}
	}

}


