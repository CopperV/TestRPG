package me.Vark123.TestRPG.API;

import java.util.Properties;

import org.bukkit.configuration.file.YamlConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import lombok.Getter;
import me.Vark123.TestRPG.FileSystem;
import me.Vark123.TestRPG.TestRPG;
import me.Vark123.TestRPG.TestRPGConst;
import me.Vark123.TestRPG.Players.PlayerClass;
import me.Vark123.TestRPG.Players.PlayerStat;
import me.Vark123.TestRPG.Players.RpgPlayer;
import me.Vark123.TestRPG.Players.Classes.DefaultClass;
import me.Vark123.TestRPG.Players.Classes.VipClass;
import me.Vark123.TestRPG.Players.Statistics.LevelStat;

@Getter
public final class TestRPGApi {

	private static final TestRPGApi api = new TestRPGApi();
	
	private SessionFactory sessionFactory;
	
	private TestRPGApi() {
		
		YamlConfiguration fYml = FileSystem.getConfigYaml();
		String host = fYml.getString("mysql.host");
		int port = fYml.getInt("mysql.port");
		String db = fYml.getString("mysql.database");
		String user = fYml.getString("mysql.username");
		String pass = fYml.getString("mysql.password");
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", TestRPGConst.HIBERNATE_DIALECT);
		properties.setProperty("connection.driver_class", TestRPGConst.CONNECTION_DRIVER_CLASS);
		properties.setProperty("hibernate.connection.url", "jdbc:mysql://"+host+":"+port+"/"+db+"?useUnicode=true");
		properties.setProperty("hibernate.connection.username", user);
		properties.setProperty("hibernate.connection.password", pass);
		properties.setProperty("hibernate.hbm2ddl.auto", TestRPGConst.HIBERNATE_HBM2DDL_AUTO);
		properties.setProperty("current_session_context_class", TestRPGConst.CURRENT_SESSION_CONTEXT_CLASS);
		
		sessionFactory = new Configuration()
				.addProperties(properties)
				.addAnnotatedClass(RpgPlayer.class)
				.addAnnotatedClass(PlayerClass.class)
				.addAnnotatedClass(PlayerStat.class)
				.addAnnotatedClass(DefaultClass.class)
				.addAnnotatedClass(VipClass.class)
				.addAnnotatedClass(LevelStat.class)
				.buildSessionFactory();
		
	}
	
	public static final TestRPGApi get() {
		return api;
	}
	
	public InventoryManager getInventoryManager() {
		return TestRPG.inst().getManager();
	}
	
}
