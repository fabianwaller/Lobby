package de.rewex.lobby.listeners;

import de.rewex.lobby.Main;
import de.rewex.lobby.manager.InventoryManager;
import de.rewex.lobby.manager.LocationManager;
import de.rewex.lobby.manager.ScoreAPI;
import de.rewex.lobby.manager.utils.ItemBuilder;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        e.setJoinMessage("");

        p.setGameMode(GameMode.SURVIVAL);
        p.setHealth(20.0D);
        p.setHealthScale(6);
        p.setFoodLevel(20);
        p.setExp(0.0F);
        p.setLevel(0);
        InventoryManager.setLobbyInv(p);
        LocationManager.telLocation(p, "spawn");



        /*RewardManager.createPlayer(p.getUniqueId().toString());
        if(RewardManager.getAllowDailyCoinsReward(p.getUniqueId().toString())) {
            RewardManager.setReward(p);
        } else {
            p.sendMessage(Main.coinspr + "§7Tägliche Belohnung verfügbar in " + RewardManager.getRemainingTime(p.getUniqueId().toString()));
        }*/
        //InventarManager.createPlayer(p.getUniqueId().toString());

		/*TitleAPI.sendTabTitle(p, "\n   §9§lRewex.de §8× §7Dein Minigames Netzwerk   "
				+ "\n§7Derzeitiger Server §8× §aLobby #1"
				+ "\n ",
			      "\n §9Discord§7: §b/discord "
				+ "\n ");*/

        //TitleAPI.sendTitle(p, 5, 60, 5, "§9Rewex.de", "§eBetaphase");
        ScoreAPI.setScoreboard(p);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage("");
    }

    @EventHandler
    public void onLeave(PlayerKickEvent e) {
        e.setLeaveMessage("");
    }


}
