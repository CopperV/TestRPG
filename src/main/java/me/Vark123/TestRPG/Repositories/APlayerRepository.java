package me.Vark123.TestRPG.Repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import me.Vark123.TestRPG.Players.RpgPlayer;

public interface APlayerRepository {

	public boolean create(RpgPlayer rpg);

	public boolean update(RpgPlayer rpg);

	public boolean delete(RpgPlayer rpg);

	public RpgPlayer getPlayerByID(UUID uid);
	
	public Optional<RpgPlayer> getPlayerByName(String nick);

	public List<RpgPlayer> getAllPlayers();

}
