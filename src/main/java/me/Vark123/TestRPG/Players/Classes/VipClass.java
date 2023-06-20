package me.Vark123.TestRPG.Players.Classes;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import me.Vark123.TestRPG.Players.PlayerClass;
import me.Vark123.TestRPG.Players.RpgPlayer;

@Getter
@Entity
@DiscriminatorValue(value = "VIP")
public class VipClass extends PlayerClass {

	public VipClass() {}
	public VipClass(RpgPlayer rpg) {
		super(rpg);
	}

}
