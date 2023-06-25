package me.Vark123.TestRPG.NPC.AI.Class.Tutorial.BehaviorImpl;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.Vark123.TestRPG.NPC.AI.Class.Tutorial.ATutorialBehavior;
import me.Vark123.TestRPG.Players.RpgPlayer;
import net.citizensnpcs.api.ai.tree.BehaviorStatus;
import net.citizensnpcs.api.npc.NPC;

public class DelayBehavior extends ATutorialBehavior<Date> {
	
	private final long delay;

	public DelayBehavior(RpgPlayer player, NPC npc, Date testObject, 
			long delay) {
		super(player, npc, null);
		
		this.delay = delay;
		this.task = (arg0, arg1, arg2) -> {
			Player p = Bukkit.getPlayer(player.getUid());
			if(p == null
					|| !p.isOnline())
				return BehaviorStatus.FAILURE;
			Date current = new Date();
			if((current.getTime() - arg2.getTime()) < this.delay)
				return BehaviorStatus.RUNNING;
			return BehaviorStatus.SUCCESS;
		};
	}


	@Override
	public BehaviorStatus run() {
		if(this.testObject == null)
			this.testObject = new Date();
		return super.run();
	}

}
