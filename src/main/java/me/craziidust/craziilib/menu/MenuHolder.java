package me.craziidust.craziilib.menu;

import org.bukkit.entity.Player;

import java.util.Map;

public interface MenuHolder {

    interface SingleMenuHolder {

        Menu getMenu();

        default void open(Player player) {
            getMenu().open(player);
        }
    }

    interface MultipleMenuHolder {

        Map<String, Menu> getMenus();

        default Menu getMenu(String id) {
            return getMenus().get(id);
        }
        default void open(Player player, String id) {
            getMenu(id).open(player);
        }
    }

    interface PerPlayerMenuHolder {

        Menu getMenu(Player player);

        default void open(Player player) {
            getMenu(player).open(player);
        }
    }
}
