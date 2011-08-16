package me.samkio.yedit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.reader.UnicodeReader;

public class Functions
{
	private static final Logger log = Logger.getLogger("Minecraft");
	private final Yaml yaml = new Yaml(new SafeConstructor());
	public static Yeditor plugin;
	Map<String, Map> data;

	@SuppressWarnings("unchecked")
	public boolean loadPermissions()
	{
		File file = new File(Yeditor.configer);
		FileInputStream rx = null;
		try {
			rx = new FileInputStream(file);
		}
		catch (FileNotFoundException e) {
			log.severe("[YEDITOR]" + e);
			return false;
		}
		this.data = ((Map<String, Map>)this.yaml.load(new UnicodeReader(rx)));
		try {
			rx.close();
		} catch (IOException e) {
			log.severe("[YEDITOR]" + e);
			return false;
		}
		return true;
	}

	public boolean writePermissions() {
		File file = new File(Yeditor.configer);
		FileWriter tx = null;
		try {
			tx = new FileWriter(file, false);
		} catch (IOException e) {
			log.severe("[YEDITOR]" + e);
			return false;
		}
		try {
			tx.write(this.yaml.dump(this.data));
			tx.flush();
		} catch (IOException e) {
			log.severe("[YEDITOR]" + e);
			return false;
		} finally {
			try {
				tx.close();
			} catch (IOException e) {
				log.severe("[YEDITOR]" + e);
				return false;
			}
		}
		return true;
	}

	public boolean addPlayer(String player, String group) {
		if (!loadPermissions())
			return false;
		Map<String, Map> users = this.data.get("users");
		if (users == null)
			users = new HashMap<String, Map>();
		Map<String, Object> userData = null;
		player = player.toLowerCase();
		for(Object o : users.keySet()) {
			if(player.equalsIgnoreCase((String) o)) {
				userData = users.get(o);
				player = (String)o;
			}
		}
		if (userData == null) {
			userData = new HashMap<String, Object>();
		}
		if (userData.get("permissions") == null) {
			userData.put("permissions", new String[0]);
		}
		userData.put("group", group);
		users.put(player, userData);
		this.data.put("users", users);

		return writePermissions();
	}

	public boolean checkPlayer(String player)
	{
		if (!loadPermissions())
			return false;
		Map<?, ?> users = this.data.get("users");
		if (users == null)
			users = new HashMap<Object, Object>();
		Map<?, ?> userData = null;
		player = player.toLowerCase();
		for(Object o : users.keySet()) {
			if(player.equalsIgnoreCase((String) o)) {
				userData = (Map<?, ?>)users.get(o);
				player = (String)o;
			}
		}

		return userData != null;
	}

	public boolean delPlayer(String player)
	{
		if (!loadPermissions())
			return false;
		Map<?, ?> users = this.data.get("users");
		if (users == null)
			users = new HashMap<Object, Object>();
		Map<?, ?> userData = null;
		player = player.toLowerCase();
		for(Object o : users.keySet()) {
			if(player.equalsIgnoreCase((String) o)) {
				userData = (Map<?, ?>)users.get(o);
				player = (String)o;
			}
		}
		if (userData == null) return false;
		users.remove(player);
		this.data.put("users", users);

		return writePermissions();
	}
}
