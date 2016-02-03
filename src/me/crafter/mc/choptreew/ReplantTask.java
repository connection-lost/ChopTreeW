package me.crafter.mc.choptreew;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class ReplantTask implements Runnable {

	public Block block;
	public int logtype = 0;
	
	@SuppressWarnings("deprecation")
	public ReplantTask(Block log){
		block = log;
		switch (log.getType()){
		case LOG:
			logtype = log.getData() % 4;
			break;
		case LOG_2:
			logtype = log.getData() % 4 + 4;
			break;
		default:
			break;
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		if (block.getType() == Material.AIR){
			switch (block.getRelative(BlockFace.DOWN).getType()){
			case DIRT:
			case GRASS:
			case MYCEL:
				block.setType(Material.SAPLING);
				block.setData((byte)logtype);
				break;
			default:
				break;
			}
		}
	}

}
