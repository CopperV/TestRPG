package me.Vark123.TestRPG.GUI.Misc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.enums.DisabledInventoryClick;
import io.github.rysefoxx.inventory.plugin.enums.TimeSetting;
import io.github.rysefoxx.inventory.plugin.other.EventCreator;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import lombok.AccessLevel;
import lombok.Getter;
import me.Vark123.TestRPG.TestRPG;
import me.Vark123.TestRPG.Classes.PlayerClass;
import me.Vark123.TestRPG.Containers.PlayerContainer;
import me.Vark123.TestRPG.Containers.RpgClassContainer;
import me.Vark123.TestRPG.Containers.RpgClassContainer.RpgClassConfig;
import me.Vark123.TestRPG.Players.PlayerState;
import me.Vark123.TestRPG.Players.RpgPlayer;
import me.Vark123.TestRPG.Utils.ItemStackUtils;

@Getter
public final class ChooseClassInvManager {

	private static final ChooseClassInvManager inst = new ChooseClassInvManager();
	
	@Getter(value = AccessLevel.NONE)
	private final int size;
	@Getter(value = AccessLevel.NONE)
	private final InventoryProvider provider;
	@Getter(value = AccessLevel.NONE)
	private final EventCreator<InventoryClickEvent> clickEvent;
	
	private ChooseClassInvManager() {
		size = RpgClassContainer.get().getClassAmount()/9 + 1;
		
		clickEvent = clickEventCreator();
		provider = new InventoryProvider() {

			@Override
			public void init(Player player, InventoryContents contents) {
				List<RpgClassConfig> configs = RpgClassContainer.get().getContainer()
						.stream()
						.filter(_class -> {
							return RpgClassContainer.get().getRpgClassConfig(_class).isPresent();
						}).map(_class -> {
							return RpgClassContainer.get().getRpgClassConfig(_class).get();
						}).toList();
				int i = 0;
				for(RpgClassConfig cfg : configs) {
					contents.set(i, cfg.getClassInfoItem());
					++i;
				}
			}

			@Override
			public void close(Player player, RyseInventory inventory) {
				InventoryProvider.super.close(player, inventory);
			}
		};
	}
	
	public static final ChooseClassInvManager get() {
		return inst;
	}
	
	public void openMenu(Player p) {
		RyseInventory.builder()
			.title("§e§lWYBIERZ KLASE")
			.identifier(p.getUniqueId())
			.openDelay(3, TimeSetting.SECONDS)
			.rows(size)
			.ignoreClickEvent(DisabledInventoryClick.BOTTOM)
			.disableUpdateTask()
			.preventClose()
			.listener(clickEvent)
			.provider(provider)
			.build(TestRPG.inst())
			.open(p);
	}
	
	private EventCreator<InventoryClickEvent> clickEventCreator() {
		Consumer<InventoryClickEvent> event = e -> {
			Player p = (Player) e.getWhoClicked();
			Optional<RpgPlayer> oRpgPlayer = PlayerContainer.get().getRpgPlayer(p);
			if(oRpgPlayer.isEmpty())
				return;
			RpgPlayer rpgPlayer = oRpgPlayer.get();
			
			ItemStack it = e.getCurrentItem();
			if(it == null
					|| it.getType().equals(Material.AIR))
				return;
			
			NBTItem nbt = new NBTItem(it);
			if(!nbt.hasTag("classPath"))
				return;
			
			PlayerClass rpgClass;
			try {
				Class<?> _class = Class.forName(nbt.getString("classPath"));
				Constructor<?> constructor = _class.getConstructor(PlayerClass.class);
				rpgClass = (PlayerClass) constructor.newInstance(rpgPlayer.getPlayerClass());
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException 
					| InstantiationException | IllegalAccessException | IllegalArgumentException 
					| InvocationTargetException e1) {
				e1.printStackTrace();
				return;
			}
			
			Optional<RyseInventory> oInventory = TestRPG.inst().getManager().getInventory(p.getUniqueId());
			if(oInventory.isEmpty())
				return;
			RyseInventory ryseInventory = oInventory.get();
			ryseInventory.allowClose();
			ryseInventory.close(p);
			
			rpgPlayer.setState(PlayerState.TUTORIAL);
			rpgPlayer.setPlayerClass(rpgClass);
			Optional<RpgClassConfig> oConfig = RpgClassContainer.get().getRpgClassConfig(rpgClass.getClass());
			oConfig.ifPresent(config -> {
				p.teleport(config.getStartLocation());
				p.getInventory().clear();
				config.getStartItems().forEach(startItem -> {
					ItemStackUtils.giveOrDropItem(startItem, p);
				});
			});
		};
		EventCreator<InventoryClickEvent> creator = new EventCreator<>(InventoryClickEvent.class, event);
		return creator;
	}
	
}
