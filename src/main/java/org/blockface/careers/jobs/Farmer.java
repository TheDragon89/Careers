package org.blockface.careers.jobs;

import org.bukkit.ChatColor;

public class Farmer extends GenericJob {

    public Farmer() {
        this("");
    }

    public Farmer(String player) {
        super(player, "Farmer", "Farmers", ChatColor.GREEN, "Grow crops.");
        loadInfo();
    }

    private void loadInfo() {
        this.abilities.add(ABILITIES.MIRACLE_GROW);
        this.abilities.add(ABILITIES.GREEN_THUMB);
        this.abilities.add(ABILITIES.CALL_TO_THE_WILD);
    }
}