package me.Vark123.TestRPG.Containers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.Getter;
import me.Vark123.TestRPG.NPC.ARpgNpc;

@Getter
public class NPCContainer {
	
	private static final NPCContainer inst = new NPCContainer();
	
	private final Map<String, ARpgNpc> npcRegistry;
	
	private NPCContainer() {
		npcRegistry = new HashMap<>();
	}
	
	public static final NPCContainer get() {
		return inst;
	}
	
	public void registerNPC(ARpgNpc npc) {
		npcRegistry.put(npc.getId(), npc);
	}
	
	public Optional<ARpgNpc> getNpc(String id) {
		if(!npcRegistry.containsKey(id))
			return Optional.empty();
		return Optional.of(npcRegistry.get(id));
	}

}
