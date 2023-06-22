package me.Vark123.TestRPG.Commands.Impl;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.mutable.MutableBoolean;
import org.apache.commons.lang3.EnumUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Vark123.TestRPG.TestRPG;
import me.Vark123.TestRPG.Commands.ARpgCommand;
import me.Vark123.TestRPG.Containers.PlayerContainer;
import me.Vark123.TestRPG.Players.PlayerStatModifyType;
import me.Vark123.TestRPG.Players.RpgPlayer;
import me.Vark123.TestRPG.Players.Statistics.PnStat;

public class RpgPnCommand extends ARpgCommand {
	
	public RpgPnCommand() {
		super("pn", "pn <gracz> <ilosc> BASE/CALC/LEARNED");
	}

	@Override
	public boolean executeCommand(CommandSender sender, String[] args) {
		if(args.length < 3) {
			sender.sendMessage(TestRPG.inst().getPrefix()+" §cZa mala liczba argumentow!");
			return false;
		}
		if(Bukkit.getPlayerExact(args[0]) == null) {
			sender.sendMessage(TestRPG.inst().getPrefix()+" §c"+args[0]+" jest offline!");
			return false;
		}
		if(!StringUtils.isNumeric(args[1])) {
			sender.sendMessage(TestRPG.inst().getPrefix()+" §c"+args[1]+" nie jest liczba!");
			return false;
		}
		if(!EnumUtils.isValidEnum(PlayerStatModifyType.class, args[2].toUpperCase())) {
			sender.sendMessage(TestRPG.inst().getPrefix()+" §c"+args[2]+" nie jest poprawnym modyfikatorem!");
			return false;
		}
		
		Player p = Bukkit.getPlayerExact(args[0]);
		Optional<RpgPlayer> oRpgPlayer = PlayerContainer.get().getRpgPlayer(p);
		if(oRpgPlayer.isEmpty()) {
			sender.sendMessage(TestRPG.inst().getPrefix()+" §cBlad bazy danych! Zglos blad administratorowi!");
			return false;
		}
		
		MutableBoolean result = new MutableBoolean();
		RpgPlayer rpg = oRpgPlayer.get();
		int level = Integer.parseInt(args[1]);
		PlayerStatModifyType modType = PlayerStatModifyType.valueOf(args[2].toUpperCase());
		
		rpg.getStats().stream().filter(stat -> {
			return stat instanceof PnStat;
		}).findAny().ifPresent(stat -> {
			stat.modifyValue(level, modType);
			result.setValue(true);
		});
		
		return result.booleanValue();
	}

}
