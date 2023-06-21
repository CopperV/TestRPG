package me.Vark123.TestRPG.API;

import java.util.Properties;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import lombok.Getter;
import me.Vark123.TestRPG.FileSystem;
import me.Vark123.TestRPG.TestRPG;
import me.Vark123.TestRPG.TestRPGConst;
import me.Vark123.TestRPG.Classes.PlayerClass;
import me.Vark123.TestRPG.Containers.HibernateClassContainer;
import me.Vark123.TestRPG.Containers.RpgClassContainer;
import me.Vark123.TestRPG.Containers.RpgStatContainer;
import me.Vark123.TestRPG.Players.PlayerStat;
import me.Vark123.TestRPG.Repositories.APlayerRepository;
import me.Vark123.TestRPG.Repositories.Impl.TestPlayerRepository;

@Getter
public final class TestRPGApi {

	private static final TestRPGApi api = new TestRPGApi();

	private final APlayerRepository playerRepository;

	private SessionFactory sessionFactory;
	private boolean init;

	private TestRPGApi() {

		playerRepository = new TestPlayerRepository();
	}

	public static final TestRPGApi get() {
		return api;
	}
	
	public void registerRpgStat(Class<? extends PlayerStat> rpgStat) {
		RpgStatContainer.get().getRpgStats().add(rpgStat);
		registerHibernateClass(rpgStat);
	}
	
	public void registerRpgClass(Class<? extends PlayerClass> rpgClass) {
		RpgClassContainer.get().storeClass(rpgClass);
		registerHibernateClass(rpgClass);
	}
	
	public void registerRpgClass(Class<? extends PlayerClass> rpgClass,
			ConfigurationSection rpgClassConfig) {
		RpgClassContainer.get().storeClass(rpgClass, rpgClassConfig);
		registerHibernateClass(rpgClass);
	}
	
	public void registerHibernateClass(Class<?> hibernateClass) {
		HibernateClassContainer.get().getClasses().add(hibernateClass);
	}
	
	public SessionFactory getSessionFactory() {
		if(!init) {
			YamlConfiguration fYml = FileSystem.getConfigYaml();
			String host = fYml.getString("mysql.host");
			int port = fYml.getInt("mysql.port");
			String db = fYml.getString("mysql.database");
			String user = fYml.getString("mysql.username");
			String pass = fYml.getString("mysql.password");

			Properties properties = new Properties();
			properties.setProperty("hibernate.dialect", TestRPGConst.HIBERNATE_DIALECT);
			properties.setProperty("connection.driver_class", TestRPGConst.CONNECTION_DRIVER_CLASS);
			properties.setProperty("hibernate.connection.url",
					"jdbc:mysql://" + host + ":" + port + "/" + db + "?useUnicode=true");
			properties.setProperty("hibernate.connection.username", user);
			properties.setProperty("hibernate.connection.password", pass);
			properties.setProperty("hibernate.hbm2ddl.auto", TestRPGConst.HIBERNATE_HBM2DDL_AUTO);
			properties.setProperty("current_session_context_class", TestRPGConst.CURRENT_SESSION_CONTEXT_CLASS);

			Configuration configuration = new Configuration().addProperties(properties);
			HibernateClassContainer.get().getClasses().forEach(_class -> {
				configuration.addAnnotatedClass(_class);
			});
			sessionFactory = configuration.buildSessionFactory();
			
			init = true;
		}
		return sessionFactory;
	}

	public InventoryManager getInventoryManager() {
		return TestRPG.inst().getManager();
	}

}
