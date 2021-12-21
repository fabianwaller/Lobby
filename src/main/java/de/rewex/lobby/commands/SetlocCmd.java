package de.rewex.lobby.commands;

import de.rewex.lobby.Lobby;
import de.rewex.lobby.manager.LocationManager;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetlocCmd implements CommandExecutor {

    private final Lobby plugin;

    public SetlocCmd(Lobby main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lobby.noplayer);
            return true;
        }
        Player p = (Player)sender;
        if (!p.hasPermission("lobby.setlocation")) {
            p.sendMessage(Lobby.noperm);
            return true;
        }
        if (args.length < 1) {
            p.sendMessage(Lobby.prefix + "§c/setlocation <Name>");
        }
        else {
           /* if(args[1].equalsIgnoreCase("true")) {
                LocationManager.setLocation(args[0], p.getLocation());
                LocationManager.setSwitchBlock(args[0]);
                SwitchBlock switchBlock = new SwitchBlock(p.getLocation().subtract(0, 1, 0));
                SwitchBlock.getSwitchblocks().add(switchBlock);
                p.sendMessage(Main.prefix + "§7Du hast die Location §6" + args[0] + "§7 erfolgreich gesetzt.");
                p.sendMessage(Main.prefix + "§7Du hast einen animierten Switch Block erstellt");
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);

                return true;
            }*/
            LocationManager.setLocation(args[0], p.getLocation());
            p.sendMessage(Lobby.prefix + "§7Du hast die Location §6" + args[0] + "§7 erfolgreich gesetzt.");
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
        }
        return true;
    }

}
