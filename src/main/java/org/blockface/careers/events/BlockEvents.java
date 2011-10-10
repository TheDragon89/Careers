package org.blockface.careers.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;

public class BlockEvents extends BlockListener{

       @Override
       public void onBlockBreak(BlockBreakEvent event){
           int bid = event.getBlock().getTypeId();
           Player player = event.getPlayer();
             if((bid <= 16 && bid >= 14) || bid == 21 || bid == 56 || bid == 73 || bid == 74){
                if(CareersEvents.DoubleDrop(player)){
                    ItemStack drop = new ItemStack(event.getBlock().getType(),1);
                    if(bid == 73 || bid == 74)
                        drop = new ItemStack(Material.REDSTONE, 4);
                    else if(bid == 56)
                        drop.setType(Material.DIAMOND);
                    else if(bid == 21)
                        drop = new ItemStack(Material.INK_SACK, 4, (short) 0, (byte) 4);
                    else if(bid == 16)
                        drop.setType(Material.COAL);
                    player.getWorld().dropItem(event.getBlock().getLocation(),drop);
                }
             }

           if(bid == 2 || bid == 3){
                if(CareersEvents.TreasureFinder(player)){
                    int chance = (int) (Math.random() * 1000);

                    if(chance%100 == 0)
                       player.getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.INK_SACK, 4, (short) 0, (byte) 2));

                    else if(chance%200 == 0)
                        player.getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT, 1));

                    else if(chance == 1000)
                         player.getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.DIAMOND, 1));

                    else
                     player.getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.GLOWSTONE_DUST, 1));

                }
           }
       }

    public void onBlockPlace(BlockPlaceEvent event){
        if(!event.getPlayer().isOp() && (event.getBlockPlaced().getTypeId() == 14 || event.getBlockPlaced().getTypeId() == 15)){

            event.getBlock().setType(Material.AIR);
            event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() + 1);

        }


    }

}
