package org.blockface.careers.jobs;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Murderer extends GenericJob {
    public Murderer() {
        this("");
    }

    public Murderer(String player) {
        super(player, "Murderer", "Murderers", ChatColor.DARK_RED, "Kill other players.");
        loadInfo();
    }

    private void loadInfo() {
        this.abilities.add(ABILITIES.KILL);
    }
}
