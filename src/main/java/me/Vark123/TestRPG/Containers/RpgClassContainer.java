package me.Vark123.TestRPG.Containers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import me.Vark123.TestRPG.FileSystem;
import me.Vark123.TestRPG.Classes.PlayerClass;

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
		
		public RpgClassConfig(Class<? extends PlayerClass> rpgClass, ConfigurationSection rpgClassConfig) {
			
		}
		
	}
	
}
