package me.Vark123.TestRPG.Containers;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import me.Vark123.TestRPG.Players.PlayerStat;

@Getter
public final class RpgStatContainer {

	private static final RpgStatContainer inst = new RpgStatContainer();
	
	private final Set<Class<? extends PlayerStat>> rpgStats;
	
	private RpgStatContainer() {
		rpgStats = new HashSet<>();
	}
	
	public static final RpgStatContainer get() {
		return inst;
	}
	
}
