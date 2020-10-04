package pw.freddie.staffmode.utils;

import pw.freddie.staffmode.StaffMode;

/**
 * @author - Freddie (FreddieJLH#0001)
 * @date - Created on 04/10/2020
 */
public class ServerUtils {

    public static String getServerName() {
        return StaffMode.getInstance().getConfig().getString("server.name");
    }
}
