/*    */ package me.samkio.yedit;
/*    */ 
/*    */ import com.nijiko.permissions.PermissionHandler;
/*    */ import com.nijikokun.bukkit.Permissions.Permissions;
/*    */ import com.nijikokun.bukkit.Permissions.PropertyHandler;
/*    */ import java.io.PrintStream;
/*    */ import java.util.logging.Logger;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.PluginDescriptionFile;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ import org.yaml.snakeyaml.Yaml;
/*    */ import org.yaml.snakeyaml.constructor.SafeConstructor;
/*    */ 
/*    */ public class Yeditor extends JavaPlugin
/*    */ {
/* 38 */   private static final Logger log = Logger.getLogger("Minecraft");
/* 39 */   private final Commander commands = new Commander();
/* 40 */   private final Functions functions = new Functions();
/* 41 */   private final Yaml yaml = new Yaml(new SafeConstructor());
/* 42 */   public static PermissionHandler Permissions = null;
/* 43 */   public static Server Server = null;
/* 44 */   private String DefaultWorld = "";
/* 45 */   public static String configer = "plugins/Permissions/world.yml";
/*    */ 
/*    */   public void onEnable() {
/* 48 */     PropertyHandler server = new PropertyHandler("server.properties");
/* 49 */     String DefaultWorld = server.getString("level-name");
/* 50 */     configer = "plugins/Permissions/" + DefaultWorld + ".yml";
/* 51 */     setupPermissions();
/*    */   }
/*    */ 
/*    */   public void onDisable()
/*    */   {
/* 56 */     System.out.println("Yeditor Disabled");
/*    */   }
/*    */ 
/*    */   public void setupPermissions() {
/* 60 */     Plugin test = getServer().getPluginManager()
/* 61 */       .getPlugin("Permissions");
/* 62 */     PluginDescriptionFile pdfFile = getDescription();
/*    */ 
/* 64 */     if (Permissions == null)
/* 65 */       if (test != null) {
/* 66 */         getServer().getPluginManager().enablePlugin(test);
/* 67 */         Permissions = ((Permissions)test).getHandler();
/*    */       } else {
/* 69 */         log.info(pdfFile.getName() + " version " + pdfFile.getVersion() + 
/* 70 */           "not enabled. Permissions not detected");
/* 71 */         getServer().getPluginManager().disablePlugin(this);
/*    */       }
/*    */   }
/*    */ 
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
/*    */   {
/* 78 */     if (((commandLabel.equalsIgnoreCase("yt")) || 
/* 79 */       (commandLabel
/* 79 */       .equalsIgnoreCase("yedit"))) && (args.length > 0)) {
/* 80 */       if (Permissions.has((Player)sender, "yeditor.editor")) {
/* 81 */         this.commands.command(sender, cmd, commandLabel, args);
/*    */       }
/*    */ 
/* 84 */       return true;
/*    */     }
/* 86 */     return false;
/*    */   }
/*    */ }

/* Location:           /Users/mike/Downloads/Yeditor.jar
 * Qualified Name:     me.samkio.yedit.Yeditor
 * JD-Core Version:    0.6.0
 */