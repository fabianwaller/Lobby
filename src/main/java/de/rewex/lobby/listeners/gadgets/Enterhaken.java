package de.rewex.lobby.listeners.gadgets;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

public class Enterhaken implements Listener {

    @EventHandler
    public void onEnterhaken(PlayerFishEvent e) {
        Player p = e.getPlayer();
        FishHook hook = e.getHook();
        if(hook.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) {
            Location ploc = p.getLocation();
            Location hookloc = hook.getLocation();

            Vector vector = p.getVelocity();
            double distance = ploc.distance(hookloc);

            vector.setX((1.08D * distance) * (hookloc.getX() - ploc.getX()) / distance);
            vector.setY((1D * distance) * (hookloc.getY() - ploc.getY()) / distance - -0.05D * distance);
            vector.setZ((1.08D * distance) * (hookloc.getZ() - ploc.getZ()) / distance);

            p.setVelocity(vector);
            p.spigot().playEffect(hookloc, Effect.ENDER_SIGNAL, 1, 0, 0, 0, 0, 1, 50, 20);
            p.getInventory().getItemInHand().setDurability((short)0);
            p.updateInventory();
        }
    }

}
