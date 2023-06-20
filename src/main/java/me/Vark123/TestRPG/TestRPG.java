package me.Vark123.TestRPG;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import lombok.Getter;
import me.Vark123.TestRPG.API.TestRPGApi;

@Getter
public class TestRPG extends JavaPlugin {

	private static TestRPG instance;

	private InventoryManager manager;

	@Override
	public void onLoad() {
		instance = this;

		super.onLoad();
	}

	@Override
	public void onEnable() {
		FileSystem.init();

		CommandExecutors.setExecutors();
		ListenerManager.registerListeners();

		manager = new InventoryManager(instance);
		manager.invoke();

		// Wywolywana jest metoda get, by uruchomic konstruktor API i zainicjowac
		// SessionFactory
		TestRPGApi.get();

		super.onEnable();
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
	}

	public static final TestRPG inst() {
		return instance;
	}

}
