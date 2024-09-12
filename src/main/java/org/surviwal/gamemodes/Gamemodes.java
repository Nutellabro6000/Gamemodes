package org.surviwal.gamemodes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.surviwal.gamemodes.HideNSeek.Commands.ScalesCommand;
import org.surviwal.gamemodes.HideNSeek.Commands.StartCommand;
import org.surviwal.gamemodes.HideNSeek.Listeners.IgnoreInvClick;
import org.surviwal.gamemodes.HideNSeek.Listeners.Scales;
import org.surviwal.gamemodes.KitPVP.Commands.ActiveKitCommand;
import org.surviwal.gamemodes.KitPVP.Commands.ArenaSystem;
import org.surviwal.gamemodes.KitPVP.Commands.KitCommand;
import org.surviwal.gamemodes.KitPVP.Commands.KitOld;

public final class Gamemodes extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.broadcastMessage(ChatColor.GREEN + "[Gamemodes]" + ChatColor.RESET + " Online");
        getCommand("activekit").setExecutor(new ActiveKitCommand());
        getCommand("Arena").setExecutor(new ArenaSystem());
        getCommand("kiteditor").setExecutor(new KitOld());
        getCommand("kit").setExecutor(new KitCommand());
        getCommand("scale").setExecutor(new ScalesCommand());
        getCommand("start").setExecutor(new StartCommand());
        Bukkit.getPluginManager().registerEvents(new ArenaSystem(), this);
        getServer().getPluginManager().registerEvents(new Scales(), this);
        getServer().getPluginManager().registerEvents(new IgnoreInvClick(), this);
        getServer().getPluginManager().registerEvents(new StartCommand(), this);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "team add HideNSeek");
        saveConfig();
    }
    @Override
    public void onDisable() {
        // Remove Later
        Bukkit.broadcastMessage(ChatColor.RED + "[Gamemodes]" + ChatColor.RESET + " Offline");
        // Plugin shutdown logic
    }
}
