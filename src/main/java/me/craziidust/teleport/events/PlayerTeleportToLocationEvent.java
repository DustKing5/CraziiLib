package me.craziidust.teleport.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerTeleportToLocationEvent extends TeleportEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player sender;
    private final Location location;
    private boolean cancelled = false;

    public PlayerTeleportToLocationEvent(Player sender, Location location) {
        this.sender = sender;
        this.location = location;
    }

    @Override
    Player getSender() {
        return sender;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
