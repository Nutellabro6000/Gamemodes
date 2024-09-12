package org.surviwal.gamemodes.HideNSeek.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ScalesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (commandSender.hasPermission("hidenseek.command.scale")) {
            if (args.length == 0) {
                Player player = (Player) commandSender;
                String PlayerName = player.getName();
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "give " + PlayerName + " amethyst_shard[item_name='{\"color\":\"green\",\"text\":\"Scale: 1\"}',lore=['\" \"','{\"color\":\"gray\",\"italic\":false,\"text\":\"Rightclick to reset\"}','{\"color\":\"gray\",\"italic\":false,\"text\":\"your scale\"}'],fire_resistant={},custom_model_data=4] 1");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "give " + PlayerName + " amethyst_shard[item_name='{\"color\":\"yellow\",\"text\":\"Scale: 0.5\"}',lore=['\" \"','{\"color\":\"gray\",\"italic\":false,\"text\":\"Rightclick to set\"}','{\"color\":\"gray\",\"italic\":false,\"text\":\"your scale to 0.5.\"}'],fire_resistant={},custom_model_data=5] 1");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "give " + PlayerName + " amethyst_shard[item_name='{\"color\":\"red\",\"text\":\"Scale: 0.25\"}',lore=['\" \"','{\"color\":\"gray\",\"italic\":false,\"text\":\"Rightclick to set\"}','{\"color\":\"gray\",\"italic\":false,\"text\":\"your scale to 0.25.\"}'],fire_resistant={},custom_model_data=6] 1");
            }
            if (args.length == 1) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "give " + args[0] + " amethyst_shard[item_name='{\"color\":\"green\",\"text\":\"Scale: 1\"}',lore=['\" \"','{\"color\":\"gray\",\"italic\":false,\"text\":\"Rightclick to reset\"}','{\"color\":\"gray\",\"italic\":false,\"text\":\"your scale\"}'],fire_resistant={},custom_model_data=4] 1");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "give " + args[0] + " amethyst_shard[item_name='{\"color\":\"yellow\",\"text\":\"Scale: 0.5\"}',lore=['\" \"','{\"color\":\"gray\",\"italic\":false,\"text\":\"Rightclick to set\"}','{\"color\":\"gray\",\"italic\":false,\"text\":\"your scale to 0.5.\"}'],fire_resistant={},custom_model_data=5] 1");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "give " + args[0] + " amethyst_shard[item_name='{\"color\":\"red\",\"text\":\"Scale: 0.25\"}',lore=['\" \"','{\"color\":\"gray\",\"italic\":false,\"text\":\"Rightclick to set\"}','{\"color\":\"gray\",\"italic\":false,\"text\":\"your scale to 0.25.\"}'],fire_resistant={},custom_model_data=6] 1");
            }
        }
        return true;
    }
}