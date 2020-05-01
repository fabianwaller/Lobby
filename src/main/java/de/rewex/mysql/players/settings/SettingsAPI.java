package de.rewex.mysql.players.settings;

import de.rewex.mysql.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingsAPI {

    public static boolean playerExists(String uuid) {
        try {
            ResultSet rs = MySQL.getResult("SELECT * FROM SETTINGS WHERE UUID='" + uuid + "'");
            if (rs.next()) {
                return rs.getString("UUID") != null;
            }
            return false;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(String uuid) {
        if (!playerExists(uuid)) {
           // MySQL.update("INSERT INTO SETTINGS (UUID, COINS, TOKENS, GAMEPASS, SPIELZEIT, ONLINE) VALUES ('" + uuid + "', '0', '0',
            // '0', " +
                   // "'0', '0');");
        }
    }

}
