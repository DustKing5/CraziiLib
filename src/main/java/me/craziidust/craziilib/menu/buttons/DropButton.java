package me.craziidust.craziilib.menu.buttons;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.craziidust.craziilib.menu.ClickContext;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DropButton implements Button {

    private final Material material;
    private final Consumer<ItemMeta> metaConsumer;
    private final Predicate<ItemStack> isValid;
    private final Consumer<ItemStack> onDrop;
    private ItemStack itemStack = null;

    private DropButton(Builder builder) {
        this.material = builder.material;
        this.metaConsumer = builder.metaConsumer;
        this.isValid = builder.isValid;
        this.onDrop = builder.onDrop;
    }
    @Override
    public ItemStack render() {
        if (itemStack == null) {
            ItemStack itemStack = new ItemStack(material);
            itemStack.editMeta(ItemMeta.class, metaConsumer);
            return itemStack;
        } else {
            return itemStack;
        }

    }

    @Override
    public void rightClick(ClickContext context) {
        itemStack = null;
    }

    @Override
    public void leftClick(ClickContext context) {
        if (context.cursor() != null) {
            if (isValid.test(context.cursor())) {
                itemStack = context.cursor();
                onDrop.accept(itemStack);
            }
        }
    }

    public static class Builder {

        private final Material material;
        private Consumer<ItemMeta> metaConsumer = itemMeta -> {};
        private Predicate<ItemStack> isValid = is -> false;
        private Consumer<ItemStack> onDrop = is -> {};

        public Builder(Material material) {
            this.material = material;
        }

        public Builder name(Component name) {
            metaConsumer = metaConsumer.andThen(itemMeta -> itemMeta.displayName(name));
            return this;
        }

        public Builder lore(List<Component> lore) {
            metaConsumer = metaConsumer.andThen(itemMeta -> itemMeta.lore(lore));
            return this;
        }

        public Builder isValid(Predicate<ItemStack> isValid) {
            this.isValid = isValid;
            return this;
        }

        public Builder onDrop(Consumer<ItemStack> onDrop) {
            this.onDrop = onDrop;
            return this;
        }

        public Builder addMeta(Consumer<ItemMeta> consumer) {
            metaConsumer = metaConsumer.andThen(consumer);
            return this;
        }

        public DropButton build() {
            return new DropButton(this);
        }
    }
}
