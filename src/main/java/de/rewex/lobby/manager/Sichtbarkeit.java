package de.rewex.lobby.manager;

import de.rewex.mysql.players.settings.LobbySettings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Sichtbarkeit {

    private Player p;

    public Sichtbarkeit(Player p) {
        this.p = p;
    }

    public void update() {
        if(LobbySettings.getSichtbarkeit(p.getUniqueId().toString()) == 0) {
            niemanden();
        } else if(LobbySettings.getSichtbarkeit(p.getUniqueId().toString()) == 1) {
            teammitglieder();
        } else if(LobbySettings.getSichtbarkeit(p.getUniqueId().toString()) == 2) {
            jeder();
        }
    }


    public void niemanden() {
        for(Player all: Bukkit.getOnlinePlayers()) {
            if(all != this.p) {
                this.p.hidePlayer(all);
            }
        }
    }

    public void teammitglieder() {
        for(Player all: Bukkit.getOnlinePlayers()) {
            if(all != this.p) {
                if(!all.hasPermission("team")) {
                    this.p.hidePlayer(all);
                }
            }
        }
    }

    public void jeder() {
        for(Player all: Bukkit.getOnlinePlayers()) {
            if(all != this.p) {
                this.p.showPlayer(all);
            }
        }
    }


}
