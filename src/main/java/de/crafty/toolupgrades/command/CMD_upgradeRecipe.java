package de.crafty.toolupgrades.command;

import de.crafty.toolupgrades.ToolUpgrades;
import de.crafty.toolupgrades.recipe.RecipeManager;
import de.crafty.toolupgrades.upgrade.UpgradeItem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CMD_upgradeRecipe implements TabExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!"upgradeRecipe".equals(cmd.getName()) || !(sender instanceof Player player))
            return false;


        if (args.length != 1)
            return false;

        for (ShapedRecipe recipe : RecipeManager.recipes()) {

            if (!recipe.getKey().getKey().equals(args[0]))
                continue;


            Inventory inv = Bukkit.createInventory(null, InventoryType.WORKBENCH, "\u00a77Upgrade: " + UpgradeItem.getByStack(recipe.getResult()).getUpgrade().getDisplayName());
            InventoryView view = player.openInventory(inv);

            List<Character> chars = new ArrayList<>();
            for (int i = 0; i < recipe.getShape().length; i++) {
                for(int j = 0; j < recipe.getShape()[i].toCharArray().length; j++){
                    chars.add(recipe.getShape()[i].toCharArray()[j]);
                }
            }

            for (int i = 0; i < 9; i++) {
                view.setItem(i + 1, recipe.getIngredientMap().get(chars.get(i)));
            }

            view.setItem(0, recipe.getResult());

            return true;
        }

        player.sendMessage(ToolUpgrades.PREFIX + "Could not find recipe for \u00a7c" + args[0]);

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        List<String> list = new ArrayList<>();

        if (!"upgradeRecipe".equals(cmd.getName()))
            return list;

        if (args.length == 1)
            CommandUtils.fetchUpgradeRecipes(args[0], list);

        return list;
    }
}
