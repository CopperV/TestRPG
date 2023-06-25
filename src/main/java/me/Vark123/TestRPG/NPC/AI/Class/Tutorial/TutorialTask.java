package me.Vark123.TestRPG.NPC.AI.Class.Tutorial;

import me.Vark123.TestRPG.Players.RpgPlayer;
import net.citizensnpcs.api.ai.tree.BehaviorStatus;
import net.citizensnpcs.api.npc.NPC;

@FunctionalInterface
public interface TutorialTask <T> {

	public BehaviorStatus taskResult(RpgPlayer p, NPC npc, T objectToTask);
	
}
