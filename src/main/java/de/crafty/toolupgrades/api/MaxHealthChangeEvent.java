package de.crafty.toolupgrades.api;

import org.bukkit.entity.Player;

public class MaxHealthChangeEvent extends Event {


    private final Player player;
    private final double oldHealth;
    private double newHealth;

    public MaxHealthChangeEvent(Player player, double oldHealth, double newHealth) {
        this.player = player;
        this.oldHealth = oldHealth;
        this.newHealth = newHealth;
    }

    public Player getPlayer() {
        return this.player;
    }

    public double getOldHealth() {
        return this.oldHealth;
    }

    public double getNewHealth() {
        return this.newHealth;
    }

    public void setNewHealth(double newHealth) {
        this.newHealth = newHealth;
    }
}
