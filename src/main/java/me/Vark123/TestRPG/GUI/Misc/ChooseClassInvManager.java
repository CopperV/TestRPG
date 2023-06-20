package me.Vark123.TestRPG.GUI.Misc;

import org.bukkit.entity.Player;

import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.enums.DisabledInventoryClick;
import io.github.rysefoxx.inventory.plugin.enums.TimeSetting;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import lombok.AccessLevel;
import lombok.Getter;
import me.Vark123.TestRPG.TestRPG;
import me.Vark123.TestRPG.Containers.RpgClassContainer;
import me.Vark123.TestRPG.Containers.RpgClassContainer.RpgClassConfig;

@Getter
public final class ChooseClassInvManager {

	private static final ChooseClassInvManager inst = new ChooseClassInvManager();
	
	@Getter(value = AccessLevel.NONE)
	private final int size;
	@Getter(value = AccessLevel.NONE)
	private final InventoryProvider provider;
	
	private ChooseClassInvManager() {
		size = RpgClassContainer.get().getClassAmount()/9 + 1;
		
		provider = new InventoryProvider() {

			@Override
			public void init(Player player, InventoryContents contents) {
				RpgClassConfig[] configs = (RpgClassConfig[]) RpgClassContainer.get().getContainer().stream()
					.filter(_class -> {
						return RpgClassContainer.get().getRpgClassConfig(_class).isPresent();
					}).map(_class -> {
						return RpgClassContainer.get().getRpgClassConfig(_class).get();
					}).toArray();
				for(int i = 0; i < configs.length; ++i) {
					contents.set(i, configs[i].getClassInfoItem());
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
			.loadDelay(3, TimeSetting.SECONDS)
			.rows(size)
			.ignoreClickEvent(DisabledInventoryClick.BOTTOM)
			.disableUpdateTask()
			.preventClose()
			.provider(provider)
			.build(TestRPG.inst())
			.open(p);
	}
	
}
