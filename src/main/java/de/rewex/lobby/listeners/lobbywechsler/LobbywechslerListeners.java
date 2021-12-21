package de.rewex.lobby.listeners.lobbywechsler;

import de.dytanic.cloudnet.ext.bridge.BridgePlayerManager;
import de.rewex.lobby.Lobby;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class LobbywechslerListeners implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)) {
            if (e.getItem() != null) {
                if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Lobby.getInstance().getInventoryHandler().getLobbyname() + " §8| §7Rechtsklick")) {
                    Lobby.getInstance().getInventoryHandler().updateLobbyInventory();
                    e.getPlayer().openInventory(Lobby.getInstance().getInventoryHandler().getLobbyInventory());
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

        if(e.getInventory().getName().equals(Lobby.getInstance().getInventoryHandler().getLobbyname())) {
            if ((e.getCurrentItem() != null) && (e.getCurrentItem().hasItemMeta())) {
                BridgePlayerManager.getInstance().proxySendPlayer(p.getUniqueId(),
                        e.getCurrentItem().getItemMeta().getDisplayName());
                p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
                e.setCancelled(true);

            }
        }
    }


}
