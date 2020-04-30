package de.rewex.lobby.manager;

import de.rewex.lobby.Main;
import de.rewex.lobby.manager.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ProfilHandler {

    private Inventory profil;
    private String profilname = "Profil";

    public ProfilHandler(Player p) {
        this.profil = Bukkit.createInventory(null, 45, profilname);
        Main.getInstance().getInventoryHandler().setLayout(profil);

        this.profil.setItem(19, new ItemBuilder(Material.BOOK).setName("§6Stats").build());
        this.profil.setItem(21, new ItemBuilder(Material.SKULL).setSkullOwner(p.getName()).setName("§cFreunde").build());
        this.profil.setItem(23, new ItemBuilder(Material.IRON_CHESTPLATE).setName("§bClan").build());
        this.profil.setItem(25, new ItemBuilder(Material.REDSTONE).setName("§4Einstellungen").build());

    }

    public Inventory getProfil() {
        return profil;
    }

    public String getProfilname() {
        return profilname;
    }


}
