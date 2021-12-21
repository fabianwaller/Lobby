package de.rewex.lobby.commands;

import de.rewex.lobby.Lobby;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BuildCmd implements CommandExecutor {

    private final Lobby plugin;

    public BuildCmd(Lobby main) {
        this.plugin = main;
    }

    public static ArrayList<String> build = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lobby.noplayer);
            return true;
        }
        Player p = (Player)sender;
        if (!p.hasPermission("server.build")) {
            p.sendMessage(Lobby.noperm);
            return true;
        }

        if(!build.contains(p.getName())) {
            build.add(p.getName());
            p.setGameMode(GameMode.CREATIVE);
            p.sendMessage(Lobby.prefix + "§aDu kannst jetzt bauen");
            p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
        } else {
            build.remove(p.getName());
            p.setGameMode(GameMode.SURVIVAL);
            p.sendMessage(Lobby.prefix + "§cDu kannst jetzt nicht mehr bauen");
            p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
        }

        return true;
    }
}
