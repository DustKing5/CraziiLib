package me.craziidust.craziilib.item;

import me.craziidust.craziilib.item.builders.MetaBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Range;

import java.util.List;

public class ItemBuilder {

    private final ItemStack itemStack;

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemBuilder(Material material, @Range(to = 0, from = 64) int amount) {
        this.itemStack = new ItemStack(material, amount);
    }

    public ItemBuilder addAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        itemStack.editMeta(itemMeta -> itemMeta.addAttributeModifier(attribute, modifier));
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level) {
        itemStack.editMeta(itemMeta -> itemMeta.addEnchant(enchantment, level, true));
        return this;
    }

    public ItemBuilder addFlags(ItemFlag ...flags) {
        itemStack.editMeta(itemMeta -> itemMeta.addItemFlags(flags));
        return this;
    }

    public ItemBuilder customModel(int data) {
        itemStack.editMeta(itemMeta -> itemMeta.setCustomModelData(data));
        return this;
    }

    public ItemBuilder unbreakable(boolean bool) {
        itemStack.editMeta(itemMeta -> itemMeta.setUnbreakable(bool));
        return this;
    }

    public ItemBuilder display(Component textComponent) {
        itemStack.editMeta(itemMeta -> itemMeta.displayName(textComponent));
        return this;
    }

    public ItemBuilder lore(Component ...lines) {
        itemStack.editMeta(itemMeta -> itemMeta.lore(List.of(lines)));
        return this;
    }

    public ItemBuilder lore(List<Component> lore) {
        itemStack.editMeta(itemMeta -> itemMeta.lore(lore));
        return this;
    }

    public <Z> ItemBuilder tag(NamespacedKey key, PersistentDataType<?,Z> type, Z value) {
        itemStack.editMeta(itemMeta -> itemMeta.getPersistentDataContainer().set(key,type,value));
        return this;
    }

    public <Z> ItemBuilder tag(String key, PersistentDataType<?, Z> type, Z value) {
        return tag(NamespacedKey.fromString(key), type, value);
    }

    public ItemBuilder tag(String key, boolean value) {
        return tag(key, PersistentDataType.BOOLEAN, value);
    }

    public ItemBuilder tag(String key, byte value) {
        return tag(key, PersistentDataType.BYTE, value);
    }

    public ItemBuilder tag(String key, byte ...value) {
        return tag(key, PersistentDataType.BYTE_ARRAY, value);
    }

    public ItemBuilder tag(String key, double value) {
        return tag(key, PersistentDataType.DOUBLE, value);
    }

    public ItemBuilder tag(String key, float value) {
        return tag(key, PersistentDataType.FLOAT, value);
    }

    public ItemBuilder tag(String key, int value) {
        return tag(key, PersistentDataType.INTEGER, value);
    }

    public ItemBuilder tag(String key, int ...value) {
        return tag(key, PersistentDataType.INTEGER_ARRAY, value);
    }

    public ItemBuilder tag(String key, long value) {
        return tag(key, PersistentDataType.LONG, value);
    }

    public ItemBuilder tag(String key, long ...value) {
        return tag(key, PersistentDataType.LONG_ARRAY, value);
    }

    public ItemBuilder tag(String key, short value) {
        return tag(key, PersistentDataType.SHORT, value);
    }

    public ItemBuilder tag(String key, String value) {
        return tag(key, PersistentDataType.STRING, value);
    }

    public ItemBuilder cloneMeta(ItemStack src) {
        itemStack.setItemMeta(src.getItemMeta().clone());
        return this;
    }

    public <T extends ItemMeta> ItemBuilder editMeta(MetaBuilder<T> builder) {
        itemStack.editMeta(builder.getType(), builder);
        return this;
    }

    public ItemStack build(){
        return itemStack;
    }
}
