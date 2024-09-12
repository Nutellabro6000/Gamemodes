package org.surviwal.gamemodes.KitPVP.Commands;

import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ArenaSystem implements CommandExecutor, Listener {
    Plugin plugin = Bukkit.getPluginManager().getPlugin("Gamemodes");
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (commandSender.hasPermission("SAS.Command.Arena")){
            if (args.length == 3){
                if (args[0].equals("create") || args[0].equals("c")){
                    //Kit from Player1, gotten from args[1]
                    Player player1;
                    //loading kit for Player2, gotten from args[2]
                    Player player2;
                    try {
                        player1 = Bukkit.getPlayer(args[1]);
                        player2 = Bukkit.getPlayer(args[2]);
                    } catch (Exception er){
                        commandSender.sendMessage(ChatColor.RED + "[SAS] " + ChatColor.RESET + "Please write an actual Player name");
                        return false;
                    }
                    if (player1 != player2) {
                        ArenaCreator(player1, player2);
                    }
                }
            }
        }
        return true;
    }
    public void ArenaCreator(Player player1, Player player2){
        String name1 = player1.getName();
        String name2 = player2.getName();
        String Kit;
        try {
            Kit = plugin.getConfig().getString("ActiveKit" + name1);
        } catch (Exception e){
            player1.sendMessage(ChatColor.RED + "[SAS] " + ChatColor.RESET + "you must select a Kit!");
            return;
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvclone ArenaTemplate Arena_" + name1 + "-" + name2);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvtp " + name1 + " Arena_" + name1 + "-" + name2);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvtp " + name2 + " Arena_" + name1 + "-" + name2);
        String kitname = "kit" + Kit;
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"kit load "+kitname +" "+name1);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"kit give "+kitname +" "+name1 +" "+name2);
            }
        }.runTaskLater(plugin, 60);
    }
    @EventHandler
    public void OnDeathEvent(PlayerDeathEvent e){
        Player player = e.getPlayer();
        Player killer = e.getPlayer().getKiller();
        String playerName = player.getName();
        String killerName = killer.getName();
        if (e.getPlayer().getWorld().equals("Arena_" + playerName + "-" + killerName)){
            player.sendMessage("You lost the Duel!");
            killer.sendMessage("You Won the Duel");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players add " + playerName + " KitPVPLosses 1");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players add " + killerName + " KitPVPWins 1");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvdelete Arena_" + playerName + "-" + killerName);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv confirm");
        } else if (player.getWorld().equals("Arena_" + killerName + "-" + playerName)) {
            player.sendMessage("You lost the Duel!");
            killer.sendMessage("You Won the Duel");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players add " + playerName + " KitPVPLosses 1");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players add " + killerName + " KitPVPWins 1");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvdelete Arena_" + killerName + "-" + playerName);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv confirm");
        }
    }
    @EventHandler
    public void OnLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        World world = player.getWorld();
        List<Player> players = new ArrayList<>();
        for (Player player1 : world.getPlayers())
            if (player1.getGameMode().equals(GameMode.SURVIVAL)){
                players.add(player1);
            }
        if (!players.isEmpty()){
            int size = players.size();
            if (size == 2){
                String playerName = player.getName();
                players.remove(player);
                Player killer = players.getFirst();
                String killerName = killer.getName();
                if (world.equals(("Arena_" + player + "-" + killerName))) {
                    player.sendMessage("You lost the Duel!");
                    killer.sendMessage("You Won the Duel");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players add " + playerName + " KitPVPLosses 1");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players add " + killerName + " KitPVPWins 1");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvdelete Arena_" + playerName + "-" + killerName);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv confirm");
                }   else if (player.getWorld().equals("Arena_" + killerName + "-" + playerName)) {
                    player.sendMessage("You lost the Duel!");
                    killer.sendMessage("You Won the Duel");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players add " + playerName + " KitPVPLosses 1");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players add " + killerName + " KitPVPWins 1");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvdelete Arena_" + killerName + "-" + playerName);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv confirm");
                }
            }
        }
    }
}