package me.Vark123.TestRPG.Commands;

import org.bukkit.command.CommandSender;

import lombok.Getter;

@Getter
public abstract class ARpgCommand {

	private final String name;
	private final String correctUsage;
	
	public ARpgCommand(String name, String correctUsage) {
		this.name = name;
		this.correctUsage = correctUsage;
	}
	
	public abstract boolean executeCommand(CommandSender sender, String[] args);
	
}
