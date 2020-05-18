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
        //counter++;
        //color++;

        /*if(counter > 7) {
            counter = 0;
            color++;
        }

		if(color >= 15) {
			color = 0;
		}*/

        SwitchBlock.getSwitchblocks().forEach(switchBlock -> {
            for(int i=0; i<7; i++) {
                color = (int) (Math.random() * 15 + 1);
                switchBlock.getLocs().get(i).getBlock().setData((byte) color);
            }

            for(Player all : Bukkit.getOnlinePlayers()) {
                //all.spigot().playEffect(switchBlock.getCenter(), Effect.TILE_DUST, 1, 0, 0, 0, 0, 1, 100, 20);
            }
        });


        /*SwitchBlock.getSwitchblocks().forEach(switchBlock -> {
            if(switchBlock.getLocs().get(counter).getBlock().getData() == color) {
                color++;
            }
            switchBlock.getLocs().get(counter).getBlock().setData((byte) color);

            for(Player all : Bukkit.getOnlinePlayers()) {
                all.spigot().playEffect(switchBlock.getCenter(), Effect.COLOURED_DUST, 1, 0, 0, 0, 0, 1, 50, 20);
            }
        });*/


    }

}
