package pw.freddie.staffmode.staff.vanish;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import pw.freddie.staffmode.StaffMode;

/**
 * @author - Freddie (FreddieJLH#0001)
 * @date - Created on 30/09/2020
 */
public class VanishListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (event.getPlayer().hasPermission("core.staff")) {
            if (StaffMode.getInstance().getVanishHandler().inVanish.contains(event.getPlayer())) {
                for (Player player : StaffMode.getInstance().getVanishHandler().inVanish) {
                    event.getPlayer().hidePlayer(player);
                }
            }
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            if (StaffMode.getInstance().getVanishHandler().inVanish.contains((Player) event.getEntity()))
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (StaffMode.getInstance().getVanishHandler().inVanish.contains((Player) event.getEntity()))
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (StaffMode.getInstance().getVanishHandler().inVanish.contains(event.getPlayer()))
            event.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (StaffMode.getInstance().getVanishHandler().inVanish.contains(event.getPlayer()))
            event.setCancelled(true);
    }
}
