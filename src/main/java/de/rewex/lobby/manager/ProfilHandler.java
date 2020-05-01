package de.rewex.lobby.manager;

import de.rewex.lobby.Main;
import de.rewex.lobby.manager.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class ProfilHandler {

    private Inventory profil;
    private String profilname = "Profil";

    private Inventory stats;
    private String statsname = "§6Stats";
    private Inventory freunde;
    private String freundename = "§aFreunde";
    private Inventory clan;
    private String clanname = "§bClan";
    private Inventory einstellungen;
    private String einstellungenname = "§4Einstellungen";


    public ProfilHandler(Player p) {
        this.profil = Bukkit.createInventory(null, 45, RangManager.getColor(p) + profilname);
        Main.getInstance().getInventoryHandler().setLayout(profil);

        this.profil.setItem(19, new ItemBuilder(Material.BOOK).setName("§6Stats").build());
        ItemStack freunde = new ItemStack(Material.getMaterial(397), 1, (short) 3);
        SkullMeta m = (SkullMeta)freunde.getItemMeta();
        m.setDisplayName("§aFreunde");
        m.setOwner(p.getName());
        freunde.setItemMeta(m);
        this.profil.setItem(21, freunde);
        this.profil.setItem(23, new ItemBuilder(Material.IRON_CHESTPLATE).setName("§bClan").build());
        this.profil.setItem(25, new ItemBuilder(Material.REDSTONE).setName("§4Einstellungen").build());

        //Stats
        this.stats = Bukkit.createInventory(null, 45, statsname);
        this.stats.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE,1,(short)14).setName("§cZurück").build());

        //Freunde
        this.freunde = Bukkit.createInventory(null, 45, freundename);
        this.freunde.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE,1,(short)14).setName("§cZurück").build());
        this.freunde.setItem(22, new ItemBuilder(Material.BARRIER).setName("§7Soon").build());

        //Clan
        this.clan = Bukkit.createInventory(null, 45, clanname);
        this.clan.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE,1,(short)14).setName("§cZurück").build());
        this.clan.setItem(22, new ItemBuilder(Material.BARRIER).setName("§7Soon").build());

        //Einstellungen
        this.einstellungen = Bukkit.createInventory(null, 45, einstellungenname);
        this.einstellungen.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE,1,(short)14).setName("§cZurück").build());




    }

    public Inventory getProfil() {
        return profil;
    }

    public Inventory getStats() {
        return stats;
    }

    public Inventory getFreunde() {
        return freunde;
    }

    public Inventory getClan() {
        return clan;
    }

    public Inventory getEinstellungen() {
        return einstellungen;
    }

    public String getProfilname() {
        return profilname;
    }

    public String getStatsname() {
        return statsname;
    }

    public String getFreundename() {
        return freundename;
    }

    public String getClanname() {
        return clanname;
    }

    public String getEinstellungenname() {
        return einstellungenname;
    }
}
