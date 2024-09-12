package org.surviwal.gamemodes.HideNSeek.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Scales implements Listener {
    @EventHandler
    public void Scales (PlayerInteractEvent e) {
        Player player = e.getPlayer();
        String playername = player.getName();
        if (player.getItemInHand().getType().equals(Material.AMETHYST_SHARD)) {
            if (player.getItemInHand().getItemMeta().hasLore()) {
                if (player.getItemInHand().getItemMeta().getCustomModelData() == 4) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "attribute " + playername + " minecraft:generic.scale base set 1");
                }
                if (player.getItemInHand().getItemMeta().getCustomModelData() == 5) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "attribute " + playername + " minecraft:generic.scale base set 0.5");
                }
                if (player.getItemInHand().getItemMeta().getCustomModelData() == 6)
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "attribute " + playername + " minecraft:generic.scale base set 0.25");
            }
        }

    }
}
