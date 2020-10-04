package pw.freddie.staffmode.staff.vanish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * @author - Freddie (FreddieJLH#0001)
 * @date - Created on 30/09/2020
 */
public class VanishHandler {

    public ArrayList<Player> inVanish = new ArrayList<>();

    public void addToVanish(Player player) {
        inVanish.add(player);
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (!inVanish.contains(all)) all.hidePlayer(player);
        }
    }

    public void removeFromVanish(Player player) {
        inVanish.remove(player);
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.showPlayer(player);
        }
    }

    public void toggleVanish(Player player) {
        if (inVanish.contains(player)) removeFromVanish(player);
        else addToVanish(player);
    }
}
