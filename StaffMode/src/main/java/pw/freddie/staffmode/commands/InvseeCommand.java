package pw.freddie.staffmode.commands;

import net.frozenorb.qlib.command.Command;
import net.frozenorb.qlib.command.Param;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pw.freddie.staffmode.utils.Chat;

/**
 * @author - Freddie (FreddieJLH#0001)
 * @date - Created on 04/10/2020
 */
public class InvseeCommand {

    @Command(names = {"invsee"}, permission = "core.staff")
    public static void invsee(Player player, @Param(name = "target")Player target) {
        Inventory inventory = Bukkit.createInventory(null, 45, target.getName() + "'s Inventory");
        inventory.setContents(target.getInventory().getContents());
        inventory.setItem(36, target.getInventory().getBoots());
        inventory.setItem(37, target.getInventory().getLeggings());
        inventory.setItem(38, target.getInventory().getChestplate());
        inventory.setItem(39, target.getInventory().getHelmet());
        player.openInventory(inventory);
        player.sendMessage(Chat.YELLOW + "Opening inventory of: " + Chat.AQUA + target.getName());
    }
}
