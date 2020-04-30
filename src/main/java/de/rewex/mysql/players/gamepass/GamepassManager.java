package de.rewex.mysql.players.gamepass;

import java.sql.ResultSet;
import java.sql.SQLException;
import de.rewex.mysql.MySQL;
import org.bukkit.Bukkit;
import de.rewex.lobby.Main;

public class GamepassManager {
    public static void setPass(String uuid, long seconds) {
        long end = 0L;
        if(seconds == -1L) {
            end = -1L;
        } else {
            long current = System.currentTimeMillis();
            long millis = seconds * 1000L;
            end = current + millis;
        }
        MySQL.update("UPDATE PLAYERS SET GAMEPASS='" + end + "' WHERE UUID='" + uuid + "'");
        if(Bukkit.getServer().getPlayer(uuid) != null) {
            Bukkit.getServer().getPlayer(uuid).sendMessage(Main.passpr + "§7Dein §6Gamepass §7wurde erfolgreich §aaktiviert");
        }
    }

    public static void removePass(String uuid) {
        MySQL.update("UPDATE PLAYERS SET GAMEPASS='" + 0 + "' WHERE UUID='" + uuid + "'");
    }

    public static boolean hasPass(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT GAMEPASS FROM PLAYERS WHERE UUID='" + uuid + "'");
        try {
            if(rs.next()) {
                if(Long.valueOf(rs.getLong("GAMEPASS"))>System.currentTimeMillis() || Long.valueOf(rs.getLong("GAMEPASS"))==-1L) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        removePass(uuid);
        return false;
    }

    public static Long getEnd(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM PLAYERS WHERE UUID='" + uuid + "'");
        try {
            if(rs.next()) {
                return Long.valueOf(rs.getLong("GAMEPASS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getRemainingTime(String uuid) {
        long current = System.currentTimeMillis();
        long end = getEnd(uuid).longValue();
        if (end == -1L) {
            return "§9PERMANENT";
        }
        long millis = end - current;

        long seconds = 0L;
        long minutes = 0L;
        long hours = 0L;
        long days = 0L;
        long weeks = 0L;
        while (millis > 1000L) {
            millis -= 1000L;
            seconds += 1L;
        }
        while (seconds > 60L) {
            seconds -= 60L;
            minutes += 1L;
        }
        while (minutes > 60L) {
            minutes -= 60L;
            hours += 1L;
        }
        while (hours > 24L) {
            hours -= 24L;
            days += 1L;
        }
        while (days > 7L) {
            days -= 7L;
            weeks += 1L;
        }
        return
                "§6" + weeks + "w " + days + "d " + hours + "h " + minutes + "m " + seconds + "s";
    }

}
