package me.Vark123.TestRPG.Commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RpgCommandManager {

	private static final RpgCommandManager manager = new RpgCommandManager();
	
	private final Map<String, ARpgCommand> rpgCommandRegistry;
	
	private RpgCommandManager() {
		rpgCommandRegistry = new HashMap<>();
	}
	
	public static final RpgCommandManager get() {
		return manager;
	}
	
	public void registerRpgCommand(ARpgCommand cmd) {
		rpgCommandRegistry.put(cmd.getName(), cmd);
	}
	
	public Optional<ARpgCommand> getCommandExecutor(String cmd){
		if(rpgCommandRegistry.containsKey(cmd))
			return Optional.of(rpgCommandRegistry.get(cmd));
		return Optional.empty();
	}
	
	public Map<String, ARpgCommand> getRegistry() {
		return new HashMap<>(rpgCommandRegistry);
	}
	
}
