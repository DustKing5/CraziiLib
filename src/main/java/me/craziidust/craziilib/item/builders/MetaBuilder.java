package me.craziidust.craziilib.item.builders;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface MetaBuilder<T extends ItemMeta> extends Consumer<T> {

    @NotNull Class<T> getType();

    static ArmorBuilder armor(TrimMaterial material, TrimPattern pattern) {
        return new ArmorBuilder(material, pattern);
    }

    static BannerBuilder banner() {
        return new BannerBuilder();
    }

    static BannerBuilder banner(List<Pattern> patterns) {
        return new BannerBuilder(patterns);
    }

    static BannerBuilder banner(Pattern ...patterns) {
        return new BannerBuilder(Arrays.stream(patterns).toList());
    }

    static BannerBuilder banner(DyeColor color, PatternType patternType) {
        return new BannerBuilder(color, patternType);
    }

    static BannerBuilder banner(Map<DyeColor, PatternType> patterns) {
        return new BannerBuilder(patterns.entrySet().stream().map(entry -> new Pattern(entry.getKey(), entry.getValue())).toList());
    }

    static BookBuilder book() {
        return new BookBuilder();
    }

    static ColorableBuilder colorable(Color color, TrimMaterial trimMaterial, TrimPattern pattern) {
        return new ColorableBuilder(color, trimMaterial, pattern);
    }

    static PotionBuilder potion(PotionType type) {
        return new PotionBuilder(type);
    }

    static PotionBuilder potion(PotionType type, Color color) {
        return new PotionBuilder(type, color);
    }
}
