package me.craziidust.craziilib.teleport.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerTeleportToPlayerEvent extends TeleportEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player sender,target;
    private boolean cancelled = false;

    public PlayerTeleportToPlayerEvent(Player sender, Player target) {
        this.sender = sender;
        this.target = target;
    }

    @Override
    public Player getSender() {
        return sender;
    }

    public Player getTarget() {
        return target;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
