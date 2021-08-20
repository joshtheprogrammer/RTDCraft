package user.uziza.RTD.RTDItems;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DistanceStick implements Listener {
	
	private Map<UUID, Location> point = new HashMap<>();
	

	@EventHandler
	public void getDistance(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Action a = e.getAction();
		ItemStack item = player.getInventory().getItemInMainHand();
		ItemMeta meta = item.getItemMeta();
		
		if (e.getHand() != null) {
			if (e.getHand().equals(EquipmentSlot.HAND)) {
				if (meta != null) {
					if (meta.getLore() != null) {
						if (item.getType().equals(Material.STICK)) {
							if (meta.getLore().contains("DS")) {
								if (a.equals(Action.RIGHT_CLICK_BLOCK)) {
									if (point.containsKey(player.getUniqueId())) {
										player.sendMessage("Point B: " + e.getClickedBlock().getType());
										player.sendMessage(String.valueOf(point.get(player.getUniqueId()).distance(e.getClickedBlock().getLocation())));
										point.remove(player.getUniqueId());
									}
									else {
										player.sendMessage("Point A: " + e.getClickedBlock().getType());
										point.put(player.getUniqueId(), e.getClickedBlock().getLocation());
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void getEntityDistance(PlayerInteractEntityEvent e2) {
		Player player = e2.getPlayer();
		Entity victim = e2.getRightClicked();
		
		ItemStack item = player.getInventory().getItemInMainHand();
		ItemMeta meta = item.getItemMeta();
		
		
		if (e2.getHand().equals(EquipmentSlot.HAND)) {
			if (meta != null) {
				if (meta.getLore() != null) {
					if (item.getType().equals(Material.STICK)) {
						if (meta.getLore().contains("DS")) {
							if (victim != null) {
								if (point.containsKey(player.getUniqueId())) {
									player.sendMessage("Point B: " + victim.getType());
									player.sendMessage(String.valueOf(point.get(player.getUniqueId()).distance(victim.getLocation())));
									point.remove(player.getUniqueId());
								}
								else {
									player.sendMessage("Point A: " + victim.getType());
									point.put(player.getUniqueId(), victim.getLocation());
								}
							}
						}
					}
				}
			}
		}
	}
}
