package user.uziza.RTD.RTDItems;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BindedItem implements Listener {
	
	public void giveItems(Player player, String CMD, String click) {
		ItemStack item = player.getInventory().getItemInMainHand();
		ItemMeta meta = item.getItemMeta();
		
		if (meta.getLore() == null) {
			//if it contains other lore?
			ArrayList<String> lore = new ArrayList<String>();
			lore.add("(RTD)");
			if (click.toLowerCase() == "ambidextrous") {
				lore.add("both hands" + ": " + CMD);
			}
			else if (click.toLowerCase() == "left hand") {
				lore.add("left hand" + ": " + CMD);
				lore.add("right hand" + ": " + "");
			}
			else if (click.toLowerCase() == "right hand") {
				lore.add("left hand" + ": " + "");
				lore.add("right hand"+ ": " + CMD);
			}
			meta.setLore(lore);
			item.setItemMeta(meta);
		}
		else if (meta.getLore().get(0).contains("(RTD)")) {
			if (click == "left hand") {
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("(RTD)");
				lore.add("left hand"+ ": " + CMD);
				lore.add(meta.getLore().get(2));
				meta.setLore(lore);
				item.setItemMeta(meta);
			}
			else if (click == "right hand") {
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("(RTD)");
				lore.add(meta.getLore().get(1));
				lore.add("right hand"+ ": " + CMD);
				meta.setLore(lore);
				item.setItemMeta(meta);
			}
			else {
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("(RTD)");
				lore.add("both hands" + ": " + CMD);
				meta.setLore(lore);
				item.setItemMeta(meta);
			}
		}
	}
	
	public void distanceStick(Player player) {
		ItemStack item = player.getInventory().getItemInMainHand();
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName("Distance stick");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("DS");
		meta.setLore(lore);
		item.setItemMeta(meta);
	}
}
