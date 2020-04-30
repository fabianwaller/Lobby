package de.rewex.lobby.listeners;

import de.rewex.lobby.Main;
import de.rewex.lobby.commands.BuildCmd;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class LobbyProtect implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(!BuildCmd.build.contains(e.getPlayer().getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if(!BuildCmd.build.contains(e.getPlayer().getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void WeatherChangeEvent(WeatherChangeEvent e) {
        if (e.toWeatherState()) {
            Bukkit.getWorld("lobby").setStorm(false);
            e.setCancelled(true);
        }
    }

	/*@EventHandler
	public void onMobSpawning(CreatureSpawnEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			e.setCancelled(true);
		}
	}*/

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerAchievementAwardedEvent(PlayerAchievementAwardedEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void noUproot(PlayerInteractEvent e) {
        if ((e.getAction() == Action.PHYSICAL) && (e.getClickedBlock().getType() == Material.SOIL)) {
            e.setCancelled(true);
        }
    }

}
