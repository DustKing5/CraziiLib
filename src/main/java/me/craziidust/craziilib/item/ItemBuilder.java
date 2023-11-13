package me.craziidust.craziilib.item;

import me.craziidust.craziilib.item.builders.MetaBuilder;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Range;

import java.util.List;

public class ItemBuilder {

    private final ItemStack itemStack;

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
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

    public ItemBuilder display(TextComponent textComponent) {
        itemStack.editMeta(itemMeta -> itemMeta.displayName(textComponent));
        return this;
    }

    public ItemBuilder lore(TextComponent ...lines) {
        itemStack.editMeta(itemMeta -> itemMeta.lore(List.of(lines)));
        return this;
    }

    public ItemBuilder lore(List<TextComponent> lore) {
        itemStack.editMeta(itemMeta -> itemMeta.lore(lore));
        return this;
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
