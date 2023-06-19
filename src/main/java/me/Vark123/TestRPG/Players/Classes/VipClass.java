package me.Vark123.TestRPG.Players.Classes;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import me.Vark123.TestRPG.Players.PlayerClass;

@Getter
@Entity
@DiscriminatorValue(value = "VIP")
public class VipClass extends PlayerClass {

}
