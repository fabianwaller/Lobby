package de.rewex.lobby.chat;

import de.dytanic.cloudnet.ext.cloudperms.CloudPermissionsHelper;
import de.dytanic.cloudnet.ext.cloudperms.CloudPermissionsManagement;
import de.dytanic.cloudnet.ext.cloudperms.node.CloudNetCloudPermissionsModule;
import de.rewex.lobby.Main;
import de.rewex.lobby.manager.LocationManager;
import de.rewex.lobby.manager.RangManager;
import de.rewex.lobby.manager.utils.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

import java.util.ArrayList;

public class ChatListeners implements Listener {

    ArrayList<String> spam = new ArrayList<>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        if (!this.spam.contains(p.getName())) {

            String msg = e.getMessage();

            if(!p.hasPermission("lobby.chat") && !p.hasPermission("team")) {
                p.sendMessage(Main.prefix + "Du benötigst mindestens den §6Prime §7Rang um schreiben zu können§8.");
                e.setMessage("");
                e.setCancelled(true);
            } else {
                Bukkit.getOnlinePlayers().forEach(all -> {
                    all.sendMessage(RangManager.getRang(p) + " §8● " + RangManager.getName(p) + " §8➜ §7" + msg);
                });
                Bukkit.getConsoleSender().sendMessage(RangManager.getRang(p) + " §8x " + p.getName() + " §8-> " + msg);
                this.spam.add(p.getName());
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                    public void run() {
                        spam.remove(p.getName());
                    }
                }, 40L);
            }

            e.setCancelled(true);
        } else {
            //p.playSound(p.getLocation(), Sound.B, 3.0F, 2.0F);
            TitleAPI.sendTitle(p, 5, 10,5, "", "§cSchreibe langsamer");
            //p.sendMessage(Main.prefix + "§cDu schreibst zu schnell...");
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void Reload(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String cmd = e.getMessage();
        if ((cmd.equalsIgnoreCase("/rl")) || (cmd.equalsIgnoreCase("/reload"))) {
            if (p.hasPermission("server.reload")) {
                Bukkit.broadcastMessage(Main.prefix + "Der Server führt einen Reload durch");
                Bukkit.reload();
                Bukkit.broadcastMessage(Main.prefix + "Reload erfolgreich durchgeführt");
                for (Player players : Bukkit.getOnlinePlayers()) {
                    LocationManager.telLocation(players, "spawn", false);
                }
                e.setCancelled(true);

            } else {

                p.sendMessage(Main.noperm);
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onfalseCommand(PlayerCommandPreprocessEvent e) {
        if (!e.isCancelled()) {
            Player p = e.getPlayer();
            String msg = e.getMessage().split(" ")[0];
            HelpTopic help = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
            if (help == null) {
                p.sendMessage(Main.prefix + "Den Befehl §7[§c" + msg + "§7] §7gibt es nicht§8!");
                e.setCancelled(true);
            }
        }
    }
}
