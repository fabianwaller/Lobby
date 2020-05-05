package de.rewex.lobby.manager;

import de.rewex.lobby.Main;
import de.rewex.lobby.manager.utils.ItemBuilder;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Color;
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

    public String teleportername = "§b•§9● Teleporter";
    private String enterhakenname = "§6•§e● Enterhaken";
    private String shopname = "§e•§6● Shop";
    private String nickname = "§d•§5● Autonick";
    private String lobbyname = "§2•§a● Lobby Wechsler";

    public InventoryHandler(){

        this.teleporter = Bukkit.createInventory(null, 54, teleportername);
        setTelporterLayout(this.teleporter);
        this.teleporter.setItem(12, new ItemBuilder(Material.FIREWORK, 0).setName("§7Soon").build());
        this.teleporter.setItem(14, new ItemBuilder(Material.ENDER_CHEST, 0).setName("§7Soon").build());

        this.teleporter.setItem(40, new ItemBuilder(Material.MAGMA_CREAM, 1).setName("§aSpawn").build());
        this.teleporter.setItem(19, new ItemBuilder(Material.STICK, 0).setName("§7Soon").build());
        this.teleporter.setItem(20, new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).setName("§dFFA").build());
        this.teleporter.setItem(29, new ItemBuilder(Material.SANDSTONE,0).setName("§7Soon").build());

        this.teleporter.setItem(24, new ItemBuilder(Material.ENDER_PEARL, 1).setName("§5Aura").build());
        this.teleporter.setItem(25, new ItemBuilder(Material.WORKBENCH, 0).setName("§7Soon").build());
        this.teleporter.setItem(33, new ItemBuilder(Material.REDSTONE_LAMP_OFF, 0).setName("§7Soon").build());

        this.shop = Bukkit.createInventory(null, 45, shopname);
        this.shop.setItem(22, new ItemBuilder(Material.BARRIER).setName("§7Soon").build());

        this.lobby = Bukkit.createInventory(null, 9, "§2•§a● Lobby Wechsler");
        Main.getInstance().getCloudServer().getLobbys().forEach(item -> {
            lobby.addItem();
        });

    }

    public void updateLobbyInventory(){
        this.lobby = Bukkit.createInventory(null, 9, "§2•§a● Lobby Wechsler");

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

        //p.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.BLUE).setName("§9Lobbyboots")
        // .build());
        @SuppressWarnings("deprecation")
        ItemStack head = new ItemStack(Material.getMaterial(397), 1, (short) 3);
        SkullMeta m = (SkullMeta)head.getItemMeta();
        m.setDisplayName(RangManager.getSecondColor(p) + "•" + RangManager.getColor(p) + "● Profil §8| §7Rechtsklick");
        m.setOwner(p.getName());
        head.setItemMeta(m);

        p.getInventory().setItem(0, new ItemBuilder(Material.RECORD_6).setName(teleportername + " §8| §7Rechtsklick").build());
        p.getInventory().setItem(1,
                new ItemBuilder(Material.FISHING_ROD).setName(enterhakenname  + " §8| §7Rechtsklick").setInfinityDurability().setFlags().build());

        p.getInventory().setItem(4,
                new ItemBuilder(Material.ENDER_CHEST).setName(shopname  + " §8| §7Rechtsklick").addEnchant(Enchantment.DAMAGE_ALL,1 ).setFlags().build());

        if(p.hasPermission("server.nick")) {
            p.getInventory().setItem(6, new ItemBuilder(Material.NAME_TAG).setName(nickname  + " §8| §7Rechtsklick").build());
        }
        p.getInventory().setItem(7, head);
        p.getInventory().setItem(8, new ItemBuilder(Material.NETHER_STAR).setName(lobbyname  + " §8| §7Rechtsklick").build());

    }

    public void setTelporterLayout(Inventory inv) {
        ItemStack schwarz = new ItemBuilder(Material.STAINED_GLASS_PANE,1 ,(short) 15).setName("").build();
        ItemStack grau = new ItemBuilder(Material.STAINED_GLASS_PANE,1 ,(short) 7).setName("").build();
        ItemStack blau = new ItemBuilder(Material.STAINED_GLASS_PANE,1 ,(short) 11).setName("").build();

        inv.setItem(0, schwarz);
        inv.setItem(1, grau);
        inv.setItem(2, grau);
        inv.setItem(3, grau);
        inv.setItem(4, grau);
        inv.setItem(5, grau);
        inv.setItem(6, grau);
        inv.setItem(7, grau);
        inv.setItem(8, schwarz);
        inv.setItem(9, blau);
        inv.setItem(10, schwarz);
        inv.setItem(11, schwarz);
        inv.setItem(13, grau);
        inv.setItem(15, schwarz);
        inv.setItem(16, schwarz);
        inv.setItem(17, blau);
        inv.setItem(18, blau);
        inv.setItem(21, schwarz);
        inv.setItem(22, grau);
        inv.setItem(23, schwarz);
        inv.setItem(26, blau);
        inv.setItem(27, grau);
        inv.setItem(28, blau);
        inv.setItem(30, schwarz);
        inv.setItem(31, grau);
        inv.setItem(32, schwarz);
        inv.setItem(34, blau);
        inv.setItem(35, grau);
        inv.setItem(36, grau);
        inv.setItem(37, grau);
        inv.setItem(38, grau);
        inv.setItem(39, blau);
        inv.setItem(41, blau);
        inv.setItem(42, grau);
        inv.setItem(43, grau);
        inv.setItem(44, grau);
        inv.setItem(45, grau);
        inv.setItem(46, grau);
        inv.setItem(47, grau);
        inv.setItem(48, grau);
        inv.setItem(49, schwarz);
        inv.setItem(50, grau);
        inv.setItem(51, grau);
        inv.setItem(52, grau);
        inv.setItem(53, grau);

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
