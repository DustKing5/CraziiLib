package me.craziidust.craziilib.teleport.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public abstract class TeleportEvent extends Event {

    abstract Player getSender();
}
