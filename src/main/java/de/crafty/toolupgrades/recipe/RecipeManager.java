package de.crafty.toolupgrades.recipe;

import de.crafty.toolupgrades.ToolUpgrades;
import de.crafty.toolupgrades.upgrade.UpgradeItem;
import net.minecraft.world.item.enchantment.EnchantmentManager;
import net.minecraft.world.item.enchantment.EnchantmentThorns;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.awt.*;

public class RecipeManager {


    public static void registerRecipes() {

        Bukkit.getConsoleSender().sendMessage(ToolUpgrades.PREFIX + "\u00a7aLoading Recipes...");

        //Magnetism
        ShapedRecipe magnetism_upgrade = new ShapedRecipe(new NamespacedKey(ToolUpgrades.getInstance(), "magnetism_upgrade"), UpgradeItem.MAGNETISM.getStack());
        magnetism_upgrade.shape("SXP", "XEX", "PXS");
        magnetism_upgrade.setIngredient('X', Material.DIAMOND);
        magnetism_upgrade.setIngredient('E', Material.ENDER_PEARL);
        magnetism_upgrade.setIngredient('S', Material.DIAMOND_SWORD);
        magnetism_upgrade.setIngredient('P', Material.DIAMOND_PICKAXE);

        Bukkit.addRecipe(magnetism_upgrade);


        //Ore Smelter
        ShapedRecipe auto_smelting_upgrade = new ShapedRecipe(new NamespacedKey(ToolUpgrades.getInstance(), "ore_smelter_upgrade"), UpgradeItem.AUTO_SMELTING.getStack());
        auto_smelting_upgrade.shape("XOX", "OFO", "XOX");
        auto_smelting_upgrade.setIngredient('X', Material.LAVA_BUCKET);
        auto_smelting_upgrade.setIngredient('O', Material.OBSIDIAN);
        auto_smelting_upgrade.setIngredient('F', Material.FURNACE);

        Bukkit.addRecipe(auto_smelting_upgrade);

        //Multi Miner
        ShapedRecipe multi_miner_upgrade = new ShapedRecipe(new NamespacedKey(ToolUpgrades.getInstance(), "multi_miner_upgrade"), UpgradeItem.MULTI_MINER.getStack());
        multi_miner_upgrade.shape("DCD", "CNC", "DCD");
        multi_miner_upgrade.setIngredient('D', Material.DEEPSLATE);
        multi_miner_upgrade.setIngredient('C', Material.COBBLESTONE);
        multi_miner_upgrade.setIngredient('N', Material.NETHERITE_INGOT);

        Bukkit.addRecipe(multi_miner_upgrade);


        //Teleportation
        ShapedRecipe teleportation_upgrade = new ShapedRecipe(new NamespacedKey(ToolUpgrades.getInstance(), "teleportation_upgrade"), UpgradeItem.TELEPORTATION.getStack());
        teleportation_upgrade.shape("EPE", "PSP", "EPE");
        teleportation_upgrade.setIngredient('E', Material.END_STONE);
        teleportation_upgrade.setIngredient('P', Material.ENDER_PEARL);
        teleportation_upgrade.setIngredient('S', Material.DIAMOND_SWORD);

        Bukkit.addRecipe(teleportation_upgrade);

        //Fadeless
        ShapedRecipe fadeless_upgrade = new ShapedRecipe(new NamespacedKey(ToolUpgrades.getInstance(), "fadeless_upgrade"), UpgradeItem.FADELESS.getStack());
        fadeless_upgrade.shape("XYX", "YWY", "XYX");
        fadeless_upgrade.setIngredient('X', Material.GLOWSTONE);
        fadeless_upgrade.setIngredient('Y', Material.GOLD_INGOT);
        fadeless_upgrade.setIngredient('W', Material.WITHER_SKELETON_SKULL);

        Bukkit.addRecipe(fadeless_upgrade);


        //Soft Fall
        ShapedRecipe soft_fall_upgrade = new ShapedRecipe(new NamespacedKey(ToolUpgrades.getInstance(), "soft_fall_upgrade"), UpgradeItem.SOFT_FALL.getStack());
        soft_fall_upgrade.shape("PFP", "FBF", "PFP");
        soft_fall_upgrade.setIngredient('P', Material.POWDER_SNOW_BUCKET);
        soft_fall_upgrade.setIngredient('F', Material.FEATHER);
        soft_fall_upgrade.setIngredient('B', Material.DIAMOND_BOOTS);

        Bukkit.addRecipe(soft_fall_upgrade);


        //Ender Mask
        ShapedRecipe ender_mask_upgrade = new ShapedRecipe(new NamespacedKey(ToolUpgrades.getInstance(), "ender_mask_upgrade"), UpgradeItem.ENDER_MASK.getStack());
        ender_mask_upgrade.shape("EEE", "EPE", "EEE");
        ender_mask_upgrade.setIngredient('E', Material.END_STONE);
        ender_mask_upgrade.setIngredient('P', Material.CARVED_PUMPKIN);

        Bukkit.addRecipe(ender_mask_upgrade);

        //Mob Capture
        ShapedRecipe mob_capture_upgrade = new ShapedRecipe(new NamespacedKey(ToolUpgrades.getInstance(), "mob_capture_upgrade"), UpgradeItem.MOB_CAPTURE.getStack());
        mob_capture_upgrade.shape("CRC", "RBR", "CRC");
        mob_capture_upgrade.setIngredient('C', Material.COBWEB);
        mob_capture_upgrade.setIngredient('R', Material.ROTTEN_FLESH);
        mob_capture_upgrade.setIngredient('B', Material.BUCKET);

        Bukkit.addRecipe(mob_capture_upgrade);


        //Silky
        ShapedRecipe silky_upgrade = new ShapedRecipe(new NamespacedKey(ToolUpgrades.getInstance(), "silky_upgrade"), UpgradeItem.SILKY.getStack());
        silky_upgrade.shape("WSW", "SBS", "WSW");
        silky_upgrade.setIngredient('W', Material.WHITE_WOOL);
        silky_upgrade.setIngredient('S', Material.STRING);
        silky_upgrade.setIngredient('B', Material.NETHER_STAR);

        Bukkit.addRecipe(silky_upgrade);


        //Life Bonus
        ShapedRecipe life_bonus_upgrade = new ShapedRecipe(new NamespacedKey(ToolUpgrades.getInstance(), "life_bonus_upgrade"), UpgradeItem.LIFE_BONUS.getStack());
        life_bonus_upgrade.shape("BGB", "GNG", "BGB");
        life_bonus_upgrade.setIngredient('B', Material.BLAZE_POWDER);
        life_bonus_upgrade.setIngredient('G', Material.GLISTERING_MELON_SLICE);
        life_bonus_upgrade.setIngredient('N', Material.NETHERITE_INGOT);

        Bukkit.addRecipe(life_bonus_upgrade);

        Bukkit.getConsoleSender().sendMessage(ToolUpgrades.PREFIX + "\u00a7aRecipes have been loaded");
    }


}
