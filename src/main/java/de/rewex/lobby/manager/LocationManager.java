package de.rewex.lobby.manager;

import java.io.File;
import java.io.IOException;
import java.util.List;

import de.rewex.lobby.Lobby;
import de.rewex.mysql.players.settings.LobbySettings;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import com.google.common.collect.Lists;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LocationManager {

    private static File file = new File("plugins/Lobby/locations.yml");
    private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void saveCfg() {
        try {
            cfg.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setLocation(String name, Location loc) {
        cfg.set(name + ".world", loc.getWorld().getName());
        cfg.set(name + ".x", Double.valueOf(loc.getX()));
        cfg.set(name + ".y", Double.valueOf(loc.getY()));
        cfg.set(name + ".z", Double.valueOf(loc.getZ()));
        cfg.set(name + ".yaw", Float.valueOf(loc.getYaw()));
        cfg.set(name + ".pitch", Float.valueOf(loc.getPitch()));
        saveCfg();
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (Exception localException) {}
        }
    }

    public static void setSwitchBlock(String name) {
        List<String> switchblocks = cfg.getStringList("switchblock");
        switchblocks.add(name);

        cfg.set("switchblock", switchblocks);
        saveCfg();
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (Exception localException) {}
        }
    }

    public static Location getLocation(String name) {
        World world = Bukkit.getWorld(cfg.getString(name + ".world"));
        double x = cfg.getDouble(name + ".x");
        double y = cfg.getDouble(name + ".y");
        double z = cfg.getDouble(name + ".z");
        Location loc = new Location(world, x, y, z);
        loc.setYaw(cfg.getInt(name + ".yaw"));
        loc.setPitch(cfg.getInt(name + ".pitch"));
        return loc;
    }

    public static List<Location> getSwitchBlocks() {
        List<Location> locs = Lists.newArrayList();

        for(int i=0; i<cfg.getStringList("switchblock").size(); i++) {
            List<String> switchblocks = cfg.getStringList("switchblock");
            String current = switchblocks.get(i);

            locs.add(getLocation(current));
        }

        return locs;
    }


    public static void telLocation(Player p, String name, boolean animated) {
        Location loc = getLocation(name);
        if(loc == null) {
            Bukkit.getConsoleSender().sendMessage(Lobby.prefix + "§cDie Location §e" + name + " §cexistiert noch nicht§8!");
            return;
        }

        if(animated==true && LobbySettings.getAnimationen(p.getUniqueId().toString()) == true) {
            PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.MOB_APPEARANCE, false, 1, 20, 1, 1, 1, 1, 0, 1);
            connection.sendPacket(packet);
            p.playSound(p.getLocation(), Sound.FIREWORK_BLAST, 3.0F, 2.0F);

            p.setVelocity(p.getVelocity().setY(3.0D));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,5,2));

            Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {
                @Override
                public void run() {
                    p.playSound(p.getLocation(), Sound.ANVIL_LAND, 3.0F, 2.0F);
                    p.teleport(loc);
                }
            }, 40L);

        } else {
            p.teleport(loc);
            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 3.0F, 2.0F);
        }



    }

    ////////////////

    public static void setChest(String name, Location loc) {
        cfg.set("chest." + name + ".world", loc.getWorld().getName());
        cfg.set("chest." + name + ".x", Double.valueOf(loc.getX()));
        cfg.set("chest." + name + ".y", Double.valueOf(loc.getY()));
        cfg.set("chest." + name + ".z", Double.valueOf(loc.getZ()));
        saveCfg();
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (Exception localException) {}
        }
    }

    public static Location getChest(String name) {
        World world = Bukkit.getWorld(cfg.getString("chest." + name + ".world"));
        double x = cfg.getDouble("chest." + name + ".x");
        double y = cfg.getDouble("chest." + name + ".y");
        double z = cfg.getDouble("chest." + name + ".z");
        Location loc = new Location(world, x, y, z);
        return loc;
    }

}
