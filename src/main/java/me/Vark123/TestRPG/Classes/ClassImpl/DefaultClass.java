package me.Vark123.TestRPG.Classes.ClassImpl;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import me.Vark123.TestRPG.Classes.PlayerClass;
import me.Vark123.TestRPG.Players.RpgPlayer;

@Getter
@Entity
@DiscriminatorValue(value = "DEFAULT")
public class DefaultClass extends PlayerClass {

	public DefaultClass() {}
	public DefaultClass(RpgPlayer rpg) {
		super(rpg);
	}
	public DefaultClass(PlayerClass oldClass) {
		super(oldClass);
	}
	
}
