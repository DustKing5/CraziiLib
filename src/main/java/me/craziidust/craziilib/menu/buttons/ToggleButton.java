package me.craziidust.craziilib.menu.buttons;

import me.craziidust.craziilib.menu.ClickContext;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ToggleButton implements Button {

    private boolean toggled = false;

    private final Predicate<ClickContext> canUse;
    private final ItemStack on;
    private final ItemStack off;
    private final Consumer<Boolean> onToggle;

    protected ToggleButton(Builder builder) {
        this.canUse = builder.canUse;
        this.on = builder.on;
        this.off = builder.off;
        this.onToggle = builder.onToggle;
    }

    public void toggle() {
        this.toggled = !toggled;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    @Override
    public ItemStack render() {
        return (toggled) ? on : off;
    }

    @Override
    public boolean canUse(ClickContext context) {
        return canUse.test(context);
    }

    @Override
    public void leftClick(ClickContext context) {
        onToggle.accept(toggled);
        toggle();
    }

    public static class Builder {

        private Predicate<ClickContext> canUse = clickContext -> true;
        private final ItemStack on;
        private final ItemStack off;
        protected Consumer<Boolean> onToggle = aBoolean -> {};

        public Builder(Material on, Material off) {
            this.on = new ItemStack(on);
            this.off = new ItemStack(off);
        }

        public Builder canUse(Predicate<ClickContext> canUse) {
            this.canUse = canUse;
            return this;
        }

        public Builder setOnName(Component component) {
            on.editMeta(itemMeta -> itemMeta.displayName(component));
            return this;
        }

        public Builder setOffName(Component component) {
            off.editMeta(itemMeta -> itemMeta.displayName(component));
            return this;
        }

        public Builder setOnLore(List<Component> list) {
            on.lore(list);
            return this;
        }

        public Builder setOffLore(List<Component> lore) {
            off.lore(lore);
            return this;
        }

        public Builder addOnLore(List<Component> list) {
            List<Component> lore = new ArrayList<>(Optional.ofNullable(on.lore()).orElse(List.of()));
            lore.addAll(list);
            on.lore(lore);
            return this;
        }

        public Builder addOffLore(List<Component> list) {
            List<Component> lore = new ArrayList<>(Optional.ofNullable(off.lore()).orElse(List.of()));
            lore.addAll(list);
            off.lore(lore);
            return this;
        }

        public Builder addOnLore(Component component) {
            return addOnLore(List.of(component));
        }

        public Builder addOffLore(Component component) {
            return addOffLore(List.of(component));
        }

        public Builder setOnToggle(Consumer<Boolean> consumer) {
            this.onToggle = consumer;
            return this;
        }

        public Button build() {
            return new ToggleButton(this);
        }
    }
}
