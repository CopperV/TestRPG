package me.Vark123.TestRPG.Players.Statistics;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import me.Vark123.TestRPG.Events.Custom.PlayerStatModifyEvent;
import me.Vark123.TestRPG.Events.Custom.Impl.PlayerLevelModifyEvent;
import me.Vark123.TestRPG.Players.PlayerStat;

@Getter
@Entity
@DiscriminatorValue(value = "LEVEL")
public class LevelStat extends PlayerStat {

	@Transient
	private static final Class<? extends PlayerStatModifyEvent> eventClass = PlayerLevelModifyEvent.class;

	@Override
	protected Class<? extends PlayerStatModifyEvent> getEventClass() {
		return eventClass;
	}
	

}
