package me.crafter.mc.choptreew;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class ChopWorker {
	
	// Check order: isLog -> isTool -> isTree
	public BlockFace[][] blockfaces = {{BlockFace.UP}, 
			{BlockFace.UP, BlockFace.NORTH}, {BlockFace.UP, BlockFace.EAST}, {BlockFace.UP, BlockFace.SOUTH}, {BlockFace.UP, BlockFace.WEST},
			{BlockFace.UP, BlockFace.NORTH_EAST}, {BlockFace.UP, BlockFace.SOUTH_EAST}, {BlockFace.UP, BlockFace.NORTH_WEST}, {BlockFace.UP, BlockFace.SOUTH_WEST},
			{BlockFace.NORTH}, {BlockFace.EAST}, {BlockFace.WEST}, {BlockFace.SOUTH}};
	
	public boolean isTree(Block block){
		if (block == null) return false; // TODO - is it necessary?
		if (!isLog(block)) return false;
		Block nextblock = block.getRelative(BlockFace.SELF);
		int searchlimit = 50;
		List<Block> searched = new ArrayList<Block>();
		while (searchlimit > 0){
			// Decrement limit first
			searchlimit --;
			// Prevent loop back to log
			if (!searched.contains(nextblock)){
				searched.add(nextblock);
				// Next log
				boolean found = false;
				for (BlockFace[] blockfacess : blockfaces){
					Block newblock = nextblock;
					for (BlockFace blockfacesss : blockfacess){
						newblock = nextblock.getRelative(blockfacesss);
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
			// TODO check search limit at least 2?
			// TODO verify newblock is the new block
			// TODO check newblock leaf
			
			
		}
		
		
		return false;
	}
	
	public boolean isLog(Block block){
		switch (block.getType()){
		case LOG:
		case LOG_2:
			return true;
		default:
			return false;
		}
	}
	
	public boolean isLeaves(Block block){
		switch (block.getType()){
		case LEAVES:
		case LEAVES_2:
			return true;
		default:
			return false;
		}
	}
	
	
}
