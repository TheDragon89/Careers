package org.blockface.careers.jobs;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Officer extends GenericJob{

    public Officer() {
        this("");
    }

    public Officer(String player) {
        super(player, "Officer", "Officers", ChatColor.BLUE, "Right click criminals to arrest.");
        loadInfo();
    }

    private void loadInfo() {
        this.abilities.add(ABILITIES.ARREST);
    }

    @Override
    public void printInfo(Player player) {
        super.printInfo(player);
        player.sendMessage(ChatColor.DARK_GRAY + "| " + ChatColor.GREEN + "Dodge Chance: " + getAbilityChance() + "%");
    }
}
