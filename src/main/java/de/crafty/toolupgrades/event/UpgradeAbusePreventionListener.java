package de.crafty.toolupgrades.event;

import de.crafty.toolupgrades.upgrade.UpgradeItem;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.SmithingInventory;

public class UpgradeAbusePreventionListener implements Listener {


    @EventHandler
    public void onItemClick$0(InventoryClickEvent event) {

        if(event.getInventory() instanceof SmithingInventory || event.getInventory() instanceof PlayerInventory || event.getWhoClicked().getGameMode() == GameMode.CREATIVE)
            return;

        if(UpgradeItem.getByStack(event.getCurrentItem()) != null)
            event.setCancelled(true);


        if(event.getHotbarButton() >= 0 && UpgradeItem.getByStack(event.getView().getBottomInventory().getItem(event.getHotbarButton())) != null)
            event.setCancelled(true);
    }

}
