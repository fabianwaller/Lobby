package de.rewex.lobby.manager;

import de.rewex.lobby.manager.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

public class InventoryManager {

    public static String teleportername = "§7Teleporter";
    public static String profilname = "Profil";
    public static String enterhakenname = "§7Enterhaken";
    public static String shopname = "§6Shop";
    public static String nickname = "§5Autonick";
    public static String lobbyname = "§aLobby Wechsler";

    public static void setLobbyInv(Player p) {
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        for(PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }

        @SuppressWarnings("deprecation")
        ItemStack head = new ItemStack(Material.getMaterial(397), 1, (short) 3);
        SkullMeta m = (SkullMeta)head.getItemMeta();
        m.setDisplayName(RangManager.getColor(p) + profilname);
        m.setOwner(p.getName());
        head.setItemMeta(m);

        p.getInventory().setItem(0, new ItemBuilder(Material.COMPASS).setName(teleportername).build());
        p.getInventory().setItem(1, new ItemBuilder(Material.FISHING_ROD).setName(enterhakenname).setInfinityDurability().build());

        p.getInventory().setItem(4,
                new ItemBuilder(Material.ENDER_CHEST).setName(shopname).addEnchant(Enchantment.DAMAGE_ALL,1 ).setFlags().build());

        if(p.hasPermission("server.nick")) {
            p.getInventory().setItem(6, new ItemBuilder(Material.NAME_TAG).setName(nickname).build());
        }
        p.getInventory().setItem(7, head);
        p.getInventory().setItem(8, new ItemBuilder(Material.NETHER_STAR).setName(lobbyname).build());

    }

}
