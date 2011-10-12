package org.blockface.careers.jobs;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Thief extends GenericJob {

    public Thief() {
        this("");
    }

    public Thief(String player) {
        super(player, "Thief", "Thieves", ChatColor.GRAY, "Pickpocket and lockpick other players..");
        loadInfo();
    }

    private void loadInfo() {
        this.abilities.add(ABILITIES.LOCKPICK);
        this.abilities.add(ABILITIES.PICKPOCKET);
    }

    @Override
    public void printInfo(Player player) {
        super.printInfo(player);
        player.sendMessage(ChatColor.DARK_GRAY + "| " + ChatColor.GREEN + "Lock Pick Chance: " + getAbilityChance() + "%");
    }
}
