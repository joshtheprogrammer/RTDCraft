package user.uziza.RTD.RTDEvents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import user.uziza.RTD.RTD;

public class RTDEvents implements Listener {
	RTD plugin;
	public RTDEvents(RTD plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	private void onPlayerAttack(EntityDamageByEntityEvent event) {
	    if (event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			ItemStack item = player.getItemInHand();
		    ItemMeta meta = item.getItemMeta();
		   
		    if (player.getInventory().getItemInHand().getType().name().endsWith("SWORD") || player.getInventory().getItemInHand().getType().name().endsWith("AXE") || player.getInventory().getItemInHand().getType().name().endsWith("PICKAXE") || player.getInventory().getItemInHand().getType().name().endsWith("STICK") || player.getInventory().getItemInHand().getType().name().endsWith("SPADE") || player.getInventory().getItemInHand().getType().name().endsWith("HOE") || player.getInventory().getItemInHand().getType().name().endsWith("BOW")) {
		    	if (meta != null) {
			    	if (meta.getLore() != null) {
				    	if (meta.getLore().get(0).contains("(RTD)")) {
							if (!plugin.getCooldowns().isPlayerInTimeout(player)) {
				    			if (!plugin.getCooldowns().isPlayerInCooldown(player)) {	
							    	Bukkit.dispatchCommand(player, meta.getLore().get(1).replace("left hand: /", ""));
							    	plugin.getCooldowns().addPlayerToCooldown(player, 0);
							    	//plugin.getCooldowns().addPlayerToTimeOut(player);
							   	}
				    			else {
						    		event.setCancelled(true);
						    	}
				    		}
							else {
								if (plugin.getCooldowns().timeSincePlayerInCooldown(player) < 220) {
								    event.setCancelled(true);
								   	plugin.getCooldowns().addPlayerToTimeOut(player);
								}
								else {
								   	plugin.getCooldowns().removePlayerToTimeOut(player);
								   	if (!plugin.getCooldowns().isPlayerInCooldown(player)) {	
								    	Bukkit.dispatchCommand(player, meta.getLore().get(1).replace("left hand: /", ""));
								    	plugin.getCooldowns().addPlayerToCooldown(player, 0);
								    	plugin.getCooldowns().addPlayerToTimeOut(player);
								   	}
								   	else {
							    		event.setCancelled(true);
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
	public void onPlayerClick(PlayerInteractEvent event) {
	    Player player = event.getPlayer();
	    Action action = event.getAction();
	    ItemStack item = player.getItemInHand();
	    ItemMeta meta = item.getItemMeta();
	    
	    //array 
	    if (event.getHand() != null) {
		    if (event.getHand().equals(EquipmentSlot.HAND)) {
		    	if (meta != null) {
			    	if (meta.getLore() != null) {
						if (player.getInventory().getItemInHand().getType().name().endsWith("SWORD") || player.getInventory().getItemInHand().getType().name().endsWith("AXE") || player.getInventory().getItemInHand().getType().name().endsWith("PICKAXE") || player.getInventory().getItemInHand().getType().name().endsWith("STICK") || player.getInventory().getItemInHand().getType().name().endsWith("SPADE") || player.getInventory().getItemInHand().getType().name().endsWith("HOE")) {
							if (meta.getLore().get(0).contains("(RTD)")) {
								if (meta.getLore().get(1).contains("ambidextrous")) {
							    	if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
							    		if (!plugin.getCooldowns().isPlayerInTimeout(player)) {
							    			if (!plugin.getCooldowns().isPlayerInCooldown(player)) {	
							    				Bukkit.dispatchCommand(player, meta.getLore().get(1).replace("both hands: /", ""));
										    	plugin.getCooldowns().addPlayerToCooldown(player, 0);
										    	//plugin.getCooldowns().addPlayerToTimeOut(player);
										   	}
							    			else {
									   			event.setCancelled(true);
									   		}
							    		}
									    else {
									    	if (plugin.getCooldowns().timeSincePlayerInCooldown(player) < 220) {
									    		event.setCancelled(true);
									    		plugin.getCooldowns().addPlayerToTimeOut(player);
									    	}
									    	else {
										    	plugin.getCooldowns().removePlayerToTimeOut(player);
										    	if (!plugin.getCooldowns().isPlayerInCooldown(player)) {	
											    	Bukkit.dispatchCommand(player, meta.getLore().get(1).replace("both hands: /", ""));
											    	plugin.getCooldowns().addPlayerToCooldown(player, 0);
											    	plugin.getCooldowns().addPlayerToTimeOut(player);
											   	}
										    	else {
										    		event.setCancelled(true);
										    	}
										    }
							    		}
							    	}
							    }
								else {
							    	if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
							    		if (!plugin.getCooldowns().isPlayerInTimeout(player)) {
							    			if (!plugin.getCooldowns().isPlayerInCooldown(player)) {	
										    	Bukkit.dispatchCommand(player, meta.getLore().get(1).replace("left hand: /", ""));
										    	plugin.getCooldowns().addPlayerToCooldown(player, 0);
										    	//plugin.getCooldowns().addPlayerToTimeOut(player);
										   	}
							    			else {
									    		event.setCancelled(true);
									    	}
							    		}
										else {
										    if (plugin.getCooldowns().timeSincePlayerInCooldown(player) < 220) {
										    	event.setCancelled(true);
										    	plugin.getCooldowns().addPlayerToTimeOut(player);
										   	}
										   	else {
										   		plugin.getCooldowns().removePlayerToTimeOut(player);
										   		if (!plugin.getCooldowns().isPlayerInCooldown(player)) {	
									    			Bukkit.dispatchCommand(player, meta.getLore().get(1).replace("left hand: /", ""));
										    		plugin.getCooldowns().addPlayerToCooldown(player, 0);
										    		plugin.getCooldowns().addPlayerToTimeOut(player);
										   		}
									    		else {
									    			event.setCancelled(true);
										    	}
							    			}
							    		}
							    	}
									else if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
								    	if (!plugin.getCooldowns().isPlayerInTimeout(player)) {
								    		if (!plugin.getCooldowns().isPlayerInCooldown(player)) {	
											    Bukkit.dispatchCommand(player, meta.getLore().get(2).replace("right hand: /", ""));
											   	plugin.getCooldowns().addPlayerToCooldown(player, 0);
											   	//plugin.getCooldowns().addPlayerToTimeOut(player);
											}
								    		else {
										   		event.setCancelled(true);
										   	}
								    	}
										else {
										    if (plugin.getCooldowns().timeSincePlayerInCooldown(player) < 220) {
										    	event.setCancelled(true);
										    	plugin.getCooldowns().addPlayerToTimeOut(player);
										    }
										    else {
											    plugin.getCooldowns().removePlayerToTimeOut(player);
											    if (!plugin.getCooldowns().isPlayerInCooldown(player)) {	
											    	Bukkit.dispatchCommand(player, meta.getLore().get(2).replace("right hand: /", ""));
											    	plugin.getCooldowns().addPlayerToCooldown(player, 0);
											    	plugin.getCooldowns().addPlayerToTimeOut(player);
											    }
											    else {
											    	event.setCancelled(true);
											    }
								    		}
								    	}
								    }
								}
							}
						}
						else if (player.getInventory().getItemInHand().getType().name().endsWith("BOW")) {
							if (meta.getLore().get(0).contains("(RTD)")) {
								if (meta.getLore().get(1).contains("ambidextrous")) {
							    	if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {							   			if (!plugin.getCooldowns().isPlayerInTimeout(player)) {
							    			if (!plugin.getCooldowns().isPlayerInCooldown(player)) {	
							    				Bukkit.dispatchCommand(player, meta.getLore().get(1).replace("both hands: /", ""));
										   		plugin.getCooldowns().addPlayerToCooldown(player, 0);
										   		//plugin.getCooldowns().addPlayerToTimeOut(player);
										  	}
							    			else {
									   			event.setCancelled(true);
									   		}
							    		}
										else {
									    	if (plugin.getCooldowns().timeSincePlayerInCooldown(player) < 220) {
									    		event.setCancelled(true);
									    		plugin.getCooldowns().addPlayerToTimeOut(player);
									    	}
									    	else {
										    	plugin.getCooldowns().removePlayerToTimeOut(player);
										    	if (!plugin.getCooldowns().isPlayerInCooldown(player)) {	
										    		Bukkit.dispatchCommand(player, meta.getLore().get(1).replace("both hands: /", ""));
										    		plugin.getCooldowns().addPlayerToCooldown(player, 0);
										    		plugin.getCooldowns().addPlayerToTimeOut(player);
										   		}
										    	else {
										    		event.setCancelled(true);
										   		}
										   	}
										}
							   		}
						    	}
								else {
							    	if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
							    		if (!plugin.getCooldowns().isPlayerInTimeout(player)) {
							    			if (!plugin.getCooldowns().isPlayerInCooldown(player)) {	
							    				Bukkit.dispatchCommand(player, meta.getLore().get(1).replace("left hand: /", ""));
										    	plugin.getCooldowns().addPlayerToCooldown(player, 0);
										    	//plugin.getCooldowns().addPlayerToTimeOut(player);
										   	}
							    			else {
									    		event.setCancelled(true);
									    	}
							    		}
										else {
										    if (plugin.getCooldowns().timeSincePlayerInCooldown(player) < 220) {
										    	event.setCancelled(true);
										    	plugin.getCooldowns().addPlayerToTimeOut(player);
										   	}
										    else {
										    	plugin.getCooldowns().removePlayerToTimeOut(player);
										    	if (!plugin.getCooldowns().isPlayerInCooldown(player)) {	
										    		Bukkit.dispatchCommand(player, meta.getLore().get(1).replace("left hand: /", ""));
										    		plugin.getCooldowns().addPlayerToCooldown(player, 0);
										    		plugin.getCooldowns().addPlayerToTimeOut(player);
										   		}
									    		else {
									    			event.setCancelled(true);
									    		}
										    }
							    		}
							    	}
							    	else if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
							   			if (!plugin.getCooldowns().isPlayerInTimeout(player)) {
							    			if (!plugin.getCooldowns().isPlayerInCooldown(player)) {	
							    				Bukkit.dispatchCommand(player, meta.getLore().get(2).replace("right hand: /", ""));
										   		plugin.getCooldowns().addPlayerToCooldown(player, 0);
										   		//plugin.getCooldowns().addPlayerToTimeOut(player);
										  	}
							    			else {
									   			event.setCancelled(true);
									   		}
							    		}
										else {
									    	if (plugin.getCooldowns().timeSincePlayerInCooldown(player) < 220) {
									    		event.setCancelled(true);
									    		plugin.getCooldowns().addPlayerToTimeOut(player);
									    	}
									    	else {
										    	plugin.getCooldowns().removePlayerToTimeOut(player);
										    	if (!plugin.getCooldowns().isPlayerInCooldown(player)) {	
										    		Bukkit.dispatchCommand(player, meta.getLore().get(2).replace("right hand: /", ""));
										    		plugin.getCooldowns().addPlayerToCooldown(player, 0);
										    		plugin.getCooldowns().addPlayerToTimeOut(player);
										   		}
										    	else {
										    		event.setCancelled(true);
										   		}
										   	}
										}
							   		}
						    	}
						    }
						}
					}
				}
		    }
		}
	}
}
