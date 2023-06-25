package me.Vark123.TestRPG.Classes;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import me.Vark123.TestRPG.Players.RpgPlayer;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "name", discriminatorType = DiscriminatorType.STRING)
public abstract class PlayerClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "player_id")
	private RpgPlayer player;
	
	public PlayerClass() {}
	public PlayerClass(RpgPlayer rpg) {
		this.player = rpg;
	}
	public PlayerClass(PlayerClass oldClass) {
		this.id = oldClass.id;
		this.player = oldClass.player;
	}
	
	public abstract String getClassIdentifier();

}
