package org.surviwal.gamemodes.KitPVP.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class ActiveKitCommand implements CommandExecutor {
    Plugin plugin = Bukkit.getPluginManager().getPlugin("Gamemodes");
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (commandSender.hasPermission("SAS.Command.Kit")){
            if (args.length == 1) {
                if (args[0].equals("1") || args[0].equals("2") || args[0].equals("3")) {
                    String name = commandSender.getName();
                    plugin.getConfig().set("ActiveKit" + name, args[0]);
                    plugin.saveConfig();
                } else {
                    commandSender.sendMessage(ChatColor.GREEN + "[SAS] " + ChatColor.RESET +  "Bitte gib einen Wert zwischen 1-3 an");
                    return false;
                }
            }
        }
        return  true;
    }
}