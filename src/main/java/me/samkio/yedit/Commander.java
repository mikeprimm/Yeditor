/*    */ package me.samkio.yedit;
/*    */ 
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class Commander
/*    */ {
/*    */   public static Yeditor plugin;
/* 14 */   private final Functions functions = new Functions();
/*    */ 
/*    */   public void command(CommandSender sender, Command cmd, String commandLabel, String[] args)
/*    */   {
/* 18 */     if ((args[0].equalsIgnoreCase("add")) && (args.length == 3)) {
/* 19 */       boolean added = this.functions.addPlayer(args[1], args[2]);
/* 20 */       if (added)
/* 21 */         sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.GREEN + 
/* 22 */           " Player:" + args[1] + " sucessfully added.");
/*    */       else
/* 24 */         sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.RED + 
/* 25 */           " An error has occued. Check log.");
/*    */     }
/* 27 */     else if ((args[0].equalsIgnoreCase("modify")) && (args.length == 3)) {
/* 28 */       boolean added = this.functions.addPlayer(args[1], args[2]);
/* 29 */       if (added)
/* 30 */         sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.GREEN + 
/* 31 */           " Player:" + args[1] + " sucessfully modified.");
/*    */       else
/* 33 */         sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.RED + 
/* 34 */           " An error has occued. Check log.");
/*    */     }
/* 36 */     else if ((args[0].equalsIgnoreCase("del")) && (args.length == 2)) {
/* 37 */       boolean delete = false;
/* 38 */       boolean exsists = this.functions.checkPlayer(args[1]);
/* 39 */       if (exsists) {
/* 40 */         delete = this.functions.delPlayer(args[1]);
/*    */       }
/* 42 */       if (delete)
/* 43 */         sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.GREEN + 
/* 44 */           " Player:" + args[1] + " sucessfully deleted.");
/* 45 */       else if (!exsists)
/* 46 */         sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.RED + 
/* 47 */           " Player does not exsist.");
/*    */       else {
/* 49 */         sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.RED + 
/* 50 */           " An error has occued. Check log.");
/*    */       }
/*    */     }
/* 53 */     else if ((args[0].equalsIgnoreCase("help")) || 
/* 54 */       (args[0].equalsIgnoreCase("?"))) {
/* 55 */       sender.sendMessage(ChatColor.AQUA + "[YEDIT] -Help- Plugin by Samkio:");
/* 56 */       sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.WHITE + 
/* 57 */         " /yt add <player> <group>");
/* 58 */       sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.WHITE + 
/* 59 */         " /yt modify <player> <group>");
/*    */     } else {
/* 61 */       sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.RED + 
/* 62 */         " Bad Syntax. /yt help.");
/*    */     }
/*    */   }
/*    */ }

/* Location:           /Users/mike/Downloads/Yeditor.jar
 * Qualified Name:     me.samkio.yedit.Commander
 * JD-Core Version:    0.6.0
 */