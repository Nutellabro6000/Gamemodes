package org.surviwal.gamemodes.HideNSeek.Commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.util.List;
import java.util.Random;

public class StartCommand implements CommandExecutor, Listener {

    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Gamemodes");
    World world = Bukkit.getWorld("HideNSeak");
    boolean HideNSeekActive = false;
    int Deaths = 0;
    int Cooldown = 0;

    @EventHandler
    public void PlayerDeath(PlayerDeathEvent e){
        if (e.getPlayer().getWorld().equals(world)) {
            List<Player> WaitingPlayers2 = world.getPlayers();
            int size2 = WaitingPlayers2.size();
            e.getPlayer().sendMessage(ChatColor.RED + "[HideNSeek] " + ChatColor.WHITE + "You Have Died!");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamemode spectator " + e.getPlayer().getName());
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players add " + e.getPlayer().getName() + " Losses 1");
            Deaths = Deaths + 1;
            System.out.println("The Death Counter is " + Deaths);
            if (Deaths >= size2 - 1) {

                for (Player player : world.getPlayers()){
                    player.sendMessage(ChatColor.GREEN + "[HideNSeek]" + ChatColor.WHITE + " The Seeker has won!");
                    player.clearActivePotionEffects();
                    if (player.getGameMode().equals(GameMode.ADVENTURE)){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players add " + player.getName() + " Wins 1");
                    }
                    if (player.getGameMode().equals(GameMode.SPECTATOR)){
                        player.setGameMode(GameMode.ADVENTURE);
                    }
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:hidenseak run tp " + player.getName() + " 62 -32 -13");
                    plugin.getServer().getScheduler().cancelTasks(plugin);
                }
                Deaths = 0;
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "clone from minecraft:hidenseak 66 -38 -12 64 -36 -16 to minecraft:hidenseak 64 -32 -16");
                HideNSeekActive = false;
                Cooldown = 0;
            }
        }
    }


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<Player> WaitingPlayers = world.getPlayers();
        int size = WaitingPlayers.size();
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        if (commandSender.hasPermission("hidenseek.command.start")){
            if (args.length == 1) {
                if (args[0].equals("reset")) {
                    HideNSeekActive = false;
                    Cooldown = 0;
                    plugin.getServer().getScheduler().cancelTasks(plugin);
                }
                if (args[0].equals("normal")) {
                    if (!HideNSeekActive) {
                        if (size > 1) {
                            HideNSeekActive = true;
                            Cooldown = (((size * 200) ^ (1 / 2)) * 20) + 1800;
                            Random random = new Random();
                            int randomNumber = random.nextInt(size);
                            Player Seeker = WaitingPlayers.get(randomNumber);
                            String SeekerName = Seeker.getName();
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:hidenseak run tp " + SeekerName + " 77 -32 -13");
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "effect give " + SeekerName + " minecraft:strength infinite 255 true");
                            for (Player player : world.getPlayers()) {
                                player.setGameMode(GameMode.ADVENTURE);
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "clear " + player.getName());
                                player.sendMessage(ChatColor.GREEN + "[HideNSeek] " + ChatColor.WHITE + SeekerName + " Is Seeker!");
                                player.sendMessage(ChatColor.GREEN + "[HideNSeek] " + ChatColor.WHITE + "You have 90 Seconds to Hide!");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scale " + player.getName());
                            }
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + SeekerName + " minecraft:iron_sword");
                            new BukkitRunnable() {
                                public void run() {
                                    for (Player player : world.getPlayers()) {
                                        player.sendMessage(ChatColor.GREEN + "[HideNSeek] " + ChatColor.WHITE + "The Seeker is now hunting!");
                                    }
                                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute at " + SeekerName + " run fill  64 -32 -12 66 -32 -16 air");
                                }
                            }.runTaskLater(plugin, 1800);
                            new BukkitRunnable() {
                                public void run() {
                                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute at " + SeekerName + " run fill 64 -31 -12 66 -30 -16 air");
                                }
                            }.runTaskLater(plugin, 1820);
                            new BukkitRunnable() {
                                public void run() {
                                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "clone from minecraft:hidenseak 66 -38 -12 64 -36 -16 to minecraft:hidenseak 64 -32 -16");
                                    for (Player player : world.getPlayers()) {
                                        player.sendMessage(ChatColor.GREEN + "[HideNSeek]" + ChatColor.WHITE + " The Hiders Won");
                                        if (player.getGameMode().equals(GameMode.ADVENTURE)) {
                                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players add " + player.getName() + " Wins 1");
                                        }
                                    }
                                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "effect clear " + SeekerName);
                                    HideNSeekActive = false;
                                }
                            }.runTaskLater(plugin, Cooldown);
                        }
                    }
                }
            }
        }  else {
            Bukkit.broadcastMessage(org.bukkit.ChatColor.RED + "[SAS]" + org.bukkit.ChatColor.DARK_RED + "You don´t have the Permission to use this Command here!");
        }

        return true;
    }

}
