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

public class CMD_applyUpgrade implements TabExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!"applyUpgrade".equals(cmd.getName()) || (args.length != 1 && args.length != 2))
            return false;


        Player target = CommandUtils.getTarget(args, 1, sender instanceof Player p ? p : null);

        if (target == null) {
            sender.sendMessage(ToolUpgrades.PREFIX + "Could not find player \u00a7b" + args[1]);
            return true;
        }


        ItemStack targetStack = target.getInventory().getItemInMainHand();

        for (ToolUpgrade upgrade : ToolUpgrade.values()) {
            if (!upgrade.name().equals(args[0].toUpperCase()))
                continue;

            if (!ToolManager.canApplyTo(targetStack, upgrade)) {

                if (ToolManager.hasUpgrade(targetStack, upgrade))
                    sender.sendMessage(ToolUpgrades.PREFIX + "Upgrade " + upgrade.getDisplayName() + "\u00a77 has already been applied to \u00a7c" + targetStack.getType().toString().toLowerCase());
                else
                    sender.sendMessage(ToolUpgrades.PREFIX + "Upgrade " + upgrade.getDisplayName() + "\u00a77 cannot be applied to \u00a7c" + targetStack.getType().toString().toLowerCase());
                return true;
            }

            ToolManager.applyUpgrade(targetStack, upgrade);
            sender.sendMessage(ToolUpgrades.PREFIX + "Upgrade " + upgrade.getDisplayName() + " \u00a77has been applied to \u00a7b" + targetStack.getType().toString().toLowerCase());
            return true;
        }

        sender.sendMessage(ToolUpgrades.PREFIX + "Unknown Upgrade > \u00a7b" + args[0] + " \u00a77<");
        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        List<String> list = new ArrayList<>();

        if (!cmd.getName().equals("applyUpgrade"))
            return list;

        if (args.length == 1)
            CommandUtils.fetchUpgrades(args[0], list);

        if (args.length == 2)
            CommandUtils.fetchPlayerNames(args[1], list);


        return list;
    }
}
