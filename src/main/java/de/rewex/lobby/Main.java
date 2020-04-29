package de.rewex.lobby;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public String prefix = "§aLobby §7| ";

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(prefix + "§aPlugin aktiviert §7[§a" + getDescription().getVersion() + "§7]");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
