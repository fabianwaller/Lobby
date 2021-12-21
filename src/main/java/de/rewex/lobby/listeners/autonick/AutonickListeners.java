package de.rewex.lobby.listeners.autonick;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AutonickListeners implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)) {
            if (e.getItem() != null) {
                if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§d•§5● Autonick §8| §7Rechtsklick")) {
                    e.getPlayer().sendMessage("§d•§5● Nick §7| §7Dieses Modul ist bald für Verfügbar§8!");
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CLICK, 12.0F, 12.0F);
                    e.setCancelled(true);
                }
            }
        }
    }

}
