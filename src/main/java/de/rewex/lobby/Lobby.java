package de.rewex.lobby;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.event.IEventManager;
import de.rewex.cloud.CloudServer;
import de.rewex.cloud.CloudServiceListeners;
import de.rewex.lobby.commands.StatsCmd;
import de.rewex.lobby.listeners.autonick.AutonickListeners;
import de.rewex.lobby.listeners.chests.DailyChests;
import de.rewex.lobby.listeners.gadgets.Enterhaken;
import de.rewex.lobby.listeners.lobbywechsler.LobbywechslerListeners;
import de.rewex.lobby.listeners.profil.ProfilListeners;
import de.rewex.lobby.listeners.shop.ShopListeners;
import de.rewex.lobby.listeners.teleporter.TeleporterListeners;
import de.rewex.lobby.chat.ChatListeners;
import de.rewex.lobby.commands.BuildCmd;
import de.rewex.lobby.commands.SetlocCmd;
import de.rewex.lobby.listeners.ConnectListeners;
import de.rewex.lobby.listeners.LobbyProtect;
import de.rewex.lobby.listeners.PlayerListeners;
import de.rewex.lobby.manager.InventoryHandler;
import de.rewex.lobby.manager.ScoreAPI;
import de.rewex.lobby.manager.switchblocks.SwitchBlock;
import de.rewex.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Lobby extends JavaPlugin implements PluginMessageListener {

    public static String prefix = "§2•§a● Lobby §7| ";
    public static String coinspr = "§1•§b● Coins §7| ";
    public static String tokenspr = "§2•§a● Tokens §7| ";
    public static String passpr = "§e•§6● Gamepass §7| ";
    public static String noperm = prefix + "§cDazu hast du keine Rechte§8!";
    public static String offplayer = prefix + "§cDieser Spieler ist offline§8!";
    public static String noplayer = "[Lobby] Nur ein Spieler kann diesen Befehl ausführen";

    public static Lobby instance;
    private CloudServer cloudServer;
    private InventoryHandler inventoryHandler;

    public static Lobby getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        this.cloudServer = new CloudServer();
        this.inventoryHandler = new InventoryHandler();

        getServer().getMessenger().registerOutgoingPluginChannel(Lobby.getInstance(), "BungeeCord");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "LABYMOD");
        getServer().getMessenger().registerIncomingPluginChannel(this, "WDL|INIT", this);
        getServer().getMessenger().registerOutgoingPluginChannel(this, "WDL|CONTROL");

        registerCommands();
        registerListeners();

        IEventManager eventManager = CloudNetDriver.getInstance().getEventManager();
        eventManager.registerListener(new CloudServiceListeners(this));

        MySQL.connect();
        MySQL.createTable();
        if (!MySQL.isConnected()) {
            Bukkit.shutdown();
        }


        //RewardManager.registerTask();
        ScoreAPI.startUpdater();

        SwitchBlock.startAnimateTask();

        Bukkit.getConsoleSender().sendMessage(Lobby.prefix + "§aPlugin aktiviert §7[§a" + getDescription().getVersion() + "]");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Lobby.prefix + "§cPlugin deaktiviert");
    }

    private void registerCommands() {
        getCommand("build").setExecutor(new BuildCmd(this));
        getCommand("setloc").setExecutor(new SetlocCmd(this));
        getCommand("stats").setExecutor(new StatsCmd(this));


    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();

        //de.rewex.lobby.chat
        pm.registerEvents(new ChatListeners(), this);

        //de.rewex.lobby.listeners

        // chests
        pm.registerEvents(new DailyChests(), this);

        //Gadgets
        pm.registerEvents(new Enterhaken(), this);

        pm.registerEvents(new AutonickListeners(), this);
        pm.registerEvents(new LobbywechslerListeners(), this);
        pm.registerEvents(new ProfilListeners(), this);
        pm.registerEvents(new ShopListeners(), this);
        pm.registerEvents(new TeleporterListeners(), this);

        pm.registerEvents(new ConnectListeners(), this);
        pm.registerEvents(new LobbyProtect(), this);
        pm.registerEvents(new PlayerListeners(), this);

    }

    public InventoryHandler getInventoryHandler() {
        return inventoryHandler;
    }

    public CloudServer getCloudServer() {
        return cloudServer;
    }

    public void onPluginMessageReceived(String channel, Player p, byte[] data) {
        if (channel.equals("WDL|INIT")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kick " + p.getName() + " §7Du wurdest von §9§lRewex.de§r §7gekickt!"
                    + "\n "
                    + "\n§7Grund: §cWorldDownloader [WDL]"
                    + "\n§7Gekickt von: §cSystem"
                    + "\n ");
        }
    }

}