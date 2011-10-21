package org.blockface.careers.jobs;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Miner extends GenericJob {

    public Miner() {
        this("");
    }

    public Miner(String player) {
        super(player, "Miner", "Miners", ChatColor.AQUA, "Chance of finding extra ores.");
        loadInfo();
    }

    private void loadInfo() {
        this.abilities.add(ABILITIES.DOUBLE_DROP);
        this.abilities.add(ABILITIES.TREASURE_FINDER);
    }
}