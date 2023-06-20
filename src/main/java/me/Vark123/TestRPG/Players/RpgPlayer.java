package me.Vark123.TestRPG.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import me.Vark123.TestRPG.Classes.PlayerClass;
import me.Vark123.TestRPG.Classes.ClassImpl.NoClass;
import me.Vark123.TestRPG.Players.Statistics.LevelStat;

@Getter
@Entity
@NamedQuery(
		name = "RpgPlayer.getByUUID", 
		query = "Select RPG From RpgPlayer RPG WHERE RPG.uid = :uid"
)
@NamedQuery(
		name = "RpgPlayer.getOptionalByName", 
		query = "Select RPG From RpgPlayer RPG WHERE RPG.lastName LIKE ':nick'"
)
@NamedQuery(
		name = "RpgPlayer.getAll", 
		query = "Select RPG From RpgPlayer RPG"
)
public class RpgPlayer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	private UUID uid;
	
	@Setter
	private String lastName;

	@Enumerated(EnumType.STRING)
	private PlayerState state;

	@OneToMany(cascade = CascadeType.ALL)
	private List<PlayerStat> stats;

	@OneToOne(cascade = CascadeType.ALL)
	private PlayerClass playerClass;

	public RpgPlayer(Player p) {
		this.lastName = p.getName();
		this.uid = p.getUniqueId();
		this.state = PlayerState.START;

		this.stats = new ArrayList<>();
		this.stats.add(new LevelStat(this));

		this.playerClass = new NoClass(this);
	}
	
	public RpgPlayer() {}

}
