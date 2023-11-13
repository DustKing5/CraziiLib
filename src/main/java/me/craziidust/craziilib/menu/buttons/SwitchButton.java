package me.craziidust.craziilib.menu.buttons;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SwitchButton implements Button {

    private final String id;
    private final ItemStack on,off;
    private boolean state = false;

    public SwitchButton(String id, ItemStack on, ItemStack off) {
        this.id = id;
        this.on = on;
        this.off = off;
    }

    public SwitchButton(String id, ItemStack on, ItemStack off, boolean state) {
        this.id = id;
        this.on = on;
        this.off = off;
        this.state = state;
    }

    @Override
    public @NotNull ItemStack render() {
        return (state) ? on : off;
    }

    @Override
    public @NotNull String getId() {
        return id;
    }

    @Override
    public void onClick() {
        state = !state;
    }

    public boolean isState() {
        return state;
    }
}
