package org.openredstone.funcommands

import org.bukkit.plugin.java.JavaPlugin
import org.openredstone.funcommands.commands.RenameCommand
import org.openredstone.funcommands.commands.SlapCommand

class FunCommands : JavaPlugin() {

    override fun onEnable() {
        getCommand("rename").executor = RenameCommand()
        getCommand("slap").executor = SlapCommand()
    }

}