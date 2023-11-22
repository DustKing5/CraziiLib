package me.craziidust.craziilib.item.builders;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.meta.BookMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static org.bukkit.inventory.meta.BookMeta.Generation;

public final class BookBuilder implements MetaBuilder<BookMeta> {

    private Component author, title;
    private Generation generation;
    private List<Component> pages;

    BookBuilder() {
    }

    public BookBuilder author(Component author) {
        this.author = author;
        return this;
    }

    public BookBuilder title(Component title) {
        this.title = title;
        return this;
    }

    public BookBuilder generation(Generation generation) {
        this.generation = generation;
        return this;
    }

    public BookBuilder addPage(Component component) {
        pages.add(component);
        return this;
    }

    public BookBuilder addPages(Component ...components) {
        return addPages(List.of(components));
    }

    public BookBuilder addPages(List<Component> components) {
        pages.addAll(components);
        return this;
    }

    @Override
    public @NotNull Class<BookMeta> getType() {
        return BookMeta.class;
    }

    @Override
    public void accept(BookMeta bookMeta) {
        bookMeta.author(author);
        bookMeta.title(title);
        bookMeta.setGeneration(generation);
        bookMeta.addPages(pages.toArray(new Component[0]));
    }
}
