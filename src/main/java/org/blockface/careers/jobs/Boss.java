package org.blockface.careers.jobs;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Boss extends GenericJob {

    public Boss() {
        loadInfo();
    }

    public Boss(String player) {
        super(player, "Boss", "Bosses", ChatColor.GOLD, "You can't get this job.");
        loadInfo();
    }

    private void loadInfo() {
        for(ABILITIES abilities : ABILITIES.values()) {
            this.abilities.add(abilities);
        }
    }

    @Override
    public double getAbilityChance() {
        return 100;
    }

    @Override
    public void printInfo(Player player) {
        super.printInfo(player);
        player.sendMessage(ChatColor.DARK_GRAY + "| " + ChatColor.GREEN + "Health Regen Rate: " + getAbilityChance() + "%");
    }
}
