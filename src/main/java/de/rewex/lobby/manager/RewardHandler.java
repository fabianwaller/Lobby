package de.rewex.lobby.manager;

import de.rewex.lobby.manager.utils.ItemBuilder;
import de.rewex.mysql.players.rewards.RewardManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import java.util.ArrayList;

public class RewardHandler {

    private Inventory reward;

    private String rewardname = "§b•§9● Tägliche Belohnung";
    private String bname = "§bBelohnung";
    private String gbname = "§6Gamepass Belohnung";

    public RewardHandler(Player p) {
        this.reward = Bukkit.createInventory(null, InventoryType.CHEST, rewardname);
        ArrayList<String> belohnunglore = new ArrayList<>();

        if(RewardManager.getAllowDailyCoinsReward(p.getUniqueId().toString())) {
            belohnunglore.add("§8-> §aAbholen");
        } else {
            belohnunglore.add("§8-> §7Verfügbar in");
            belohnunglore.add("     " + RewardManager.getRemainingTime(p.getUniqueId().toString()));
        }
        this.reward.setItem(11, new ItemBuilder(Material.FIREWORK, 1).setName(bname).setLore(belohnunglore).build());

        this.reward.setItem(15,
                new ItemBuilder(Material.ENDER_CHEST, 1).setName(gbname).addLoreLine("§8-> §7SOON").addEnchant(Enchantment.ARROW_DAMAGE,
                        1).setFlags().build());
    }

    public void updateReward(Player p) {
        ArrayList<String> belohnunglore = new ArrayList<>();

        if(RewardManager.getAllowDailyCoinsReward(p.getUniqueId().toString())) {
            belohnunglore.add("§8-> §aAbholen");
        } else {
            belohnunglore.add("§8-> §7Verfügbar in");
            belohnunglore.add("     " + RewardManager.getRemainingTime(p.getUniqueId().toString()));
        }
        this.reward.setItem(11, new ItemBuilder(Material.FIREWORK, 0).setName("§bBelohnung").setLore(belohnunglore).build());

        this.reward.setItem(15, new ItemBuilder(Material.ENDER_CHEST, 0).setName("§6Gamepass Belohnung").addLoreLine("§8-> §7SOON").addEnchant(Enchantment.ARROW_DAMAGE,1).setFlags().build());
    }

    public Inventory getReward() {
        return reward;
    }

    public String getRewardname() {
        return rewardname;
    }

    public String getBname() {
        return bname;
    }

    public String getGbname() {
        return gbname;
    }

}
