package org.blockface.careers.jobs;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Knight extends GenericJob {

    public Knight() {
        loadInfo();
    }

    public Knight(String player) {
        super(player, "Knight", "Knights", ChatColor.YELLOW, "Kill mobs and be paid.");
        loadInfo();
    }

    private void loadInfo() {
        this.abilities.add(ABILITIES.ANTIMOB);
    }

    @Override
    public void printInfo(Player player) {
        super.printInfo(player);
        player.sendMessage(ChatColor.DARK_GRAY + "| " + ChatColor.GREEN + "KO Chance: " + getAbilityChance() + "%");
    }
}
