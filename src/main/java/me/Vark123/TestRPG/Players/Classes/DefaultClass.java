package me.Vark123.TestRPG.Players.Classes;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import me.Vark123.TestRPG.Players.PlayerClass;
import me.Vark123.TestRPG.Players.RpgPlayer;

@Getter
@Entity
@DiscriminatorValue(value = "DEFAULT")
public class DefaultClass extends PlayerClass {

	public DefaultClass() {}
	public DefaultClass(RpgPlayer rpg) {
		super(rpg);
	}
	
}
