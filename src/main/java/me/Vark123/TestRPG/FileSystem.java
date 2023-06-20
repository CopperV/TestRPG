package me.Vark123.TestRPG;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

public class FileSystem {

	private static final File config = new File(TestRPG.inst().getDataFolder(), "config.yml");

	private FileSystem() {
	}

	public static void init() {
		if (!TestRPG.inst().getDataFolder().exists())
			TestRPG.inst().getDataFolder().mkdir();
		TestRPG.inst().saveResource(config.getName(), false);
	}

	public static YamlConfiguration getConfigYaml() {
		return YamlConfiguration.loadConfiguration(config);
	}

}
