package me.craziidust.craziilib.menu;

import me.craziidust.craziilib.menu.buttons.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public record ClickContext(Player player, Button button, ClickType type) {
}
