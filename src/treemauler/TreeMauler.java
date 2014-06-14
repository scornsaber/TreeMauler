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
				loc.setY(1 + loc.getBlockY());
				breakLog(loc);
				log.info("[TreeMauler] Block broken.");

			}
		}
	}

	public void breakLog(Location loc) {
		if (loc.getBlock().getType() == Material.LOG) {
			loc.getBlock().breakNaturally();
			loc.setY(1 + loc.getBlockY());
			breakLog(loc);
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
