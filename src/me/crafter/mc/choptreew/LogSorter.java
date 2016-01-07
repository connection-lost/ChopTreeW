package me.crafter.mc.choptreew;

import java.util.Comparator;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class LogSorter implements Comparator<Block> {
	
	Block oni;
	
	public LogSorter(Block onis){
		oni = onis;
	}
	
    @Override
    public int compare(Block o1, Block o2) {
    	Location oniloc = oni.getLocation();
    	Location o1loc = o1.getLocation();
    	Location o2loc = o2.getLocation();
    	int onio1 = Math.abs(oniloc.getBlockX() - o1loc.getBlockX()) + 2*Math.abs(oniloc.getBlockY() - o1loc.getBlockY()) + Math.abs(oniloc.getBlockZ() - o1loc.getBlockZ());
    	int onio2 = Math.abs(oniloc.getBlockX() - o2loc.getBlockX()) + 2*Math.abs(oniloc.getBlockY() - o2loc.getBlockY()) + Math.abs(oniloc.getBlockZ() - o2loc.getBlockZ());
        return onio1 - onio2;
    }
}