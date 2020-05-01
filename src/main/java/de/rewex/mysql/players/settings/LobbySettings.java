package de.rewex.mysql.players.settings;

import de.rewex.mysql.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LobbySettings {

    public static boolean playerExists(String uuid) {
        try {
            ResultSet rs = MySQL.getResult("SELECT * FROM LOBBYSETTINGS WHERE UUID='" + uuid + "'");
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
            MySQL.update("INSERT INTO LOBBYSETTINGS (UUID, Sichtbarkeit, Animationen, Hotbarsounds, Scoreboard, Echtzeit) VALUES " +
                    "('" + uuid + "', '2', '1', '1', '1', '1');");
        }
    }

    /////////////////////////////////////////

    public static Integer getSichtbarkeit(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT * FROM LOBBYSETTINGS WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    Integer.valueOf(rs.getInt("Sichtbarkeit"));
                }
                i = Integer.valueOf(rs.getInt("Sichtbarkeit"));
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getSichtbarkeit(uuid);
        }
        return i;
    }

    public static void setSichtbarkeit(String uuid, Integer sichtbarkeit) {
        if (playerExists(uuid)) {
            MySQL.update("UPDATE LOBBYSETTINGS SET Sichtbarkeit='" + sichtbarkeit + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setSichtbarkeit(uuid, sichtbarkeit);
        }
    }

    public static boolean getAnimationen(String uuid) {
        if (playerExists(uuid)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT * FROM LOBBYSETTINGS WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    int i = Integer.valueOf(rs.getInt("Animationen"));
                    if(i==0) {
                        return false;
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getSichtbarkeit(uuid);
        }
        return true;
    }

    public static void setAnimationen(String uuid, boolean animationen) {
        if (playerExists(uuid)) {
            if(animationen == true) {
                MySQL.update("UPDATE LOBBYSETTINGS SET Animationen='" + 1 + "' WHERE UUID='" + uuid + "'");
            } else {
                MySQL.update("UPDATE LOBBYSETTINGS SET Animationen='" + 0 + "' WHERE UUID='" + uuid + "'");
            }
        } else {
            createPlayer(uuid);
            setAnimationen(uuid, animationen);
        }
    }

    public static boolean getHotbarSounds(String uuid) {
        if (playerExists(uuid)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT * FROM LOBBYSETTINGS WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    int i = Integer.valueOf(rs.getInt("Hotbarsounds"));
                    if(i==0) {
                        return false;
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getHotbarSounds(uuid);
        }
        return true;
    }

    public static void setHotbarsounds(String uuid, boolean hotbarsounds) {
        if (playerExists(uuid)) {
            if(hotbarsounds == true) {
                MySQL.update("UPDATE LOBBYSETTINGS SET Hotbarsounds='" + 1 + "' WHERE UUID='" + uuid + "'");
            } else {
                MySQL.update("UPDATE LOBBYSETTINGS SET Hotbarsounds='" + 0 + "' WHERE UUID='" + uuid + "'");
            }
        } else {
            createPlayer(uuid);
            setHotbarsounds(uuid, hotbarsounds);
        }
    }

    public static boolean getScoreboard(String uuid) {
        if (playerExists(uuid)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT * FROM LOBBYSETTINGS WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    int i = Integer.valueOf(rs.getInt("Scoreboard"));
                    if(i==0) {
                        return false;
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getScoreboard(uuid);
        }
        return true;
    }

    public static void setScoreboard(String uuid, boolean scoreboard) {
        if (playerExists(uuid)) {
            if(scoreboard == true) {
                MySQL.update("UPDATE LOBBYSETTINGS SET Scoreboard='" + 1 + "' WHERE UUID='" + uuid + "'");
            } else {
                MySQL.update("UPDATE LOBBYSETTINGS SET Scoreboard='" + 0 + "' WHERE UUID='" + uuid + "'");
            }
        } else {
            createPlayer(uuid);
            setScoreboard(uuid, scoreboard);
        }
    }

    public static boolean getEchtzeit(String uuid) {
        if (playerExists(uuid)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT * FROM LOBBYSETTINGS WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    int i = Integer.valueOf(rs.getInt("Echtzeit"));
                    if(i==0) {
                        return false;
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getEchtzeit(uuid);
        }
        return true;
    }

    public static void setEchtzeit(String uuid, boolean echtzeit) {
        if (playerExists(uuid)) {
            if(echtzeit == true) {
                MySQL.update("UPDATE LOBBYSETTINGS SET Echtzeit='" + 1 + "' WHERE UUID='" + uuid + "'");
            } else {
                MySQL.update("UPDATE LOBBYSETTINGS SET Echtzeit='" + 0 + "' WHERE UUID='" + uuid + "'");
            }
        } else {
            createPlayer(uuid);
            setEchtzeit(uuid, echtzeit);
        }
    }

}
