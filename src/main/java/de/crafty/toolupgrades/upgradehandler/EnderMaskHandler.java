package de.crafty.toolupgrades.upgradehandler;

import de.crafty.toolupgrades.upgrade.ToolUpgrade;
import de.crafty.toolupgrades.util.ToolManager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class EnderMaskHandler implements Listener {



    @EventHandler
    public void onEnderMask(EntityTargetLivingEntityEvent event){


        if(event.getReason() != EntityTargetEvent.TargetReason.CLOSEST_PLAYER || event.getEntityType() != EntityType.ENDERMAN || !(event.getTarget() instanceof Player player))
            return;

        if(!ToolManager.hasUpgrade(player.getInventory().getArmorContents()[3], ToolUpgrade.ENDER_MASK))
            return;

        event.setCancelled(true);
    }

}
