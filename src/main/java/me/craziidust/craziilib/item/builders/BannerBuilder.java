package me.craziidust.craziilib.item.builders;

import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.meta.BannerMeta;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public final class BannerBuilder implements MetaBuilder<BannerMeta> {

    private final List<Pattern> patterns;

    BannerBuilder() {
        this.patterns = new LinkedList<>();
    }

    BannerBuilder(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    BannerBuilder(DyeColor color, PatternType type) {
        this.patterns = new LinkedList<>();
        patterns.add(new Pattern(color, type));
    }

    public BannerBuilder addPattern(Pattern pattern) {
        patterns.add(pattern);
        return this;
    }

    public BannerBuilder addPattern(DyeColor color, PatternType type) {
        return addPattern(new Pattern(color, type));
    }

    @Override
    public @NotNull Class<BannerMeta> getType() {
        return BannerMeta.class;
    }

    @Override
    public void accept(BannerMeta bannerMeta) {
        bannerMeta.setPatterns(patterns);
    }
}
