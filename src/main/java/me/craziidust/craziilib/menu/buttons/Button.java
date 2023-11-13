package me.craziidust.craziilib.menu.buttons;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface Button {
    @NotNull ItemStack render();

    @NotNull String getId();

    default void onClick(){}
}