package me.Vark123.TestRPG.NPC;

import lombok.Getter;
import me.Vark123.TestRPG.Players.RpgPlayer;
import net.citizensnpcs.api.npc.NPC;

@Getter
public abstract class ARpgNpc {
	
	protected final NPC npc;
	private final String id;
	
	public ARpgNpc(NPC npc, String id) {
		this.npc = npc;
		this.id = id;
	}
	
	public String getId() {
		return new String(id);
	}
	
	public abstract void addBehavior(RpgPlayer p, int behaviorId);

}
