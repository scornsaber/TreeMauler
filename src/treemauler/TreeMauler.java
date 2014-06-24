package treemauler;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TreeMauler extends JavaPlugin implements Listener {
	public static Logger log = Logger.getLogger("Minecraft");
	private int radius = 10;

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		log.info("[TreeMauler] Start up.");
	}

	public void onReload() {
		log.info("[TreeMauler] Server reloaded.");
	}

	public void onDisable() {
		log.info("[TreeMauler] Server stopping.");
	}

	@EventHandler
	public void blockBreakListener(BlockBreakEvent event) {
		log.info("[TreeMauler] Block broken:  " + event.getBlock().getType());
		if (event.getBlock().getType() == Material.LOG) {
			if ((event.getPlayer().getItemInHand().getType() == Material.WOOD_AXE)
					|| (event.getPlayer().getItemInHand().getType() == Material.STONE_AXE)
					|| (event.getPlayer().getItemInHand().getType() == Material.IRON_AXE)
					|| (event.getPlayer().getItemInHand().getType() == Material.GOLD_AXE)
					|| (event.getPlayer().getItemInHand().getType() == Material.DIAMOND_AXE)) {
				Location loc = event.getBlock().getLocation();
				breakLog(loc, loc.getBlockX(), loc.getBlockZ());
				log.info("[TreeMauler] Block broken.");

			}
		}
	}

	public void breakLog(Location loc, int x, int z) {
		if (loc.getBlock().getType() == Material.LOG) {
			// Check if current block is within radius of original chopped
			// block.
			int i = loc.getBlockX();
			int j = loc.getBlockZ();
			if ((i >= x - radius) && (i <= x + radius) && (j >= z - radius)
					&& (j <= z + radius)) {
				// break this block
				loc.getBlock().breakNaturally();
				// check all other blocks in a 3x3 ring around current location
				// and above it.
				Location l = loc;
				for (i = -1; i <= 1; i++) {
					for (j = -1; j <= 1; j++) {
						for (int k = 0; k <= 1; j++) {
							if (i != 0 || j != 0 || k != 0) {
								l.setX(i + loc.getBlockX());
								l.setY(k + loc.getBlockY());
								l.setZ(j + loc.getBlockZ());
								breakLog(l, x, z);
							}
						}
					}
				}
			}
		}
	}

	public boolean onCommand(CommandSender sender, Command command,
			String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("treemauler")) {
			if (sender instanceof Player) {
				Player me = (Player) sender;
				// Put your code after this line:

				// ...and finish your code before this line.
				return true;
			}
		}
		return false;
	}
}
