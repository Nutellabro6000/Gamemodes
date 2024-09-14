package org.surviwal.gamemodes.KitPVP.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.surviwal.gamemodes.ItemBuilder;


public class BookshelfListener implements Listener {

    @EventHandler
    public void Rightclick(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (player.getWorld().equals("KitPVP") || player.getWorld().equals("ArenaTemplate")){
            Action action = e.getAction();
            if (action.equals(Action.RIGHT_CLICK_BLOCK)){
                Block block = e.getClickedBlock();
                    if (block.getType().equals(Material.BOOKSHELF)){
                        Inventory inventory = Bukkit.createInventory(null, 6 * 9, "\uD83D\uDD57");
                        inventory.setItem(3, new ItemBuilder(Material.ENCHANTED_BOOK).setDisplayname(ChatColor.RESET + "Sharpness").setLore("Sharpness").build());
                        inventory.setItem(5, new ItemBuilder(Material.ENCHANTED_BOOK).setDisplayname(ChatColor.RESET + "Protection").setLore("Protection").build());
                        player.openInventory(inventory);

                }
            }

        }
    }
    @EventHandler
    public void InvListener(InventoryClickEvent event){
        Inventory inventory = event.getClickedInventory();
        if (event.getView().getTitle().equals("\uD83D\uDD57")){
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem().getLore().equals("Sharpness")){
                ItemStack item = player.getActiveItem();
                Enchantment enchantment = Enchantment.SHARPNESS;
                if (enchantment.canEnchantItem(item)){
                    item.addEnchantment(enchantment, 5);
                }
            } else if (event.getCurrentItem().getLore().equals("Protection")){
                ItemStack item = player.getActiveItem();
                Enchantment enchantment = Enchantment.PROTECTION;
                if (enchantment.canEnchantItem(item)){
                    item.addEnchantment(enchantment, 4);
                }
            }
        }

    }
}
