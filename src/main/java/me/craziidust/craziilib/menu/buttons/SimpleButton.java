package me.craziidust.craziilib.menu.buttons;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SimpleButton implements Button {

    private final String id;
    private final ItemStack itemStack;

    public SimpleButton(String id, ItemStack itemStack) {
        this.id = id;
        this.itemStack = itemStack;
    }

    @Override
    public @NotNull ItemStack render() {
        return itemStack;
    }

    @Override
    public @NotNull String getId() {
        return null;
    }
}
