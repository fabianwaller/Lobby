package de.rewex.lobby.manager;

import de.rewex.lobby.Main;
import de.rewex.lobby.manager.utils.ItemBuilder;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

public class InventoryHandler {

    private Inventory teleporter;
    private Inventory shop;
    private Inventory lobby;

    public String teleportername = "§7Teleporter";
    private String enterhakenname = "§7Enterhaken";
    private String shopname = "§6Shop";
    private String nickname = "§5Autonick";
    private String lobbyname = "§aLobby Wechsler";

    public InventoryHandler(){

        this.teleporter = Bukkit.createInventory(null, 45, teleportername);
        setLayout(this.teleporter);
        this.teleporter.setItem(22, new ItemBuilder(Material.MAGMA_CREAM).setName("§aSpawn").build());
        this.teleporter.setItem(11, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName("§dFFA").build());
        this.teleporter.setItem(15, new ItemBuilder(Material.STICK).setName("§7Soon").build());
        this.teleporter.setItem(29, new ItemBuilder(Material.ENDER_PEARL).setName("§5Aura").build());
        this.teleporter.setItem(33, new ItemBuilder(Material.DEAD_BUSH).setName("§7Soon").build());

        this.shop = Bukkit.createInventory(null, 45, shopname);
        this.shop.setItem(22, new ItemBuilder(Material.BARRIER).setName("§7Soon").build());

        this.lobby = Bukkit.createInventory(null, 9, "§aLobby Wechsler");
        Main.getInstance().getCloudServer().getLobbys().forEach(item -> {
            lobby.addItem();
        });

    }

    public void updateLobbyInventory(){
        this.lobby = Bukkit.createInventory(null, 9, "§aLobby Wechsler");

        Main.getInstance().getCloudServer().getLobbys().forEach(item -> {
            lobby.addItem(item);
        });

        /*for(int i=0; i<Main.getInstance().getCloudServer().getLobbys().size(); i++) {
            inventory.addItem(Main.getInstance().getCloudServer().getLobbys().get(i));
        }*/
    }

    public void setPlayerinv(Player p) {
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        for(PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }

        @SuppressWarnings("deprecation")
        ItemStack head = new ItemStack(Material.getMaterial(397), 1, (short) 3);
        SkullMeta m = (SkullMeta)head.getItemMeta();
        m.setDisplayName(RangManager.getColor(p) + "Profil");
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

    public void setLayout(Inventory inv) {
        ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE,1 ,(short) 15).setName("").build();

        inv.setItem(1, glass);
        inv.setItem(2, glass);
        inv.setItem(6, glass);
        inv.setItem(7, glass);
        inv.setItem(37, glass);
        inv.setItem(38, glass);
        inv.setItem(42, glass);
        inv.setItem(43, glass);

        inv.setItem(16, glass);
        inv.setItem(17, glass);
        inv.setItem(9, glass);
        inv.setItem(10, glass);
        inv.setItem(27, glass);
        inv.setItem(28, glass);
        inv.setItem(34, glass);
        inv.setItem(35, glass);

        inv.setItem(18, glass);
        inv.setItem(26, glass);
    }

    public Inventory getTeleporterInventory() {
        return teleporter;
    }

    public Inventory getShopInventory() {
        return shop;
    }

    public Inventory getLobbyInventory() {
        return lobby;
    }

    public String getTeleportername() {
        return teleportername;
    }

    public String getEnterhakenname() {
        return enterhakenname;
    }

    public String getShopname() {
        return shopname;
    }

    public String getLobbyname() {
        return lobbyname;
    }


}
