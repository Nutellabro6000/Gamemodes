package org.surviwal.gamemodes.KitPVP.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class KitCommand implements CommandExecutor {
    Plugin plugin = Bukkit.getPluginManager().getPlugin("KitPVP");
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (args.length == 2) {
            if (args[0].equals("save")) {
                if (args[1].equals("kit1") || args[1].equals("kit2") || args[1].equals("kit3")) {
                    if (commandSender instanceof Player) {
                        Player player = (Player) commandSender;
                        String name = player.getName();
                        String kitname = name + args[1];
                        ItemStack air = new ItemStack(Material.AIR);
                        List<ItemStack> kits = new ArrayList<>();
                        for (ItemStack item : player.getInventory()) {
                            try {
                                if (!item.hasItemMeta()) {
                                    kits.add(item);
                                } else {
                                    kits.add(item);
                                }
                            } catch (Exception e) {
                                kits.add(air);
                            }
                        }
                        plugin.getConfig().addDefault(name + "kit1", "");
                        plugin.getConfig().set(kitname, kits);
                        plugin.saveConfig();
                        player.sendMessage(ChatColor.GREEN + "[KitPVP] " + ChatColor.RESET + "successfully saved " + args[1]);
                    }
                }
            } else if (args[0].equals("load")) {
                if (args[1].equals("kit1") || args[1].equals("kit2") || args[1].equals("kit3")) {
                    if (commandSender instanceof Player) {
                        Player player = (Player) commandSender;
                        String kit = player.getName() + args[1];
                        loadkitplugin(player, kit);
                    }
                }
            }
        } else if (args.length == 4){
            if (args[0].equals("give")){
                if (args[1].equals("kit1") || args[1].equals("kit2") || args[1].equals("kit3")) {
                    Player KitPlayer;
                    Player GivePlayer;
                    Boolean Catch = true;
                    try {
                        KitPlayer = Bukkit.getPlayer(args[2]);
                        GivePlayer = Bukkit.getPlayer(args[3]);
                    } catch (Exception e) {
                        commandSender.sendMessage(ChatColor.RED + "[KitPVP]" + ChatColor.RESET +  "Bitte gib einen gültigen spieler an");
                        Catch = false;
                        return false;
                    }
                    if (Catch) {
                        String kit = KitPlayer.getName() + args[1];
                        loadkitplugin(GivePlayer, kit);
                    }
                }
            }
        } else if (args.length == 3) {
            if (commandSender.hasPermission("KitPVP.Command.Kit")){
                if (args[0].equals("load")) {
                    if (args[1].equals("kit1") || args[1].equals("kit2") || args[1].equals("kit3")) {
                        Player player;
                        try {
                            player = Bukkit.getPlayer(args[2]);
                        } catch (Exception e) {
                            commandSender.sendMessage(ChatColor.RED + "[KitPVP]" + ChatColor.RESET + " Bitte gib einen gültigen spieler an");
                            return false;
                        }
                        String kit = player.getName() + args[1];
                        loadkitplugin(player, kit);
                    }
                }
            }
        }
        return true;
    }



    public void loadkitplugin(Player player, String kit){
        ItemStack air = new ItemStack(Material.AIR);
        ItemStack pane = new ItemStack(Material.COMMAND_BLOCK_MINECART);
        plugin.reloadConfig();
        List<ItemStack> list = (List<ItemStack>) plugin.getConfig().getList(kit);
        if (list.isEmpty()){
            plugin.reloadConfig();
            list = (List<ItemStack>) plugin.getConfig().getList(kit);
        }
        for (ItemStack item : list) {
            if (!item.equals(air) && !item.equals(pane)){
                int slot = list.indexOf(item);
                player.getInventory().setItem(slot, item);
                list.set(slot, pane);
            }
        }
        list.clear();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "clear " + player.getName() + " minecraft:command_block_minecart");
        player.sendMessage(ChatColor.GREEN + "[KitPVP] " + ChatColor.RESET +"Loaded Kit");
    }
}