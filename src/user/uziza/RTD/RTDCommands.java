package user.uziza.RTD;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import user.uziza.RTD.RTDItems.BindedItem;

public class RTDCommands implements CommandExecutor {
	
	private RTD plugin;
	
	public String roll = "roll";
	public String rollmax = "rollmax";
	public String bind = "bind";
	
	private BindedItem bindedItem = new BindedItem();
	
	public RTDCommands(RTD p) {
        plugin = p;
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase(bind)) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				
				//use an array
				if (player.getInventory().getItemInHand().getType().name().endsWith("SWORD") || player.getInventory().getItemInHand().getType().name().endsWith("AXE") || player.getInventory().getItemInHand().getType().name().endsWith("PICKAXE") || player.getInventory().getItemInHand().getType().name().endsWith("STICK") || player.getInventory().getItemInHand().getType().name().endsWith("SPADE") || player.getInventory().getItemInHand().getType().name().endsWith("HOE") || player.getInventory().getItemInHand().getType().name().endsWith("BOW")) {
					if (args.length >= 2 && args.length <= 3) {
						if (args[0].matches("/roll")) {							
							if (args.length == 3) {
								if (args[2].matches("right")) {
									bindedItem.giveItems(player, String.valueOf(args[0])+" "+String.valueOf(args[1]), "right hand");
								}
								else if (args[2].matches("left")) {
									bindedItem.giveItems(player, String.valueOf(args[0])+" "+String.valueOf(args[1]), "left hand");
								}
								else if (args[2].matches("both")) {
									bindedItem.giveItems(player, String.valueOf(args[0])+" "+String.valueOf(args[1]), "both");
								}
							}
							else {
								bindedItem.giveItems(player, String.valueOf(args[0])+" "+String.valueOf(args[1]), "right hand");
							}
						}
					}
					if (args.length == 1) {
						if (args[0].matches("DS")) {
							if (player.getInventory().getItemInHand().getType().name().endsWith("STICK")) {
								bindedItem.distanceStick(player);
							}
						}
 					}
				}
			}
			return true;
		}
		else if (command.getName().equalsIgnoreCase(roll)) {
			if (args.length == 1) {
				try {
					String RTD = args[0];
					ArrayList<String> listOfMaths = new ArrayList<String>();
					
					String sorted = args[0].replaceAll("\\-", "+-");
					
					String sort1[] = sorted.split("[+]");
					
					int aggregate1 = 0;
				    int aggregate2 = 0;
					
					for (int i = 0; i < sort1.length; i++) {
						if (sort1[i].matches("[0-9]+d[0-9]+|\\-[0-9]+d[0-9]+")) {
							ArrayList<String> listOfRolls = new ArrayList<String>();
							int num = Integer.parseInt(sort1[i].replace("-", "").split("d")[0]);
						    int size = Integer.parseInt(sort1[i].split("d")[1]);
						    
						    if (num > 100 && size > 100) {
						    	throw new ArithmeticException("Too much and too big");
						    }
						    else {
							    if (num > 100) {
							    	throw new ArithmeticException("Too much");
							    }
							    if (size > 100) {
							    	throw new ArithmeticException("Too big");
							    }
						    }
						    
						    int roll = 0;
						    
						    if (sort1[i].matches("\\-[0-9]+d[0-9]+")) {
								for (int ii = 0; ii < num; ii++) {
									roll = (int)Math.floor(Math.random()*(size-1+1)+1);
									listOfRolls.add(String.valueOf(roll));
									aggregate2 -= roll;
								}
								if (num == 1) {
									listOfMaths.add("-"+listOfRolls.toString().replace(" ", "").replace(",", "+").replace("[", "").replace("]", ""));
								}
								else {
									listOfMaths.add("-"+listOfRolls.toString().replace(" ", "").replace(",", "+").replace("[", "(").replace("]", ")"));
								}
							}
							else {
								for (int ii = 0; ii < num; ii++) {
									roll = (int)Math.floor(Math.random()*(size-1+1)+1);
									listOfRolls.add(String.valueOf(roll));
									aggregate2 += roll;
								}
								if (num == 1) {
									listOfMaths.add("+"+listOfRolls.toString().replace(" ", "").replace(",", "+").replace("[", "").replace("]", ""));
								}
								else {
									listOfMaths.add("+"+listOfRolls.toString().replace(" ", "").replace(",", "+").replace("[", "(").replace("]", ")"));
								}
							}
						}
						else {
							listOfMaths.add("+"+sort1[i]);
							aggregate1 += Integer.parseInt(sort1[i]);
						}
					}
					plugin.getServer().broadcastMessage("[" + sender.getName() + ":" + RTD + "] " + listOfMaths.toString().replace("[+", "").replace("[", "").replace("]", "").replace("+-", "-").replace(" ", "").replace(",", "") + " = " + String.valueOf(aggregate1+aggregate2));
					return true;
				}
				catch (ArithmeticException a) {
					sender.sendMessage(String.valueOf(a.getMessage()));
					return true;
				}
				catch (Exception e) {
					return false;
				}
			}
		}
		else if (command.getName().equalsIgnoreCase(rollmax)) {
			if (args.length == 1) {
				try {
					String RTD = args[0];
					ArrayList<String> listOfMaths = new ArrayList<String>();
					
					String sorted = args[0].replaceAll("\\-", "+-");
					
					String sort1[] = sorted.split("[+]");
					
					int aggregate1 = 0;
				    int aggregate2 = 0;
					
					for (int i = 0; i < sort1.length; i++) {
						if (sort1[i].matches("[0-9]+d[0-9]+|\\-[0-9]+d[0-9]+")) {
							ArrayList<String> listOfRolls = new ArrayList<String>();
							int num = Integer.parseInt(sort1[i].replace("-", "").split("d")[0]);
						    int size = Integer.parseInt(sort1[i].split("d")[1]);
						    
						    if (num > 100 && size > 100) {
						    	throw new ArithmeticException("Too much and too big");
						    }
						    else {
							    if (num > 100) {
							    	throw new ArithmeticException("Too much");
							    }
							    if (size > 100) {
							    	throw new ArithmeticException("Too big");
							    }
						    }
						    
						    int roll = 0;
						    
						    if (sort1[i].matches("\\-[0-9]+d[0-9]+")) {
								for (int ii = 0; ii < num; ii++) {
									roll = (int)Math.floor(Math.random()*(size-1+1)+1);
									listOfRolls.add(String.valueOf(roll));
									aggregate2 -= roll;
								}
								if (num == 1) {
									listOfMaths.add("-"+listOfRolls.toString().replace(" ", "").replace(",", "+").replace("[", "").replace("]", ""));
								}
								else {
									listOfMaths.add("-"+listOfRolls.toString().replace(" ", "").replace(",", "+").replace("[", "(").replace("]", ")"));
								}
							}
							else {
								for (int ii = 0; ii < num; ii++) {
									roll = (int)Math.floor(Math.random()*(size-1+1)+1);
									listOfRolls.add(String.valueOf(roll));
									aggregate2 += roll;
								}
								if (num == 1) {
									listOfMaths.add("+"+listOfRolls.toString().replace(" ", "").replace(",", "+").replace("[", "").replace("]", ""));
								}
								else {
									listOfMaths.add("+"+listOfRolls.toString().replace(" ", "").replace(",", "+").replace("[", "(").replace("]", ")"));
								}
							}
						}
						else {
							listOfMaths.add("+"+sort1[i]);
							aggregate1 += Integer.parseInt(sort1[i]);
						}
					}
					
					String[] math_list = listOfMaths.toString().replace("[+", "").replace("[", "").replace("]", "").replace("+-", "-").replace(" ", "").replace(",", "").replace("(", "").replace(")", " ").split(" ");
					String math_max = "0";
					
					String[] add_list = math_list[0].replace("+", " ").replace("-", " -").split(" ");
					
					for (int i = 0; i < add_list.length; i++) {
						if (Integer.valueOf(math_max) < Integer.valueOf(add_list[i])) {
							math_max = add_list[i];
						}
					}
					
					plugin.getServer().broadcastMessage("[" + sender.getName() + ":" + RTD + "] " + math_max + math_list[1] + " = " + String.valueOf(Integer.valueOf(math_max) + Integer.valueOf(math_list[1])));
					return true;
				}
				catch (ArithmeticException a) {
					sender.sendMessage(String.valueOf(a.getMessage()));
					return true;
				}
				catch (Exception e) {
					sender.sendMessage(String.valueOf(e.getMessage()));
					return false;
				}
			}
		}
		return false;
	}
}
