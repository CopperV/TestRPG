package me.Vark123.TestRPG.NPC.Class.Tutorial.NPCs;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;

import me.Vark123.TestRPG.Classes.ClassImpl.VipClass;
import me.Vark123.TestRPG.Containers.RpgClassContainer;
import me.Vark123.TestRPG.Containers.RpgClassContainer.RpgClassConfig;
import me.Vark123.TestRPG.NPC.ARpgNpc;
import me.Vark123.TestRPG.NPC.AI.Class.Tutorial.ATutorialBehavior;
import me.Vark123.TestRPG.NPC.AI.Class.Tutorial.BehaviorImpl.DelayBehavior;
import me.Vark123.TestRPG.NPC.AI.Class.Tutorial.BehaviorImpl.MessageBehavior;
import me.Vark123.TestRPG.NPC.AI.Class.Tutorial.BehaviorImpl.TutorialEndBehavior;
import me.Vark123.TestRPG.Players.RpgPlayer;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.ai.tree.Behavior;
import net.citizensnpcs.api.ai.tree.Sequence;

public class VipClassNPC extends ARpgNpc {

	public VipClassNPC() {
		super(
				CitizensAPI.getNPCRegistry().createNPC(
						EntityType.PLAYER,
						"§e§lPremium NPC"
				),
				"VIP"
		);
		
		Optional<RpgClassConfig> oConfig = RpgClassContainer.get().getRpgClassConfig(VipClass.class);
		oConfig.ifPresent(config -> {
			npc.spawn(config.getStartLocation());
		});
	}

	@Override
	public void addBehavior(RpgPlayer rpg, int behaviorId) {
		List<Behavior> behaviors = new LinkedList<>();
		
		List<ATutorialBehavior<?>> tmp = new LinkedList<>();
		tmp.add(new DelayBehavior(rpg, npc, new Date(), 3*1000));
		tmp.add(new MessageBehavior(rpg, npc, "Witaj %player_name% w naszej wiosce!"));
		tmp.add(new DelayBehavior(rpg, npc, new Date(), 4*1000));
		tmp.add(new MessageBehavior(rpg, npc, "Wybrales klase vip."));
		tmp.add(new DelayBehavior(rpg, npc, new Date(), (long) (2.5*1000)));
		tmp.add(new MessageBehavior(rpg, npc, "Pozwol, ze opowiem Ci o nas..."));
		tmp.add(new DelayBehavior(rpg, npc, new Date(), 3*1000));
		tmp.add(new MessageBehavior(rpg, npc, "[Wprowadzenie etc...]"));
		tmp.add(new TutorialEndBehavior(rpg, npc, Bukkit.getPlayer(rpg.getUid())));
		
		behaviors.addAll(tmp);
		npc.getDefaultGoalController().addGoal(Sequence.createSequence(behaviors), 1);
	}

}
