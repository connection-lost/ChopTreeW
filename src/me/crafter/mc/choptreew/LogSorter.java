package me.crafter.mc.choptreew;

import java.util.Comparator;

import org.bukkit.block.Block;

public class LogSorter implements Comparator<Block> {
    @Override
    public int compare(Block o1, Block o2) {
        return (o1.getY() - o2.getY());
    }
}