package me.Vark123.TestRPG.Listeners;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Vark123.TestRPG.API.TestRPGApi;
import me.Vark123.TestRPG.Containers.NPCContainer;
import me.Vark123.TestRPG.Containers.PlayerContainer;
import me.Vark123.TestRPG.Containers.RpgClassContainer;
import me.Vark123.TestRPG.Containers.RpgClassContainer.RpgClassConfig;
import me.Vark123.TestRPG.GUI.Misc.ChooseClassInvManager;
import me.Vark123.TestRPG.NPC.ARpgNpc;
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
		
		switch(rpg.getState()) {
			case START:
				ChooseClassInvManager.get().openMenu(p);
				break;
			case TUTORIAL:
				Optional<RpgClassConfig> oClassConfig = RpgClassContainer.get()
						.getRpgClassConfig(rpg.getPlayerClass().getClass());
				oClassConfig.ifPresent(classConfig -> {
					Location loc = classConfig.getStartLocation();
					p.teleport(loc);
				});
				
				Optional<ARpgNpc> oNpc = NPCContainer.get().getNpc(rpg.getPlayerClass().getClassIdentifier());
				final RpgPlayer copyRpg = rpg;
				oNpc.ifPresent(npc -> {
					npc.addBehavior(copyRpg, 0);
				});
				break;
			case GAME:
				break;
		}
	}

}
