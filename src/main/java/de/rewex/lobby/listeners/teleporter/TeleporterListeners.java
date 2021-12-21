package de.rewex.lobby.listeners.teleporter;

import de.rewex.lobby.Lobby;
import de.rewex.lobby.manager.LocationManager;
import de.rewex.mysql.players.settings.LobbySettings;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TeleporterListeners implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)) {
            if (e.getItem() != null) {
                if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Lobby.getInstance().getInventoryHandler().getTeleportername() + " §8| §7Rechtsklick")) {
                    e.getPlayer().openInventory(Lobby.getInstance().getInventoryHandler().getTeleporterInventory());
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CLICK, 12.0F, 12.0F);
                    if(LobbySettings.getAnimationen(e.getPlayer().getUniqueId().toString()) == true) {
                        //e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,Integer.MAX_VALUE,2));
                        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,Integer.MAX_VALUE,2));
                    }
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if(e.getInventory().getName().equalsIgnoreCase(Lobby.getInstance().getInventoryHandler().getTeleportername())) {
            for(PotionEffect effect : e.getPlayer().getActivePotionEffects()) {
                e.getPlayer().removePotionEffect(effect.getType());
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = null;
        if((e.getWhoClicked() instanceof Player)) {
            p = (Player) e.getWhoClicked();
        }

        if(e.getInventory().getName().equals(Lobby.getInstance().getInventoryHandler().getTeleportername())) {
            if ((e.getCurrentItem() != null) && (e.getCurrentItem().hasItemMeta())) {

                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aSpawn")) {
                    LocationManager.telLocation(p, "spawn", true);
                    p.closeInventory();
                }

                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§dFFA")) {
                    LocationManager.telLocation(p, "ffa", true);
                    p.closeInventory();
                }

                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§5Aura")) {
                    LocationManager.telLocation(p, "aura", true);
                    p.closeInventory();
                }


                p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);



            }

            e.setCancelled(true);
        }
    }

}
