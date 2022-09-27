package de.crafty.toolupgrades.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class AutoSmeltEvent extends Event implements Cancellable {


    private boolean cancelled;

    private final Player player;
    private final ItemStack input;
    private ItemStack output;
    private final FurnaceRecipe recipe;

    public AutoSmeltEvent(Player player, ItemStack input, ItemStack output, FurnaceRecipe recipe) {
        this.cancelled = false;
        this.player = player;
        this.input = input;
        this.output = output;
        this.recipe = recipe;
    }


    public Player getPlayer() {
        return this.player;
    }

    public ItemStack getInput() {
        return this.input;
    }

    public ItemStack getOutput() {
        return this.output;
    }

    public FurnaceRecipe getRecipe() {
        return this.recipe;
    }

    public void setOutput(ItemStack output) {
        this.output = output;
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
