package me.craziidust.craziilib.menu;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Menu implements Listener, InventoryHolder {
    private final JavaPlugin javaPlugin;
    private final Inventory inventory;

    private boolean active = false;
    private Menu parent;

    /**
     * Create new Menu instance
     * @param javaPlugin
     * @param menuType
     * @param title
     */
    public Menu(JavaPlugin javaPlugin,MenuType menuType, Component title) {
        this.javaPlugin = javaPlugin;
        this.inventory = menuType.create(this,title);
    }

    /**
     * Add item to menu
     * @param itemStack
     */
    public void addItem(ItemStack itemStack) {
        inventory.addItem(itemStack);
    }

    /**
     * Add item to slot
     * @param itemStack
     * @param slot
     */
    public void addItem(ItemStack itemStack, int slot) {
        inventory.setItem(slot, itemStack);
    }

    /**
     * Add item to menu
     * @param itemStack
     * @param row
     * @param column
     */
    public void addItem(ItemStack itemStack, int row, int column) {
        addItem(itemStack,(row*9+column));
    }

    /**
     * Remove item from menu
     * @param itemStack
     */
    public void removeItem(ItemStack itemStack) {
        inventory.remove(itemStack);
    }

    /**
     * Remove item from slot
     * @param slot
     */
    public void removeItem(int slot) {
        inventory.clear(slot);
    }

    /**
     * Remove item
     * @param row
     * @param column
     */
    public void removeItem(int row, int column) {
        removeItem((row*9)+column);
    }

    /**
     * Open the Menu to specified player
     * @param player
     */
    public final void open(Player player) {
        if (!active) {
            Bukkit.getPluginManager().registerEvents(this, javaPlugin);
            active = true;
        }
        player.openInventory(inventory);
    }

    /**
     * Close the menu for every viewer
     */
    public final void close() {
        active = false;
        HandlerList.unregisterAll(this);
        inventory.close();
    }

    /**
     * Close the menu for specified player
     * @param player
     */
    public final void close(Player player) {
        if (inventory.getViewers().isEmpty()) {
            close();
        }
        if (player.getOpenInventory().getTopInventory() == inventory) {
            player.closeInventory(InventoryCloseEvent.Reason.PLUGIN);
        }
    }

    /**
     * Open subMenu
     * @param menu
     */
    public final void openChild(Player player, Menu menu) {
        menu.setParent(this);
        menu.open(player);
    }

    public final void openParent(Player player) {
        if (parent == null) {
            return;
        }
        parent.open(player);
    }

    /**
     * Handle inventory click
     * @param event
     */
    public void onClick(InventoryClickEvent event) {

    };

    @EventHandler
    public final void onInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTopInventory() == inventory) {
            close((Player) event.getPlayer());
        }
    }

    @EventHandler
    public final void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == inventory) {
            onClick(event);
        }
    }

    @EventHandler
    public final void onPluginDisable(PluginDisableEvent event) {
        if (event.getPlugin() == javaPlugin) {
            close();
        }
    }

    @Override
    public final @NotNull Inventory getInventory() {
        return inventory;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }
}
