package me.Vark123.TestRPG.Events.Custom;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import me.Vark123.TestRPG.Players.PlayerStatModifyType;
import me.Vark123.TestRPG.Players.RpgPlayer;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class PlayerStatModifyEvent extends Event implements Cancellable {
	
	boolean cancelled;
	private static final HandlerList handlers = new HandlerList();
	
	@Setter(value = AccessLevel.NONE)
	private RpgPlayer player;
	private double value;
	@Setter(value = AccessLevel.NONE)
	private PlayerStatModifyType modType;
	
	public PlayerStatModifyEvent(RpgPlayer player, double value, PlayerStatModifyType modType) {
		this(player, value, modType, false);
	}
	
	public PlayerStatModifyEvent(RpgPlayer player, double value, PlayerStatModifyType modType, boolean async) {
		super(async);
		this.player = player;
		this.value = value;
		this.modType = modType;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	

}
