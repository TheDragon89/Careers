package org.blockface.careers.jobs;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Tamer extends GenericJob {

    public Tamer() {
        loadInfo();
    }

    public Tamer(String player) {
        super(player);
        loadInfo();
    }

    private void loadInfo() {
        this.name = "Tamer";
        this.pluralName = "Tamers";
        this.abilities.add(ABILITIES.TAME);
        this.color = ChatColor.LIGHT_PURPLE;
    }

    @Override
    public void printInfo(Player player) {
        super.printInfo(player);
        player.sendMessage(ChatColor.DARK_GRAY + "| " + ChatColor.GREEN + "No Ability Yet: " + getAbilityChance() + "%");


    }

}