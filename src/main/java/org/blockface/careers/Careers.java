package org.blockface.careers;

import org.blockface.bukkitstats.CallHome;
import org.blockface.careers.commands.*;
import org.blockface.careers.config.Config;
import org.blockface.careers.events.BlockEvents;
import org.blockface.careers.events.EntityEvents;
import org.blockface.careers.events.PlayerEvents;
import org.blockface.careers.locale.Language;
import org.blockface.careers.locale.Logging;
import org.blockface.careers.managers.ChunkyVillageManager;
import org.blockface.careers.managers.EconomyManager;
import org.blockface.careers.managers.HellManager;
import org.blockface.careers.managers.JailManager;
import org.blockface.careers.persistance.PersistanceManager;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Careers extends JavaPlugin {

    private static Careers plugin;

    public void onDisable() {
        PersistanceManager.unload();
        System.out.println(this + " is now disabled!");
    }

    public void onEnable() {
        plugin = this;

        //Call Home
        CallHome.load(this);

        try {
            //Load Config
            Config.load();
            //Load Language
            Language.load();
            //Load Persistance
            PersistanceManager.load();

            //Load Jail
            JailManager.load();

            //Load Logger
            Logging.load(this);

            //Load Economy
            EconomyManager.load(this);

            //Load Hell
            HellManager.load();

            //Hook ChunkyVillage
            ChunkyVillageManager.loadChunky();

        } catch (IOException e) {
            e.printStackTrace();
        }

        setCommands();
        registerEvents();

        System.out.println(this + " is now enabled!");
    }

    private void setCommands() {
        getCommand("setjob").setExecutor(new SetJob());
        getCommand("jobinfo").setExecutor(new Info());
        getCommand("addexp").setExecutor(new AddExp());
        getCommand("setjail").setExecutor(new SetJail());
        getCommand("joblist").setExecutor(new JobList());
    }

    private void registerEvents() {
        PluginManager pm = this.getServer().getPluginManager();

        //Player Events
        PlayerEvents pe = new PlayerEvents();
        pm.registerEvent(Event.Type.PLAYER_JOIN,pe, Event.Priority.Normal,this);
        pm.registerEvent(Event.Type.PLAYER_CHAT,pe, Event.Priority.Normal,this);
        pm.registerEvent(Event.Type.PLAYER_INTERACT,pe, Event.Priority.Highest,this);
        pm.registerEvent(Event.Type.PLAYER_INTERACT_ENTITY,pe, Event.Priority.Highest,this);
        pm.registerEvent(Event.Type.PLAYER_TELEPORT,pe, Event.Priority.Highest,this);
        pm.registerEvent(Event.Type.PLAYER_RESPAWN,pe, Event.Priority.Highest,this);

        //Entity Events
        EntityEvents ee = new EntityEvents();
        pm.registerEvent(Event.Type.ENTITY_DAMAGE,ee, Event.Priority.Highest,this);
        pm.registerEvent(Event.Type.ENTITY_REGAIN_HEALTH,ee, Event.Priority.Normal,this);
        pm.registerEvent(Event.Type.ENTITY_DEATH,ee, Event.Priority.Normal,this);

        //Block Events
        BlockEvents be = new BlockEvents();
        pm.registerEvent(Event.Type.BLOCK_BREAK, be, Event.Priority.Highest, this);
        pm.registerEvent(Event.Type.BLOCK_PLACE, be, Event.Priority.Normal, this);
    }

    public static Careers getInstance() {
        return plugin;
    }


}
