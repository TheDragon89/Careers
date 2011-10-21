package org.blockface.careers.jobs;


import org.bukkit.ChatColor;

public class Tamer extends GenericJob {

    public Tamer() {
        this("");
    }

    public Tamer(String player) {
        super(player, "Tamer", "Tamers", ChatColor.LIGHT_PURPLE, "Sell wolves.");
        loadInfo();
    }

    private void loadInfo() {
        this.abilities.add(ABILITIES.TAME);
        this.abilities.add(ABILITIES.PACKANIMAL);
    }

}