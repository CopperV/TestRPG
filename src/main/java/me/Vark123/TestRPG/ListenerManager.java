package me.Vark123.TestRPG;

import org.bukkit.Bukkit;

import me.Vark123.TestRPG.Listeners.PlayerDisconnectListener;
import me.Vark123.TestRPG.Listeners.PlayerJoinListener;
import me.Vark123.TestRPG.Listeners.PlayerLevelUpdateListener;

public class ListenerManager {

	private ListenerManager() {
	}

	public static void registerListeners() {
		TestRPG inst = TestRPG.inst();

		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), inst);
		Bukkit.getPluginManager().registerEvents(new PlayerDisconnectListener(), inst);
		Bukkit.getPluginManager().registerEvents(new PlayerLevelUpdateListener(), inst);
	}

}
