package me.Vark123.TestRPG.NPC.AI.Class.Tutorial.BehaviorImpl;

import org.bukkit.entity.Player;

import me.Vark123.TestRPG.NPC.AI.Class.Tutorial.ATutorialBehavior;
import me.Vark123.TestRPG.Players.PlayerState;
import me.Vark123.TestRPG.Players.RpgPlayer;
import net.citizensnpcs.api.ai.tree.BehaviorStatus;
import net.citizensnpcs.api.npc.NPC;

public class TutorialEndBehavior extends ATutorialBehavior<Player> {

	public TutorialEndBehavior(RpgPlayer player, NPC npc, Player testObject) {
		super(player, npc, testObject);
		
		this.task = (arg0, arg1, arg2) -> {
			if(!arg2.isOnline())
				return BehaviorStatus.FAILURE;
			arg0.setState(PlayerState.GAME);
			return BehaviorStatus.SUCCESS;
		};
	}

}
