package pw.freddie.staffmode.commands;

import net.frozenorb.qlib.command.Command;
import org.bukkit.entity.Player;
import pw.freddie.staffmode.StaffMode;

/**
 * @author - Freddie (FreddieJLH#0001)
 * @date - Created on 04/10/2020
 */
public class StaffModeCommand {

    @Command(names = {"staffmode", "mod", "staff", "h"}, permission = "core.staff")
    public static void staffMode(Player player) { StaffMode.getInstance().getStaffModeHandler().toggleStaff(player); }
}
