package org.blockface.careers.jobs;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Doctor extends GenericJob {

    public Doctor() {
        loadInfo();
    }

    public Doctor(String player) {
        super(player, "Doctor", "Doctors", ChatColor.DARK_PURPLE, "Heal players who right-click you.");
        loadInfo();
    }

    private void loadInfo() {
        this.abilities.add(ABILITIES.HEAL);
    }

    @Override
    public double getAbilityChance() {
        float level = this.getLevel();
        return Math.floor(100*(1-6/(level+6)));
    }

    @Override
    public void printInfo(Player player) {
        super.printInfo(player);
        player.sendMessage(ChatColor.DARK_GRAY + "| " + ChatColor.GREEN + "Health Regen Rate: " + getAbilityChance() + "%");
    }
}
