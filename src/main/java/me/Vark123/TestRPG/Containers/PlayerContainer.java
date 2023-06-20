package me.Vark123.TestRPG.Containers;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

import lombok.AccessLevel;
import lombok.Getter;
import me.Vark123.TestRPG.Players.RpgPlayer;

@Getter
public class PlayerContainer {

	private static final PlayerContainer inst = new PlayerContainer();

	@Getter(value = AccessLevel.NONE)
	private final Map<Player, RpgPlayer> container;

	private PlayerContainer() {
		container = new ConcurrentHashMap<>();
	}

	public static final PlayerContainer get() {
		return inst;
	}

	public Optional<RpgPlayer> getRpgPlayer(Player p) {
		if (container.containsKey(p))
			return Optional.of(container.get(p));
		return Optional.empty();
	}

	public void storePlayer(Player p, RpgPlayer rpg) {
		container.put(p, rpg);
	}

	public void removePlayer(Player p) {
		container.remove(p);
	}

}
