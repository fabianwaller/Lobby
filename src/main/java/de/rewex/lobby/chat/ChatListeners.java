package de.rewex.lobby.chat;

import de.rewex.lobby.Main;
import de.rewex.lobby.manager.LocationManager;
import de.rewex.lobby.manager.RangManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import java.util.ArrayList;

public class ChatListeners implements Listener {

    ArrayList<String> spam = new ArrayList<>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        if (!this.spam.contains(p.getName())) {
            this.spam.add(p.getName());

            String msg = e.getMessage();

            e.setFormat(RangManager.getName(p) + " §7» " + msg);
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.sendMessage(RangManager.getName(p) + " §7» " + msg);
            }
            Bukkit.getConsoleSender().sendMessage(" " + p.getName() + " -> " + msg);

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                public void run() {
                    spam.remove(p.getName());
                }
            }, 30L);

            e.setCancelled(true);
        } else {
            p.sendMessage(Main.prefix + "§cDu schreibst zu schnell...");
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

                p.sendMessage(Main.prefix + Main.noperm);
                e.setCancelled(true);
            }
        }
    }
}
