package me.Vark123.TestRPG;

import org.bukkit.Bukkit;

import me.Vark123.TestRPG.Commands.RpgCommandExecutor;
import me.Vark123.TestRPG.Commands.RpgCommandManager;
import me.Vark123.TestRPG.Commands.Impl.RpgLevelCommand;
import me.Vark123.TestRPG.Commands.Impl.RpgPnCommand;

public class CommandExecutors {

	private CommandExecutors() {}

	public static void setExecutors() {
		Bukkit.getPluginCommand("rpg").setExecutor(new RpgCommandExecutor());
		
		RpgCommandManager.get().registerRpgCommand(new RpgLevelCommand());
		RpgCommandManager.get().registerRpgCommand(new RpgPnCommand());
	}

}
