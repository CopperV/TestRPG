package me.Vark123.TestRPG.Events.Custom.Impl;

import me.Vark123.TestRPG.Events.Custom.PlayerStatModifyEvent;
import me.Vark123.TestRPG.Players.PlayerStatModifyType;
import me.Vark123.TestRPG.Players.RpgPlayer;

public class PlayerLevelModifyEvent extends PlayerStatModifyEvent {

	public PlayerLevelModifyEvent(RpgPlayer player, double value, PlayerStatModifyType modType) {
		super(player, value, modType);
	}
	public PlayerLevelModifyEvent(RpgPlayer player, double value, PlayerStatModifyType modType, boolean async) {
		super(player, value, modType, async);
	}

}
