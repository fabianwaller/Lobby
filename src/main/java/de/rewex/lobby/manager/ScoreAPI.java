package de.rewex.lobby.manager;

import de.rewex.lobby.Lobby;
import de.rewex.lobby.manager.utils.TitleAPI;
import de.rewex.mysql.players.gamepass.GamepassManager;
import de.rewex.mysql.players.settings.LobbySettings;
import de.rewex.mysql.players.stats.PlayersAPI;
import de.rewex.mysql.players.stats.Spielzeit;
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

        if(LobbySettings.getScoreboard(p.getUniqueId().toString())) {
            obj.setDisplayName("  §9Rewex.de  ");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);

            //•●⬤
            obj.getScore("§1").setScore(12);
            obj.getScore("§8•§7● Rang").setScore(11);
            obj.getScore(updateTeam(sb, "Rang", "§8➜ " + RangManager.getRang(p), "", ChatColor.BLACK)).setScore(10);
            obj.getScore("§f").setScore(9);
            obj.getScore("§8•§7● Coins").setScore(8);
            obj.getScore(updateTeam(sb, "Coins", "§8➜ §b" + PlayersAPI.getCoins(p.getUniqueId().toString()), "", ChatColor.AQUA)).setScore(7);
            obj.getScore("§3").setScore(6);
            obj.getScore("§8•§7● Gamepass").setScore(5);
            if(GamepassManager.hasPass(p.getUniqueId().toString())) {
                obj.getScore(updateTeam(sb, "a", "§8➜ §aaktiviert", " §7/ §a✔", ChatColor.DARK_GREEN)).setScore(4);
            } else {
                obj.getScore(updateTeam(sb, "b", "§8➜ §cdeaktivi", "§cert §7/ §c✖", ChatColor.GRAY)).setScore(4);
            }
            obj.getScore("§4").setScore(3);
            obj.getScore(updateTeam(sb, "Level", "§8•§7● Level", "§8(§e§l1§r§8)", ChatColor.LIGHT_PURPLE)).setScore(2);
            obj.getScore(updateTeam(sb, "progress", "§8➜ §e▮§7▮▮▮▮▮▮", "§7▮▮▮ §8● §e0%", ChatColor.GREEN)).setScore(1);
            obj.getScore("§5").setScore(0);

        }

        Team admin = getTeam(sb, "000Admin", "§4Admin §8● §4", "");
        Team mod = getTeam(sb, "001Mod", "§9Mod §8● §9", "");
        Team sup = getTeam(sb, "002Sup", "§bSup §8● §b", "");
        Team dev = getTeam(sb, "003Dev", "§dDev §8● §d", "");
        Team builder = getTeam(sb, "004Builder", "§aBuilder §8● §a", "");
        Team content = getTeam(sb, "005Content", "§3Content §8● §3", "");

        Team yootuber = getTeam(sb, "006Youtuber", "§5YT §8● §5", "");
        Team titan = getTeam(sb, "007Titan", "§eTitan §8● §e", "");
        Team champ = getTeam(sb, "008Champ", "§cChamp §8● §c", "");
        Team prime = getTeam(sb, "009Prime", "§6Prime §8● §6", "");
        Team spieler = getTeam(sb, "010Spieler", "§7", "");

        for(Player all:Bukkit.getOnlinePlayers()) {
            String team = "010Spieler";
            if (all.hasPermission("team.admin")) {
                team = "000Admin";
            } else if (all.hasPermission("team.mod")) {
                team = "001Mod";
            } else if (all.hasPermission("team.sup")) {
                team = "002Sup";
            } else if (all.hasPermission("team.dev")) {
                team = "003Dev";
            } else if (all.hasPermission("team.builder")) {
                team = "004Builder";
            } else if (all.hasPermission("team.content")) {
                team = "005Content";
            } else if (all.hasPermission("server.yt")) {
                team = "006Youtuber";
            } else if (all.hasPermission("server.titan")) {
                team = "007Titan";
            } else if (all.hasPermission("server.champ")) {
                team = "008Champ";
            } else if (all.hasPermission("server.prime")) {
                team = "009Prime";
            }

            sb.getTeam(team).addPlayer(all);

            all.setDisplayName(sb.getTeam(team).getPrefix() + all.getName());

            all.setPlayerListName(sb.getTeam(team).getPrefix() + all.getName());
        }

        p.setScoreboard(sb);

    }

    static int animatenumber = 0;

    public static void updateScoreboard(Player p, int scorenumber) {
        if(p.getScoreboard() == null) {
            setScoreboard(p);
        }

        Scoreboard sb = p.getScoreboard();
        Objective obj = sb.getObjective("aaa");

        if(LobbySettings.getScoreboard(p.getUniqueId().toString()) == true) {
            obj.getScore(updateTeam(sb, "Rang", "§8➜ " + RangManager.getRang(p), "", ChatColor.BLACK)).setScore(10);
            obj.getScore(updateTeam(sb, "Coins", "§8➜ §b" + PlayersAPI.getCoins(p.getUniqueId().toString()), "", ChatColor.AQUA)).setScore(7);

            obj.getScore("§8•§7● Gamepass").setScore(5);
            if(GamepassManager.hasPass(p.getUniqueId().toString())) {
                obj.getScore(updateTeam(sb, "a", "§8➜ §aaktiviert", " §7/ §a✔", ChatColor.DARK_GREEN)).setScore(4);
            } else {
                obj.getScore(updateTeam(sb, "b", "§8➜ §cdeaktivi", "§cert §7/ §c✖", ChatColor.GRAY)).setScore(4);
            }

            obj.getScore(updateTeam(sb, "Level", "§8•§7● Level", "§8(§e§l1§r§8)", ChatColor.LIGHT_PURPLE)).setScore(2);
            obj.getScore(updateTeam(sb, "progress", "§8➜ §e▮§7▮▮▮▮▮▮", "§7▮▮▮ §8● §e0%", ChatColor.GREEN)).setScore(1);

        }

        Team admin = getTeam(sb, "000Admin", "§4Admin §8● §4", "");
        Team mod = getTeam(sb, "001Mod", "§9Mod §8● §9", "");
        Team sup = getTeam(sb, "002Sup", "§bSup §8● §b", "");
        Team dev = getTeam(sb, "003Dev", "§dDev §8● §d", "");
        Team builder = getTeam(sb, "004Builder", "§aBuilder §8● §a", "");
        Team content = getTeam(sb, "005Content", "§3Content §8● §3", "");

        Team yootuber = getTeam(sb, "006Youtuber", "§5YT §8● §5", "");
        Team titan = getTeam(sb, "007Titan", "§eTitan §8● §e", "");
        Team champ = getTeam(sb, "008Champ", "§cChamp §8● §c", "");
        Team prime = getTeam(sb, "009Prime", "§6Prime §8● §6", "");
        Team spieler = getTeam(sb, "010Spieler", "§7", "");

        for(Player all:Bukkit.getOnlinePlayers()) {
            String team = "010Spieler";
            if (all.hasPermission("team.admin")) {
                team = "000Admin";
            } else if (all.hasPermission("team.mod")) {
                team = "001Mod";
            } else if (all.hasPermission("team.sup")) {
                team = "002Sup";
            } else if (all.hasPermission("team.dev")) {
                team = "003Dev";
            } else if (all.hasPermission("team.builder")) {
                team = "004Builder";
            } else if (all.hasPermission("team.content")) {
                team = "005Content";
            } else if (all.hasPermission("server.yt")) {
                team = "006Youtuber";
            } else if (all.hasPermission("server.titan")) {
                team = "007Titan";
            } else if (all.hasPermission("server.champ")) {
                team = "008Champ";
            } else if (all.hasPermission("server.prime")) {
                team = "009Prime";
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

    private static int scorenumber;
    private static int actionbarnumber;

    public static void startUpdater() {
        actionbarnumber = 0;
        scorenumber = 0;
        new BukkitRunnable() {

            @Override
            public void run() {
                if(actionbarnumber == 50) {
                    actionbarnumber = 0;
                }
                if(scorenumber == 15) {
                    scorenumber = 0;
                }
                for(Player all:Bukkit.getOnlinePlayers()) {
                    updateScoreboard(all, scorenumber);

                    TitleAPI.sendActionBar(all, "§7Chat §8● §aAn §8| §7Spielzeit §8● " + Spielzeit.getSpielzeit(all.getUniqueId().toString()));
                }

                actionbarnumber++;
                scorenumber++;

            }
        }.runTaskTimer(Lobby.getInstance(), 20, 20);
    }

}
