package me.Vark123.TestRPG.Players;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "rpg_player")
public class RpgPlayer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private UUID uid;
	
	@OneToMany(mappedBy = "rpg_player", cascade = CascadeType.ALL)
	private List<PlayerStat> stats;
	
	@OneToOne
	private PlayerClass playerClass;

}
