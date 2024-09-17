package org.surviwal.gamemodes.KitPVP.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.surviwal.gamemodes.ItemBuilder;

import java.util.ArrayList;
import java.util.List;


public class BookshelfListener implements Listener {
    World world = Bukkit.getWorld("KitPVP");
    World world1 = Bukkit.getWorld("ArenaTemplate");
    List<World> worlds = new ArrayList<>();

    @EventHandler
    public void Rightclick(PlayerInteractEvent e) {
        worlds.add(world1);
        worlds.add(world);
        Player player = e.getPlayer();
        if (worlds.contains(player.getWorld())) {
            Action action = e.getAction();
            if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
                Block block = e.getClickedBlock();
                if (block.getType().equals(Material.BOOKSHELF)) {
                    ItemStack item = player.getItemInHand();
                    Material material = item.getType();
                    e.setCancelled(true);
                    List<Enchantment> enchantments = new ArrayList<>();
                    for (Enchantment enchants : Enchantment.values()) {
                        if (enchants.canEnchantItem(item)) {
                            enchantments.add(enchants);
                        }
                    }
                    Inventory inventory = Bukkit.createInventory(null, 6 * 9, "Bookshelf");
                    for (Enchantment ench : enchantments) {
                        String name = ench.getName();
                        inventory.setItem(27 + enchantments.indexOf(ench), new ItemBuilder(Material.ENCHANTED_BOOK).setDisplayname(name).addEnchantment(ench, 1).build());
                    }
                    inventory.setItem(13, new ItemBuilder(material).build());
                    player.openInventory(inventory);
                }
            }

        }
    }

    @EventHandler
    public void InvListener(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Bookshelf")) {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            if (event.getCurrentItem() == null) {
                return;
            }
            if (event.getCurrentItem().getItemMeta().hasEnchants()) {
                ItemStack item = event.getCurrentItem();
                Enchantment enchantment = Enchantment.getByName(item.getItemMeta().getDisplayName());
                ItemStack itemInHand = player.getItemInHand();
                int Level = 0;
                try {
                    Level = itemInHand.getEnchantmentLevel(enchantment);
                } catch (Exception error) {
                }
                if (event.getClick().isLeftClick()) {
                    if (enchantment.canEnchantItem(itemInHand)) {
                        itemInHand.addEnchantment(enchantment, Level + 1);
                    }
                } else {
                    itemInHand.removeEnchantment(enchantment);
                    itemInHand.addEnchantment(enchantment, Level - 1);
                }
            }
        }
    }
}