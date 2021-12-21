package de.rewex.lobby.listeners.shop;

import de.rewex.lobby.Lobby;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ShopListeners implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)) {
            if (e.getItem() != null) {
                if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Lobby.getInstance().getInventoryHandler().getShopname() + " §8| §7Rechtsklick")) {
                   // e.getPlayer().openInventory(Main.getInstance().getInventoryHandler().getShopInventory());
                    e.getPlayer().sendMessage("§e•§6● Shop §7| §7Dieses Modul ist bald für Verfügbar§8!");
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

        if(e.getInventory().getName().equals(Lobby.getInstance().getInventoryHandler().getShopname())) {
            if ((e.getCurrentItem() != null) && (e.getCurrentItem().hasItemMeta())) {

                p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
                e.setCancelled(true);

            }
        }
    }

}
