package me.craziidust.craziilib.item.builders;

import org.bukkit.inventory.meta.Damageable;
import org.jetbrains.annotations.NotNull;

public final class DamageableBuilder implements MetaBuilder<Damageable> {

    int damage = 0;

    DamageableBuilder() {
    }

    DamageableBuilder(int damage) {
        this.damage = damage;
    }

    public DamageableBuilder damage(int damage) {
        this.damage = damage;
        return this;
    }

    @Override
    public @NotNull Class<Damageable> getType() {
        return Damageable.class;
    }

    @Override
    public void accept(Damageable damageable) {
        damageable.setDamage(damage);
    }
}
