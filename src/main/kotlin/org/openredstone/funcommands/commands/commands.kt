package org.openredstone.funcommands.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.concurrent.ThreadLocalRandom

class RenameCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<out String>?): Boolean {
        if (sender is Player && args != null && args.isNotEmpty()) {
            // Rename the item in the main hand
            sender.inventory.itemInMainHand.itemMeta.displayName = args.joinToString(" ").replace("&", ChatColor.COLOR_CHAR + "")
            return true
        } else {
            sender?.sendMessage("Please provide a name for the item you're renaming.")
            return false
        }
    }

}

class SlapCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<out String>?): Boolean {
        if (args != null && args.isNotEmpty()) {
            val name = args[0]
            // Remove the name from the arguments so we can join them together for the weapon
            args.drop(1)

            val weapon = if (args.isNotEmpty()) args.joinToString(" ") else "large trout"
            val victim = if (name == sender?.name) "themselves" else name

            Bukkit.broadcastMessage(
                    "${ChatColor.DARK_PURPLE}${sender?.name} " +
                            "${ChatColor.RED}slapped " +
                            "${ChatColor.DARK_PURPLE}$victim " +
                            "${ChatColor.RED}with a " +
                            "${ChatColor.GOLD}$weapon${ChatColor.RED}!")

            return true
        } else {
            sender?.sendMessage("Please provide a player to slap.")
            return false
        }
    }

}

class DerpCommand(plugin: JavaPlugin) : CommandExecutor {

    // Derps file is called "Derps.txt" and not "derps.txt" for legacy reasons
    val derps = File(plugin.dataFolder.absolutePath + File.separator + "Derps.txt").readLines()

    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<out String>?): Boolean {
        if (sender != null && !sender.name.isNullOrEmpty()) {
            val name = sender.name
            val derp = derps[ThreadLocalRandom.current().nextInt(0, derps.size + 1)]

            Bukkit.broadcastMessage(
                    "${ChatColor.DARK_PURPLE}* " +
                            "${ChatColor.WHITE}$name " +
                            "${ChatColor.DARK_BLUE}DERP! " +
                            "${ChatColor.DARK_PURPLE}$derp")

            return true
        } else
            return false
    }

}