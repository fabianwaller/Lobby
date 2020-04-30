package de.rewex.cloud;

import com.sun.java.browser.net.ProxyService;
import de.dytanic.cloudnet.CloudNet;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.ext.bridge.ServiceInfoSnapshotUtil;
import de.dytanic.cloudnet.ext.syncproxy.node.CloudNetSyncProxyModule;
import de.rewex.lobby.manager.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.Collection;

public class CloudServer {

    public ArrayList<ItemStack> getLobbys(){
        ArrayList<ItemStack> list = new ArrayList<>();
        Collection<ServiceInfoSnapshot> lobbys = CloudNetDriver.getInstance().getCloudService("Lobby");

        int onlineplayers = Bukkit.getServer().getOnlinePlayers().size();
        int maxplayers = Bukkit.getServer().getMaxPlayers();

        for(ServiceInfoSnapshot servers : lobbys){
            if(ServiceInfoSnapshotUtil.isOnline(servers)){
                if(servers.getServiceId().getName().equalsIgnoreCase("Lobby-1")) {
                    list.add(new ItemBuilder(Material.GOLD_HELMET).setName(servers.getServiceId().getName()).
                            addLoreLine("§7Deine Lobby").addLoreLine("§7- §a" + onlineplayers + "§7/§a" + maxplayers + " §7-").build());
                } else {
                    list.add(new ItemBuilder(Material.CHAINMAIL_HELMET).setName(servers.getServiceId().getName()).build());
                }

            }
        }


        return list;
    }

}
