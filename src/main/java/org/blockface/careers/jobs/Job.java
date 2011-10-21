package org.blockface.careers.jobs;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public interface Job {

    public enum ABILITIES {
        HEAL,
        CURE,
        LOCKPICK,
        ANTI_MOB,
        KILL,
        POISON,
        DOUBLE_DROP,
        SUPER_BREAKER,
        TREASURE_FINDER,
        PICKPOCKET,
        ARREST,
        MIRACLE_GROW,
        GREEN_THUMB,
        CALL_TO_THE_WILD,
        TAME;


        @Override
        public String toString() {
           return (Character.toUpperCase(name().charAt(0)) + name().toLowerCase().substring(1)).replace("_", " ");
        }
    }

    public String getName();

    public String getFormattedName();

    public String getPluralName();

    public ChatColor getColor();

    public String getPlayer();

    public double getAbilityChance();

    public void setPlayer(String player);

    public Boolean hasAbility(ABILITIES type);

    public int getExperience();

    public void addExperience();

    public void setExperience(int amount);

    public void addExperience(int amount);

    public int getLevel();

    public void printInfo(Player player);

    public void applyTitle();

    public String getDescription();

}
