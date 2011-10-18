package org.blockface.careers.events;

import org.blockface.careers.config.Config;
import org.blockface.careers.jobs.*;
import org.blockface.careers.locale.Language;
import org.blockface.careers.managers.*;
import org.blockface.careers.objects.Crime;
import org.blockface.careers.util.Tools;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.getchunky.chunky.ChunkyManager;
import org.getchunky.chunky.object.ChunkyPlayer;
import org.getchunky.chunkyvillage.ChunkyTownManager;
import org.getchunky.chunkyvillage.objects.ChunkyTown;

public class CareersEvents {

    public static boolean canSwitch(Player player) {
        if(JailManager.isJailed(player)) return false;
        Job job = JobsManager.getJob(player);
        if(!job.hasAbility(Job.ABILITIES.LOCKPICK)) return false;
        Boolean res = Tools.randBoolean(job.getAbilityChance());
        if(res) {
            job.addExperience();
            Language.THEFT_SUCCEEDED.good(player);
        }
        else {
            Language.THEFT_FAILED.bad(player);
            player.damage(Config.getThiefDamage());
        }
        CrimeManager.alertWitnesses(player,player, Crime.TYPE.THEFT);
        return res;
    }

    public static boolean canPVP(Player attacker, Player victim) {
        //Allow if enemy towns.
        if(ChunkyVillageManager.usingChunkyVillage()) {
            ChunkyPlayer a = ChunkyManager.getChunkyPlayer(attacker);
            ChunkyPlayer b = ChunkyManager.getChunkyPlayer(victim);
            if(ChunkyTownManager.getStance(a,b) != ChunkyTown.Stance.ALLY) return true;
        }
        if(!Tools.isNight(attacker.getLocation())) return false;
        Job ja = JobsManager.getJob(attacker);
        Job jv = JobsManager.getJob(victim);

        //Return if cannot kill
        if(!ja.hasAbility(Job.ABILITIES.KILL) && !ProvokeManager.isProvoker(attacker,victim)) return false;
        if(ja.hasAbility(Job.ABILITIES.KILL)) {
            if(Tools.randBoolean(ja.getAbilityChance())) {
                victim.damage(2);
                Language.CRITICAL_HIT.good(attacker);
            }}
        //Try to dodge if Officer
        if(jv.hasAbility(Job.ABILITIES.ARREST)) {
            if(!CrimeManager.isWanted(attacker.getName())) CrimeManager.addWanted(attacker.getName(), Crime.TYPE.ASSAULT);
            if(Tools.randBoolean(jv.getAbilityChance())) {
                Language.DODGED.good(victim);
                Language.WAS_DODGED.bad(attacker);
                return false;
            }
        }

        //Log provoke
        ProvokeManager.addProvoker(victim,attacker);
        return true;
    }

    public static void onPlayerDeath(Player attacker, Player victim) {
        Job ja = JobsManager.getJob(attacker);
        if(ja instanceof Murderer) {
            ja.addExperience();
            CrimeManager.alertWitnesses(attacker,victim, Crime.TYPE.MURDER);
        }
        EconomyManager.payAll(victim,attacker);
    }

    public static void onPoke(Player player, Player rightClicked) {
        Job jp = JobsManager.getJob(player);
        Job jrc = JobsManager.getJob(rightClicked);

        //Police Arrest
        if(jp.hasAbility(Job.ABILITIES.ARREST) && CrimeManager.isWanted(rightClicked.getName())) {
            JailManager.arrestPlayer(rightClicked,player);
            jp.addExperience();
            return;}

        //Poison
        if(jp.hasAbility(Job.ABILITIES.POISON) && (player.getItemInHand().getType().equals(Material.BROWN_MUSHROOM) || player.getItemInHand().getType().equals(Material.RED_MUSHROOM)) && !PoisonManager.isPoisoned(rightClicked)) {
            PoisonManager.poisonPlayer(rightClicked,player, jp);
            return;}

        //Pickpocket
        if(!CrimeManager.isWanted(player.getName()) && jp.hasAbility(Job.ABILITIES.PICKPOCKET) && !(jrc instanceof Doctor)){
        	pickpocket(player, rightClicked);
            return;}

        //Heal
        if(jrc.hasAbility(Job.ABILITIES.HEAL)) {
            HealthManager.healPlayer(player,rightClicked,jrc);}

        //Tame
        if(jrc.hasAbility(Job.ABILITIES.TAME)) {
            WolfManager.buyWolf(player,rightClicked,jrc);}
        
    }
    
