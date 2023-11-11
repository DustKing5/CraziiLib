package me.craziidust.craziilib.menu;

import me.craziidust.craziilib.menu.buttons.Button;
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
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Menu implements Listener, InventoryHolder {
    private final JavaPlugin javaPlugin;
    private final Inventory inventory;
    private final Map<Integer, Button> buttonMap;
    public Menu(JavaPlugin javaPlugin,MenuType menuType, Component title) {
        this.javaPlugin = javaPlugin;
        this.inventory = menuType.create(this,title);
        this.buttonMap = new HashMap<>();
    }

    public final void addButton(int slot,Button button) {
        if (slot < 0) {
            throw new IllegalArgumentException("Slot can't be lower than 0");
        }
        if (slot > inventory.getSize()) {
            throw new IllegalArgumentException("Slot can't be higher then " + inventory.getSize() * 9);
        }
        buttonMap.put(slot,button);
    }
    public final void addButton(int row, int column, Button button) {
        if (column < 0 || column > 8) {
            throw new IllegalArgumentException("Column out of bonds: 0-8");
        }
        if (row < 0 || row > inventory.getSize() / 9) {
            throw new IllegalArgumentException("Row out of bonds: 0-" + inventory.getSize() / 9);
        }
        addButton((row*9)+column, button);
    }

    public final void removeButton(int slot) {
        if (slot < 0) {
            throw new IllegalArgumentException("Slot can't be lower than 0");
        }
        if (slot > inventory.getSize()) {
            throw new IllegalArgumentException("Slot can't be higher then " + inventory.getSize() * 9);
        }
        buttonMap.remove(slot);
    }

    public final void removeButton(int row, int column) {
        if (column < 0 || column > 8) {
            throw new IllegalArgumentException("Column out of bonds: 0-8");
        }
        if (row < 0 || row > inventory.getSize() / 9) {
            throw new IllegalArgumentException("Row out of bonds: 0-" + inventory.getSize() / 9);
        }
        removeButton((row*9)+column);
    }

    public final void open(Player player) {
        Bukkit.getPluginManager().registerEvents(this, javaPlugin);
        populate();
        player.openInventory(inventory);
    }

    public final void close() {
        HandlerList.unregisterAll(this);
        inventory.close();
    }

    public final void clearButtons(){
        buttonMap.clear();
    }

    @EventHandler
    public final void onInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTopInventory() == inventory) {
            close();
        }
    }

    @EventHandler
    public final void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == inventory) {
            event.setCancelled(true);
            if (buttonMap.containsKey(event.getSlot())) {
                ClickContext context = new ClickContext(event);
                Button button = buttonMap.get(event.getSlot());

                if (!button.canUse(context)) {
                    return;
                }

                switch (event.getClick()) {
                    case RIGHT -> button.rightClick(context);
                    case LEFT -> button.leftClick(context);
                }

                inventory.setItem(event.getSlot(), button.render());
            }
        }
    }

    @EventHandler
    public final void onPluginDisable(PluginDisableEvent event) {
        if (event.getPlugin() == javaPlugin) {
            close();
        }
    }

    public final void populate() {
        buttonMap.forEach((integer, button) -> inventory.setItem(integer,button.render()));
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
