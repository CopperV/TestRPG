package me.Vark123.TestRPG.Classes.ClassImpl;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import me.Vark123.TestRPG.Classes.PlayerClass;
import me.Vark123.TestRPG.Players.RpgPlayer;

@Getter
@Entity
@DiscriminatorValue(value = "VIP")
public class VipClass extends PlayerClass {

	public VipClass() {}
	public VipClass(RpgPlayer rpg) {
		super(rpg);
	}
	public VipClass(PlayerClass oldClass) {
		super(oldClass);
	}
	
	@Override
	public String getClassIdentifier() {
		return "VIP";
	}

}
