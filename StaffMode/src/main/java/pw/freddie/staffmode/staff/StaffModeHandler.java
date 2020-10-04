package pw.freddie.staffmode.staff;

import net.frozenorb.qlib.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import pw.freddie.staffmode.StaffMode;
import pw.freddie.staffmode.staff.events.StaffModeEnterEvent;
import pw.freddie.staffmode.staff.events.StaffModeLeaveEvent;
import pw.freddie.staffmode.utils.Chat;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author - Freddie (FreddieJLH#0001)
 * @date - Created on 30/09/2020
 */
public class StaffModeHandler {

    public ArrayList<Player> inStaff = new ArrayList<>();
    public HashMap<Player, ItemStack[]> inventoryContents = new HashMap<>();
    public HashMap<Player, ItemStack[]> armorContents = new HashMap<>();
    public static ItemStack wand = ItemBuilder.of(Material.WOOD_AXE).name(Chat.translate("&bWorldEdit Wand")).build();
    public static ItemStack compass = ItemBuilder.of(Material.COMPASS).name(Chat.translate("&3WorldEdit Compass")).build();
    public static ItemStack inspect = ItemBuilder.of(Material.BOOK).name(Chat.translate("&aInspect")).build();
    public static ItemStack bettersee = ItemBuilder.of(Material.CARPET).data((short) 1).name(Chat.translate("&eBetter Visibility")).build();
    public static ItemStack vanishoff = ItemBuilder.of(Material.INK_SACK).data((short) 8).name(Chat.translate("&aVanish &7(Off)")).build();
    public static ItemStack vanishon = ItemBuilder.of(Material.INK_SACK).data((short) 10).name(Chat.translate("&aVanish &7(On)")).build();
    public static ItemStack randomtp = ItemBuilder.of(Material.SKULL).name(Chat.translate("&eRandom TP")).build();

    public void addToStaff(Player player) {
        if (!inStaff.contains(player)) inStaff.remove(player);
        armorContents.put(player, player.getInventory().getArmorContents());
        inventoryContents.put(player, player.getInventory().getContents());
        player.getInventory().clear();
        Bukkit.getPluginManager().callEvent(new StaffModeEnterEvent(player));
        giveStaffItems(player);
        player.sendMessage(Chat.GREEN + "Staff mode enabled.");
    }

    public void removeFromStaff(Player player) {
        if (inStaff.contains(player)) inStaff.remove(player);
        player.getInventory().clear();
        player.getInventory().setContents(inventoryContents.get(player));
        player.getInventory().setArmorContents(armorContents.get(player));
        Bukkit.getPluginManager().callEvent(new StaffModeLeaveEvent(player));
        inventoryContents.remove(player);
        armorContents.remove(player);
        player.sendMessage(Chat.RED + "Staff mode disabled.");
    }

    public void toggleStaff(Player player) {
        if (inStaff.contains(player)) removeFromStaff(player);
        else addToStaff(player);
    }

    public void giveStaffItems(Player player) {
        PlayerInventory inventory = player.getInventory();
        inventory.setItem(0, compass);
        inventory.setItem(1, inspect);
        if (player.hasPermission("core.admin")) {
            inventory.setItem(2, wand);
            inventory.setItem(3, bettersee);
        } else inventory.setItem(2, bettersee);
        inventory.setItem(7, randomtp);
        if (StaffMode.getInstance().getVanishHandler().inVanish.contains(player)) inventory.setItem(8, vanishon);
        else inventory.setItem(8, vanishoff);
    }
}
