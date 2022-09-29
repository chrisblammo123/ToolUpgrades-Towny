package de.crafty.toolupgrades.command;

import de.crafty.toolupgrades.ToolUpgrades;
import de.crafty.toolupgrades.upgrade.ToolUpgrade;
import de.crafty.toolupgrades.upgrade.UpgradeItem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CMD_upgrade implements TabExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!cmd.getName().equalsIgnoreCase("upgrade") || !(sender instanceof Player player))
            return false;


        if (args.length != 1 && args.length != 2)
            return false;

        Player target = CommandUtils.getTarget(args, 1, player);

        if (target == null) {
            sender.sendMessage(ToolUpgrades.PREFIX + "Could not find player \u00a7b" + args[1]);
            return true;
        }


        for (UpgradeItem upgradeItem : UpgradeItem.list()) {
            if (!upgradeItem.getId().equalsIgnoreCase(args[0]))
                continue;

            target.getInventory().addItem(upgradeItem.getStack());
            player.sendMessage(ToolUpgrades.PREFIX + "Gave " + upgradeItem.getUpgrade().getDisplayName() + " \u00a77Upgrade Item to \u00a7b" + target.getName());
            return true;
        }


        sender.sendMessage(ToolUpgrades.PREFIX + "Unknown Upgrade Item > \u00a7b" + args[0] + " \u00a77<");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        List<String> list = new ArrayList<>();

        if (args.length == 1)
            CommandUtils.fetchUpgradeItems(args[0], list);


        if (args.length == 2)
            Bukkit.getOnlinePlayers().stream().filter(player -> player.getName().toUpperCase().startsWith(args[1].toUpperCase())).forEach(player -> list.add(player.getName()));

        return list;
    }


}
