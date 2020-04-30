package de.rewex.lobby.listeners;

import de.rewex.lobby.Main;
import de.rewex.lobby.commands.BuildCmd;
import de.rewex.lobby.manager.InventoryManager;
import de.rewex.lobby.manager.LocationManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onOutofMap(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(!BuildCmd.build.contains(e.getPlayer())) {
			/*if(p.getLocation().getBlockY() < 60) {
				LocationManager.telLocation(p, "spawn");
			}*/
            if(LocationManager.getLocation("spawn").distance(p.getLocation()) > 150) {
                LocationManager.telLocation(p, "spawn");
            }
        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(!BuildCmd.build.contains(e.getWhoClicked().getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHotbarswitch(PlayerItemHeldEvent e) {
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.NOTE_STICKS, 2.0F, 1.0F);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if(!BuildCmd.build.contains(e.getPlayer().getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if((e.getEntity() instanceof Player)) {
            Player p = (Player)e.getEntity();
            p.setFoodLevel(20);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if((e.getEntity() instanceof Player)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        //e.setCancelled(true);
    }

}
