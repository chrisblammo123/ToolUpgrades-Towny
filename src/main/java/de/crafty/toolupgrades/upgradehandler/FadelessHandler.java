package de.crafty.toolupgrades.upgradehandler;

import de.crafty.toolupgrades.ToolUpgrades;
import de.crafty.toolupgrades.upgrade.ToolUpgrade;
import de.crafty.toolupgrades.util.ToolManager;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FadelessHandler implements Listener {


    private final File CACHE_FILE = new File("plugins/ToolUpgrades", "fadelessItemCache.yml");
    private final FileConfiguration CONFIG = YamlConfiguration.loadConfiguration(CACHE_FILE);


    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        Player player = event.getEntity();
        PlayerInventory playerInv = player.getInventory();

        for (ItemStack stack : playerInv.getStorageContents()) {
            if (!ToolManager.hasUpgrade(stack, ToolUpgrade.FADELESS))
                continue;

            this.cacheItem(player, stack);
            event.getDrops().remove(stack);
        }

        ItemStack[] armorContents = playerInv.getArmorContents();

        for (int i = 0; i <= armorContents.length; i++) {

            ItemStack armorStack = i < armorContents.length ? armorContents[i] : playerInv.getItemInOffHand();

            if (!ToolManager.hasUpgrade(armorStack, ToolUpgrade.FADELESS))
                continue;

            ItemMeta meta = armorStack.getItemMeta();
            meta.getPersistentDataContainer().set(new NamespacedKey(ToolUpgrades.getInstance(), "fadelessArmorSlot"), PersistentDataType.INTEGER, i);
            armorStack.setItemMeta(meta);

            this.cacheItem(player, armorStack);
            event.getDrops().remove(armorStack);
        }


    }


    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {

        Player player = event.getPlayer();
        PlayerInventory playerInv = player.getInventory();

        this.loadCache(player).forEach(stack -> {

            ItemMeta meta = stack.getItemMeta();

            boolean b = meta.getPersistentDataContainer().has(new NamespacedKey(ToolUpgrades.getInstance(), "fadelessArmorSlot"), PersistentDataType.INTEGER);

            if (b) {
                int slot = meta.getPersistentDataContainer().get(new NamespacedKey(ToolUpgrades.getInstance(), "fadelessArmorSlot"), PersistentDataType.INTEGER);

                if (slot == 3)
                    playerInv.setHelmet(stack);

                if (slot == 2)
                    playerInv.setChestplate(stack);

                if (slot == 1)
                    playerInv.setLeggings(stack);

                if (slot == 0)
                    playerInv.setBoots(stack);

                if (slot == 4)
                    playerInv.setItemInOffHand(stack);

                meta.getPersistentDataContainer().remove(new NamespacedKey(ToolUpgrades.getInstance(), "fadelessArmorSlot"));
                stack.setItemMeta(meta);
            }

            if (!b)
                playerInv.addItem(stack);
        });

    }


    private void cacheItem(Player player, ItemStack stack) {

        List<ItemStack> list = this.loadCache(player);
        list.add(stack);

        for (int i = 0; i < list.size(); i++) {
            CONFIG.set(player.getUniqueId() + "." + i, list.get(i));
        }

        try {
            CONFIG.save(CACHE_FILE);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage(ToolUpgrades.PREFIX + "Failed to cache Items for " + player.getName() + "(" + player.getUniqueId() + ")");
        }

    }

    private List<ItemStack> loadCache(Player player) {

        List<ItemStack> list = new ArrayList<>();

        if (CONFIG.isSet(player.getUniqueId().toString())) {
            CONFIG.getConfigurationSection(player.getUniqueId().toString()).getKeys(false).forEach(key -> {

                list.add(CONFIG.getItemStack(player.getUniqueId() + "." + key));

            });
        }


        CONFIG.set(player.getUniqueId().toString(), null);

        try {
            CONFIG.save(CACHE_FILE);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage(ToolUpgrades.PREFIX + "Failed to load Items for " + player.getName() + "(" + player.getUniqueId() + ")");
        }

        return list;
    }


}
