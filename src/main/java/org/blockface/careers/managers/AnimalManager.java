package org.blockface.careers.managers;


import org.blockface.careers.jobs.Job;
import org.bukkit.entity.*;

public class AnimalManager {

    public static void buyWolf(Player buyer, Player seller, Job job) {
            if(!EconomyManager.pay(buyer,seller,20,"a wolf")) return;
            Wolf wolf = (Wolf)buyer.getWorld().spawnCreature(buyer.getLocation(), CreatureType.WOLF);
            wolf.setOwner(buyer);
    }

    public static void SummonFarmAnimal(Player player, int type){
           if(type == 0)
              player.getWorld().spawnCreature(player.getLocation(), CreatureType.COW);
        if(type == 1)
               player.getWorld().spawnCreature(player.getLocation(), CreatureType.PIG);
        if(type == 2)
               player.getWorld().spawnCreature(player.getLocation(), CreatureType.SHEEP);
        else
               player.getWorld().spawnCreature(player.getLocation(), CreatureType.CHICKEN);
    }

}
