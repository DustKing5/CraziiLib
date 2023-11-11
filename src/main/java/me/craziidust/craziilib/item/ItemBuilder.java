package me.craziidust.craziilib.item;

import org.apache.commons.lang3.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Consumer;

public class ItemBuilder {

    private final Material material;
    private int amount = 1;

    public ItemBuilder(Material material) {
        this.material = material;
    }

    public ItemBuilder(Material material, int amount) {
        this.material = material;
        Validate.inclusiveBetween(1,64, amount, "The amount not between 0 and 64");
        this.amount = amount;
    }

    public<T extends ItemMeta> ItemStack build(Class<? extends T> metaClass, Consumer<T> consumer) {
        ItemStack itemStack = new ItemStack(material, amount);
        itemStack.editMeta(metaClass, consumer);
        return itemStack;
    }
}
