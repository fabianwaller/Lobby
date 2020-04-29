package de.rewex.lobby.commands;

import de.rewex.lobby.Main;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BuildCmd implements CommandExecutor {

    private final Main plugin;

    public BuildCmd(Main main) {
        this.plugin = main;
    }

    public static ArrayList<String> build = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.noplayer);
            return true;
        }
        Player p = (Player)sender;
        if (!p.hasPermission("server.build")) {
            p.sendMessage(Main.noperm);
            return true;
        }

        if(!build.contains(p.getName())) {
            build.add(p.getName());
            p.setGameMode(GameMode.CREATIVE);
            p.sendMessage(Main.prefix + "§aDu kannst jetzt bauen");
            p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
        } else {
            build.remove(p.getName());
            p.setGameMode(GameMode.SURVIVAL);
            p.sendMessage(Main.prefix + "§cDu kannst jetzt nicht mehr bauen");
            p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
        }

        return true;
    }
}
