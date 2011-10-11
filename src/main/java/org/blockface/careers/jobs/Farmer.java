package org.blockface.careers.jobs;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Farmer extends GenericJob {

    public Farmer() {
        loadInfo();
    }

    public Farmer(String player) {
        super(player, "Farmer", "Farmers", ChatColor.GREEN, "Grow crops.");
        loadInfo();
    }

    private void loadInfo() {
        this.abilities.add(ABILITIES.MIRACLEGROW);
        this.abilities.add(ABILITIES.GREENTHUMB);
    }

    @Override
    public void printInfo(Player player) {
        super.printInfo(player);
        player.sendMessage(ChatColor.DARK_GRAY + "| " + ChatColor.GREEN + "Miracle Grow Chance: " + getAbilityChance() + "%");
    }
}