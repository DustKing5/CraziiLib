package me.craziidust.craziilib.item;

import org.apache.commons.lang3.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Consumer;

public class ItemBuilder {

    private final Material material;
    private int amount = 1;

    /**
     * New ItemBuilder
     * @param material
     */
    public ItemBuilder(Material material) {
        this.material = material;
    }

    /**
     * new ItemBuilder with specified amount
     * @param material
     * @param amount
     */
    public ItemBuilder(Material material, int amount) {
        this.material = material;
        Validate.inclusiveBetween(1,64, amount, "The amount not between 0 and 64");
        this.amount = amount;
    }

    /**
     * Build ItemStack with specified ItemMeta
     * @param metaClass
     * @param consumer Meta editor
     * @return the new ItemStack
     * @param <T> Type of ItemMeta
     */
    public<T extends ItemMeta> ItemStack build(Class<? extends T> metaClass, Consumer<T> consumer) {
        ItemStack itemStack = new ItemStack(material, amount);
        itemStack.editMeta(metaClass, consumer);
        return itemStack;
    }

    /**
     * Build ItemStack
     * @param consumer
     * @return the new ItemStack
     */
    public ItemStack build(Consumer<ItemMeta> consumer) {
        ItemStack itemStack = new ItemStack(material, amount);
        itemStack.editMeta(consumer);
        return itemStack;
    }
}
