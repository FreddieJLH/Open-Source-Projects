package pw.freddie.staffmode.listeners;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pw.freddie.core.Core;

import java.util.ArrayList;

/**
 * @author - Freddie (FreddieJLH#0001)
 * @date - Created on 30/09/2020
 */
public class StaffModeListeners implements Listener {

    @Getter private static ArrayList<Player> onlinePlayers = new ArrayList<>();

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (Core.getInstance().getVanishHandler().inVanish.contains(event.getPlayer())) {
            if (!event.getPlayer().hasPermission("core.admin"))
                event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (Core.getInstance().getVanishHandler().inVanish.contains(event.getPlayer())) {
            if (!event.getPlayer().hasPermission("core.admin"))
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) { onlinePlayers.add(event.getPlayer()); }
    @EventHandler
    public void onLeave(PlayerQuitEvent event) { onlinePlayers.remove(event.getPlayer()); }
}
