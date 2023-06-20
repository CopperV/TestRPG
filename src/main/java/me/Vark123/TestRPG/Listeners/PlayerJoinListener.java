package me.Vark123.TestRPG.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Vark123.TestRPG.API.TestRPGApi;
import me.Vark123.TestRPG.Containers.PlayerContainer;
import me.Vark123.TestRPG.Players.RpgPlayer;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		RpgPlayer rpg = TestRPGApi.get().getPlayerRepository().getPlayerByID(p.getUniqueId());
		if (rpg == null) {
			rpg = new RpgPlayer(p);
			TestRPGApi.get().getPlayerRepository().create(rpg);
		}
		PlayerContainer.get().storePlayer(p, rpg);
	}

}
