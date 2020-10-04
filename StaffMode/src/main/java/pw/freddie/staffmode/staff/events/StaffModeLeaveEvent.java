package pw.freddie.staffmode.staff.events;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author - Freddie (FreddieJLH#0001)
 * @date - Created on 23/08/2020
 */
public class StaffModeLeaveEvent extends Event {

    @Getter private static final HandlerList handlerList = new HandlerList();
    private Player player;

    public StaffModeLeaveEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
