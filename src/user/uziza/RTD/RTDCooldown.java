package user.uziza.RTD;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RTDCooldown {
	private Map<UUID, Integer> cooldown = new HashMap<>();
	private Map<UUID, Long> timeout = new HashMap<>();
	
	public RTDCooldown(RTD plugin) {
		new BukkitRunnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (UUID uuid: cooldown.keySet()) {
					if (cooldown.get(uuid) < 1) {
						cooldown.remove(uuid);
						continue;
					}
					
					cooldown.put(uuid, cooldown.get(uuid)-1);
				}
			}
		}.runTaskTimer(plugin, 0, 20);
	}
	
	public void addPlayerToCooldown(Player player, Integer time) {
		cooldown.put(player.getUniqueId(), time);
	}
	
	public boolean isPlayerInCooldown(Player player) {
		return cooldown.containsKey(player.getUniqueId());
	}
	
	public int timePlayerInCooldown(Player player) {
		if (cooldown.containsKey(player.getUniqueId())) {
			return cooldown.get(player.getUniqueId());
		}
		else {
			return 0;
		}
	}
	
	public boolean isPlayerInTimeout(Player player) {
		return timeout.containsKey(player.getUniqueId());
	}
	
	public void addPlayerToTimeOut(Player player) {
		timeout.put(player.getUniqueId(), System.currentTimeMillis());
	}
	
	public void removePlayerToTimeOut(Player player) {
		timeout.remove(player.getUniqueId());
	}
	
	public Integer timeSincePlayerInCooldown(Player player) {
		if (timeout.containsKey(player.getUniqueId())) {
			return (int) (System.currentTimeMillis() - timeout.get(player.getUniqueId()));
		}
		else {
			return null;

		}
	}
}
