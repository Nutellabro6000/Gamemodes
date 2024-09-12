package org.surviwal.gamemodes.KitPVP.Commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class KitOld implements CommandExecutor {
    World world = Bukkit.getServer().getWorld("KitPVP");
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (commandSender.isOp()) {
            if (args.length == 2) {
                if (args[0].equals("SMP_Diamond")) {
                    //Retired Code can be deleted if needed
                    String name = args[1];
                    Player player;
                    try {
                        player = Bukkit.getPlayer(name);
                    } catch (Exception e) {
                        commandSender.sendMessage("Gib einen Gültigen Spielernamen an");
                        return false;
                    }
                    if (player.getWorld().equals(world)) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " diamond_sword[enchantments={levels:{\"minecraft:looting\":3,\"minecraft:sharpness\":5,\"minecraft:sweeping_edge\":3,\"minecraft:mending\":1,\"minecraft:unbreaking\":3}}] 1");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " bow[enchantments={levels:{\"minecraft:flame\":1,\"minecraft:power\":5,\"minecraft:punch\":2}}] 1");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " diamond_axe[enchantments={levels:{\"minecraft:sharpness\":5,\"minecraft:efficiency\":5,\"minecraft:silk_touch\":1,\"minecraft:mending\":1,\"minecraft:unbreaking\":3}}] 1");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "item replace entity " + player.getName() + " weapon.offhand with minecraft:shield[minecraft:enchantments={levels:{\"minecraft:mending\":1,\"minecraft:unbreaking\":3}}]");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " splash_potion[potion_contents={potion:\"minecraft:long_fire_resistance\"}] 3");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " ender_pearl 16");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " golden_carrot 64");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " golden_apple 64");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "item replace entity " + player.getName() + " with armor.head diamond_helmet[enchantments={levels:{\"minecraft:protection\":4,\"minecraft:aqua_affinity\":1,\"minecraft:respiration\":3,\"minecraft:mending\":1,\"minecraft:unbreaking\":3}}] 1");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "item replace entity " + player.getName() + " with armor.chest diamond_chestplate[enchantments={levels:{\"minecraft:protection\":4,\"minecraft:mending\":1,\"minecraft:unbreaking\":3}}] 1");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "item replace entity " + player.getName() + " with armor.legs diamond_leggings[enchantments={levels:{\"minecraft:protection\":4,\"minecraft:swift_sneak\":3,\"minecraft:mending\":1,\"minecraft:unbreaking\":3}}] 1");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "item replace entity " + player.getName() + " with armor.feet diamond_boots[enchantments={levels:{\"minecraft:protection\":4,\"minecraft:depth_strider\":3,\"minecraft:soul_speed\":3,\"minecraft:mending\":1,\"minecraft:unbreaking\":3}}] 1");
                    }
                }
                if (args[0].equals("SMP_OPDiamond")) {
                    String name = args[1];
                    Player player;
                    try {
                        player = Bukkit.getPlayer(name);
                    } catch (Exception e) {
                        commandSender.sendMessage("Gib einen Gültigen Spielernamen an");
                        return false;
                    }
                    if (player.getWorld().equals(world)) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " diamond_sword[enchantments={levels:{\"minecraft:looting\":3,\"minecraft:sharpness\":5,\"minecraft:sweeping_edge\":3,\"minecraft:mending\":1,\"minecraft:unbreaking\":3}}] 1");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " bow[enchantments={levels:{\"minecraft:flame\":1,\"minecraft:power\":5,\"minecraft:punch\":2}}] 1");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " diamond_axe[enchantments={levels:{\"minecraft:sharpness\":5,\"minecraft:efficiency\":5,\"minecraft:silk_touch\":1,\"minecraft:mending\":1,\"minecraft:unbreaking\":3}}] 1");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " cobwebs 64");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " water_bucket 1");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "item replace entity " + player.getName() + " weapon.offhand with minecraft:shield[minecraft:enchantments={levels:{\"minecraft:mending\":1,\"minecraft:unbreaking\":3}}]");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " splash_potion[potion_contents={potion:\"minecraft:strong_strength\"}] 1");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " ender_pearl 16");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " golden_carrot 64");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " golden_apple 64");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " splash_potion[potion_contents={potion:\"minecraft:strong_swiftness\"}] 5");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " splash_potion[potion_contents={potion:\"minecraft:strong_strength\"}] 4");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " splash_potion[potion_contents={potion:\"minecraft:long_fire_resistance\"}] 3");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "item replace entity " + player.getName() + " armor.head diamond_helmet[enchantments={levels:{\"minecraft:protection\":4,\"minecraft:aqua_affinity\":1,\"minecraft:respiration\":3,\"minecraft:mending\":1,\"minecraft:unbreaking\":3}}] 1");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "item replace entity " + player.getName() + " armor.chest diamond_chestplate[enchantments={levels:{\"minecraft:protection\":4,\"minecraft:mending\":1,\"minecraft:unbreaking\":3}}] 1");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "item replace entity " + player.getName() + " armor.legs diamond_leggings[enchantments={levels:{\"minecraft:protection\":4,\"minecraft:swift_sneak\":3,\"minecraft:mending\":1,\"minecraft:unbreaking\":3}}] 1");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "item replace entity " + player.getName() + " armor.feet diamond_boots[enchantments={levels:{\"minecraft:protection\":4,\"minecraft:depth_strider\":3,\"minecraft:soul_speed\":3,\"minecraft:mending\":1,\"minecraft:unbreaking\":3}}] 1");
                    }
                }
            }
        }
        return true;
    }
}

