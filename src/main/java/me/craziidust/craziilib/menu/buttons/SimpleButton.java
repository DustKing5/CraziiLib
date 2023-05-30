package me.craziidust.craziilib.menu.buttons;

import me.craziidust.craziilib.menu.ClickContext;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SimpleButton implements Button {

    private final Consumer<ClickContext> onRightClick;
    private final Consumer<ClickContext> onLeftClick;
    private final Predicate<ClickContext> canUse;
    private final Consumer<ItemMeta> metaConsumer;
    private Material material;
    private int amount;

    protected SimpleButton(Builder builder) {
        this.onRightClick = builder.onRightClick;
        this.onLeftClick = builder.onLeftClick;
        this.canUse = builder.canUse;
        this.material = builder.material;
        this.amount = builder.amount;
        this.metaConsumer = builder.metaConsumer;
    }

    @Override
    public ItemStack render() {
        ItemStack itemStack = new ItemStack(material,amount);
        itemStack.editMeta(ItemMeta.class, metaConsumer);
        return itemStack;
    }

    @Override
    public boolean canUse(ClickContext context) {
        return canUse.test(context);
    }

    @Override
    public void rightClick(ClickContext context) {
        onRightClick.accept(context);
    }

    @Override
    public void leftClick(ClickContext context) {
        onLeftClick.accept(context);
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static class Builder {

        private final Material material;

        private final List<Component> lore = new ArrayList<>();
        private int amount = 1;
        private Consumer<ClickContext> onRightClick = clickContext -> {};
        private Consumer<ClickContext> onLeftClick = clickContext -> {};
        private Predicate<ClickContext> canUse = clickContext -> true;
        private Consumer<ItemMeta> metaConsumer = itemMeta -> {};
        public Builder(Material material) {
            this.material = material;
        }

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder onRightClick(Consumer<ClickContext> consumer) {
            this.onRightClick = consumer;
            return this;
        }

        public Builder onLeftClick(Consumer<ClickContext> consumer) {
            this.onLeftClick = consumer;
            return this;
        }

        public Builder canUser(Predicate<ClickContext> canUse) {
            this.canUse = canUse;
            return this;
        }

        public Builder name(Component component) {
            metaConsumer = metaConsumer.andThen(itemMeta -> itemMeta.displayName(component));
            return this;
        }

        public Builder lore(List<Component> lore) {
            metaConsumer = metaConsumer.andThen(itemMeta -> itemMeta.lore(lore));
            return this;
        }

        public Builder addLoreLine(Component component) {
            metaConsumer = metaConsumer.andThen(itemMeta -> itemMeta.lore(
                    Stream.concat(Optional.ofNullable(itemMeta.lore()).orElse(List.of()).stream(),Stream.of(component)).toList()
            ));
            return this;
        }

        public  Builder addMeta(Consumer<ItemMeta> metaConsumer) {
            this.metaConsumer = metaConsumer.andThen(metaConsumer);
            return this;
        }

        public SimpleButton build() {
            return new SimpleButton(this);
        }
    }
}
