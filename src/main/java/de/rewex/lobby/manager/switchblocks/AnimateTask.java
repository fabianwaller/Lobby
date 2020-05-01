package de.rewex.lobby.manager.switchblocks;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;

public class AnimateTask implements Runnable {

    private static int color;
    private static int counter;

    @SuppressWarnings("deprecation")
    @Override
    public void run() {
        counter++;
        color++;
       // color = (int) (Math.random() * 15 + 1);

        if(counter > 7) {
            counter = 0;
        }

		if(color >= 15) {
			color = 1;
		}

        SwitchBlock.getSwitchblocks().forEach(switchBlock -> {
            if(switchBlock.getLocs().get(counter).getBlock().getData() == color) {
                color++;
            }
            switchBlock.getLocs().get(counter).getBlock().setData((byte) color);

            for(Player all : Bukkit.getOnlinePlayers()) {
                all.spigot().playEffect(switchBlock.getCenter(), Effect.SMOKE, 1, 0, 0, 0, 0, 5, 20, 20);
            }
        });


    }

}
