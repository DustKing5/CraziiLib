package me.craziidust.craziilib.menu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public interface MenuHolder {

    Menu getMenu(@Nullable Player player, @Nullable String id);

    void closeMenu();

    default Menu getMenu() {
        return getMenu(null,null);
    }

    default Menu getMenu(Player player) {
        return getMenu(player, null);
    }

    default Menu getMenu(String id) {
        return getMenu(null, id);
    }

    default void openMenu(Player player) {
        getMenu(player).open(player);
    }

    default void openMenu(Player player, String id) {
        getMenu(id).open(player);
    }
}
