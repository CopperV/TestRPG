package me.Vark123.TestRPG.NPC.AI.Class.Tutorial.BehaviorImpl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.Vark123.TestRPG.NPC.AI.Class.Tutorial.ATutorialBehavior;
import me.Vark123.TestRPG.Players.RpgPlayer;
import me.clip.placeholderapi.PlaceholderAPI;
import net.citizensnpcs.api.ai.tree.BehaviorStatus;
import net.citizensnpcs.api.npc.NPC;

public class MessageBehavior extends ATutorialBehavior<String> {

	public MessageBehavior(RpgPlayer player, NPC npc, String testObject) {
		super(player, npc, testObject);
		
		this.task = (arg0, arg1, arg2) -> {
			Player p = Bukkit.getPlayer(player.getUid());
			if(p == null
					|| !p.isOnline())
				return BehaviorStatus.FAILURE;
			String message = PlaceholderAPI.setPlaceholders(p, arg2);
			p.sendMessage(npc.getFullName()+"§7: §a"+message);
			return BehaviorStatus.SUCCESS;
		};
	}

}
