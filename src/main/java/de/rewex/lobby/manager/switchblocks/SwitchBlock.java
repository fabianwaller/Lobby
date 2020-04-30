package de.rewex.lobby.manager.switchblocks;

import com.google.common.collect.Lists;
import de.rewex.lobby.Main;
import de.rewex.lobby.manager.LocationManager;
import org.bukkit.Location;

import java.util.List;

public class SwitchBlock {

    private static final List<SwitchBlock> switchBlocks = Lists.newArrayList();

    private final Location center;
    private final List<Location> locs;

    public SwitchBlock(Location center) {
        this.center = center;
        this.locs = this.loadLocations();
    }

    private List<Location> loadLocations() {
        List<Location> locs = Lists.newArrayList();

        locs.add(this.center.clone().add(-1, 0, -1));
        locs.add(this.center.clone().add(-1, 0, 0));
        locs.add(this.center.clone().add(-1, 0, 1));
        locs.add(this.center.clone().add(0, 0, 1));
        locs.add(this.center.clone().add(1, 0, 1));
        locs.add(this.center.clone().add(1, 0, 0));
        locs.add(this.center.clone().add(1, 0, -1));
        locs.add(this.center.clone().add(0, 0, -1));

        return locs;
    }

    public List<Location> getLocs() {
        return locs;
    }

    public Location getCenter() {
        return center;
    }

    public static List<SwitchBlock> getSwitchblocks() {
        return switchBlocks;
    }

    public static void startAnimateTask() {
        LocationManager.getSwitchBlocks().forEach(switchloc -> {
            SwitchBlock switchBlock = new SwitchBlock(switchloc.subtract(0, 1, 0));
            SwitchBlock.getSwitchblocks().add(switchBlock);
        });

        Main.getInstance().getServer().getScheduler().runTaskTimer(Main.getInstance(), new AnimateTask(), 0L, 15L);
    }


}
