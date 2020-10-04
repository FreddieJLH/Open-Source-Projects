package pw.freddie.staffmode.commands;

import net.frozenorb.qlib.command.Command;
import org.bukkit.entity.Player;
import pw.freddie.staffmode.StaffMode;

/**
 * @author - Freddie (FreddieJLH#0001)
 * @date - Created on 04/10/2020
 */
public class VanishCommand {

    @Command(names = {"vanish", "v"}, permission = "core.staff")
    public static void vanish(Player player) { StaffMode.getInstance().getVanishHandler().toggleVanish(player); }
}
