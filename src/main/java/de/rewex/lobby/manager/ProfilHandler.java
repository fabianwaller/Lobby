package de.rewex.lobby.manager;

import de.rewex.lobby.Main;
import de.rewex.lobby.manager.utils.ItemBuilder;
import de.rewex.mysql.players.settings.LobbySettings;
import de.rewex.mysql.players.stats.AuraStatsAPI;
import de.rewex.mysql.players.stats.FFAStatsAPI;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import java.util.ArrayList;

public class ProfilHandler {

    private Inventory profil;
    private String profilname = "Profil";

    private Inventory stats;
    private String statsname = "§e•§6● Stats";
    private Inventory freunde;
    private String freundename = "§2•§a● Freunde";
    private Inventory clan;
    private String clanname = "§9•§b● Clan";
    private Inventory einstellungen;
    private String einstellungenname = "§c•§4● Einstellungen";


    public ProfilHandler(Player p) {
        this.profil = Bukkit.createInventory(null, 54, RangManager.getSecondColor(p) + "•" + RangManager.getColor(p) + "● Profil");

        //Stats
        this.stats = Bukkit.createInventory(null, 54, statsname);
        setLayout(p, this.stats);
        //this.stats.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE,1,(short)14).setName("§cZurück").build());


        // FFA
        int ffakills = FFAStatsAPI.getKills(p.getUniqueId().toString());
        int ffadeaths = FFAStatsAPI.getDeaths(p.getUniqueId().toString());
        double ffakd = ffakills;
        if(ffadeaths>0) {
            ffakd = (double)ffakills/ffadeaths;
            ffakd = FFAStatsAPI.round(ffakd);
        }
        int killstreak = FFAStatsAPI.getKillstreak(p.getUniqueId().toString());
        ArrayList<String> ffastats = new ArrayList<>();

        ffastats.add("§7Statistiken von " + RangManager.getName(p) + " §7(insgesamt)");
        ffastats.add("§8x §7Kills§8: §d" + ffakills);
        ffastats.add("§8x §7Deaths§8: §d" + ffadeaths);
        ffastats.add("§8x §7K/D§8: §d" + ffakd);
        ffastats.add("§8x §7Killstreak§8: §d" + killstreak);
        this.stats.setItem(0, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName("§dFFA").setLore(ffastats).build());

        //Aura
        int aurakills = AuraStatsAPI.getKills(p.getUniqueId().toString());
        int auradeaths = AuraStatsAPI.getDeaths(p.getUniqueId().toString());
        double aurakd = aurakills;
        if(auradeaths>0) {
            aurakd = (double)aurakills/auradeaths;
            aurakd = AuraStatsAPI.round(aurakd);
        }
        int auramatches = AuraStatsAPI.getMatches(p.getUniqueId().toString());
        int aurawins = AuraStatsAPI.getWins(p.getUniqueId().toString());
        double aurawinrate;
        if(auramatches > 0) {
            aurawinrate = ((double)aurawins/auramatches)*100;
            aurawinrate = AuraStatsAPI.round(aurawinrate);
        } else {
            aurawinrate = 0;
        }
        ArrayList<String> aurastats = new ArrayList<>();
        aurastats.add("§7Statistiken von " + RangManager.getName(p) + " §7(insgesamt)");
        aurastats.add("§8x §7Kills§8: §d" + aurakills);
        aurastats.add("§8x §7Deaths§8: §d" + auradeaths);
        aurastats.add("§8x §7K/D§8: §5" + aurakd);
        aurastats.add("§8x §7Gespielte Spiele§8: §d" + auramatches);
        aurastats.add("§8x §7Gewonnene Spiele§8: §d" + aurawins);
        aurastats.add("§8x §7Siegeswahrscheinlichkeit§8: §5" + aurawinrate + "%");
        this.stats.setItem(1, new ItemBuilder(Material.ENDER_PEARL).setName("§5Aura").setLore(aurastats).build());


        //Freunde
        this.freunde = Bukkit.createInventory(null, 54, freundename);
        setLayout(p, this.freunde);
        this.freunde.setItem(0, new ItemBuilder(Material.BARRIER).setName("§7Soon").build());

        //Clan
        this.clan = Bukkit.createInventory(null, 54, clanname);
        setLayout(p, this.clan);
        this.clan.setItem(0, new ItemBuilder(Material.BARRIER).setName("§7Soon").build());

        //Einstellungen
        this.einstellungen = Bukkit.createInventory(null, 54, einstellungenname);
        setLayout(p, this.einstellungen);

        ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE,1 ,(short) 15).setName(" ").build();
        int j = 1;
        while(j < 45) {
            this.einstellungen.setItem(j, glass);
            j = j+9;
        }
        for(int i=36; i<45; i++) {
            this.einstellungen.setItem(i, glass);
            this.einstellungen.setItem(40,
                    new ItemBuilder(Material.PAPER).setName("§7Seite 1/1").addLoreLine("§aLobby").build());
        }
        this.einstellungen.setItem(0, new ItemBuilder(Material.ENDER_PEARL).setName("§aSichtbarkeit").build());
        this.einstellungen.setItem(9, new ItemBuilder(Material.EYE_OF_ENDER).setName("§bAnimationen").build());
        this.einstellungen.setItem(18, new ItemBuilder(Material.NOTE_BLOCK).setName("§cHotbar Sounds").build());
        this.einstellungen.setItem(27, new ItemBuilder(Material.SIGN).setName("§eScoreboard").build());
        //this.einstellungen.setItem(36, new ItemBuilder(Material.WATCH).setName("§aLobby Echtzeit").build());

