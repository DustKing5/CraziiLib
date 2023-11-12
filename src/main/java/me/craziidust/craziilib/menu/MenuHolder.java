package me.craziidust.craziilib.menu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;

import java.util.Map;

public interface MenuHolder {

    Menu getMenu(@Nullable Player player, @Nullable String id);

    default Menu getMenu(Player player) {
        return getMenu(player, null);
    }

    default Menu getMenu(String id) {
        return getMenu(null, id);
    }
}
