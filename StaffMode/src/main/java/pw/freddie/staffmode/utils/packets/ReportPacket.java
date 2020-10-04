package pw.freddie.staffmode.utils.packets;

import lombok.Getter;
import net.frozenorb.qlib.xpacket.XPacket;
import org.bukkit.entity.Player;
import pw.freddie.staffmode.StaffMode;
import pw.freddie.staffmode.utils.Chat;
import pw.freddie.staffmode.utils.ServerUtils;

/**
 * @author - Freddie (FreddieJLH#0001)
 * @date - Created on 04/10/2020
 */
public class ReportPacket implements XPacket {

    @Getter private Player reportedBy, reported;
    @Getter private String reason;

    public ReportPacket() {}
    public ReportPacket(Player reportedBy, Player reported, String reason) {
        this.reportedBy = reportedBy;
        this.reported = reported;
        this.reason = reason;
    }

    @Override
    public void onReceive() {
        Chat.sendToPermission("&9[Report] &7[" + ServerUtils.getServerName() + "] &b" + reported.getName() + " &7was reported by &b" + reportedBy.getName(), "core.staff");
        Chat.sendToPermission("     &9Reason: &7" + reason, "core.staff");
    }
}
