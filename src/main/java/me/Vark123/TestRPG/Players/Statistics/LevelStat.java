package me.Vark123.TestRPG.Players.Statistics;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import me.Vark123.TestRPG.Events.APlayerStatModifyEvent;
import me.Vark123.TestRPG.Events.Impl.PlayerLevelModifyEvent;
import me.Vark123.TestRPG.Players.PlayerStat;

@Getter
@Entity
@DiscriminatorValue(value = "LEVEL")
public class LevelStat extends PlayerStat {

	@Transient
	private static final Class<? extends APlayerStatModifyEvent> eventClass = PlayerLevelModifyEvent.class;

	@Override
	protected Class<? extends APlayerStatModifyEvent> getEventClass() {
		return eventClass;
	}
	

}
