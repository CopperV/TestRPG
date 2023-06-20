package me.Vark123.TestRPG.GUI.Misc;

import lombok.Getter;

@Getter
public final class ChooseClassInvManager {

	private static final ChooseClassInvManager inst = new ChooseClassInvManager();
	
	private ChooseClassInvManager() {
		
	}
	
	public static final ChooseClassInvManager get() {
		return inst;
	}
	
}
