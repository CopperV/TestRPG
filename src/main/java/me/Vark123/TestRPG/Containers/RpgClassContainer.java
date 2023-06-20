package me.Vark123.TestRPG.Containers;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import me.Vark123.TestRPG.Players.PlayerClass;

@Getter
public final class RpgClassContainer {

	private static final RpgClassContainer inst = new RpgClassContainer();
	
	private final Set<Class<? extends PlayerClass>> rpgClasses;
	
	private RpgClassContainer() {
		this.rpgClasses = new HashSet<>();
	}
	
	public static final RpgClassContainer get() {
		return inst;
	}
	
}
