package de.crafty.toolupgrades.upgradehandler;

import de.crafty.toolupgrades.upgrade.ToolUpgrade;
import de.crafty.toolupgrades.util.ToolManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class SoftFallHandler implements Listener {



    @EventHandler
    public  void onFallDamage(EntityDamageEvent event){

        if(event.getCause() != EntityDamageEvent.DamageCause.FALL)
            return;

        if(!(event.getEntity() instanceof Player player))
            return;

        ItemStack bootsStack = player.getInventory().getArmorContents()[0];

        if(!ToolManager.hasUpgrade(bootsStack, ToolUpgrade.SOFT_FALL))
            return;

        event.setCancelled(true);

    }

}
