package me.craziidust.craziilib.menu.buttons;

import me.craziidust.craziilib.menu.ClickContext;
import org.bukkit.inventory.ItemStack;

public interface Button {
    ItemStack render();

    default void rightClick(ClickContext context) {}

    default void leftClick(ClickContext context) {}
}