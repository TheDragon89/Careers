package org.blockface.careers.managers;


import org.blockface.careers.jobs.Job;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class WolfManager {

    public static void buyWolf(Player buyer, Player seller, Job job) {

        //Hope this is right



            if(!EconomyManager.pay(buyer,seller,20,"a wolf")) return;
            Wolf wolf = (Wolf)buyer.getWorld().spawnCreature(buyer.getLocation(), CreatureType.WOLF);
            wolf.setOwner(buyer);

    }

}
