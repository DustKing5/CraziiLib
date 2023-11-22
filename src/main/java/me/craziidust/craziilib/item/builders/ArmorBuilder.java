package me.craziidust.craziilib.item.builders;

import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.jetbrains.annotations.NotNull;

public final class ArmorBuilder implements MetaBuilder<ArmorMeta> {

    private TrimMaterial material;
    private TrimPattern pattern;

    ArmorBuilder() {
        this.material = null;
        this.pattern = null;
    }

    ArmorBuilder(TrimMaterial material, TrimPattern pattern) {
        this.material = material;
        this.pattern = pattern;
    }

    public ArmorBuilder material(TrimMaterial material) {
        this.material = material;
        return this;
    }

    public ArmorBuilder pattern(TrimPattern pattern) {
        this.pattern = pattern;
        return this;
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
