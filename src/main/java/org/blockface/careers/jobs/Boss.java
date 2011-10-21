package org.blockface.careers.jobs;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Boss extends GenericJob {

    public Boss() {
        this("");
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
}
