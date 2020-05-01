package de.rewex.lobby;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.event.IEventManager;
import de.rewex.cloud.CloudServer;
import de.rewex.cloud.CloudServiceListeners;
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

public class Main extends JavaPlugin implements PluginMessageListener {

    public static String prefix = "§7» §aLobby §7| ";
    public static String coinspr = "§7» §bCoins §7| ";
    public static String tokenspr = "§7» §aTokens §7| ";
    public static String passpr = "§7» §1Gamepass §7| ";
    public static String noperm = prefix + "§cDazu hast du keine Rechte§8!";
    public static String offplayer = prefix + "§cDieser Spieler ist offline§8!";
    public static String noplayer = "[Lobby] Nur ein Spieler kann diesen Befehl ausführen";

    public static Main instance;
    private CloudServer cloudServer;
    private InventoryHandler inventoryHandler;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        this.cloudServer = new CloudServer();
        this.inventoryHandler = new InventoryHandler();

        getServer().getMessenger().registerOutgoingPluginChannel(Main.getInstance(), "BungeeCord");
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

        Bukkit.getConsoleSender().sendMessage(Main.prefix + "§aPlugin aktiviert §7[§a" + getDescription().getVersion() + "]");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Main.prefix + "§cPlugin deaktiviert");
    }

    private void registerCommands() {
        getCommand("build").setExecutor(new BuildCmd(this));
        getCommand("setloc").setExecutor(new SetlocCmd(this));


    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();

        //de.rewex.lobby.chat
        pm.registerEvents(new ChatListeners(), this);

        //de.rewex.lobby.listeners
        //Gadgets
        pm.registerEvents(new Enterhaken(), this);

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