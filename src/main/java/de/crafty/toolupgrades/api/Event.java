package de.crafty.toolupgrades.api;

import org.bukkit.event.HandlerList;

public class Event extends org.bukkit.event.Event {

    private static final HandlerList handlers = new HandlerList();


    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
