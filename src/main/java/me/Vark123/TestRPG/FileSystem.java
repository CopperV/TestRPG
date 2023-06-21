package me.Vark123.TestRPG;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import me.Vark123.TestRPG.Classes.PlayerClass;

public class FileSystem {

	private static final File rpgClassDir = new File(TestRPG.inst().getDataFolder(), "classes");
	
	private static final File config = new File(TestRPG.inst().getDataFolder(), "config.yml");

	private FileSystem() {
	}

	public static void init() {
		if (!TestRPG.inst().getDataFolder().exists())
			TestRPG.inst().getDataFolder().mkdir();
		TestRPG.inst().saveResource(config.getName(), false);
				
		TestRPG.inst().saveResource("classes/", false);
		TestRPG.inst().saveResource("classes/DefaultClass.yml", false);
		TestRPG.inst().saveResource("classes/VipClass.yml", false);
	}

	public static YamlConfiguration getConfigYaml() {
		return YamlConfiguration.loadConfiguration(config);
	}
	
	public static ConfigurationSection getClassConfig(Class<? extends PlayerClass> rpgClass) {
		File file = new File(rpgClassDir, rpgClass.getSimpleName()+".yml");
		YamlConfiguration fYml = YamlConfiguration.loadConfiguration(file);
		return fYml;
	}

}
