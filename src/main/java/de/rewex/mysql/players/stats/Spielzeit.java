package de.rewex.mysql.players.stats;

public class Spielzeit {

    public static String getSpielzeit(String uuid) {
        long seconds = PlayersAPI.getSpielzeit(uuid);
        long minutes = 0L;
        long hours = 0L;

        while (seconds > 60L) {
            seconds -= 60L;
            minutes += 1L;
        }
        while (minutes > 60L) {
            minutes -= 60L;
            hours += 1L;
        }

        return "§b" + hours + "h " + minutes + "m ";
    }

}
