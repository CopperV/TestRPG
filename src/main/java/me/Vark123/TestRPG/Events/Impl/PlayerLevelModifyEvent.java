package me.Vark123.TestRPG.Events.Impl;

import me.Vark123.TestRPG.Events.APlayerStatModifyEvent;
import me.Vark123.TestRPG.Players.PlayerStatModifyType;
import me.Vark123.TestRPG.Players.RpgPlayer;

public class PlayerLevelModifyEvent extends APlayerStatModifyEvent {

	public PlayerLevelModifyEvent(RpgPlayer player, double value, PlayerStatModifyType modType) {
		super(player, value, modType);
	}

	public PlayerLevelModifyEvent(RpgPlayer player, double value, PlayerStatModifyType modType, boolean async) {
		super(player, value, modType, async);
	}

}
