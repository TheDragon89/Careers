package org.blockface.careers.managers;

import org.blockface.careers.Careers;
import org.blockface.careers.locale.Logging;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.getchunky.chunkyvillage.objects.ChunkyResident;
import org.getchunky.chunkyvillage.objects.ChunkyTown;

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

    public static ChunkyTown.Stance getStance(Player a, Player b) {
        ChunkyResident ca = new ChunkyResident(a);
        ChunkyResident cb = new ChunkyResident(b);
        if(ca.getTown() == null) return ChunkyTown.Stance.NEUTRAL;
        return ca.getTown().getEffectiveStance(cb.getTown());
    }
}
