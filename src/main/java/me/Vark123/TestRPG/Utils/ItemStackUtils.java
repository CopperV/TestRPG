package me.Vark123.TestRPG.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.mutable.MutableBoolean;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
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
		
		if(section.contains("enchantments")) {
			List<String> enchantList = section.getStringList("enchantments");
			enchantList.stream().filter(enchant -> {
				if(!enchant.contains(":"))
					return false;
				String[] split = enchant.split(":");
				if(!StringUtils.isNumeric(split[1]))
					return false;
				
				MutableBoolean check = new MutableBoolean();
				Field[] fields = Enchantment.class.getFields();
				
				Arrays.stream(fields).filter(field -> {
					if(!Modifier.isStatic(field.getModifiers()))
						return false;
					if(!field.getName().equals(split[0].toUpperCase()))
						return false;
					try {
						if(!(field.get(null) instanceof Enchantment))
							return false;
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
						return false;
					}
					return true;
				}).findAny().ifPresent(field -> {
					check.setValue(true);
				});
				
				return check.booleanValue();
			}).forEach(enchant -> {
				String[] split = enchant.split(":");
				int level = Integer.parseInt(split[1]);
				
				Field field;
				try {
					field = Enchantment.class.getField(split[0].toUpperCase());
					Enchantment ench = (Enchantment) field.get(null);
					it.addUnsafeEnchantment(ench, level);
				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			});
		}
		
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
