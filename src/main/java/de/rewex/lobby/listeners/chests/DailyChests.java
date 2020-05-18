package de.rewex.lobby.listeners.chests;

import de.rewex.lobby.Main;
import de.rewex.lobby.manager.RewardHandler;
import de.rewex.mysql.players.rewards.RewardManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class DailyChests implements Listener {

    private RewardHandler rewardHandler;

    @EventHandler
    public void onDailyChest(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getClickedBlock() != null) {
                if(e.getClickedBlock().getType() == Material.CHEST) {

                    // Location Abfrage
                    Location chestloc = e.getClickedBlock().getLocation();

                    rewardHandler = new RewardHandler(p);
                    p.openInventory(rewardHandler.getReward());
                    p.playSound(p.getLocation(), Sound.CLICK, 12.0F, 12.0F);
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onDailyChestClick(InventoryClickEvent e) {
        Player p = null;
        if (e.getWhoClicked() instanceof Player) {
            p = (Player) e.getWhoClicked();
        }

        if (e.getInventory().getName().equals(rewardHandler.getRewardname())) {

            if ((e.getCurrentItem() != null) && (e.getCurrentItem().hasItemMeta())) {

                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(rewardHandler.getBname())) {

                    if (RewardManager.getAllowDailyCoinsReward(p.getUniqueId().toString())) {
                        RewardManager.setReward(p);
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
                        rewardHandler.updateReward(p);
                    } else {
                        p.sendMessage(Main.coinspr + "§7Tägliche Belohnung verfügbar in " + RewardManager.getRemainingTime(p.getUniqueId().toString()));
                        p.playSound(p.getLocation(), Sound.CLICK, 3.0F, 2.0F);
                        p.closeInventory();
                    }

                    e.setCancelled(true);
                }

            }


        }
    }
}
