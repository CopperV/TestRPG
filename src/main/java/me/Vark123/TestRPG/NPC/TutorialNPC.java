package me.Vark123.TestRPG.NPC;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

public class TutorialNPC {

	public static void spawn(Player p) {
		NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Tester", p.getLocation());
	}
	
}
