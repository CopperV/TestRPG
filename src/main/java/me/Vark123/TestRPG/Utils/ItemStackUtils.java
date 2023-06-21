package me.Vark123.TestRPG.Utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;

public class ItemStackUtils {

	private ItemStackUtils() {}
	
	public static ItemStack generateItem(ConfigurationSection section) {
		Material material = Material.getMaterial(section.getString("material"));
		String display = ChatColor.translateAlternateColorCodes('&', section.getString("display"));
		List<String> lore = section.getStringList("lore")
				.stream()
				.map(line -> {
					return ChatColor.translateAlternateColorCodes('&', line);
				}).collect(Collectors.toList());
		
		ItemStack it = new ItemStack(material);
		ItemMeta im = it.getItemMeta();
		im.setDisplayName(display);
		im.setLore(lore);
		it.setItemMeta(im);
		
		if(section.contains("nbt")) {
			NBTItem nbt = new NBTItem(it);
			ConfigurationSection nbtSection = section.getConfigurationSection("nbt");
			loadNBT(nbtSection, nbt);			
			nbt.applyNBT(it);
		}
		
		return it;
	}
	
	public static void giveOrDropItem(ItemStack it, Player p) {
		Inventory inv = p.getInventory();
		int freeSlot = inv.firstEmpty();
		if(freeSlot >= 0 && freeSlot < 36) {
			inv.setItem(freeSlot, it);
		} else {
			p.getWorld().dropItem(p.getLocation(), it);
		}
	}
	
	private static void loadNBT(ConfigurationSection section, NBTCompound nbt) {
		Set<String> keys = section.getKeys(false);
		for(String key : keys) {
			if(section.isConfigurationSection(key)) {
				NBTCompound compound = nbt.getOrCreateCompound(key);
				ConfigurationSection compoundSection = section.getConfigurationSection(key);
				loadNBT(compoundSection, compound);
				continue;
			}
			Object obj = section.get(key);
			nbt.setString(key, obj.toString());
		}
	}
	
}
