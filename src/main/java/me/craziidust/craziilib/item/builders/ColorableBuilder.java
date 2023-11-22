package me.craziidust.craziilib.item.builders;

import org.bukkit.Color;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;

public final class ColorableBuilder implements MetaBuilder<LeatherArmorMeta> {

    private final Color color;

    ColorableBuilder(Color color) {
        this.color = color;
    }

    @Override
    public @NotNull Class<LeatherArmorMeta> getType() {
        return LeatherArmorMeta.class;
    }

    @Override
    public void accept(LeatherArmorMeta leatherArmorMeta) {
        leatherArmorMeta.setColor(color);
    }
}
