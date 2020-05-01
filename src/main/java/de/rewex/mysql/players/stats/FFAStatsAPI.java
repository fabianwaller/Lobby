package de.rewex.mysql.players.stats;

import de.rewex.mysql.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FFAStatsAPI {

    public static boolean playerExists(String uuid) {
        try {
            ResultSet rs = MySQL.getResult("SELECT * FROM FFA WHERE UUID='" + uuid + "'");
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

            MySQL.update("INSERT INTO FFA (UUID, KILLS, DEATHS, KILLSTREAK) VALUES ('" + uuid + "', '0', '0', '0');");
        }
    }

    public static Integer getKills(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT * FROM FFA WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    Integer.valueOf(rs.getInt("KILLS"));
                }
                i = Integer.valueOf(rs.getInt("KILLS"));
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else {
            createPlayer(uuid);
            getKills(uuid);
        }
        return i;
    }

    public static Integer getDeaths(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid))
        {
            try
            {
                ResultSet rs = MySQL.getResult("SELECT * FROM FFA WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    Integer.valueOf(rs.getInt("DEATHS"));
                }
                i = Integer.valueOf(rs.getInt("DEATHS"));
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            createPlayer(uuid);
            getDeaths(uuid);
        }
        return i;
    }


    public static Integer getKillstreak(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT * FROM FFA WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    Integer.valueOf(rs.getInt("KILLSTREAK"));
                }
                i = Integer.valueOf(rs.getInt("KILLSTREAK"));
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else {
            createPlayer(uuid);
            getKillstreak(uuid);
        }
        return i;
    }


    public static void setKills(String uuid, Integer kills)
    {
        if (playerExists(uuid))
        {
            MySQL.update("UPDATE FFA SET KILLS='" + kills + "' WHERE UUID='" + uuid + "'");
        }
        else
        {
            createPlayer(uuid);
            setKills(uuid, kills);
        }
    }

    public static void setDeaths(String uuid, Integer deaths)
    {
        if (playerExists(uuid))
        {
            MySQL.update("UPDATE FFA SET DEATHS='" + deaths + "' WHERE UUID='" + uuid + "'");
        }
        else
        {
            createPlayer(uuid);
            setDeaths(uuid, deaths);
        }
    }

    public static void setKillstreak(String uuid, Integer killstreak)
    {
        if (playerExists(uuid))
        {
            MySQL.update("UPDATE FFA SET KILLSTREAK='" + killstreak + "' WHERE UUID='" + uuid + "'");
        }
        else
        {
            createPlayer(uuid);
            setKillstreak(uuid, killstreak);
        }
    }

    public static void addKills(String uuid, Integer kills)
    {
        if (playerExists(uuid))
        {
            setKills(uuid, Integer.valueOf(getKills(uuid).intValue() + kills.intValue()));
        }
        else
        {
            createPlayer(uuid);
            addKills(uuid, kills);
        }
    }

    public static void addDeaths(String uuid, Integer deaths)
    {
        if (playerExists(uuid))
        {
            setDeaths(uuid, Integer.valueOf(getDeaths(uuid).intValue() + deaths.intValue()));
        }
        else
        {
            createPlayer(uuid);
            addDeaths(uuid, deaths);
        }
    }

    public static void removeKills(String uuid, Integer kills)
    {
        if (playerExists(uuid))
        {
            setKills(uuid, Integer.valueOf(getKills(uuid).intValue() - kills.intValue()));
        }
        else
        {
            createPlayer(uuid);
            removeKills(uuid, kills);
        }
    }


    public static void removeDeaths(String uuid, Integer deaths)
    {
        if (playerExists(uuid))
        {
            setKills(uuid, Integer.valueOf(getDeaths(uuid).intValue() - deaths.intValue()));
        }
        else
        {
            createPlayer(uuid);
            removeDeaths(uuid, deaths);
        }
    }

    public static double round(double value) {
        value = value*100;
        value=(int)value;
        value=(double)value/100;
        return value;
    }

}
