package de.rewex.lobby.listeners.profil;

import de.rewex.lobby.Main;
import de.rewex.lobby.manager.InventoryHandler;
import de.rewex.lobby.manager.LocationManager;
import de.rewex.lobby.manager.ProfilHandler;
import de.rewex.lobby.manager.RangManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ProfilListeners implements Listener {

    private ProfilHandler profilHandler;

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)) {
            Player p = e.getPlayer();
            profilHandler = new ProfilHandler(p);
            if (e.getItem() != null) {
                if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(RangManager.getColor(e.getPlayer()) + profilHandler.getProfilname())) {

                    e.getPlayer().openInventory(profilHandler.getProfil());
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

        if(e.getInventory().getName().equals(RangManager.getColor(p) + profilHandler.getProfilname())) {
            if ((e.getCurrentItem() != null) && (e.getCurrentItem().hasItemMeta())) {

                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Stats")) {
                    p.openInventory(profilHandler.getStats());
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aFreunde")) {
                    p.openInventory(profilHandler.getFreunde());
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§bClan")) {
                    p.openInventory(profilHandler.getClan());
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4Einstellungen")) {
                    p.openInventory(profilHandler.getEinstellungen());
                }


                p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);

            }
            e.setCancelled(true);
        }

        if(e.getInventory().getName().equals(profilHandler.getStatsname())) {
            if ((e.getCurrentItem() != null) && (e.getCurrentItem().hasItemMeta())) {

                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cZurück")) {
                    p.openInventory(profilHandler.getProfil());
                }
                p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
            }
            e.setCancelled(true);
        }

        if(e.getInventory().getName().equals(profilHandler.getFreundename())) {
            if ((e.getCurrentItem() != null) && (e.getCurrentItem().hasItemMeta())) {

                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cZurück")) {
                    p.openInventory(profilHandler.getProfil());
                }
                p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
            }
            e.setCancelled(true);
        }

        if(e.getInventory().getName().equals(profilHandler.getClanname())) {
            if ((e.getCurrentItem() != null) && (e.getCurrentItem().hasItemMeta())) {

                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cZurück")) {
                    p.openInventory(profilHandler.getProfil());
                }
                p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
            }
            e.setCancelled(true);
        }

        if(e.getInventory().getName().equals(profilHandler.getEinstellungenname())) {
            if ((e.getCurrentItem() != null) && (e.getCurrentItem().hasItemMeta())) {

                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cZurück")) {
                    p.openInventory(profilHandler.getProfil());
                }
                p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
            }
            e.setCancelled(true);
        }
    }

}
