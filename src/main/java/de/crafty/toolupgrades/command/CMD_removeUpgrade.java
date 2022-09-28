package de.crafty.toolupgrades.command;

import de.crafty.toolupgrades.ToolUpgrades;
import de.crafty.toolupgrades.upgrade.ToolUpgrade;
import de.crafty.toolupgrades.util.ToolManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CMD_removeUpgrade implements TabExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if (!cmd.getName().equals("removeUpgrade") || (args.length != 1 && args.length != 2))
            return false;


        Player target = CommandUtils.getTarget(args, 1, sender instanceof Player p ? p : null);

        if(target == null){
            sender.sendMessage(ToolUpgrades.PREFIX + "Could not find player \u00a7b" + args[1]);
            return true;
        }

        ItemStack targetStack = target.getInventory().getItemInMainHand();

        for (ToolUpgrade upgrade : ToolUpgrade.values()) {
            if (!upgrade.name().equals(args[0].toUpperCase()))
                continue;

            if (!ToolManager.hasUpgrade(targetStack, upgrade)) {
                sender.sendMessage(ToolUpgrades.PREFIX + "Upgrade " + upgrade.getDisplayName() + "\u00a77 is not applied to \u00a7c" + targetStack.getType().toString().toLowerCase());
                return true;
            }


            ToolManager.removeUpgrade(targetStack, upgrade);
            sender.sendMessage(ToolUpgrades.PREFIX + "Upgrade " + upgrade.getDisplayName() + " \u00a77has been removed from \u00a7b" + targetStack.getType().toString().toLowerCase());
            return true;
        }

        sender.sendMessage(ToolUpgrades.PREFIX + "Unknown Upgrade > \u00a7b" + args[0] + " \u00a77<");
        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        List<String> list = new ArrayList<>();

        if (args.length == 1)
            CommandUtils.fetchUpgrades(args[0], list);

        if (args.length == 2)
            CommandUtils.fetchPlayerNames(args[1], list);

        return list;
    }
}
