package org.surviwal.gamemodes.HideNSeek.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class IgnoreInvClick implements Listener {
    World world = Bukkit.getWorld("HideNSeak");
    @EventHandler
    public Void InventoryClick (InventoryClickEvent e){
        Player player = (Player)e.getWhoClicked();
        if (player.getGameMode().equals(GameMode.ADVENTURE)) {
            if (player.getWorld().equals(world)) {
                e.setCancelled(true);
            }
        }
        return null;
    }
    @EventHandler
    public Void OnDrop (PlayerDropItemEvent e){
        Player player = e.getPlayer();
        if (player.getGameMode().equals(GameMode.ADVENTURE)) {
            if (player.getWorld().equals(world)) {
                e.setCancelled(true);
            }
        }
        return null;
    }
}
