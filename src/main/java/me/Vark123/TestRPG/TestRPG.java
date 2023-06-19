package me.Vark123.TestRPG;

import org.bukkit.plugin.java.JavaPlugin;

public class TestRPG extends JavaPlugin {

	private static TestRPG instance;

	@Override
	public void onLoad() {
		instance = this;
		super.onLoad();
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
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
