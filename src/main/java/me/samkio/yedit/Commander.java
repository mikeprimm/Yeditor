/*    */ package me.samkio.yedit;
/*    */ 
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commander
{
	public static Yeditor plugin;
	private final Functions functions = new Functions();

	public void command(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if ((args[0].equalsIgnoreCase("add")) && (args.length == 3)) {
			if((sender instanceof Player) && (Yeditor.Permissions.has((Player)sender, "yeditor.groupadd." + args[2]) == false)) {
				sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.RED + " You lack the privileges to add users to group " + args[2]);
			}
			else {
				boolean added = this.functions.addPlayer(args[1], args[2]);
				if (added) {
					sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.GREEN + " Player:" + args[1] + " successfully added.");
				 	Yeditor.doPermissionsReload(sender);
               	}
				else
					sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.RED + " An error has occurred. Check log.");
			}
		}
		else if ((args[0].equalsIgnoreCase("modify")) && (args.length == 3)) {
			if((sender instanceof Player) && (Yeditor.Permissions.has((Player)sender, "yeditor.groupadd." + args[2]) == false)) {
				sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.RED + " You lack the privileges to modify users into group " + args[2]);
			}
			else {
				boolean added = this.functions.addPlayer(args[1], args[2]);
				if (added) {
					sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.GREEN + " Player:" + args[1] + " successfully modified.");
					Yeditor.doPermissionsReload(sender);
				}
				else
					sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.RED + " An error has occurred. Check log.");
			}
		}
		else if ((args[0].equalsIgnoreCase("del")) && (args.length == 2)) {
			boolean delete = false;
			boolean exists = this.functions.checkPlayer(args[1]);
			if (exists) {
				delete = this.functions.delPlayer(args[1]);
			}
			if (delete) {
				sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.GREEN + " Player:" + args[1] + " successfully deleted.");
				Yeditor.doPermissionsReload(sender);
			}
			else if (!exists)
				sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.RED + " Player does not exist.");
			else
				sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.RED + " An error has occurred. Check log.");
		}
        else if(args[0].equalsIgnoreCase("autoreload")) {
            if(args.length == 1) {
            }
            else if(args[1].equals("true")) {
            	Yeditor.do_reload = true;
            }
            else if(args[1].equals("false")) {
            	Yeditor.do_reload = false;
            }
        	sender.sendMessage("auto-reload: " + Yeditor.do_reload);
        }
        else if ((args[0].equalsIgnoreCase("help")) || (args[0].equalsIgnoreCase("?"))) {
        	sender.sendMessage(ChatColor.AQUA + "[YEDIT] -Help- Plugin by Samkio (updated by mikeprimm):");
        	sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.WHITE + " /yt add <player> <group>");
        	sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.WHITE + " /yt modify <player> <group>");
        	sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.WHITE + " /yt del <player>");
        	sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.WHITE + " /yt autoreload <true|false>");
        } 
        else {
        	sender.sendMessage(ChatColor.AQUA + "[YEDIT]" + ChatColor.RED + " Bad Syntax. /yt help.");
        }
	}
}
