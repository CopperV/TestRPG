package me.Vark123.TestRPG.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import me.Vark123.TestRPG.TestRPGConst;
import me.Vark123.TestRPG.Events.Impl.PlayerLevelModifyEvent;
import me.Vark123.TestRPG.Players.RpgPlayer;
import me.Vark123.TestRPG.Players.Statistics.PnStat;

public class PlayerLevelUpdateListener implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void onUpdate(PlayerLevelModifyEvent e) {
		if(e.isCancelled())
			return;
		
		int value = (int) e.getValue();
		if(value < 1)
			return;
		
		RpgPlayer rpg = e.getPlayer();
		int pn = value * TestRPGConst.PN_PER_LEVEL;
		rpg.getStats().stream().filter(stat -> {
			return stat instanceof PnStat;
		}).findAny().ifPresent(stat -> {
			stat.modifyValue(pn, e.getModType());
		});
	}
	
}
