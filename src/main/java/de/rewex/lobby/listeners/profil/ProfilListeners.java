package de.rewex.lobby.listeners.profil;

import de.rewex.lobby.Main;
import de.rewex.lobby.listeners.ConnectListeners;
import de.rewex.lobby.manager.*;
import de.rewex.mysql.players.settings.LobbySettings;
import org.bukkit.Material;
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
                    p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
                    return;
                }

                String action = e.getCurrentItem().getItemMeta().getDisplayName();
                if(action.equalsIgnoreCase("§aJeden Anzeigen")) {
                    LobbySettings.setSichtbarkeit(p.getUniqueId().toString(), 2);
                    Sichtbarkeit sichtbarkeit = new Sichtbarkeit(p);
                    sichtbarkeit.update();
                    p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
                } else if(action.equalsIgnoreCase("§5Teammitglieder Anzeigen")) {
                    LobbySettings.setSichtbarkeit(p.getUniqueId().toString(), 1);
                    Sichtbarkeit sichtbarkeit = new Sichtbarkeit(p);
                    sichtbarkeit.update();
                    p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
                } else if(action.equalsIgnoreCase("§cNiemanden Anzeigen")) {
                    LobbySettings.setSichtbarkeit(p.getUniqueId().toString(), 0);
                    Sichtbarkeit sichtbarkeit = new Sichtbarkeit(p);
                    sichtbarkeit.update();
                    p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
                }

                int line = profilHandler.getLine(e.getRawSlot());
                int object = line*9;
                if(profilHandler.getEinstellungen().getItem(object).getItemMeta().getDisplayName().equalsIgnoreCase("§bAnimationen")) {
                    if(action == "§aAktivieren") {
                        LobbySettings.setAnimationen(p.getUniqueId().toString(), true);
                        p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
                    } else if(action == "§cDeaktivieren") {
                        LobbySettings.setAnimationen(p.getUniqueId().toString(), false);
                        p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
                    }
                } else if(profilHandler.getEinstellungen().getItem(object).getItemMeta().getDisplayName().equalsIgnoreCase("§cHotbar Sounds")) {
                    if(action == "§aAktivieren") {
                        LobbySettings.setHotbarsounds(p.getUniqueId().toString(), true);
                        p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
                    } else if(action == "§cDeaktivieren") {
                        LobbySettings.setHotbarsounds(p.getUniqueId().toString(), false);
                        p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
                    }
                } else if(profilHandler.getEinstellungen().getItem(object).getItemMeta().getDisplayName().equalsIgnoreCase("§eScoreboard")) {
                    if(action == "§aAktivieren") {
                        LobbySettings.setScoreboard(p.getUniqueId().toString(), true);
                        p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
                    } else if(action == "§cDeaktivieren") {
                        LobbySettings.setScoreboard(p.getUniqueId().toString(), false);
                        p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
                    }
                }  else if(profilHandler.getEinstellungen().getItem(object).getItemMeta().getDisplayName().equalsIgnoreCase("§aLobby Echtzeit")) {
                    if(action == "§aAktivieren") {
                        LobbySettings.setEchtzeit(p.getUniqueId().toString(), true);
                        p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
                    } else if(action == "§cDeaktivieren") {
                        LobbySettings.setEchtzeit(p.getUniqueId().toString(), false);
                        p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
                    }
                }

                profilHandler.updateEinstellungen(p, profilHandler.getEinstellungen());
                p.updateInventory();
            }
            e.setCancelled(true);
        }
    }

}
