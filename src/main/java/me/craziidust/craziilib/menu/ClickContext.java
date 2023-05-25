package me.craziidust.craziilib.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public record ClickContext(Player player, boolean shift, InventoryAction inventoryAction, ItemStack current, ItemStack cursor) {
    public ClickContext(InventoryClickEvent event) {
        this((Player) event.getWhoClicked(), event.isShiftClick(), event.getAction(),event.getCurrentItem(),event.getCursor());
    }
}
