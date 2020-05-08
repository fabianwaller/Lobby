package de.rewex.lobby.listeners.gadgets;

import org.bukkit.*;
import org.bukkit.entity.Fish;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

public class Enterhaken implements Listener {

    @EventHandler
    public void onEnterhaken(PlayerFishEvent event) {
        Player p = event.getPlayer();
        FishHook hook = event.getHook();
        if ((event.getState().equals(PlayerFishEvent.State.IN_GROUND)) ||
                (event.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY)) ||
                (event.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT))) {
            Location hookloc = hook.getLocation();
            if (Bukkit.getWorld(event.getPlayer().getWorld().getName()).getBlockAt(hook.getLocation().getBlockX(),
                    hook.getLocation().getBlockY() - 1, hook.getLocation().getBlockZ()).getType() != Material.AIR) {
                if (Bukkit.getWorld(event.getPlayer().getWorld().getName()).getBlockAt(hook.getLocation().getBlockX(), hook.getLocation().getBlockY() - 1, hook.getLocation().getBlockZ()).getType() != Material.STATIONARY_WATER) {
                    Location lc = p.getLocation();
                    Location to = event.getHook().getLocation();

                    lc.setY(lc.getY() + 0.5D);
                    p.teleport(lc);

                    double g = -0.08D;
                    double d = to.distance(lc);
                    double t = d;
                    double v_x = (1.0D + 0.07D * t) * (to.getX() - lc.getX()) / t;
                    double v_y = (1.0D + 0.03D * t) * (to.getY() - lc.getY()) / t - 0.5D * g * t;
                    double v_z = (1.0D + 0.07D * t) * (to.getZ() - lc.getZ()) / t;

                    Vector v = p.getVelocity();
                    v.setX(v_x);
                    v.setY(v_y);
                    v.setZ(v_z);
                    p.setVelocity(v);

                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 2.0F, 2.0F);
                    p.spigot().playEffect(hookloc, Effect.ENDER_SIGNAL, 1, 0, 0, 0, 0, 1, 100, 20);
                    p.getInventory().getItemInHand().setDurability((short)0);
                    p.updateInventory();
                }
            }
        }
    }

}
