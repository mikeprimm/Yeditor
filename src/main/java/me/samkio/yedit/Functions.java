/*     */ package me.samkio.yedit;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Logger;
/*     */ import org.yaml.snakeyaml.Yaml;
/*     */ import org.yaml.snakeyaml.constructor.SafeConstructor;
/*     */ import org.yaml.snakeyaml.reader.UnicodeReader;
/*     */ 
/*     */ public class Functions
/*     */ {
/*  35 */   private static final Logger log = Logger.getLogger("Minecraft");
/*  36 */   private final Yaml yaml = new Yaml(new SafeConstructor());
/*     */   public static Yeditor plugin;
/*     */   Map data;
/*     */ 
/*     */   public boolean loadPermissions()
/*     */   {
/*  41 */     File file = new File(Yeditor.configer);
/*  42 */     FileInputStream rx = null;
/*     */     try {
/*  44 */       rx = new FileInputStream(file);
/*     */     }
/*     */     catch (FileNotFoundException e) {
/*  47 */       log.severe("[YEDITOR]" + e);
/*  48 */       return false;
/*     */     }
/*  50 */     this.data = ((Map)this.yaml.load(new UnicodeReader(rx)));
/*     */     try {
/*  52 */       rx.close();
/*     */     } catch (IOException e) {
/*  54 */       log.severe("[YEDITOR]" + e);
/*  55 */       return false;
/*     */     }
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean writePermissions() {
/*  61 */     File file = new File(Yeditor.configer);
/*  62 */     FileWriter tx = null;
/*     */     try {
/*  64 */       tx = new FileWriter(file, false);
/*     */     } catch (IOException e) {
/*  66 */       log.severe("[YEDITOR]" + e);
/*  67 */       return false;
/*     */     }
/*     */     try {
/*  70 */       tx.write(this.yaml.dump(this.data));
/*  71 */       tx.flush();
/*     */     } catch (IOException e) {
/*  73 */       log.severe("[YEDITOR]" + e);
/*  74 */       return false;
/*     */     } finally {
/*     */       try {
/*  77 */         tx.close();
/*     */       } catch (IOException e) {
/*  79 */         log.severe("[YEDITOR]" + e);
/*  80 */         return false;
/*     */       }
/*     */     }
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean addPlayer(String player, String group) {
/*  87 */     if (!loadPermissions())
/*  88 */       return false;
/*  89 */     Map users = (Map)this.data.get("users");
/*  90 */     if (users == null)
/*  91 */       users = new HashMap();
              Map userData = null;
              player = player.toLowerCase();
			  for(Object o : users.keySet()) {
				  if(player.equalsIgnoreCase((String) o)) {
					  userData = (Map)users.get(o);
					  player = (String)o;
				  }
			  }
/*  93 */     if (userData == null) {
/*  94 */       userData = new HashMap();
/*     */     }
/*  96 */     if (userData.get("permissions") == null) {
/*  97 */       userData.put("permissions", new String[0]);
/*     */     }
/*  99 */     userData.put("group", group);
/* 100 */     users.put(player, userData);
/* 101 */     this.data.put("users", users);
/*     */ 
/* 104 */     return writePermissions();
/*     */   }
/*     */ 
/*     */   public boolean checkPlayer(String player)
/*     */   {
/* 110 */     if (!loadPermissions())
/* 111 */       return false;
/* 112 */     Map users = (Map)this.data.get("users");
/* 113 */     if (users == null)
/* 114 */       users = new HashMap();
              Map userData = null;
              player = player.toLowerCase();
              for(Object o : users.keySet()) {
                if(player.equalsIgnoreCase((String) o)) {
                	userData = (Map)users.get(o);
                	player = (String)o;
                }
              }
/*     */ 
/* 117 */     return userData != null;
/*     */   }
/*     */ 
/*     */   public boolean delPlayer(String player)
/*     */   {
/* 125 */     if (!loadPermissions())
/* 126 */       return false;
/* 127 */     Map users = (Map)this.data.get("users");
/* 128 */     if (users == null)
/* 129 */       users = new HashMap();
              Map userData = null;
              player = player.toLowerCase();
              for(Object o : users.keySet()) {
                if(player.equalsIgnoreCase((String) o)) {
                  userData = (Map)users.get(o);
  	              player = (String)o;
  	            }
              }
/* 131 */     if (userData == null) return false;
/* 132 */     users.remove(player);
/* 133 */     this.data.put("users", users);
/*     */ 
/* 136 */     return writePermissions();
/*     */   }
/*     */ }

/* Location:           /Users/mike/Downloads/Yeditor.jar
 * Qualified Name:     me.samkio.yedit.Functions
 * JD-Core Version:    0.6.0
 */