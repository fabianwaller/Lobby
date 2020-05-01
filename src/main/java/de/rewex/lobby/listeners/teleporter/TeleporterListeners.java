package de.rewex.lobby.listeners.teleporter;

import de.rewex.lobby.Main;
import de.rewex.lobby.manager.LocationManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class TeleporterListeners implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)) {
            if (e.getItem() != null) {
                if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Main.getInstance().getInventoryHandler().getTeleportername())) {
                    e.getPlayer().openInventory(Main.getInstance().getInventoryHandler().getTeleporterInventory());
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CLICK, 12.0F, 12.0F);
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = null;
        if((e.getWhoClicked() instanceof Player)) {
            p = (Player) e.getWhoClicked();
        }

        if(e.getInventory().getName().equals(Main.getInstance().getInventoryHandler().getTeleportername())) {
            if ((e.getCurrentItem() != null) && (e.getCurrentItem().hasItemMeta())) {

                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aSpawn")) {
                    LocationManager.telLocation(p, "spawn");
                    p.closeInventory();
                }

                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§dFFA")) {
                    LocationManager.telLocation(p, "ffa");
                    p.closeInventory();
                }

                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§5Aura")) {
                    LocationManager.telLocation(p, "aura");
                    p.closeInventory();
                }


                p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);



            }

            e.setCancelled(true);
        }
    }

}