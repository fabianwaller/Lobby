package de.rewex.mysql.players.rewards;

import de.rewex.lobby.Main;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.UUID;

import de.rewex.mysql.MySQL;
import de.rewex.mysql.players.stats.PlayersAPI;
import org.bukkit.entity.Player;

public class RewardManager {

    // Template for global daily Reward
    /*public static void registerTask() {
        RewardTimer tsk = new RewardTimer();
        Timer t = new Timer();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy:H:mm:ss");
        Date dt = null;
        try
        {
            dt = sdf.parse("15.04.2020:19:00:00");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        t.schedule(tsk, dt);
    }*/

    public static boolean playerExists(String uuid) {
        try {
            ResultSet rs = MySQL.getResult("SELECT * FROM REWARDS WHERE UUID='" + uuid + "'");
            if (rs.next()) {
                return rs.getString("UUID") != null;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(String uuid) {
        if (!playerExists(uuid)) {
            MySQL.update("INSERT INTO REWARDS (UUID, LOGIN, DAILY, PASSDAILY) VALUES ('" + uuid + "', ' " + System.currentTimeMillis() + " ', '1', '1');");
        }
    }

    public static boolean getAllowDailyCoinsReward(String uuid) {
        long current = System.currentTimeMillis();
        long millis = getTime(uuid);
        return current > millis;
    }

    public static Integer getAllowDailyChestReward(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT * FROM REWARDS WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    Integer.valueOf(rs.getInt("DAILY"));
                }
                i = Integer.valueOf(rs.getInt("DAILY"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getAllowDailyChestReward(uuid);
        }
        return i;
    }

    public static void removeDailyChestAcces(String uuid) {
        if (playerExists(uuid)) {
            MySQL.update("UPDATE REWARDS SET DAILY='0' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            removeDailyChestAcces(uuid);
        }
    }

    public static Integer getAllowDailyPassChestReward(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT * FROM REWARDS WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    i = Integer.valueOf(rs.getInt("PASSDAILY"));
                }
                i = Integer.valueOf(rs.getInt("PASSDAILY"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getAllowDailyPassChestReward(uuid);
        }
        return i;
    }

    public static void removeDailyPassChestAcces(String uuid) {
        if (playerExists(uuid)) {
            MySQL.update("UPDATE REWARDS SET PASSDAILY='0' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            removeDailyPassChestAcces(uuid);
        }
    }

    public static void setReward(Player p) {
        long toSet = System.currentTimeMillis() + 86400000L;

        setTime(p.getUniqueId().toString(), Long.valueOf(toSet));

        PlayersAPI.addCoins(p.getUniqueId().toString(), Integer.valueOf(30));
        p.sendMessage(Main.coinspr + "§7Tägliche Belohnung erhalten: §a+ §b30 Coins");
    }

    public static long getTime(String uuid) {
        long i = Long.valueOf(0L).longValue();
        if (playerExists(uuid)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT * FROM REWARDS WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    Long.valueOf(rs.getLong("LOGIN"));
                }
                i = Long.valueOf(rs.getLong("LOGIN")).longValue();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getTime(uuid);
        }
        return i;
    }

    public static void setTime(String uuid, Long millis) {
        if (playerExists(uuid)) {
            MySQL.update("UPDATE REWARDS SET LOGIN='" + millis + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setTime(uuid, millis);
        }
    }

    public static String getRemainingTime(String uuid) {
        long current = System.currentTimeMillis();
        long end = getTime(uuid);

        long millis = end - current;

        long seconds = 0L;
        long minutes = 0L;
        long hours = 0L;
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
        return
                "§b" + hours + " Stunde(n) " + minutes + " Minute(n) ";
    }

}