    private static void pickpocket(Player thief, Player mark){
    	Job job = JobsManager.getJob(thief);
    	Boolean res = Tools.randBoolean(job.getAbilityChance()/2);
    	if(res) {
            job.addExperience();
            Language.PICKPOCKETED.good(thief,mark.getName());
            EconomyManager.pocketpicked(thief, mark);
        }
        else {
            Language.FAILED_PICKPOCKET.bad(thief);
            thief.damage(Config.getThiefDamage());
            CrimeManager.addWanted(thief.getName(), Crime.TYPE.THEFT);
        }
    	
    }

    public static void onMobDamage(Entity entity, Player damager, int damage) {
        Job jd = JobsManager.getJob(damager.getName());
        if(!jd.hasAbility(Job.ABILITIES.ANTIMOB) || !(entity instanceof Creature)) return;
        Creature creature = (Creature)entity;
        if(creature.getHealth() < 0) return;
        if(Tools.randBoolean(jd.getAbilityChance())) creature.damage(1000);
        if(creature.getHealth() - damage < 0) {
            EconomyManager.payWage(damager, Config.getKnightWage(), "killing a mob.");
            jd.addExperience();}

    }

    public static boolean DoubleDrop(Player player){
        Job jd = JobsManager.getJob(player.getName());
        if(!jd.hasAbility(Job.ABILITIES.DOUBLEDROP)) return false;

        if(Tools.randBoolean(jd.getAbilityChance())){
            jd.addExperience();
            Language.DOUBLE_DROP.good(player);
            return true;
        }

        return false;
    }

    public static void GreenThumb(Player player, Block block){
        Job jd = JobsManager.getJob(player.getName());
        if(!jd.hasAbility(Job.ABILITIES.GREENTHUMB)) return;
        int blockid = block.getTypeId();
        player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
        if(Tools.randBoolean(jd.getAbilityChance())){
               if(blockid == 3)
                block.setTypeId(2);
               if(blockid == 4)
                   block.setTypeId(48);
               if(blockid == 98)
                   block.setData((byte) 1); //not sure if this will work
            Language.GREEN_THUMB.good(player);
            jd.addExperience();
        }
        else
        Language.GREEN_THUMB_FAILED.bad(player);

    }

    public static void MiracleGrow(Player player, Block block, int id){
        Job jd = JobsManager.getJob(player.getName());
        if(!jd.hasAbility(Job.ABILITIES.MIRACLEGROW)) return;
          if(Tools.randBoolean(jd.getAbilityChance())){
              if(id == 295)
                     block.getRelative(BlockFace.UP).setTypeIdAndData(59, (byte) 7, false);
              else if(id == 364)
                     block.getRelative(BlockFace.UP).setTypeIdAndData(104, (byte) 7, false);
              else
                    block.getRelative(BlockFace.UP).setTypeIdAndData(105, (byte) 7, false);
              Language.MIRACLE_GROW.good(player);
              jd.addExperience();
          }
    }

    public static boolean TreasureFinder(Player player){
       Job jd = JobsManager.getJob(player.getName());
        if(!jd.hasAbility(Job.ABILITIES.TREASUREFINDER)) return false;
          if(Tools.randBoolean(jd.getAbilityChance())){
            jd.addExperience();
            Language.TREASURE_FINDER.good(player);
            return true;
        }
        return false;
    }
}