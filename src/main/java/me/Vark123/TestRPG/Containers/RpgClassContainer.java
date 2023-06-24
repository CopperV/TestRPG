package me.Vark123.TestRPG.Containers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;
import lombok.AccessLevel;
import lombok.Getter;
import me.Vark123.TestRPG.FileSystem;
import me.Vark123.TestRPG.Classes.PlayerClass;
import me.Vark123.TestRPG.Utils.ItemStackUtils;

public final class RpgClassContainer {

	private static final RpgClassContainer inst = new RpgClassContainer();
	
	private final Set<Class<? extends PlayerClass>> rpgClasses;
	private final Map<Class<? extends PlayerClass>, RpgClassConfig> rpgClassConfigs;
	
	private RpgClassContainer() {
		this.rpgClasses = new HashSet<>();
		this.rpgClassConfigs = new HashMap<>();
	}
	
	public static final RpgClassContainer get() {
		return inst;
	}
	
	public int getClassAmount() {
		return rpgClassConfigs.size();
	}
	
	public Optional<RpgClassConfig> getRpgClassConfig(Class<? extends PlayerClass> rpgClass) {
		if(!rpgClassConfigs.containsKey(rpgClass))
			return Optional.empty();
		RpgClassConfig config = rpgClassConfigs.get(rpgClass);
		return Optional.of(config);
	}
	
	public Set<Class<? extends PlayerClass>> getContainer(){
		return new HashSet<>(rpgClasses);
	}
	
	public void storeClass(Class<? extends PlayerClass> rpgClass) {
		storeClass(rpgClass, FileSystem.getClassConfig(rpgClass));
	}
	
	public void storeClass(Class<? extends PlayerClass> rpgClass,
			ConfigurationSection rpgClassConfig) {
		RpgClassConfig config = new RpgClassConfig(rpgClass, rpgClassConfig);
		
		rpgClasses.add(rpgClass);
		rpgClassConfigs.put(rpgClass, config);
	}
	
	@Getter
	public class RpgClassConfig {
		
		private ItemStack classInfoItem;
		
		private Location startLocation;
		private Set<ItemStack> startItems;

		@Getter(value = AccessLevel.NONE)
		private final String strWorld;
		@Getter(value = AccessLevel.NONE)
		private final double x;
		@Getter(value = AccessLevel.NONE)
		private final double y;
		@Getter(value = AccessLevel.NONE)
		private final double z;
		@Getter(value = AccessLevel.NONE)
		private final float pitch;
		@Getter(value = AccessLevel.NONE)
		private final float yaw;
		
		public RpgClassConfig(Class<? extends PlayerClass> rpgClass, ConfigurationSection rpgClassConfig) {
			classInfoItem = ItemStackUtils.generateItem(rpgClassConfig.getConfigurationSection("chooseitem"));
			NBTItem nbt = new NBTItem(classInfoItem);
			nbt.setString("classPath", rpgClass.getName());
			nbt.applyNBT(classInfoItem);
			
			strWorld = rpgClassConfig.getString("startlocation.world");
			x = rpgClassConfig.getDouble("startlocation.location.x");
			y = rpgClassConfig.getDouble("startlocation.location.y");
			z = rpgClassConfig.getDouble("startlocation.location.z");
			pitch = (float) rpgClassConfig.getDouble("startlocation.location.pitch", 0);
			yaw = (float) rpgClassConfig.getDouble("startlocation.location.yaw", 0);
			
			startItems = new HashSet<>();
			if(rpgClassConfig.contains("startitems")) {
				ConfigurationSection itemSection = rpgClassConfig.getConfigurationSection("startitems");
				itemSection.getKeys(false).stream().forEach(key -> {
					ItemStack it = ItemStackUtils.generateItem(itemSection.getConfigurationSection(key));
					startItems.add(it);
				});
			}
		}
		
		public Location getStartLocation() {
			if(startLocation == null) {
				World world = Bukkit.getWorld(strWorld);
				this.startLocation = new Location(world, x, y, z, yaw, pitch);
			}
			return startLocation;
		}
		
	}
	
}
