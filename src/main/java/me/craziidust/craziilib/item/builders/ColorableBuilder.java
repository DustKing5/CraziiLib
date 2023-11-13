package me.craziidust.craziilib.item.builders;

import org.bukkit.Color;
import org.bukkit.inventory.meta.ColorableArmorMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.jetbrains.annotations.NotNull;

public class ColorableBuilder implements MetaBuilder<ColorableArmorMeta> {

    private final Color color;
    private final TrimMaterial material;
    private final TrimPattern pattern;

    protected ColorableBuilder(Color color, TrimMaterial material, TrimPattern pattern) {
        this.color = color;
        this.material = material;
        this.pattern = pattern;
    }


    @Override
    public @NotNull Class<ColorableArmorMeta> getType() {
        return ColorableArmorMeta.class;
    }

    @Override
    public void accept(ColorableArmorMeta colorableArmorMeta) {
        colorableArmorMeta.setColor(color);
        colorableArmorMeta.setTrim(new ArmorTrim(material, pattern));
    }
}
