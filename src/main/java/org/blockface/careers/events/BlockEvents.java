package org.blockface.careers.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;

public class BlockEvents extends BlockListener{

       @Override
       public void onBlockBreak(BlockBreakEvent event){
           int bid = event.getBlock().getTypeId();
           Player player = event.getPlayer();
             if((bid <= 16 && bid >= 12) || bid == 21 || bid == 56 || bid == 73 || bid == 74){
                if(CareersEvents.DoubleDrop(player)){
                    ItemStack drop = new ItemStack(event.getBlock().getType(),1);
                    if(bid == 73 || bid == 74)
                        drop.setType(Material.REDSTONE);
                    else if(bid == 56)
                        drop.setType(Material.DIAMOND);
                    else if(bid == 21)
                        drop.setType(Material.LAPIS_ORE);
                    else if(bid == 16)
                        drop.setType(Material.COAL);
                    player.getWorld().dropItemNaturally(event.getBlock().getLocation(),drop);
                }
             }
       }

}
