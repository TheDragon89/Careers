package org.blockface.careers.events;

import org.blockface.careers.jobs.Job;
import org.blockface.careers.jobs.JobsManager;
import org.blockface.careers.locale.Logging;
import org.blockface.careers.util.Tools;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;
import sun.rmi.runtime.Log;

public class BlockEvent extends BlockListener{

       @Override
       public void onBlockBreak(BlockBreakEvent event){
           int bid = event.getBlock().getTypeId();
           Player player = event.getPlayer();
             if((bid <= 16 && bid >= 14) || bid == 21 || bid == 56 || bid == 73 || bid == 74){
                if(CareersEvents.DoubleDrop(player)){
                    //could not figure out how to actually make the block drop twice. So I just added the item to the inventory.
                    if(bid == 73 || bid == 74)
                         player.getInventory().addItem(new ItemStack(Material.REDSTONE, 4));

                    else if(bid == 56)
                        player.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));

                    else if(bid == 21)
                        player.getInventory().addItem(new ItemStack(Material.INK_SACK, 4, (short) 0, (byte) 4));

                    else if(bid == 16)
                        player.getInventory().addItem(new ItemStack(Material.COAL, 1));

                    else
                        player.getInventory().addItem(new ItemStack(event.getBlock().getType(), 1));
                }
             }
       }

    public void onBlockPlace(BlockPlaceEvent event){
        if(event.getBlockPlaced().getType() == Material.IRON_ORE || event.getBlockPlaced().getType() == Material.GOLD_ORE){

            event.getBlock().setType(Material.AIR);
            event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() + 1);

        }


    }


}
