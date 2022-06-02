package user.uziza.RTD.RTDItems;


import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import user.uziza.RTD.RTD;

public class DistanceStick implements Listener {
	RTD plugin;
	public DistanceStick(RTD plugin) {
		this.plugin = plugin;
	}
	
	private Map<UUID, Location> point = new HashMap<>();
	
	/*
	private Map<UUID, Entity> loc_occ = new HashMap<>();
	
	@EventHandler
	  public void onProjectileHit(ProjectileHitEvent e) {
	          if ((e.getEntity().hasMetadata("LocationGetter"))){
	        	  Player player = (Player) e.getEntity().getShooter();
	        	  Location loc1 = player.getLocation();
	        	  Location loc2 = null;
	        	  
	        	  if (e.getHitBlock() != null) {
	        		  loc2 = e.getHitBlock().getLocation();
	        	  }
	        	  else if (e.getHitEntity() != null) {
	        		  loc2 = e.getHitEntity().getLocation();
	        	  }
	              
	              player.sendMessage("Distance: " + String.valueOf(loc1.distance(loc2)));
	              
	              loc_occ.remove(player.getUniqueId());
	          }
	}
	*/

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
								if (!player.isSneaking() && (a.equals(Action.RIGHT_CLICK_BLOCK) || a.equals(Action.RIGHT_CLICK_AIR))) {
									String MSG = null;
									try {
										Location shootLocation = player.getEyeLocation();
										Vector directionVector = shootLocation.getDirection().normalize();
										RayTraceResult rt = player.getWorld().rayTrace(shootLocation, directionVector, 250, FluidCollisionMode.ALWAYS, false, 0, Entity -> (Entity.getUniqueId() != player.getUniqueId()));
										
										DecimalFormat DF_2 = new DecimalFormat("0.00");
										
										if (rt.getHitEntity() != null) {
												String thing = DF_2.format((Double) player.getLocation().distance(rt.getHitEntity().getLocation())-1);
												MSG = " ["+rt.getHitEntity().getType().toString()+"]"+": "+ "("+thing+" Feet)";
										}
										else if (rt.getHitBlock() != null) {
												String thing =  DF_2.format((Double) player.getLocation().distance(rt.getHitBlock().getLocation())-1);
												MSG = " ["+rt.getHitBlock().getType().toString()+"]"+": "+ "("+thing+" Feet)";
										}
										
									}
									catch (Exception p) {
										MSG = ": Greater than 250 blocks.";
									}
									finally {
										player.sendMessage("Distance" + MSG);
									}
									/*
									if (loc_occ.containsKey(player.getUniqueId())) {
										loc_occ.get(player.getUniqueId()).remove();
										loc_occ.remove(player.getUniqueId());
									}
									
									Location shootLocation = player.getEyeLocation();
									Vector directionVector = shootLocation.getDirection().normalize();
								 
									Projectile loc = shootLocation.getWorld().spawn(shootLocation, ThrownPotion.class);
									loc_occ.put(player.getUniqueId(), loc);
									
									loc.setVelocity(directionVector.multiply(2.5));
									loc.setGravity(false);
									
									loc.setShooter(player);
									loc.setMetadata("LocationGetter", new FixedMetadataValue(plugin, true));
									*/
								}
								else if (a.equals(Action.LEFT_CLICK_BLOCK)) {
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
							if (victim != null && victim instanceof Tameable) {
								String MSG = null;
								try {
									Location shootLocation = player.getEyeLocation();
									Vector directionVector = shootLocation.getDirection().normalize();
									RayTraceResult rt = player.getWorld().rayTrace(shootLocation, directionVector, 250, FluidCollisionMode.ALWAYS, false, 0, Entity -> (Entity.getUniqueId() != player.getUniqueId()));
									
									if (rt.getHitEntity() != null) {
										MSG = " ["+rt.getHitEntity().getType().toString()+"]"+": "+String.valueOf(player.getLocation().distance(rt.getHitEntity().getLocation())) + " ("+String.valueOf(player.getLocation().distance(rt.getHitEntity().getLocation())*2.5)+")";
									}
									else if (rt.getHitBlock() != null) {
											MSG = " ["+rt.getHitBlock().getType().toString()+"]"+": "+String.valueOf(player.getLocation().distance(rt.getHitBlock().getLocation())) + " ("+String.valueOf(player.getLocation().distance(rt.getHitBlock().getLocation())*2.5)+")";
									}
								}
								catch (Exception p) {
									MSG = ": Greater than 250 blocks.";
								}
								finally {
									player.sendMessage("Distance" + MSG);
								}
								e2.setCancelled(true);
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void getEntityDistance1(EntityDamageByEntityEvent e2) {
		Entity damager = e2.getDamager();
		
		
		if (damager instanceof Player) {
		Player player = (Player) damager;
		Entity victim = e2.getEntity();
		
		ItemStack item = player.getInventory().getItemInMainHand();
		ItemMeta meta = item.getItemMeta();
		
		
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
									e2.setCancelled(true);
							}
						}
					}
				}
			}
		}
	}
}
