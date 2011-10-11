package org.blockface.careers.commands;

import org.blockface.careers.jobs.GenericJob;
import org.blockface.careers.jobs.Job;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JobList implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        try {
            String[] classes = getResourceListing(GenericJob.class,"org/blockface/careers/jobs/");
            sender.sendMessage(ChatColor.DARK_GRAY + "|-----------------| " + ChatColor.GREEN + "Jobs" + ChatColor.DARK_GRAY + " |-----------------|");
            for(String c : classes) {
                if(c.length()<1) continue;
                if(c.contains("Job")) continue;
                Class clazz = Class.forName("org.blockface.careers.jobs."+c.substring(0,c.length()-6));
                if(clazz.isInterface()) continue;
                Job job = (Job)clazz.newInstance();
                sender.sendMessage(ChatColor.DARK_GRAY + "| " + job.getFormattedName() + " - " + ChatColor.WHITE + job.getDescription());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    String[] getResourceListing(Class clazz, String path) throws URISyntaxException, IOException {
      URL dirURL = clazz.getClassLoader().getResource(path);
      if (dirURL != null && dirURL.getProtocol().equals("file")) {
        /* A file path: easy enough */
        return new File(dirURL.toURI()).list();
      }

      if (dirURL == null) {
        /*
         * In case of a jar file, we can't actually find a directory.
         * Have to assume the same jar as clazz.
         */
        String me = clazz.getName().replace(".", "/")+".class";
        dirURL = clazz.getClassLoader().getResource(me);
      }

      if (dirURL.getProtocol().equals("jar")) {
        /* A JAR path */
        String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!")); //strip out only the JAR file
        JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
        Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
        Set<String> result = new HashSet<String>(); //avoid duplicates in case it is a subdirectory
        while(entries.hasMoreElements()) {
          String name = entries.nextElement().getName();
          if (name.startsWith(path)) { //filter according to the path
            String entry = name.substring(path.length());
            int checkSubdir = entry.indexOf("/");
            if (checkSubdir >= 0) {
              // if it is a subdirectory, we just return the directory name
              entry = entry.substring(0, checkSubdir);
            }
            result.add(entry);
          }
        }
        return result.toArray(new String[result.size()]);
      }

      throw new UnsupportedOperationException("Cannot list files for URL "+dirURL);
  }

}
