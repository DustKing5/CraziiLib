package me.craziidust.teleport;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public interface TeleportManager {

    default void senderToTarget(Player sender, Player target) {
        senderToTarget(sender,target,false);
    }

    void senderToTarget(Player sender,Player target, boolean instant);

    default void targetToSender(Player sender, Player target) {
        targetToSender(sender,target,false);
    }

    void targetToSender(Player sender, Player target, boolean instant);

    default void senderToLocation(Player sender, Location location){
        senderToLocation(sender,location,false);
    }

    void senderToLocation(Player player, Location location, boolean instant);

    void cancelTeleport(Player player);

    void acceptTeleport(Player target, Player sender);

    void refuseTeleport(Player target, Player sender);

    List<Player> getTeleportRequests(Player target);
}
