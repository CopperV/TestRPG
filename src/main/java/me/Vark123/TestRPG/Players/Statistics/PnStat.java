package me.Vark123.TestRPG.Players.Statistics;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import me.Vark123.TestRPG.Events.APlayerStatModifyEvent;
import me.Vark123.TestRPG.Events.Impl.PlayerPnModifyEvent;
import me.Vark123.TestRPG.Players.PlayerStat;
import me.Vark123.TestRPG.Players.RpgPlayer;

@Getter
@Entity
@DiscriminatorValue(value = "PN")
public class PnStat extends PlayerStat {

	@Transient
	private static final Class<? extends APlayerStatModifyEvent> eventClass = PlayerPnModifyEvent.class;
	
	public PnStat() {}
	public PnStat(RpgPlayer rpg) {
		super(rpg);
	}

	@Override
	protected Class<? extends APlayerStatModifyEvent> getEventClass() {
		return eventClass;
	}

}
