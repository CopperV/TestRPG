package me.Vark123.TestRPG.Players;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import me.Vark123.TestRPG.Events.APlayerStatModifyEvent;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class PlayerStat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "player_id")
	protected RpgPlayer player;
	
	protected double baseValue;
	protected double learnedValue;
	
	@Transient
	protected double calculatedValue;
	
	protected abstract Class<? extends APlayerStatModifyEvent> getEventClass();
	
	public void modifyValue(double value, PlayerStatModifyType type){
		APlayerStatModifyEvent event = null;
		try {
			Constructor<?> constructor = getEventClass().getConstructor(RpgPlayer.class, double.class, PlayerStatModifyType.class);
			event = (APlayerStatModifyEvent) constructor.newInstance(player, value, type);
		} catch (NoSuchMethodException | SecurityException | InstantiationException 
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(event == null)
			return;
		
		Bukkit.getPluginManager().callEvent(event);
		if(event.isCancelled()) {
			return;
		}
		
		switch(type) {
			case BASE:
				baseValue += event.getValue();
				break;
			case LEARNED:
				learnedValue += event.getValue();
				break;
			case CALC:
				calculatedValue += (event.getValue() + learnedValue + baseValue);
				break;
		}
		
	}
	
}
