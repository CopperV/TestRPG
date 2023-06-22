package me.Vark123.TestRPG.Commands;

import java.util.Arrays;
import java.util.Optional;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.Vark123.TestRPG.TestRPG;

public class RpgCommandExecutor implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("rpg"))
			return false;
		if(!sender.hasPermission("rpg.gm")) {
			sender.sendMessage(TestRPG.inst().getPrefix()+" §cNie posiadasz uprawnien do tej komendy!");
			return false;
		}
		if(args.length == 0) {
			showCorrectUsage(sender);
			return false;
		}
		Optional<ARpgCommand> oCommand = RpgCommandManager.get().getCommandExecutor(args[0]);
		if(oCommand.isEmpty()) {
			showCorrectUsage(sender);
			return false;
		}
		ARpgCommand command = oCommand.get();
		String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
		return command.executeCommand(sender, newArgs);
	}

	private void showCorrectUsage(CommandSender sender) {
		sender.sendMessage(TestRPG.inst().getPrefix()+" §cPoprawne uzycie komendy /rpg:");
		RpgCommandManager.get().getRegistry()
			.values()
			.stream()
			.forEach(command -> {
				sender.sendMessage("§4- §e/rpg "+command.getCorrectUsage());
			});
	}
	
	
}
