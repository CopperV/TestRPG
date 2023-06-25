package me.Vark123.TestRPG.NPC.AI.Class.Tutorial;

import me.Vark123.TestRPG.Players.RpgPlayer;
import net.citizensnpcs.api.ai.tree.BehaviorGoalAdapter;
import net.citizensnpcs.api.ai.tree.BehaviorStatus;
import net.citizensnpcs.api.npc.NPC;

public abstract class ATutorialBehavior <T> extends BehaviorGoalAdapter {

	protected RpgPlayer player;
	protected NPC npc;
	protected T testObject;
	protected TutorialTask<T> task;
	
	public ATutorialBehavior(RpgPlayer player, NPC npc, T testObject) {
		this.player = player;
		this.npc = npc;
		this.testObject = testObject;
	}

	@Override
	public void reset() {
		player = null;
		npc = null;
		testObject = null;
		task = null;
	}

	@Override
	public BehaviorStatus run() {
		if(task == null) {
			return BehaviorStatus.FAILURE;
		}
		
		BehaviorStatus status = task.taskResult(player, npc, testObject);
		switch(status) {
			case FAILURE:
				break;
			case RESET_AND_REMOVE:
				break;
			case RUNNING:
				break;
			case SUCCESS:
				break;
			default:
				break;
		}
		return status;
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}
	
	

}
