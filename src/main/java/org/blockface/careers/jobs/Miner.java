package org.blockface.careers.jobs;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Miner extends GenericJob {

    public Miner() {
        loadInfo();
    }

    public Miner(String player) {
        super(player);
        loadInfo();
    }

    private void loadInfo() {
        this.name = "Miner";
        this.pluralName = "Miners";
        this.abilities.add(ABILITIES.DOUBLEDROP);
        this.abilities.add(ABILITIES.SUPERBREAKER);
        this.abilities.add(ABILITIES.TREASUREFINDER);
        this.color = ChatColor.AQUA;
    }

    @Override
    public void printInfo(Player player) {
        super.printInfo(player);
        player.sendMessage(ChatColor.DARK_GRAY + "| " + ChatColor.GREEN + "Double Drop Chance: " + getAbilityChance() + "%");
    }
}