package de.crafty.toolupgrades.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class UpgradeRecipeViewListener implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if(event.getView().getTitle().startsWith("\u00a77Upgrade: "))
            event.setCancelled(true);

    }
}
