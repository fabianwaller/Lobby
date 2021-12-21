package de.rewex.cloud;

import de.dytanic.cloudnet.driver.event.EventListener;
import de.dytanic.cloudnet.driver.event.events.service.CloudServiceStartEvent;
import de.dytanic.cloudnet.driver.event.events.service.CloudServiceStopEvent;
import de.dytanic.cloudnet.ext.bridge.ServiceInfoSnapshotUtil;
import de.dytanic.cloudnet.ext.bridge.bukkit.event.BukkitCloudServiceInfoUpdateEvent;
import de.rewex.lobby.Lobby;
import org.bukkit.event.Listener;

public class CloudServiceListeners implements Listener {

    private final Lobby plugin;

    public CloudServiceListeners(Lobby main) {
        this.plugin = main;
    }

    @EventListener
    public void onStop(CloudServiceStartEvent e){
        String servicename = e.getServiceInfo().getServiceId().getName().split("-")[0];
        if(e.getServiceInfo().getServiceId().getName().split("-")[0].equals("Lobby")){
            while (ServiceInfoSnapshotUtil.isOnline(e.getServiceInfo())){
                Lobby.getInstance().getInventoryHandler().updateLobbyInventory();
            }
        }

    }
    @EventListener
    public void onStop(CloudServiceStopEvent e){
        String servicename = e.getServiceInfo().getServiceId().getName().split("-")[0];
        if(e.getServiceInfo().getServiceId().getName().split("-")[0].equals("Lobby")){
            while (!ServiceInfoSnapshotUtil.isOnline(e.getServiceInfo())){
                Lobby.getInstance().getInventoryHandler().updateLobbyInventory();
            }
        }
    }

    @EventListener
    public void onUpdate(BukkitCloudServiceInfoUpdateEvent e){
        if(e.getServiceInfoSnapshot().getServiceId().getName().split("-")[0].equals("Lobby")){
            Lobby.getInstance().getInventoryHandler().updateLobbyInventory();
        }
    }


}
