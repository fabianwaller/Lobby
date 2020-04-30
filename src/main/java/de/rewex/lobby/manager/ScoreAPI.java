package de.rewex.lobby.manager;

import de.rewex.mysql.players.gamepass.GamepassManager;
import de.rewex.mysql.players.stats.PlayersAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreAPI {

    @SuppressWarnings({ "deprecation", "unused" })
    public static void setScoreboard(Player p) {
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = sb.getObjective("aaa");
        if(obj == null) {
            obj = sb.registerNewObjective("aaa", "bbb");
        }

        obj.setDisplayName("  §9§lREWEX.DE  ");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore("§1").setScore(12);
        obj.getScore("§f§lLiga").setScore(11);
        obj.getScore(updateTeam(sb, "Rang", "" + RangManager.getRang(p), "", ChatColor.BLACK)).setScore(10);
        obj.getScore("§d").setScore(9);
        obj.getScore("§f§lCoins").setScore(8);
        obj.getScore(updateTeam(sb, "Coins", "§b" + PlayersAPI.getCoins(p.getUniqueId().toString()), "", ChatColor.AQUA)).setScore(7);
        obj.getScore("§3").setScore(6);
        obj.getScore("§f§lTokens").setScore(5);
        obj.getScore(updateTeam(sb, "Tokens", "§a" + PlayersAPI.getTokens(p.getUniqueId().toString()), "", ChatColor.GREEN)).setScore(4);
        obj.getScore("§4").setScore(3);
        obj.getScore("§6§lGamepass").setScore(2);
        if(GamepassManager.hasPass(p.getUniqueId().toString())) {
            obj.getScore(updateTeam(sb, "Gamepassa", "§aaktiviert", "", ChatColor.DARK_GREEN)).setScore(1);
        } else {
            obj.getScore(updateTeam(sb, "Gamepasso", "§7deaktiviert", "", ChatColor.GRAY)).setScore(1);
        }
        obj.getScore("§5").setScore(0);

        Team admin = getTeam(sb, "000Admin", "§c", "");
        Team mod = getTeam(sb, "001Mod", "§b", "");
        Team dev = getTeam(sb, "002Dev", "§d", "");
        Team builder = getTeam(sb, "003Builder", "§a", "");

        Team legende = getTeam(sb, "004Legende", "§4", "");
        Team titan = getTeam(sb, "005Titan", "§b", "");
        Team champion = getTeam(sb, "006Champion", "§d", "");
        Team meister = getTeam(sb, "007Meister", "§a", "");
        Team spieler = getTeam(sb, "008Spieler", "§7", "");

        for(Player all:Bukkit.getOnlinePlayers()) {
            String team = "008Spieler";
            if (all.hasPermission("server.admin")) {
                team = "000Admin";
            } else if (all.hasPermission("server.mod")) {
                team = "001Mod";
            } else if (all.hasPermission("server.dev")) {
                team = "002Dev";
            } else if (all.hasPermission("server.builder")) {
                team = "003Builder";
            } else {
                if(RangManager.getRang(all).equalsIgnoreCase("Legende")) {
                    team ="004Legende";
                } else if(RangManager.getRang(all).equalsIgnoreCase("Titan")) {
                    team = "005Titan";
                } else if(RangManager.getRang(all).equalsIgnoreCase("Champion")) {
                    team = "006Champion";
                } else if(RangManager.getRang(all).equalsIgnoreCase("Meister")) {
                    team = "007Meister";
                }
            }

            sb.getTeam(team).addPlayer(all);

            all.setDisplayName(sb.getTeam(team).getPrefix() + all.getName());

            all.setPlayerListName(RangManager.getRang(all) + sb.getTeam(team).getPrefix() + all.getName());
        }

        p.setScoreboard(sb);

    }

    @SuppressWarnings({ "deprecation", "unused" })
    public static void updateScoreboard(Player p) {
        if(p.getScoreboard() == null) {
            setScoreboard(p);
        }

        Scoreboard sb = p.getScoreboard();
        Objective obj = sb.getObjective("aaa");

        obj.getScore(updateTeam(sb, "Rang", "" + RangManager.getRang(p), "", ChatColor.BLACK)).setScore(10);
        obj.getScore(updateTeam(sb, "Coins", "§b" + PlayersAPI.getCoins(p.getUniqueId().toString()), "", ChatColor.AQUA)).setScore(7);
        obj.getScore(updateTeam(sb, "Tokens", "§a" + PlayersAPI.getTokens(p.getUniqueId().toString()), "", ChatColor.GREEN)).setScore(4);
        if(GamepassManager.hasPass(p.getUniqueId().toString())) {
            obj.getScore(updateTeam(sb, "Gamepassa", "§aaktiviert", "", ChatColor.DARK_GREEN)).setScore(1);
        } else {
            obj.getScore(updateTeam(sb, "Gamepasso", "§7deaktiviert", "", ChatColor.GRAY)).setScore(1);
        }


        Team admin = getTeam(sb, "000Admin", "§c", "");
        Team mod = getTeam(sb, "001Mod", "§b", "");
        Team dev = getTeam(sb, "002Dev", "§d", "");
        Team builder = getTeam(sb, "003Builder", "§a", "");

        Team legende = getTeam(sb, "004Legende", "§9", "");
        Team titan = getTeam(sb, "005Titan", "§e", "");
        Team champion = getTeam(sb, "006Champion", "§c", "");
        Team meister = getTeam(sb, "007Meister", "§5", "");
        Team spieler = getTeam(sb, "008Spieler", "§7", "");

        for(Player all:Bukkit.getOnlinePlayers()) {
            String team = "008Spieler";
            if (all.hasPermission("server.admin")) {
                team = "000Admin";
            } else if (all.hasPermission("server.mod")) {
                team = "001Mod";
            } else if (all.hasPermission("server.dev")) {
                team = "002Dev";
            } else if (all.hasPermission("server.builder")) {
                team = "003Builder";
            } else {
                if(RangManager.getRang(all).equalsIgnoreCase("Legende")) {
                    team ="004Legende";
                } else if(RangManager.getRang(all).equalsIgnoreCase("Titan")) {
                    team = "005Titan";
                } else if(RangManager.getRang(all).equalsIgnoreCase("Champion")) {
                    team = "006Champion";
                } else if(RangManager.getRang(all).equalsIgnoreCase("Meister")) {
                    team = "007Meister";
                }
            }

            sb.getTeam(team).addPlayer(all);
        }

    }

    public static Team getTeam(Scoreboard sb, String Team, String prefix, String suffix) {
        Team team = sb.getTeam(Team);
        if(team == null) {
            team = sb.registerNewTeam(Team);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.setAllowFriendlyFire(true);
        team.setCanSeeFriendlyInvisibles(true);
        return team;
    }

    public static String updateTeam(Scoreboard sb, String Team, String prefix, String suffix, ChatColor entry) {
        Team team = sb.getTeam(Team);
        if(team == null) {
            team = sb.registerNewTeam(Team);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addEntry(entry.toString());
        return entry.toString();
    }

    private static int actionbarnumber;

    public static void startUpdater() {
        /*actionbarnumber = 0;
        new BukkitRunnable() {

            @Override
            public void run() {
                if(actionbarnumber == 50) {
                    actionbarnumber = 0;
                }
                for(Player all:Bukkit.getOnlinePlayers()) {
                    updateScoreboard(all);

                    if(actionbarnumber < 10) {
                        TitleAPI.sendActionBar(all, "§e+ §e§lBETAPHASE §7Release §f§lBUGREPORT §b§l/discord");
                    } else if(actionbarnumber < 20) {
                        TitleAPI.sendActionBar(all, "§b+ §9§lDiscord §7§lonline§7: §b§l/discord");
                    } else if(actionbarnumber < 30) {
                        TitleAPI.sendActionBar(all, "§5+ §f§lNEUES §5§lAURA§7-§d§lUPDATE");
                    } else if(actionbarnumber < 40) {
                        TitleAPI.sendActionBar(all, "§e+ §7NEUES §9§lRangsystem §8§l+ §b§lREWARDSYSTEM");
                    } else if(actionbarnumber < 50) {
                        TitleAPI.sendActionBar(all, "§c+ §b§lBUGFIXES §8§l+ §e§lVerbesserungen");
                    } else if(actionbarnumber < 60) {
                        TitleAPI.sendActionBar(all, "§e+ §6§lGAMEPASS §7Release: shop.rewex.de");
                    }

                }

                actionbarnumber++;

            }
        }.runTaskTimer(Main.getInstance(), 20, 20);*/
    }

}
