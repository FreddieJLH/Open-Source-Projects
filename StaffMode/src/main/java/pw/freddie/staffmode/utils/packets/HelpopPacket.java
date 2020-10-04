package pw.freddie.staffmode.utils.packets;

import lombok.Getter;
import net.frozenorb.qlib.xpacket.XPacket;
import org.bukkit.entity.Player;
import pw.freddie.staffmode.utils.Chat;
import pw.freddie.staffmode.utils.ServerUtils;

/**
 * @author - Freddie (FreddieJLH#0001)
 * @date - Created on 04/10/2020
 */
public class HelpopPacket implements XPacket {

    @Getter private Player reportedBy;
    @Getter private String reason;

    public HelpopPacket() {}
    public HelpopPacket(Player reportedBy, String reason) {
        this.reportedBy = reportedBy;
        this.reason = reason;
    }

    @Override
    public void onReceive() {
        Chat.sendToPermission("&9[Request] &7[" + ServerUtils.getServerName() + "] &b" + reportedBy.getName() + " &7has requested assistance", "core.staff");
        Chat.sendToPermission("     &9Reason: &7" + reason, "core.staff");
    }
}
