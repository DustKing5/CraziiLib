package me.craziidust.craziilib.item.builders;

import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.jetbrains.annotations.NotNull;

public class ArmorBuilder implements MetaBuilder<ArmorMeta> {

    private final TrimMaterial material;
    private final TrimPattern pattern;

    protected ArmorBuilder(TrimMaterial material, TrimPattern pattern) {
        this.material = material;
        this.pattern = pattern;
    }

    @Override
    public void accept(ArmorMeta armorMeta) {
        armorMeta.setTrim(new ArmorTrim(material, pattern));
    }

    @Override
    public @NotNull Class<ArmorMeta> getType() {
        return ArmorMeta.class;
    }
}
