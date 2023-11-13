package me.craziidust.craziilib.item.builders;

import org.bukkit.Color;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PotionBuilder implements MetaBuilder<PotionMeta> {

    private final PotionType base;
    private Color color;
    private List<PotionEffect> effects;
    private boolean overwrite = true;

    public PotionBuilder(PotionType base) {
        this(base, null);
    }

    public PotionBuilder(PotionType base, Color color) {
        this.base = base;
        this.color = color;
        this.effects = new LinkedList<>();
    }

    public PotionBuilder overwrite(boolean overwrite) {
        this.overwrite = overwrite;
        return this;
    }

    public PotionBuilder addEffects(List<PotionEffect> effects) {
        this.effects.addAll(effects);
        return this;
    }

    public PotionBuilder addEffects(PotionEffect ...effects) {
        return addEffects(Arrays.stream(effects).toList());
    }

    public PotionBuilder addEffect(PotionEffect effect) {
        effects.add(effect);
        return this;
    }

    public PotionBuilder addEffect(PotionEffectType type) {
        return addEffect(type, -1);
    }

    public PotionBuilder addEffect(PotionEffectType type, int duration) {
        return addEffect(type, duration, 0);
    }

    public PotionBuilder addEffect(PotionEffectType type, int duration, int amplifier) {
        return addEffect(type, duration, amplifier, true);
    }

    public PotionBuilder addEffect(PotionEffectType type, int duration, int amplifier, boolean ambient) {
        return addEffect(type, duration, amplifier, ambient, true);
    }

    public PotionBuilder addEffect(PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles) {
        return addEffect(type, duration, amplifier, ambient, particles, true);
    }

    public PotionBuilder addEffect(PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles, boolean icon) {
        effects.add(new PotionEffect(type, duration, amplifier, ambient, particles, icon));
        return this;
    }


    @Override
    public @NotNull Class<PotionMeta> getType() {
        return PotionMeta.class;
    }

    @Override
    public void accept(PotionMeta potionMeta) {
        potionMeta.setBasePotionType(base);
        potionMeta.setColor(color);
        effects.forEach(effect -> potionMeta.addCustomEffect(effect, overwrite));
    }
}
