package user.uziza.RTD;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import user.uziza.RTD.RTDEvents.RTDEvents;
import user.uziza.RTD.RTDItems.DistanceStick;

public class RTD extends JavaPlugin implements Listener{
	
	private RTDCommands rtdCMD = new RTDCommands(this); 
	RTDCooldown cooldowns;
	
	@Override
	public void onEnable() {
		this.getCommand(rtdCMD.roll).setExecutor(rtdCMD);
		this.getCommand(rtdCMD.rollmax).setExecutor(rtdCMD);
		this.getCommand(rtdCMD.bind).setExecutor(rtdCMD);
		getServer().getPluginManager().registerEvents(new RTDEvents(this), this);
		getServer().getPluginManager().registerEvents(new DistanceStick(this), this);
		cooldowns = new RTDCooldown(this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public RTDCooldown getCooldowns() {
		return cooldowns;
	}
}
