package me.craziidust.craziilib.menu;

import me.craziidust.craziilib.menu.buttons.Button;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Menu implements Listener, InventoryHolder {
    private final JavaPlugin javaPlugin;
    private final Inventory inventory;
    private final Map<Integer, Button> buttonMap;

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
        this.buttonMap = new HashMap<>();
    }

    /**
     * Add button to specified slot
     * @param slot
     * @param button
     */
    public final void addButton(int slot,Button button) {
        if (slot < 0) {
            throw new IllegalArgumentException("Slot can't be lower than 0");
        }
        if (slot > inventory.getSize()) {
            throw new IllegalArgumentException("Slot can't be higher then " + inventory.getSize() * 9);
        }
        buttonMap.put(slot,button);
    }

    /**
     * Add button to specified column and row
     * @param row
     * @param column
     * @param button
     */
    public final void addButton(int row, int column, Button button) {
        if (column < 0 || column > 8) {
            throw new IllegalArgumentException("Column out of bonds: 0-8");
        }
        if (row < 0 || row > inventory.getSize() / 9) {
            throw new IllegalArgumentException("Row out of bonds: 0-" + inventory.getSize() / 9);
        }
        addButton((row*9)+column, button);
    }

    /**
     * Remove Button from specified slot
     * @param slot
     */
    public final void removeButton(int slot) {
        if (slot < 0) {
            throw new IllegalArgumentException("Slot can't be lower than 0");
        }
        if (slot > inventory.getSize()) {
            throw new IllegalArgumentException("Slot can't be higher then " + inventory.getSize() * 9);
        }
        buttonMap.remove(slot);
    }

    /**
     * Remove Button from specified row and column
     * @param row
     * @param column
     */
    public final void removeButton(int row, int column) {
        if (column < 0 || column > 8) {
            throw new IllegalArgumentException("Column out of bonds: 0-8");
        }
        if (row < 0 || row > inventory.getSize() / 9) {
            throw new IllegalArgumentException("Row out of bonds: 0-" + inventory.getSize() / 9);
        }
        removeButton((row*9)+column);
    }

    /**
     * Open the Menu to specified player
     * @param player
     */
    public final void open(Player player) {
        if (!active) {
            Bukkit.getPluginManager().registerEvents(this, javaPlugin);
            populate();
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
    private final void populate() {
        buttonMap.forEach((integer, button) -> inventory.setItem(integer,button.render()));
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

    public boolean onClick(Player player, Button button, ClickType clickType) {
        return false;
    };

    public boolean hasAccess(int slot) {
        return false;
    }

    @EventHandler
    public final void onInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTopInventory() == inventory) {
            close((Player) event.getPlayer());
        }
    }

    @EventHandler
    public final void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == inventory) {
            Button button = buttonMap.get(event.getSlot());
            if (onClick((Player) event.getWhoClicked(), button, event.getClick())) {
                populate();
            }
            event.setCancelled(button != null || hasAccess(event.getSlot()));
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
