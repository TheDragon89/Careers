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
        this.abilities.add(ABILITIES.DOUBLEDROP);
        this.abilities.add(ABILITIES.SUPERBREAKER);
        this.abilities.add(ABILITIES.TREASUREFINDER);
    }

    @Override
    public void printInfo(Player player) {
        super.printInfo(player);
        player.sendMessage(ChatColor.DARK_GRAY + "| " + ChatColor.GREEN + "Double Drop Chance: " + getAbilityChance() + "%");
    }
}