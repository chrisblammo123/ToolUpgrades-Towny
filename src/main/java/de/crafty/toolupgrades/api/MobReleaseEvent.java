package de.crafty.toolupgrades.api;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class MobReleaseEvent extends Event implements Cancellable {


    private boolean cancelled;
    private final Player player;
    private Entity releasedEntity;

    public MobReleaseEvent(Player player, Entity releasedEntity) {
        this.cancelled = false;
        this.player = player;
        this.releasedEntity = releasedEntity;

    }


    public Player getPlayer() {
        return this.player;
    }

    public Entity getReleasedEntity() {
        return this.releasedEntity;
    }

    public void setReleasedEntity(Entity releasedEntity) {
        this.releasedEntity = releasedEntity;
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
