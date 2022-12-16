package de.crafty.toolupgrades.upgradehandler;

import de.crafty.toolupgrades.ToolUpgrades;
import de.crafty.toolupgrades.upgrade.ToolUpgrade;
import de.crafty.toolupgrades.util.ToolManager;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

// Towny API Import
import com.palmergames.bukkit.towny.TownyAPI;

import java.util.ArrayList;
import java.util.List;

public class MultiMinerHandler implements Listener {



	private final List<ItemStack> currentlyInUse = new ArrayList<>();

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {

		Player player = event.getPlayer();
		ItemStack usedStack = player.getInventory().getItemInMainHand();

		if (!ToolManager.hasUpgrade(usedStack, ToolUpgrade.MULTI_MINER) || player.isSneaking() || currentlyInUse.contains(usedStack))
			return;


		List<Block> located = this.locateBlocks(event.getBlock(), ToolUpgrades.getInstance().multiMinerMaxBlocks());


		currentlyInUse.add(usedStack);
		located.forEach(b -> {

			if(usedStack.getType() == Material.AIR)
				return;

				//checking if a player can build/destyroy somewhere
				// boolean bBuild = PlayerCacheUtil.getCachePermission(player, block.getLocation(), block.getType(), TownyPermission.ActionType.BUILD);
			if (PlayerCacheUtil.getCachePermission(player, b.getLocation(), b.getType(), TownyPermission.ActionType.DESTROY)) {
				
				// Logging for testing purposes
				console.log(String.format("%s is breaking block at %s, of type %s", player.getDisplayName(), b.getLocation().toString(), b.getType()));
				
				// if player is in town/has perms, break block
				player.breakBlock(b);
			}
			else {
				// Log the failure
				console.log(String.format("%s failed to break block at %s, of type %s", player.getDisplayName(), b.getLocation().toString(), b.getType()))
			}

		});

		currentlyInUse.remove(usedStack);
	}


	private List<Block> locateBlocks(Block src, int max) {
		List<Block> blocks = this.getAttachedBlocks(src);
		List<Block> tmpBlocks = new ArrayList<>(blocks);

		while (tmpBlocks.size() > 0) {

			List<Block> currentBlocks = new ArrayList<>(tmpBlocks);
			tmpBlocks.clear();

			currentBlocks.forEach(block -> {


				List<Block> attached = this.getAttachedBlocks(block).stream().filter(b -> !blocks.contains(b) && !b.equals(src)).toList();
				for (Block b : attached) {
					if (blocks.size() >= max)
						break;

					blocks.add(b);
					tmpBlocks.add(b);
				}
			});

		}

		return blocks;
	}

	private List<Block> getAttachedBlocks(Block src) {

		List<Block> list = new ArrayList<>();

		World world = src.getWorld();

		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				for (int y = -1; y <= 1; y++) {

					if (x == 0 && y == 0 && z == 0)
						continue;

					Block block = world.getBlockAt(src.getX() + x, src.getY() + y, src.getZ() + z);
					if (block.getType() == src.getType())
						list.add(block);

				}
			}
		}

		return list;

	}

}
