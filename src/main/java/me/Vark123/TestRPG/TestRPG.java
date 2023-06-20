package me.Vark123.TestRPG;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import lombok.Getter;
import me.Vark123.TestRPG.API.TestRPGApi;
import me.Vark123.TestRPG.Classes.PlayerClass;
import me.Vark123.TestRPG.Classes.ClassImpl.DefaultClass;
import me.Vark123.TestRPG.Classes.ClassImpl.NoClass;
import me.Vark123.TestRPG.Classes.ClassImpl.VipClass;
import me.Vark123.TestRPG.Players.PlayerStat;
import me.Vark123.TestRPG.Players.RpgPlayer;
import me.Vark123.TestRPG.Players.Statistics.LevelStat;

@Getter
public class TestRPG extends JavaPlugin {

	private static TestRPG instance;

	private InventoryManager manager;

	@Override
	public void onLoad() {
		instance = this;
		
		TestRPGApi.get().registerHibernateClass(RpgPlayer.class);
		TestRPGApi.get().registerHibernateClass(PlayerClass.class);
		TestRPGApi.get().registerHibernateClass(PlayerStat.class);
		TestRPGApi.get().registerHibernateClass(NoClass.class);
		TestRPGApi.get().registerRpgClass(DefaultClass.class);
		TestRPGApi.get().registerRpgClass(VipClass.class);
		TestRPGApi.get().registerRpgStat(LevelStat.class);
		
		super.onLoad();
	}

	@Override
	public void onEnable() {
		FileSystem.init();

		CommandExecutors.setExecutors();
		ListenerManager.registerListeners();

		manager = new InventoryManager(instance);
		manager.invoke();

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
