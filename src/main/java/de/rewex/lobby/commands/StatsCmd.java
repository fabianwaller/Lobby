package de.rewex.lobby.commands;

import de.rewex.lobby.Main;
import de.rewex.lobby.manager.ProfilHandler;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCmd implements CommandExecutor  {

    private final Main plugin;

    public StatsCmd(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Main.noplayer);
            return true;
        }

        Player p = (Player) sender;

        ProfilHandler profilHandler = new ProfilHandler(p);
        p.openInventory(profilHandler.getStats());

        p.playSound(p.getLocation(), Sound.CLICK, 3.0F, 2.0F);

        return true;
    }

}