        ItemStack aktivieren = new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.LIME).setName("§aAktivieren").build();
        ItemStack aktiviert =
                new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.LIME).setName("§aAktiviert").addEnchant(Enchantment.ARROW_DAMAGE, 1).setFlags().build();
        ItemStack deaktivieren = new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.RED).setName("§cDeaktivieren").build();
        ItemStack deaktiviert = new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.RED).setName("§cDeaktiviert").addEnchant(Enchantment.ARROW_DAMAGE, 1 ).setFlags().build();

        ItemStack jeder = new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.LIME).setName("§aJeden Anzeigen").build();
        ItemStack team = new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.PURPLE).setName("§5Teammitglieder Anzeigen").build();
        ItemStack keinen = new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.RED).setName("§cNiemanden Anzeigen").build();
        if(LobbySettings.getSichtbarkeit(p.getUniqueId().toString()) == 2) {
            this.einstellungen.setItem(2,
                    new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.LIME).setName("§aJeden Anzeigen").addUnsafeEnchantment(Enchantment.ARROW_DAMAGE,1).setFlags().build());
            this.einstellungen.setItem(3, team);
            this.einstellungen.setItem(4, keinen);
        } else if(LobbySettings.getSichtbarkeit(p.getUniqueId().toString()) == 1) {
            this.einstellungen.setItem(2, jeder);
            this.einstellungen.setItem(3, new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.PURPLE).setName("§5Teammitglieder Anzeigen").addUnsafeEnchantment(Enchantment.ARROW_DAMAGE,1).setFlags().build());
            this.einstellungen.setItem(4, keinen);
        } else {
            this.einstellungen.setItem(2, jeder);
            this.einstellungen.setItem(3, team);
            this.einstellungen.setItem(4, new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.RED).setName("§cNiemanden Anzeigen").addUnsafeEnchantment(Enchantment.ARROW_DAMAGE,1).setFlags().build());
        }
        if(LobbySettings.getAnimationen(p.getUniqueId().toString())==true) {
            this.einstellungen.setItem(11, aktiviert);
            this.einstellungen.setItem(12, deaktivieren);
        } else {
            this.einstellungen.setItem(11, aktivieren);
            this.einstellungen.setItem(12, deaktiviert);
        }
        if(LobbySettings.getHotbarSounds(p.getUniqueId().toString())==true) {
            this.einstellungen.setItem(20, aktiviert);
            this.einstellungen.setItem(21, deaktivieren);
        } else {
            this.einstellungen.setItem(20, aktivieren);
            this.einstellungen.setItem(21, deaktiviert);
        }
        if(LobbySettings.getScoreboard(p.getUniqueId().toString())==true) {
            this.einstellungen.setItem(29, aktiviert);
            this.einstellungen.setItem(30, deaktivieren);
        } else {
            this.einstellungen.setItem(29, aktivieren);
            this.einstellungen.setItem(30, deaktiviert);
        }
    }


    public void setLayout(Player p, Inventory inv) {
        if(inv == this.freunde) {
            ItemStack freunde = new ItemStack(Material.getMaterial(397), 1, (short) 3);
            SkullMeta m = (SkullMeta)freunde.getItemMeta();
            m.setDisplayName("§aFreunde");
            m.setOwner(p.getName());
            freunde.setItemMeta(m);
            inv.setItem(45, freunde);
        } else {
            ItemStack freunde = new ItemStack(Material.getMaterial(397), 1, (short) 3);
            SkullMeta m = (SkullMeta)freunde.getItemMeta();
            m.setDisplayName("§aFreunde");
            m.setOwner(p.getName());
            freunde.setItemMeta(m);
            inv.setItem(45, freunde);
        }
        if(inv == this.clan) {
            inv.setItem(46, new ItemBuilder(Material.IRON_CHESTPLATE).setName("§bClan").addEnchant(Enchantment.ARROW_DAMAGE,1).setFlags().build());
        } else {
            inv.setItem(46, new ItemBuilder(Material.IRON_CHESTPLATE).setName("§bClan").build());
        }
        if(inv == this.stats) {
            inv.setItem(47, new ItemBuilder(Material.BOOK).setName("§6Stats").addEnchant(Enchantment.ARROW_DAMAGE,1).setFlags().build());
        } else {
            inv.setItem(47, new ItemBuilder(Material.BOOK).setName("§6Stats").build());
        }
        if(inv == this.einstellungen) {
            inv.setItem(53, new ItemBuilder(Material.REDSTONE).setName("§4Einstellungen").addEnchant(Enchantment.ARROW_DAMAGE,1).setFlags().build());
        } else {
            inv.setItem(53, new ItemBuilder(Material.REDSTONE).setName("§4Einstellungen").build());
        }
        ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE,1 ,(short) 15).setName(" ").build();
        for(int i=36; i<45; i++) {
            inv.setItem(i, glass);
        }
    }

    public void updateEinstellungen(Player p, Inventory inv) {
        setLayout(p, inv);

        ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE,1 ,(short) 15).setName(" ").build();
        int j = 1;
        while(j < 45) {
            inv.setItem(j, glass);
            j = j+9;
        }
        for(int i=36; i<45; i++) {
            inv.setItem(i, glass);
            inv.setItem(40,
                    new ItemBuilder(Material.PAPER).setName("§7Seite 1/1").addLoreLine("§aLobby").build());
        }
        inv.setItem(0, new ItemBuilder(Material.ENDER_PEARL).setName("§aSichtbarkeit").build());
        inv.setItem(9, new ItemBuilder(Material.EYE_OF_ENDER).setName("§bAnimationen").build());
        inv.setItem(18, new ItemBuilder(Material.NOTE_BLOCK).setName("§cHotbar Sounds").build());
        inv.setItem(27, new ItemBuilder(Material.SIGN).setName("§eScoreboard").build());
        //this.einstellungen.setItem(36, new ItemBuilder(Material.WATCH).setName("§aLobby Echtzeit").build());

        ItemStack aktivieren = new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.LIME).setName("§aAktivieren").build();
        ItemStack aktiviert =
                new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.LIME).setName("§aAktiviert").addEnchant(Enchantment.ARROW_DAMAGE, 1).setFlags().build();
        ItemStack deaktivieren = new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.RED).setName("§cDeaktivieren").build();
        ItemStack deaktiviert = new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.RED).setName("§cDeaktiviert").addEnchant(Enchantment.ARROW_DAMAGE, 1 ).setFlags().build();

        ItemStack jeder = new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.LIME).setName("§aJeden Anzeigen").build();
        ItemStack team = new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.PURPLE).setName("§5Teammitglieder Anzeigen").build();
        ItemStack keinen = new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.RED).setName("§cNiemanden Anzeigen").build();
        if(LobbySettings.getSichtbarkeit(p.getUniqueId().toString()) == 2) {
            inv.setItem(2, new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.LIME).setName("§aJeden Anzeigen").addUnsafeEnchantment(Enchantment.ARROW_DAMAGE,1).setFlags().build());
            inv.setItem(3, team);
            inv.setItem(4, keinen);
        } else if(LobbySettings.getSichtbarkeit(p.getUniqueId().toString()) == 1) {
            inv.setItem(2, jeder);
            inv.setItem(3, new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.PURPLE).setName("§5Teammitglieder Anzeigen").addUnsafeEnchantment(Enchantment.ARROW_DAMAGE,1).setFlags().build());
            inv.setItem(4, keinen);
        } else {
            inv.setItem(2, jeder);
            inv.setItem(3, team);
            inv.setItem(4, new ItemBuilder(Material.WOOL).setWoolColor(DyeColor.RED).setName("§cNiemanden Anzeigen").addUnsafeEnchantment(Enchantment.ARROW_DAMAGE,1).setFlags().build());
        }
        if(LobbySettings.getAnimationen(p.getUniqueId().toString())==true) {
            inv.setItem(11, aktiviert);
            inv.setItem(12, deaktivieren);
        } else {
            inv.setItem(11, aktivieren);
            inv.setItem(12, deaktiviert);
        }
        if(LobbySettings.getHotbarSounds(p.getUniqueId().toString())==true) {
            inv.setItem(20, aktiviert);
            inv.setItem(21, deaktivieren);
        } else {
            inv.setItem(20, aktivieren);
            inv.setItem(21, deaktiviert);
        }
        if(LobbySettings.getScoreboard(p.getUniqueId().toString())==true) {
            inv.setItem(29, aktiviert);
            inv.setItem(30, deaktivieren);
        } else {
            inv.setItem(29, aktivieren);
            inv.setItem(30, deaktiviert);
        }
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

    public int getLine(int rawslot) {
        return rawslot/9;
    }
}
