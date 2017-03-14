package org.openredstone.funcommands.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

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
            val weapon = if (args.size > 1) args[1] else "large trout"
            val victim = if (name == sender?.name) "themselves" else name

            Bukkit.broadcastMessage("${ChatColor.DARK_PURPLE}${sender?.name} ${ChatColor.RED}slapped ${ChatColor.DARK_PURPLE}$victim ${ChatColor.RED}with a")
            return true
        } else {
            sender?.sendMessage("Please provide a player to slap.")
            return false
        }
    }

}