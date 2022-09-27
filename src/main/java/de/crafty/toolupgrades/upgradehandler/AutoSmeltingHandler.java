package de.crafty.toolupgrades.upgradehandler;

import de.crafty.toolupgrades.api.AutoSmeltEvent;
import de.crafty.toolupgrades.upgrade.ToolUpgrade;
import de.crafty.toolupgrades.util.ToolManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AutoSmeltingHandler implements Listener {


    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreak(BlockDropItemEvent event) {

        Player player = event.getPlayer();
        ItemStack usedStack = player.getInventory().getItemInMainHand();

        if (!ToolManager.hasUpgrade(usedStack, ToolUpgrade.AUTO_SMELTING) || player.getGameMode() == GameMode.CREATIVE)
            return;


        List<FurnaceRecipe> recipes = this.getFurnaceRecipes();

        event.getItems().forEach(item -> {

            ItemStack stack = item.getItemStack();


            for (FurnaceRecipe recipe : recipes) {
                if (!recipe.getInputChoice().test(stack))
                    continue;

                ItemStack result = recipe.getResult();
                result.setAmount(stack.getAmount() * recipe.getResult().getAmount());

                AutoSmeltEvent e = new AutoSmeltEvent(player, stack, result, recipe);
                Bukkit.getPluginManager().callEvent(e);

                if (e.isCancelled())
                    return;

                item.setItemStack(e.getOutput());
                break;
            }
        });

    }


    private List<FurnaceRecipe> getFurnaceRecipes() {
        List<FurnaceRecipe> recipes = new ArrayList<>();

        Bukkit.recipeIterator().forEachRemaining(recipe -> {
            if (recipe instanceof FurnaceRecipe furnaceRecipe)
                recipes.add(furnaceRecipe);
        });

        return recipes;
    }

}
