package de.crafty.toolupgrades.api;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class MobCaptureEvent extends Event implements Cancellable {


    private boolean cancelled;
    private final Player player;
    private Entity capturedEntity;

    public MobCaptureEvent(Player player, Entity capturedEntity) {
        this.cancelled = false;
        this.player = player;
        this.capturedEntity = capturedEntity;
    }


    public Player getPlayer() {
        return this.player;
    }

    public Entity getCapturedEntity() {
        return this.capturedEntity;
    }


    public void setCapturedEntity(Entity entity) {
        this.capturedEntity = entity;
    }


    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
