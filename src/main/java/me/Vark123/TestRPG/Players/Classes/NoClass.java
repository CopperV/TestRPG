package me.Vark123.TestRPG.Players.Classes;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import me.Vark123.TestRPG.Players.PlayerClass;
import me.Vark123.TestRPG.Players.RpgPlayer;

@Getter
@Entity
@DiscriminatorValue(value = "NONE")
public class NoClass extends PlayerClass {

	public NoClass() {}
	public NoClass(RpgPlayer rpg) {
		super(rpg);
	}

}
