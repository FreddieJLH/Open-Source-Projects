package pw.freddie.staffmode.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import pw.freddie.staffmode.StaffMode;
import pw.freddie.staffmode.staff.StaffModeHandler;
import pw.freddie.staffmode.utils.Chat;

import java.util.Random;

/**
 * @author - Freddie (FreddieJLH#0001)
 * @date - Created on 30/09/2020
 */
public class StaffItemListeners implements Listener {

    @EventHandler
    public void onRandomTP(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() != null && event.getItem().isSimilar(StaffModeHandler.randomtp)) {
                if (StaffMode.getInstance().getStaffModeHandler().inStaff.contains(player)) {
                    int randomPlayer = new Random().nextInt(Bukkit.getOnlinePlayers().size());
                    Player pickedPlayer = StaffModeListeners.getOnlinePlayers().get(randomPlayer);

                    if (pickedPlayer == player) {
                        player.sendMessage(Chat.RED + "You were randomly picked!");
                        return;
                    }
                    player.teleport(pickedPlayer);
                    player.sendMessage(Chat.YELLOW + "You have been randomly teleported to " + pickedPlayer.getName());
                }
            }
        }
    }

    @EventHandler
    public void onVanish(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!StaffMode.getInstance().getStaffModeHandler().inStaff.contains(player)) return;
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() != null) {
                if (event.getItem().isSimilar(StaffModeHandler.vanishoff)) {
                    player.setItemInHand(StaffModeHandler.vanishon);
                    player.updateInventory();
                    if (StaffMode.getInstance().getVanishHandler().inVanish.contains(player)) return;
                    else StaffMode.getInstance().getVanishHandler().toggleVanish(player);
                    player.sendMessage(Chat.GREEN + "Enabled Vanish.");
                } else if (event.getItem().isSimilar(StaffModeHandler.vanishon)) {
                    player.setItemInHand(StaffModeHandler.vanishoff);
                    player.updateInventory();
                    if (!StaffMode.getInstance().getVanishHandler().inVanish.contains(player)) return;
                    else StaffMode.getInstance().getVanishHandler().toggleVanish(player);
                    player.sendMessage(Chat.GREEN + "Disabled Vanish.");
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (StaffMode.getInstance().getStaffModeHandler().inStaff.contains(player)) {
            if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.CHEST) {
                Chest chest = (Chest) event.getClickedBlock();
                Inventory inventory = Bukkit.createInventory(null, 63, Chat.translate("&7&lSilent &fChest"));
                inventory.setContents(chest.getBlockInventory().getContents());
                player.openInventory(inventory);
            }
        }
    }

    @EventHandler
    public void onInteractAtPlayer(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Player) {
            Player player = event.getPlayer();
            Player rightClicked = (Player) event.getRightClicked();

            if (player.getItemInHand() != null && player.getItemInHand().isSimilar(StaffModeHandler.inspect)) {
                if (StaffMode.getInstance().getStaffModeHandler().inStaff.contains(player)) {
                    Inventory inventory = Bukkit.createInventory(null, 45, rightClicked.getName() + "'s Inventory");
                    inventory.setContents(rightClicked.getInventory().getContents());
                    inventory.setItem(36, rightClicked.getInventory().getBoots());
                    inventory.setItem(37, rightClicked.getInventory().getLeggings());
                    inventory.setItem(38, rightClicked.getInventory().getChestplate());
                    inventory.setItem(39, rightClicked.getInventory().getHelmet());
                    player.openInventory(inventory);
                    player.sendMessage(Chat.YELLOW + "Opening inventory of: " + Chat.AQUA + rightClicked.getName());
                }
            }
        }
    }
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        for (Player all : Bukkit.getOnlinePlayers())
            if (event.getInventory().getTitle().equalsIgnoreCase(Chat.translate(all.getName() + "'s Inventory"))) event.setCancelled(true);
    }
}
