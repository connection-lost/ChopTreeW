package me.crafter.mc.choptreew;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ChopListener implements Listener {
	
	// Check order: isLog -> isTool -> isTree
	@EventHandler(priority = EventPriority.MONITOR)
	public void onChopTree(BlockBreakEvent event){
		if (event.isCancelled()) return;
		if (ChopWorker.isLog(event.getBlock()) && ChopWorker.isTool(event.getPlayer().getItemInHand()) && ChopWorker.isTree(event.getBlock())){
			ChopWorker.chop(event.getBlock());
		}
	}

}


