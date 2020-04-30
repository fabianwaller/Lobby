package de.rewex.lobby.manager;

import de.rewex.lobby.Main;
import de.rewex.lobby.manager.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryHandler {

    private Inventory teleporter;
    private Inventory lobby;


    public InventoryHandler(){

        this.teleporter = Bukkit.createInventory(null, 45, "§7Teleporter");
        setLayout(this.teleporter);
        this.teleporter.setItem(22, new ItemBuilder(Material.MAGMA_CREAM).setName("§aSpawn").build());
        this.teleporter.setItem(11, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName("§dFFA").build());
        this.teleporter.setItem(15, new ItemBuilder(Material.STICK).setName("§7Soon").build());
        this.teleporter.setItem(29, new ItemBuilder(Material.ENDER_PEARL).setName("§5Aura").build());
        this.teleporter.setItem(33, new ItemBuilder(Material.DEAD_BUSH).setName("§7Soon").build());


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

    public Inventory getLobbyInventory() {
        return lobby;
    }


}