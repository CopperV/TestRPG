package me.Vark123.TestRPG.Listeners;

import java.util.Optional;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Vark123.TestRPG.API.TestRPGApi;
import me.Vark123.TestRPG.Containers.PlayerContainer;
import me.Vark123.TestRPG.Players.RpgPlayer;

public class PlayerDisconnectListener implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		Optional<RpgPlayer> oRpgPlayer = PlayerContainer.get().getRpgPlayer(p);
		if (oRpgPlayer.isEmpty())
			return;
		RpgPlayer rpgPlayer = oRpgPlayer.get();
		TestRPGApi.get().getPlayerRepository().update(rpgPlayer);
	}

	@EventHandler
	public void onKick(PlayerKickEvent e) {
		Player p = e.getPlayer();
		Optional<RpgPlayer> oRpgPlayer = PlayerContainer.get().getRpgPlayer(p);
		if (oRpgPlayer.isEmpty())
			return;
		RpgPlayer rpgPlayer = oRpgPlayer.get();
		TestRPGApi.get().getPlayerRepository().update(rpgPlayer);
	}

}
