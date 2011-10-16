package org.blockface.careers.managers;

import org.blockface.careers.Careers;
import org.blockface.careers.locale.Logging;
import org.bukkit.plugin.Plugin;

public class ChunkyVillageManager {

    private static boolean usingChunkyVillage = false;

    public static void loadChunky() {
        Plugin p = Careers.getInstance().getServer().getPluginManager().getPlugin("ChunkyVillage");
        if(p==null) {
            usingChunkyVillage = false;
            return;
        }
        usingChunkyVillage = true;
        Logging.info("Found ChunkyVillage.");
    }

    public static boolean usingChunkyVillage() {
        return usingChunkyVillage;
    }
}
