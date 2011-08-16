package me.samkio.yedit;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Yeditor extends JavaPlugin
{
	private static final Logger log = Logger.getLogger("Minecraft");
	private final Commander commands = new Commander();
	public static boolean do_reload = false;
	public static PermissionHandler Permissions = null;
	public static Server Server = null;
	public static String configer = "plugins/Permissions/world.yml";

	public void onEnable() {
		log.info("Yeditor: enabling");
		File serverprops = new File("server.properties");
		Properties sp = new Properties();
		try {
			sp.load(new FileInputStream(serverprops));
			String DefaultWorld = sp.getProperty("level-name");
			configer = "plugins/Permissions/" + DefaultWorld + ".yml";
			setupPermissions();
			log.info("Yeditor: enabled");
		} catch (IOException iox) {
			log.severe("[Yeditor] ERROR LOADING server.properties");
		}
	}

	public void onDisable()
	{
		System.out.println("Yeditor disabled.");
	}

	public void setupPermissions() {

		Plugin test = getServer().getPluginManager().getPlugin("Permissions");
		PluginDescriptionFile pdfFile = getDescription();

		if (test != null) {
			Permissions = ((Permissions)test).getHandler();
		} else {
			log.info(pdfFile.getName() + " version " + pdfFile.getVersion() + "not enabled. Permissions not detected");
			getServer().getPluginManager().disablePlugin(this);
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		Player p = null;
		if(sender instanceof Player)
			p = (Player)sender;
		if (((commandLabel.equalsIgnoreCase("yt")) || (commandLabel.equalsIgnoreCase("yedit"))) && (args.length > 0)) {
			if ((p == null) || (Permissions.has(p, "yeditor.editor"))) {
				this.commands.command(sender, cmd, commandLabel, args);
			}
			return true;
		}
		return false;
	}

	public static void doPermissionsReload(CommandSender sender) {
		if(do_reload) {
			Permissions.reload();
			sender.sendMessage("Permissions reloaded");
		}
	}
}
