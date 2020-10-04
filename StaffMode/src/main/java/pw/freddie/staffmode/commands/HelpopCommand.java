package pw.freddie.staffmode.commands;

import net.frozenorb.qlib.command.Command;
import net.frozenorb.qlib.command.Param;
import net.frozenorb.qlib.xpacket.FrozenXPacketHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.freddie.staffmode.StaffMode;
import pw.freddie.staffmode.utils.Chat;
import pw.freddie.staffmode.utils.packets.HelpopPacket;
import pw.freddie.staffmode.utils.packets.ReportPacket;

import java.util.ArrayList;

/**
 * @author - Freddie (FreddieJLH#0001)
 * @date - Created on 04/10/2020
 */
public class HelpopCommand {

    private static ArrayList<Player> onCooldown = new ArrayList<>();

    @Command(names = {"helpop", "request"}, permission = "")
    public static void helpop(Player player, @Param(name = "reason", wildcard = true)String reason) {
        if (onCooldown.contains(player)) {
            player.sendMessage(Chat.RED + "You are on helpop cooldown.");
        } else {
            FrozenXPacketHandler.sendToAll(new HelpopPacket(player, reason));
            player.sendMessage(Chat.GREEN + "Helpop submitted.");
            addToCooldown(player);
        }
    }

    private static void addToCooldown(Player player) {
        if (onCooldown.contains(player)) return;
        onCooldown.add(player);
        Bukkit.getScheduler().runTaskLaterAsynchronously(StaffMode.getInstance(), () -> onCooldown.remove(player), 20*120L);
    }
}
