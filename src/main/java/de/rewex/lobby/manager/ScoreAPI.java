package de.rewex.lobby.manager;

import de.rewex.lobby.Main;
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

        obj.setDisplayName("  §9Rewex.de  ");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        //•●⬤
        obj.getScore("§1").setScore(12);
        obj.getScore("§8•§7● Rang").setScore(11);
        obj.getScore(updateTeam(sb, "Rang", "§8➜ " + RangManager.getRang(p), "", ChatColor.BLACK)).setScore(10);
        obj.getScore("§d").setScore(9);
        obj.getScore("§8•§7● Coins").setScore(8);
        obj.getScore(updateTeam(sb, "Coins", "§8➜ §b" + PlayersAPI.getCoins(p.getUniqueId().toString()), "", ChatColor.AQUA)).setScore(7);
        obj.getScore("§3").setScore(6);
        obj.getScore("§8•§7● Tokens").setScore(5);
        obj.getScore(updateTeam(sb, "Tokens", "§8➜ §a" + PlayersAPI.getTokens(p.getUniqueId().toString()), "", ChatColor.GREEN)).setScore(4);
        obj.getScore("§4").setScore(3);
        obj.getScore("§8•§7● Gamepass").setScore(2);
        if(GamepassManager.hasPass(p.getUniqueId().toString())) {
            obj.getScore(updateTeam(sb, "a", "§8➜ §aaktiviert", " §7/ §a✔", ChatColor.DARK_GREEN)).setScore(1);
        } else {
            obj.getScore(updateTeam(sb, "b", "§8➜ §cdeaktivi", "§cert §7/ §c✖", ChatColor.GRAY)).setScore(1);
        }
        obj.getScore("§5").setScore(0);

        Team admin = getTeam(sb, "00Admin", "§4", "");
        Team mod = getTeam(sb, "01Mod", "§9", "");
        Team sup = getTeam(sb, "02Sup", "§b", "");
        Team dev = getTeam(sb, "03Dev", "§d", "");
        Team builder = getTeam(sb, "04Builder", "§a", "");
        Team content = getTeam(sb, "05Content", "§3", "");

        Team yootuber = getTeam(sb, "06Youtuber", "§5", "");
        Team titan = getTeam(sb, "07Titan", "§e", "");
        Team champ = getTeam(sb, "08Champ", "§c", "");
        Team prime = getTeam(sb, "09Prime", "§6", "");
        Team spieler = getTeam(sb, "10Spieler", "§7", "");

        for(Player all:Bukkit.getOnlinePlayers()) {
            String team = "010Spieler";
            if (all.hasPermission("team.admin")) {
                team = "00Admin";
            } else if (all.hasPermission("team.mod")) {
                team = "01Mod";
            } else if (all.hasPermission("team.sup")) {
                team = "02Sup";
            } else if (all.hasPermission("team.dev")) {
                team = "03Dev";
            } else if (all.hasPermission("team.builder")) {
                team = "04Builder";
            } else if (all.hasPermission("team.content")) {
                team = "05Content";
            } else if (all.hasPermission("server.yt")) {
                team = "06Youtuber";
            } else if (all.hasPermission("server.titan")) {
                team = "07Titan";
            } else if (all.hasPermission("server.champ")) {
                team = "08Champ";
            } else if (all.hasPermission("server.prime")) {
                team = "09Prime";
            }

            sb.getTeam(team).addPlayer(all);

            //all.setDisplayName(sb.getTeam(team).getPrefix() + all.getName());
            all.setDisplayName(all.getName());

            all.setPlayerListName(all.getName());
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

        obj.getScore(updateTeam(sb, "Rang", "§8➜ " + RangManager.getRang(p), "", ChatColor.BLACK)).setScore(10);
        obj.getScore(updateTeam(sb, "Coins", "§8➜ §b" + PlayersAPI.getCoins(p.getUniqueId().toString()), "", ChatColor.AQUA)).setScore(7);
        obj.getScore(updateTeam(sb, "Tokens", "§8➜ §a" + PlayersAPI.getTokens(p.getUniqueId().toString()), "", ChatColor.GREEN)).setScore(4);
        if(GamepassManager.hasPass(p.getUniqueId().toString())) {
            obj.getScore(updateTeam(sb, "a", "§8➜ §aaktiviert", " §7/ §a✔", ChatColor.DARK_GREEN)).setScore(1);
        } else {
            obj.getScore(updateTeam(sb, "b", "§8➜ §cdeaktivi", "ert §7/ §c✖", ChatColor.GRAY)).setScore(1);
        }

        Team admin = getTeam(sb, "00Admin", "§4", "");
        Team mod = getTeam(sb, "01Mod", "§9", "");
        Team sup = getTeam(sb, "02Sup", "§b", "");
        Team dev = getTeam(sb, "03Dev", "§d", "");
        Team builder = getTeam(sb, "04Builder", "§a", "");
        Team content = getTeam(sb, "05Content", "§3", "");

        Team yootuber = getTeam(sb, "06Youtuber", "§5", "");
        Team titan = getTeam(sb, "07Titan", "§e", "");
        Team champ = getTeam(sb, "08Champ", "§c", "");
        Team prime = getTeam(sb, "09Prime", "§6", "");
        Team spieler = getTeam(sb, "10Spieler", "§7", "");

        for(Player all:Bukkit.getOnlinePlayers()) {
            String team = "010Spieler";
            if (all.hasPermission("team.admin")) {
                team = "00Admin";
            } else if (all.hasPermission("team.mod")) {
                team = "01Mod";
            } else if (all.hasPermission("team.sup")) {
                team = "02Sup";
            } else if (all.hasPermission("team.dev")) {
                team = "03Dev";
            } else if (all.hasPermission("team.builder")) {
                team = "04Builder";
            } else if (all.hasPermission("team.content")) {
                team = "05Content";
            } else if (all.hasPermission("server.yt")) {
                team = "06Youtuber";
            } else if (all.hasPermission("server.titan")) {
                team = "07Titan";
            } else if (all.hasPermission("server.champ")) {
                team = "08Champ";
            } else if (all.hasPermission("server.prime")) {
                team = "09Prime";
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
        actionbarnumber = 0;
        new BukkitRunnable() {

            @Override
            public void run() {
                if(actionbarnumber == 50) {
                    actionbarnumber = 0;
                }
                for(Player all:Bukkit.getOnlinePlayers()) {
                   // updateScoreboard(all);

                    /*if(actionbarnumber < 10) {
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
                    }*/

                }

                actionbarnumber++;

            }
        }.runTaskTimer(Main.getInstance(), 20, 20);
    }

}
